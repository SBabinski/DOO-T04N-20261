import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

// classe pra puxar os dados da api
public class TVMazeAPICliente {
    private static final String BASE_URL = "https://api.tvmaze.com/search/shows?q=";

    public List<Serie> buscarSeriePorNome(String nomeBusca) {
        List<Serie> seriesEncontradas = new ArrayList<>();
        
        try {
            // troca os espacos que o usuario digitou por %20 pro link nao quebrar
            String query = nomeBusca.replace(" ", "%20");
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + query))
                    .GET() // verbo http pra consultar
                    .build();

            // manda o pedido pra api
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            // se o site responder 200 (tudo ok) le o json
            if (response.statusCode() == 200) {
                String jsonBody = response.body();
                
                // corta a string gigante dividindo cada serie num pedaco de array
                String[] blocosDeShows = jsonBody.split("\"show\":\\{");
                
                // comeca do 1 porque o 0 é lixo do json
                for (int i = 1; i < blocosDeShows.length; i++) {
                    String bloco = blocosDeShows[i];
                    Serie s = new Serie();
                    
                    // pega os dados puxando com os metodos manuais que criei la em baixo
                    s.setName(extrairTexto(bloco, "\"name\":\""));
                    s.setLanguage(extrairTexto(bloco, "\"language\":\""));
                    s.setStatus(extrairTexto(bloco, "\"status\":\""));
                    s.setPremiered(extrairTexto(bloco, "\"premiered\":\""));
                    s.setEnded(extrairTexto(bloco, "\"ended\":\""));
                    
                    // gambiarra pra pegar o nome da emissora sem quebrar o codigo
                    String network = extrairTexto(bloco, "\"network\":{\"id\":");
                    if (!network.equals("N/A")) {
                        s.setNetworkName(extrairTexto(network + bloco.substring(bloco.indexOf("\"network\":{\"id\":") + 10), "\"name\":\""));
                    } else {
                        s.setNetworkName("N/A");
                    }
                    
                    s.setImageUrl(extrairTexto(bloco, "\"medium\":\""));
                    s.setScore(extrairNota(bloco));
                    s.setGenres(extrairGeneros(bloco));
                    
                    // adiciona a serie pronta na lista
                    seriesEncontradas.add(s);
                }
            }
        } catch (Exception e) {
            // tratamento de erro de rede
            System.err.println("erro na requisicao: " + e.getMessage());
        }
        
        return seriesEncontradas;
    }

    // funcao q procura onde a palavra comeca, onde terminam as aspas e recorta o valor
    private String extrairTexto(String json, String chave) {
        int inicio = json.indexOf(chave);
        if (inicio != -1) {
            inicio += chave.length();
            int fim = json.indexOf("\"", inicio);
            if (fim != -1) return json.substring(inicio, fim);
        }
        return "N/A";
    }

    // extrai numero e ja tenta converter pra double
    private double extrairNota(String json) {
        String chave = "\"rating\":{\"average\":";
        int inicio = json.indexOf(chave);
        if (inicio != -1) {
            inicio += chave.length();
            int fim = json.indexOf("}", inicio);
            // tira o null se nao tiver nota
            String valor = json.substring(inicio, fim).replace("null", "0.0");
            try { return Double.parseDouble(valor); } catch (Exception e) { return 0.0; }
        }
        return 0.0;
    }

    // pega a lista de generos q vem entre chaves no json
    private List<String> extrairGeneros(String json) {
        List<String> generos = new ArrayList<>();
        String chave = "\"genres\":[";
        int inicio = json.indexOf(chave);
        if (inicio != -1) {
            inicio += chave.length();
            int fim = json.indexOf("]", inicio);
            String arrayGeneros = json.substring(inicio, fim);
            
            // se tiver algo dentro, separa pela virgula e limpa as aspas
            if (!arrayGeneros.isEmpty()) {
                for (String item : arrayGeneros.split(",")) {
                    generos.add(item.replace("\"", "").trim());
                }
            }
        }
        return generos;
    }
}