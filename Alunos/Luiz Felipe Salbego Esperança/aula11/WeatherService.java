import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class WeatherService {

    public WeatherResponse buscarClima(String cidade)
            throws IOException, InterruptedException {

        String url =
                "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                        + cidade
                        + "?unitGroup=metric&include=current&key="
                        + ApiConfig.API_KEY
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
                        HttpResponse.BodyHandlers.ofString()
                );

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(
                response.body(),
                WeatherResponse.class
        );
    }
}