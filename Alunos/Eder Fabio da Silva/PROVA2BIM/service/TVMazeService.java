package com.tvtracker.service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tvtracker.model.Show;

/**
 * Responsável por toda comunicação com a API pública do TVMaze. Utiliza o
 * HttpClient nativo do Java 11+ e Gson para parsing JSON.
 */
public class TVMazeService {

    private static final String BASE_URL = "https://api.tvmaze.com";

    private final HttpClient httpClient;

    public TVMazeService() {
        this.httpClient = HttpClient.newBuilder() // Configurações do HttpClient
                .connectTimeout(Duration.ofSeconds(10)) // Timeout para conexões
                .followRedirects(HttpClient.Redirect.NORMAL) // Segue redirecionamentos HTTP
                .build(); // Cria o HttpClient com as configurações definidas
    }

    /**
     * Busca séries pelo nome usando GET /search/shows?q={query}.
     *
     * @param query Termo de busca informado pelo usuário.
     * @return Lista de {@link Show} com os resultados encontrados.
     * @throws IOException Se ocorrer erro de I/O ou resposta não-200.
     * @throws InterruptedException Se a requisição for interrompida.
     */
    // ── Método principal para buscar séries por nome
    public List<Show> searchShows(String query) throws IOException, InterruptedException {
        String encoded = URLEncoder.encode(query.trim(), StandardCharsets.UTF_8);
        String url = BASE_URL + "/search/shows?q=" + encoded;
        // Construção da requisição HTTP GET para a API do TVMaze
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url)) //define o endereço (URI) da requisição HTTP
                .timeout(Duration.ofSeconds(15)) // Timeout para a requisição   
                .GET() //define o método HTTP da requisição.
                .build(); //finaliza a construção do objeto 

        // Envio da requisição e obtenção da resposta
        HttpResponse<String> response
                = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("API retornou status HTTP " + response.statusCode());
        }

        return parseSearchResults(response.body());
    }

    // ── Parsing de JSON para objetos Show 
    // O método parseSearchResults converte a resposta JSON da API em uma lista de objetos Show.
    private List<Show> parseSearchResults(String json) {
        List<Show> shows = new ArrayList<>();
        JsonArray results = JsonParser.parseString(json).getAsJsonArray();

        for (JsonElement element : results) {
            try {
                JsonObject showObj = element.getAsJsonObject().getAsJsonObject("show");
                shows.add(parseShow(showObj));
            } catch (Exception ignored) {
                // Pula entradas malformadas sem abortar a lista inteira
            }
        }

        return shows;
    }

    // O método parseShow extrai os detalhes de cada série do JSON e cria um objeto Show.
    private Show parseShow(JsonObject obj) {
        int id = getInt(obj, "id");
        String name = getString(obj, "name");
        String language = getString(obj, "language");
        String status = getString(obj, "status");
        String premiered = getString(obj, "premiered");
        String ended = getString(obj, "ended");
        // Gêneros podem ser uma lista, então iteramos sobre o array JSON para extrair 
        // cada gênero
        List<String> genres = new ArrayList<>();
        if (obj.has("genres") && !obj.get("genres").isJsonNull()) {
            for (JsonElement g : obj.getAsJsonArray("genres")) {
                genres.add(g.getAsString());
            }
        }

        // Rating pode ser nulo, então verificamos antes de extrair o valor
        double rating = 0.0;
        if (obj.has("rating") && !obj.get("rating").isJsonNull()) {
            JsonObject rObj = obj.getAsJsonObject("rating");
            if (rObj.has("average") && !rObj.get("average").isJsonNull()) {
                rating = rObj.get("average").getAsDouble();
            }
        }

        String networkName = extractNetworkName(obj);

        return new Show(id, name, language, genres, status, premiered, ended, rating, networkName);
    }

    // O método extractNetworkName lida com a lógica de extrair o nome da rede, considerando 
    // tanto "network" quanto "webChannel".   
    private String extractNetworkName(JsonObject obj) {
        if (obj.has("network") && !obj.get("network").isJsonNull()) {
            return getString(obj.getAsJsonObject("network"), "name");
        }
        if (obj.has("webChannel") && !obj.get("webChannel").isJsonNull()) {
            String ch = getString(obj.getAsJsonObject("webChannel"), "name");
            return ch != null ? ch + " (Web)" : null;
        }
        return null;
    }

    // ── Helpers de JSON 
    // O método getString é um helper para extrair valores de string do JSON, 
    // lidando com casos onde a chave pode estar ausente ou nula.
    private String getString(JsonObject obj, String key) {
        if (obj != null && obj.has(key) && !obj.get(key).isJsonNull()) {
            return obj.get(key).getAsString();
        }
        return null;
    }

    // O método getInt é um helper para extrair valores inteiros do JSON, 
    // lidando com casos onde a chave pode estar ausente ou nula.    
    private int getInt(JsonObject obj, String key) {
        if (obj != null && obj.has(key) && !obj.get(key).isJsonNull()) {
            return obj.get(key).getAsInt();
        }
        return 0;
    }
}
