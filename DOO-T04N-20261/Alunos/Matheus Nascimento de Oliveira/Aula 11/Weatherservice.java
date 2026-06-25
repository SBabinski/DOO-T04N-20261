import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
 
public class WeatherService {
 
    private static final String URL_BASE =
            "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
 
    private String apiKey;
 
    public WeatherService(String apiKey) {
        this.apiKey = apiKey;
    }
 
    public WeatherData buscarClima(String cidade) throws WeatherApiException {
        String urlStr = montarUrl(cidade);
        String json = fazerRequisicao(urlStr);
        return parsearJson(json, cidade);
    }
 
    private String montarUrl(String cidade) throws WeatherApiException {
        try {
            String cidadeCodificada = URLEncoder.encode(cidade, StandardCharsets.UTF_8.name());
            return URL_BASE + cidadeCodificada + "/today"
                    + "?unitGroup=metric"
                    + "&include=current,days"
                    + "&key=" + apiKey
                    + "&contentType=json";
        } catch (Exception e) {
            throw new WeatherApiException("Erro ao montar a URL: " + e.getMessage(), e);
        }
    }
 
    private String fazerRequisicao(String urlStr) throws WeatherApiException {
        HttpURLConnection conexao = null;
        try {
            URL url = new URL(urlStr);
            conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");
            conexao.setConnectTimeout(10000);
            conexao.setReadTimeout(10000);
 
            int status = conexao.getResponseCode();
 
            if (status == 401) {
                throw new WeatherApiException("Chave de API invalida.", status);
            }
            if (status == 429) {
                throw new WeatherApiException("Limite de requisicoes atingido.", status);
            }
            if (status != 200) {
                throw new WeatherApiException("Erro na requisicao. Codigo HTTP: " + status, status);
            }
 
            StringBuilder sb = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conexao.getInputStream(), StandardCharsets.UTF_8))) {
                String linha;
                while ((linha = reader.readLine()) != null) {
                    sb.append(linha);
                }
            }
            return sb.toString();
 
        } catch (IOException e) {
            throw new WeatherApiException("Falha de conexao com a API: " + e.getMessage(), e);
        } finally {
            if (conexao != null) conexao.disconnect();
        }
    }
 
    private WeatherData parsearJson(String json, String cidade) throws WeatherApiException {
        if (json == null || json.isBlank()) {
            throw new WeatherApiException("Resposta da API veio vazia.");
        }
 
        try {
            String enderecoResolvido = JsonParser.extrairString(json, "resolvedAddress");
 
            // dados do momento atual
            String atual = JsonParser.extrairObjeto(json, "currentConditions");
            double tempAtual    = JsonParser.extrairDouble(atual, "temp");
            double umidade      = JsonParser.extrairDouble(atual, "humidity");
            String condicao     = JsonParser.extrairString(atual, "conditions");
            double precipitacao = JsonParser.extrairDouble(atual, "precip");
            double velVento     = JsonParser.extrairDouble(atual, "windspeed");
            double dirVento     = JsonParser.extrairDouble(atual, "winddir");
 
            // maxima e minima do dia
            String dia    = JsonParser.extrairPrimeiroDoArray(json, "days");
            double tempMax = JsonParser.extrairDouble(dia, "tempmax");
            double tempMin = JsonParser.extrairDouble(dia, "tempmin");
 
            return new WeatherData(
                    cidade,
                    tempAtual,
                    tempMax,
                    tempMin,
                    umidade,
                    condicao.isEmpty() ? "Nao informado" : condicao,
                    precipitacao,
                    velVento,
                    grausParaDirecao(dirVento),
                    enderecoResolvido
            );
 
        } catch (Exception e) {
            throw new WeatherApiException("Erro ao processar o JSON: " + e.getMessage(), e);
        }
    }
 
    private String grausParaDirecao(double graus) {
        String[] pontos = {"N", "NE", "L", "SE", "S", "SO", "O", "NO"};
        int indice = (int) Math.round(graus / 45.0) % 8;
        return pontos[indice];
    }
}
