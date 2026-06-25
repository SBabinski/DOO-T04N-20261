import java.util.List;
import java.util.ArrayList;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TVMazeService {

    private final ObjectMapper mapper = new ObjectMapper();

    public String buscarTexto(String nome) throws Exception {
        // esse trecho vai mudar o input do usuario de 'breaking bad' para
        // 'breaking%20bad' ja que a url da requisicao nao aceita espaco
        // %20 = ' ' em UTF8
        String nomeCodificado = URLEncoder.encode(nome, StandardCharsets.UTF_8);
        // o q= significa query=, ou seja, a query que vai ser buscada no banco de dados
        // das series
        String url = "https://api.tvmaze.com/search/shows?q=" + nomeCodificado;

        HttpClient cliente = HttpClient.newHttpClient();
        HttpRequest requisicao = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> resposta = cliente.send(requisicao, HttpResponse.BodyHandlers.ofString());
        return resposta.body();
    }

    public List<Serie> buscarSeries(String nome) throws Exception {
        String json = buscarTexto(nome);
        List<Serie> series = new ArrayList<>();

        JsonNode raiz = mapper.readTree(json);
        for (JsonNode item : raiz) {
            JsonNode show = item.get("show");
            series.add(montarSerie(show));
        }
        return series;
    }

    private Serie montarSerie(JsonNode show) {
        int id = show.path("id").asInt();
        String nome = textoOuNull(show.path("name"));
        String idioma = textoOuNull(show.path("language"));
        String status = textoOuNull(show.path("status"));
        String dataEstreia = textoOuNull(show.path("premiered"));
        String dataTermino = textoOuNull(show.path("ended"));

        List<String> generos = new ArrayList<>();
        for (JsonNode g : show.path("genres")) {
            generos.add(g.asText());
        }

        double nota = show.path("rating").path("average").asDouble(0.0);

        String emissora = "Desconhecida";
        JsonNode network = show.path("network");
        if (!network.isMissingNode() && !network.isNull()) {
            emissora = network.path("name").asText();
        } else {
            JsonNode webChannel = show.path("webChannel");
            if (!webChannel.isMissingNode() && !webChannel.isNull()) {
                emissora = webChannel.path("name").asText();
            }
        }

        return new Serie(id, nome, idioma, generos, nota, status, dataEstreia, dataTermino, emissora);
    }

    private String textoOuNull(JsonNode no) {
        if (no.isMissingNode() || no.isNull()) {
            return null;
        }
        return no.asText();
    }
}