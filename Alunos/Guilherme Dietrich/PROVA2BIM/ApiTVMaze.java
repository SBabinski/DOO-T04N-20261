package objetos;

import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ApiTVMaze {

    private static final String BASE_URL = "https://api.tvmaze.com";
    private final Gson gson = new Gson();
    private final HttpClient clienteHttp = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(5))
            .build();

    public List<Serie> buscarSeries(String termoBusca) throws Exception {
        String termoFormatado = termoBusca.replace(" ", "+");
        String urlCompleta = BASE_URL + "/search/shows?q=" + termoFormatado;

        String respostaJson = enviarRequisicao(urlCompleta);
        ResultadoBusca[] resultadosBusca = gson.fromJson(respostaJson, ResultadoBusca[].class);

        List<Serie> seriesEncontradas = new ArrayList<>();
        for (ResultadoBusca resultado : resultadosBusca) {
            if (resultado.getShow() != null) {
                seriesEncontradas.add(resultado.getShow());
            }
        }
        return seriesEncontradas;
    }

    private String enviarRequisicao(String urlCompleta) throws Exception {
        HttpRequest requisicao = HttpRequest.newBuilder()
                .uri(URI.create(urlCompleta))
                .timeout(Duration.ofSeconds(5))
                .GET()
                .build();

        HttpResponse<String> resposta = clienteHttp.send(
                requisicao,
                HttpResponse.BodyHandlers.ofString()
        );

        if (resposta.statusCode() != 200) {
            throw new Exception("Erro na API. Codigo: " + resposta.statusCode());
        }

        return resposta.body();
    }
}
