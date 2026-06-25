import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.json.JSONArray;
import org.json.JSONObject;

// Utiliza a biblioteca org.json para leitura da resposta da API professor.

public class ServicoClima {

        private static final String API_KEY = "H9HAXHTA432ZBBGATF9KRF4BK";

        public WeatherData buscarClima(String cidade) throws ApiException {

                try {

                        String cidadeFormatada = URLEncoder.encode(cidade, StandardCharsets.UTF_8);

                        String url = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                                        + cidadeFormatada
                                        + "?unitGroup=metric"
                                        + "&include=current,days"
                                        + "&elements=temp,tempmax,tempmin,humidity,conditions,precip,windspeed,winddir"
                                        + "&key=" + API_KEY
                                        + "&contentType=json";

                        HttpClient client = HttpClient.newHttpClient();

                        HttpRequest request = HttpRequest.newBuilder()
                                        .uri(URI.create(url))
                                        .GET()
                                        .build();

                        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                        if (response.statusCode() == 401) {
                                throw new ApiException("API Key inválida.");
                        }

                        if (response.statusCode() == 429) {
                                throw new ApiException("Limite de consultas excedido.");
                        }

                        if (response.statusCode() != 200) {
                                throw new ApiException(
                                                "Erro HTTP: " + response.statusCode());
                        }

                        JSONObject json = new JSONObject(response.body());

                        JSONObject currentConditions = json.getJSONObject("currentConditions");

                        JSONArray days = json.getJSONArray("days");

                        JSONObject hoje = days.getJSONObject(0);

                        double temperaturaAtual = currentConditions.getDouble("temp");

                        double temperaturaMaxima = hoje.getDouble("tempmax");

                        double temperaturaMinima = hoje.getDouble("tempmin");

                        double umidade = currentConditions.getDouble("humidity");

                        String condicao = currentConditions.getString("conditions");

                        double precipitacao = currentConditions.optDouble("precip", 0);

                        double velocidadeVento = currentConditions.getDouble("windspeed");

                        double direcaoVento = currentConditions.getDouble("winddir");

                        return new WeatherData(
                                        temperaturaAtual,
                                        temperaturaMaxima,
                                        temperaturaMinima,
                                        umidade,
                                        condicao,
                                        precipitacao,
                                        velocidadeVento,
                                        direcaoVento);

                } catch (IOException e) {

                        throw new ApiException(
                                        "Erro de comunicação com a API.",
                                        e);

                } catch (InterruptedException e) {

                        Thread.currentThread().interrupt();

                        throw new ApiException(
                                        "Consulta interrompida.",
                                        e);
                }
        }
}