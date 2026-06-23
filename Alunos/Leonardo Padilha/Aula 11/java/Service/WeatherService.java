package service;

import exception.WeatherException;
import model.WeatherData;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.ConnectException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDate;

public class WeatherService {

    private static final String API_KEY = "R2LCLMY8TULUT2GV8CPSN7FJP";

    public WeatherData buscarClima(String cidade) throws Exception {

        if (cidade == null || cidade.trim().isEmpty()) {
            throw new WeatherException("Informe uma cidade.");
        }

        try {

            String dataHoje = LocalDate.now().toString();

            String url =
                    "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                            + URLEncoder.encode(cidade, StandardCharsets.UTF_8)
                            + "/"
                            + dataHoje
                            + "?key="
                            + API_KEY
                            + "&unitGroup=metric&lang=pt";

            HttpClient client = HttpClient.newBuilder()
                    .connectTimeout(Duration.ofSeconds(10))
                    .build();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(Duration.ofSeconds(15))
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            switch (response.statusCode()) {

                case 200:
                    break;

                case 400:
                    throw new WeatherException(
                            "Parâmetros inválidos enviados para a API.");

                case 401:
                    throw new WeatherException(
                            "API Key inválida.");

                case 404:
                    throw new WeatherException(
                            "Cidade não encontrada.");

                case 429:
                    throw new WeatherException(
                            "Limite de consultas excedido.");

                case 500:
                case 502:
                case 503:
                    throw new WeatherException(
                            "Serviço meteorológico indisponível.");

                default:
                    throw new WeatherException(
                            "Erro HTTP: " + response.statusCode());
            }

            String body = response.body();

            if (body == null || body.isBlank()) {
                throw new WeatherException(
                        "A API retornou uma resposta vazia.");
            }

            JSONObject json;

            try {
                json = new JSONObject(body);
            } catch (Exception e) {
                throw new WeatherException(
                        "Resposta inválida recebida da API.");
            }

            if (!json.has("currentConditions")) {
                throw new WeatherException(
                        "Dados climáticos não encontrados.");
            }

            JSONObject current =
                    json.getJSONObject("currentConditions");

            JSONArray days =
                    json.getJSONArray("days");

            JSONObject day =
                    days.getJSONObject(0);

            WeatherData data =
                    new WeatherData();

            data.setTemperaturaAtual(
                    current.optDouble("temp", 0));

            data.setTemperaturaMaxima(
                    day.optDouble("tempmax", 0));

            data.setTemperaturaMinima(
                    day.optDouble("tempmin", 0));

            data.setUmidade(
                    current.optDouble("humidity", 0));

            data.setCondicao(
                    current.optString("conditions", "Não informado"));

            data.setPrecipitacao(
                    day.optDouble("precip", 0));

            data.setVelocidadeVento(
                    current.optDouble("windspeed", 0));

            data.setDirecaoVento(
                    current.optDouble("winddir", 0));

            return data;

        } catch (ConnectException e) {

            throw new WeatherException(
                    "Sem conexão com a internet.");
        }
    }
}