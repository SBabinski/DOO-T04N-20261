package PROVA2BIM;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ArrayList;

public class TvMazeServico {
    private final HttpClient client = HttpClient.newHttpClient();

    public List<Serie> buscarSeries(String nome) {
        List<Serie> resultados = new ArrayList<>();
        try {
            String query = URLEncoder.encode(nome, StandardCharsets.UTF_8);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.tvmaze.com/search/shows?q=" + query))
                    .GET().build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonArray array = JsonParser.parseString(response.body()).getAsJsonArray();

            for (JsonElement elemento : array) {
                JsonObject show = elemento.getAsJsonObject().get("show").getAsJsonObject();
                resultados.add(mapearParaSerie(show));
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar séries: " + e.getMessage());
        }
        return resultados;
    }

    private Serie mapearParaSerie(JsonObject show) {
        String nome = show.has("name") ? show.get("name").getAsString() : "N/A";
        String idioma = jsonString(show, "language");
        String estado = jsonString(show, "status");
        String estreia = jsonString(show, "premiered");
        String fim = jsonString(show, "ended");

        double nota = 0.0;
        if (show.has("rating") && !show.get("rating").isJsonNull()) {
            JsonObject rating = show.getAsJsonObject("rating");
            nota = rating.has("average") && !rating.get("average").isJsonNull()
                    ? rating.get("average").getAsDouble() : 0.0;
        }

        String emissora = "N/A";
        if (show.has("network") && !show.get("network").isJsonNull()) {
            emissora = jsonString(show.getAsJsonObject("network"), "name");
        }

        List<String> generos = new ArrayList<>();
        if (show.has("genres")) {
            show.getAsJsonArray("genres").forEach(g -> generos.add(g.getAsString()));
        }

        return new Serie(nome, idioma, generos, nota, estado, estreia, fim, emissora);
    }

    private String jsonString(JsonObject obj, String chave) {
        return obj.has(chave) && !obj.get(chave).isJsonNull()
                ? obj.get(chave).getAsString() : "N/A";
    }
}
