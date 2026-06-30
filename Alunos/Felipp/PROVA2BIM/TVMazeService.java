package Fag.services;

import Fag.EstadoSerie;
import Fag.FonteDeSeries;
import Fag.Serie;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// servico responsavel por buscar series na api tvmaze.
// concentra os conteudos de requisicoes http, regex, json e jackson.
public class TVMazeService implements FonteDeSeries {

    private static final String URL_BUSCA = "https://api.tvmaze.com/search/shows?q=";

    private final HttpClient cliente;
    private final ObjectMapper mapper;

    // regex que aceita apenas trechos "saudaveis" no termo de busca:
    // letras (com acento), numeros, espacos e alguns sinais comuns.
    private static final Pattern TERMO_VALIDO =
            Pattern.compile("[\\p{L}\\p{N} :'._-]+");

    public TVMazeService() {
        this.cliente = HttpClient.newHttpClient();
        this.mapper = new ObjectMapper();
    }

    @Override
    public List<Serie> buscarPorNome(String termo) throws ServiceException {
        if (termo == null || termo.isBlank()) {
            throw new ServiceException("digite um nome para a busca.");
        }

        // limpa a entrada do usuario com a regex antes de montar a url.
        String termoLimpo = limparTermo(termo);

        try {
            String url = URL_BUSCA + URLEncoder.encode(termoLimpo, StandardCharsets.UTF_8);

            HttpRequest requisicao = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> resposta =
                    cliente.send(requisicao, HttpResponse.BodyHandlers.ofString());

            if (resposta.statusCode() != 200) {
                throw new ServiceException(
                        "a api respondeu com o código " + resposta.statusCode() + ".");
            }

            return converter(resposta.body());

        } catch (ServiceException e) {
            throw e; // repassa a mensagem ja tratada
        } catch (Exception e) {
            // qualquer falha de rede, interrupcao ou json invalido cai aqui
            // e vira uma excecao tratada, sem derrubar o programa.
            throw new ServiceException("não foi possível buscar as séries. verifique a conexão.", e);
        }
    }

    // mantem apenas os trechos do termo que casam com o padrao permitido.
    private String limparTermo(String termo) {
        StringBuilder sb = new StringBuilder();
        Matcher matcher = TERMO_VALIDO.matcher(termo);
        while (matcher.find()) {
            sb.append(matcher.group());
        }
        String limpo = sb.toString().trim();
        return limpo.isBlank() ? termo.trim() : limpo;
    }

    // percorre a arvore json devolvida pela tvmaze e cria objetos serie.
    private List<Serie> converter(String json) throws Exception {
        List<Serie> series = new ArrayList<>();
        JsonNode raiz = mapper.readTree(json);

        // a busca devolve um array de objetos no formato { score, show }.
        for (JsonNode item : raiz) {
            JsonNode show = item.get("show");
            if (show != null) {
                series.add(montarSerie(show));
            }
        }
        return series;
    }

    private Serie montarSerie(JsonNode show) {
        Serie serie = new Serie();

        serie.setId(show.path("id").asInt());
        serie.setNome(textoOuPadrao(show.path("name"), "sem nome"));
        serie.setIdioma(textoOuPadrao(show.path("language"), "não informado"));

        // generos vem como um array de textos.
        List<String> generos = new ArrayList<>();
        for (JsonNode g : show.path("genres")) {
            generos.add(g.asText());
        }
        serie.setGeneros(generos);

        // a nota fica em rating.average e pode ser nula.
        JsonNode nota = show.path("rating").path("average");
        serie.setNota((nota.isNull() || nota.isMissingNode()) ? null : nota.asDouble());

        serie.setEstado(EstadoSerie.apartirDaApi(textoOuNulo(show.path("status"))));

        serie.setDataEstreia(textoOuNulo(show.path("premiered")));
        serie.setDataTermino(textoOuNulo(show.path("ended")));

        // a emissora pode estar em network (tv) ou em webChannel (streaming).
        String emissora = textoOuNulo(show.path("network").path("name"));
        if (emissora == null) {
            emissora = textoOuNulo(show.path("webChannel").path("name"));
        }
        serie.setEmissora(emissora == null ? "não informado" : emissora);

        return serie;
    }

    private String textoOuPadrao(JsonNode no, String padrao) {
        return (no.isNull() || no.isMissingNode()) ? padrao : no.asText();
    }

    private String textoOuNulo(JsonNode no) {
        return (no.isNull() || no.isMissingNode()) ? null : no.asText();
    }
}
