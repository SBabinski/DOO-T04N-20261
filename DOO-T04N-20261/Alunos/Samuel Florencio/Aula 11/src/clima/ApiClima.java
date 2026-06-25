package clima;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ApiClima {

    private static final String CHAVE_API = "XP5Y6AA94MB4NZKW5V9KTA597";
    

    public InfoClima buscarClima(String cidade)
            throws ClimaException {

        try {

            String cidadeCodificada =
                    URLEncoder.encode(cidade, StandardCharsets.UTF_8);

            String url =
                    "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                    + cidadeCodificada
                    + "/today?unitGroup=metric&include=current&key="
                    + CHAVE_API
                    + "&contentType=json";

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request =
                    HttpRequest.newBuilder()
                            .uri(URI.create(url))
                            .GET()
                            .build();

            HttpResponse<String> response =
                    client.send(
                            request,
                            HttpResponse.BodyHandlers.ofString());   
            if (response.statusCode() != 200) {
                throw new ClimaException(
                        "Erro ao consultar a API. Código: "
                                + response.statusCode());
            }

            JsonObject root =
                    JsonParser.parseString(response.body())
                            .getAsJsonObject();

            JsonObject climaAtual =
                    root.getAsJsonObject("currentConditions");

            JsonArray dias =
                    root.getAsJsonArray("dias");

            JsonObject hoje =
                    dias.get(0).getAsJsonObject();

            InfoClima dadosclima = new InfoClima();

            dadosclima.setTempAtual(
            		climaAtual.get("temp").getAsDouble());

            dadosclima.setUmidade(
            		climaAtual.get("humidity").getAsDouble());

            dadosclima.setCondicao(
            		climaAtual.get("conditions").getAsString());

            if (climaAtual.get("precip").isJsonNull()) {
            	dadosclima.setPrecipitacao(0);
            } else {
            	dadosclima.setPrecipitacao(
                		climaAtual.get("precip").getAsDouble());
            }

            dadosclima.setVelocidadeVento(
            		climaAtual.get("windspeed").getAsDouble());

            dadosclima.setDirecaoVento(
                    String.valueOf(
                    		climaAtual.get("winddir").getAsDouble()));

            dadosclima.setTempMaxima(
                    hoje.get("tempmax").getAsDouble());

            dadosclima.setTempMinima(
                    hoje.get("tempmin").getAsDouble());

            return dadosclima;

        } catch (IOException | InterruptedException e) {

            throw new ClimaException(
                    "Falha na comunicação com a API.",
                    e);

        } catch (Exception e) {

            throw new ClimaException(
                    "Erro ao processar os dados.",
                    e);
        }
    }
}