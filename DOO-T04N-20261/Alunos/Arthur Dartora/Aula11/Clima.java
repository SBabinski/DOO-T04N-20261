import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;
import java.util.Map;

/**
 * Busca o clima de uma cidade na Timeline Weather API da Visual Crossing
 * (free tier).
 *
 * <p>Documentação:
 * <a href="https://www.visualcrossing.com/resources/documentation/weather-api/timeline-weather-api/">
 * Timeline Weather API</a>.
 */
public class Clima {

    private static final String URL_BASE =
            "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";

    private final String chave;
    private final HttpClient http;

    /**
     * @param chave chave da API (criada em https://www.visualcrossing.com/sign-up)
     */
    public Clima(String chave) {
        if (chave == null || chave.isBlank()) {
            throw new IllegalArgumentException("A chave da API não pode ser vazia.");
        }
        this.chave = chave;
        this.http = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(15))
                .build();
    }

    /**
     * Busca o clima atual de uma cidade.
     *
     * @param cidade nome da cidade (ex.: "Cascavel, PR")
     * @return dados do clima
     * @throws ErroClima se houver falha de rede, autenticação ou na resposta
     */
    public Tempo buscar(String cidade) throws ErroClima {
        if (cidade == null || cidade.isBlank()) {
            throw new IllegalArgumentException("A cidade não pode ser vazia.");
        }
        String json = baixar(montarUrl(cidade));
        return ler(json);
    }

    private URI montarUrl(String cidade) {
        String local = URLEncoder.encode(cidade.trim(), StandardCharsets.UTF_8);
        String url = URL_BASE + local
                + "?key=" + URLEncoder.encode(chave, StandardCharsets.UTF_8)
                + "&unitGroup=metric"   // Celsius, km/h, mm
                + "&lang=pt"            // condições em português
                + "&include=current,days"
                + "&contentType=json";
        return URI.create(url);
    }

    private String baixar(URI uri) throws ErroClima {
        HttpRequest req = HttpRequest.newBuilder()
                .uri(uri)
                .timeout(Duration.ofSeconds(30))
                .header("Accept", "application/json")
                .GET()
                .build();
        try {
            HttpResponse<String> resp = http.send(req, HttpResponse.BodyHandlers.ofString());
            if (resp.statusCode() == 200) {
                return resp.body();
            }
            throw new ErroClima(mensagemErro(resp.statusCode(), resp.body()));
        } catch (java.io.IOException e) {
            throw new ErroClima("Falha de comunicação com a API.", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ErroClima("A consulta foi interrompida.", e);
        }
    }

    private String mensagemErro(int status, String corpo) {
        return switch (status) {
            case 400 -> "Requisição inválida (400). Verifique o nome da cidade.";
            case 401 -> "Não autorizado (401). Verifique sua chave de API.";
            case 404 -> "Cidade não encontrada (404).";
            case 429 -> "Limite de requisições excedido (429). Tente mais tarde.";
            default -> "Erro da API (HTTP " + status + ").";
        };
    }

    @SuppressWarnings("unchecked")
    private Tempo ler(String json) throws ErroClima {
        try {
            Map<String, Object> raiz = (Map<String, Object>) Json.parse(json);

            Map<String, Object> agora = (Map<String, Object>) raiz.get("currentConditions");
            List<Object> dias = (List<Object>) raiz.get("days");
            if (agora == null || dias == null || dias.isEmpty()) {
                throw new ErroClima("Resposta da API em formato inesperado.");
            }
            Map<String, Object> hoje = (Map<String, Object>) dias.get(0);

            String cidade = texto(raiz.get("resolvedAddress"), texto(raiz.get("address"), "Desconhecido"));

            return new Tempo(
                    cidade,
                    num(agora.get("temp")),
                    num(hoje.get("tempmax")),
                    num(hoje.get("tempmin")),
                    num(agora.get("humidity")),
                    texto(agora.get("conditions"), "Indisponível"),
                    num(agora.get("precip")),
                    num(agora.get("windspeed")),
                    num(agora.get("winddir")));
        } catch (ErroClima e) {
            throw e;
        } catch (RuntimeException e) {
            throw new ErroClima("Não foi possível interpretar a resposta.", e);
        }
    }

    private static double num(Object v) {
        return (v instanceof Number n) ? n.doubleValue() : 0.0;
    }

    private static String texto(Object v, String padrao) {
        return (v instanceof String s && !s.isBlank()) ? s : padrao;
    }
}
