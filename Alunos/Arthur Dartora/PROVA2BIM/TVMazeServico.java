import java.net.*;
import java.net.http.*;
import java.time.*;
import java.util.*;
import java.util.regex.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

// chamada da API e implementa a interface ServicoDeSeries
public class TVMazeServico implements ServicoDeSeries {

    private static final String API = "https://api.tvmaze.com/search/shows?q=";

    // ferramenta de requisicao http
    private final HttpClient cliente = HttpClient.newHttpClient();

    // jackson para ler o JSON
    private final ObjectMapper mapper = new ObjectMapper();

    // regex que encontra tag HTML
    private static final Pattern TAGS_HTML = Pattern.compile("<[^>]+>");

    @Override
    public List<Serie> buscar(String nome) throws Exception {
        List<Serie> series = new ArrayList<Serie>();

        if (nome == null || nome.trim().isEmpty()) {
            return series;
        }

        String endereco = API + URLEncoder.encode(nome.trim(), "UTF-8");

        // monta e envia a requisicao 
        HttpRequest requisicao = HttpRequest.newBuilder()
                .uri(URI.create(endereco))
                .timeout(Duration.ofSeconds(10))
                .GET()
                .build();

        HttpResponse<String> resposta =
                cliente.send(requisicao, HttpResponse.BodyHandlers.ofString());

        if (resposta.statusCode() != 200) {
            throw new Exception("Api respondeu corretamente " + resposta.statusCode());
        }

        // jackson le o JSON e transforma numa arvore de leitura
        JsonNode raiz = mapper.readTree(resposta.body());

        //  esposta e uma lista, e cada item tem um campo
        for (JsonNode item : raiz) {
            JsonNode show = item.get("show");
            if (show != null) {
                series.add(criarSerie(show));
            }
        }

        return series;
    }

    //jackson le o "show" do JSON e monta um objeto serie
    private Serie criarSerie(JsonNode show) {
        int id = show.path("id").asInt(0);
        String nome = texto(show, "name");
        String idioma = texto(show, "language");
        String estado = texto(show, "status");
        String dataEstreia = texto(show, "premiered");
        String dataTermino = texto(show, "ended");

        // generos vem como uma lista dentro do JSON
        List<String> generos = new ArrayList<String>();
        JsonNode arrayGeneros = show.get("genres");
        if (arrayGeneros != null && arrayGeneros.isArray()) {
            for (JsonNode g : arrayGeneros) {
                generos.add(g.asText());
            }
        }

        //a nota fica dentro de rating.average
        Double nota = null;
        JsonNode rating = show.get("rating");
        if (rating != null && rating.get("average") != null && !rating.get("average").isNull()) {
            nota = rating.get("average").asDouble();
        }

        String emissora = null;
        JsonNode network = show.get("network");
        if (network != null && network.get("name") != null) {
            emissora = network.get("name").asText();
        }
        if (emissora == null) {
            JsonNode web = show.get("webChannel");
            if (web != null && web.get("name") != null) {
                emissora = web.get("name").asText();
            }
        }

        //o resumo vem com HTML que limpa com regex
        String resumo = limparHtml(texto(show, "summary"));

        return new Serie(id, nome, idioma, generos, nota, estado,
                dataEstreia, dataTermino, emissora, resumo);
    }

    //pega um campo de texto do JSON
    private String texto(JsonNode no, String campo) {
        JsonNode valor = no.get(campo);
        if (valor == null || valor.isNull()) {
            return null;
        }
        return valor.asText();
    }

    //regex para apagar as tags HTML
    private String limparHtml(String texto) {
        if (texto == null) {
            return null;
        }
        return TAGS_HTML.matcher(texto).replaceAll("").trim();
    }
}
