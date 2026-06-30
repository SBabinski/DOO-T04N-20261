package controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Serie;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TvMazeController {
    private final HttpClient clienteHttp;
    private final ObjectMapper objectMapper;

    public TvMazeController() {
        this.clienteHttp = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public List<Serie> consultarSeriesPorNome(String nomeSerieBusca) {
        List<Serie> listaSeriesEncontradas = new ArrayList<>();
        try {
            String urlFormatada = "https://api.tvmaze.com/search/shows?q=" + URLEncoder.encode(nomeSerieBusca, StandardCharsets.UTF_8);
            HttpRequest requisicaoApi = HttpRequest.newBuilder()
                    .uri(URI.create(urlFormatada))
                    .GET()
                    .build();

            HttpResponse<String> respostaServidor = clienteHttp.send(requisicaoApi, HttpResponse.BodyHandlers.ofString());

            if (respostaServidor.statusCode() == 200) {
                JsonNode nodoRaiz = objectMapper.readTree(respostaServidor.body());
                if (nodoRaiz.isArray()) {
                    for (JsonNode itemNodo : nodoRaiz) {
                        JsonNode nodoDetalheShow = itemNodo.path("show");
                        if (!nodoDetalheShow.isMissingNode()) {
                            int id = nodoDetalheShow.path("id").asInt();
                            String nome = nodoDetalheShow.path("name").asText("Sem nome");
                            String status = nodoDetalheShow.path("status").asText("Desconhecido");
                            String dataEstreia = nodoDetalheShow.path("premiered").asText("N/A");
                            String dataFim = nodoDetalheShow.path("ended").asText("N/A");
                            double nota = nodoDetalheShow.path("rating").path("average").asDouble(0.0);

                            String emissora = nodoDetalheShow.path("network").path("name").asText(null);
                            if (emissora == null) {
                                emissora = nodoDetalheShow.path("webChannel").path("name").asText("Desconhecida");
                            }

                            List<String> listaGeneros = new ArrayList<>();
                            JsonNode nodoGeneros = nodoDetalheShow.path("genres");
                            if (nodoGeneros.isArray()) {
                                for (JsonNode g : nodoGeneros) {
                                    listaGeneros.add(g.asText());
                                }
                            }
                            String generosFormatados = String.join(", ", listaGeneros);

                            String sumario = nodoDetalheShow.path("summary").asText("");
                            if (sumario != null) {
                                sumario = sumario.replaceAll("<[^>]*>", "");
                            }

                            String idioma = nodoDetalheShow.path("language").asText("Desconhecido");

                            listaSeriesEncontradas.add(new Serie(id, nome, status, dataEstreia, dataFim, nota, emissora, generosFormatados, sumario, idioma));
                        }
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return listaSeriesEncontradas;
    }
}