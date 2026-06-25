package climatempo.services;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class ClimaService {
    static final ObjectMapper mapper = new ObjectMapper();
    private static final String BASE_URL =
            "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";

    private static final String API_KEY = "RVKBDT865HZYF3CNQ7YG9AZ6K";

    public static RespostaClima buscarClima(String cidade) {
        try {
            String cidadeCodificada = URLEncoder.encode(cidade, StandardCharsets.UTF_8);

            String url = BASE_URL + cidadeCodificada
                    + "?unitGroup=metric"
                    + "&include=current,days"
                    + "&contentType=json"
                    + "&key=" + API_KEY;

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.out.println("Erro na requisicao. Status: " + response.statusCode());
                System.out.println("Resposta: " + response.body());
                return null;
            }

            String body = response.body();
            RespostaClima clima = mapper.readValue(body, RespostaClima.class);

            return clima;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
