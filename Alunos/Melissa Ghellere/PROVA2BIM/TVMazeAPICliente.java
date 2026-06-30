import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

// busca as series la no site do tvmaze 
public class TVMazeAPICliente {
    private static final String BASE_URL = "https://api.tvmaze.com/search/shows?q=";

    public List<Serie> buscarSeriePorNome(String nomeBusca) {
        List<Serie> seriesEncontradas = new ArrayList<>();
        
        try {
            // arruma o texto pro link nao quebrar (🩹)
            String query = nomeBusca.replace(" ", "%20");
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + query))
                    .GET() 
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            // se deu bom (200), a gente corta o texto json 
            if (response.statusCode() == 200) {
                String jsonBody = response.body();
                String[] blocosDeShows = jsonBody.split("\"show\":\\{");
                
                for (int i = 1; i < blocosDeShows.length; i++) {
                    String bloco = blocosDeShows[i];
                    Serie s = new Serie();
                    
                    // preenche os dados da serie 
                    s.setName(extrairTexto(bloco, "\"name\":\""));
                    s.setLanguage(extrairTexto(bloco, "\"language\":\""));
                    s.setStatus(extrairTexto(bloco, "\"status\":\""));
                    s.setPremiered(extrairTexto(bloco, "\"premiered\":\""));
                    s.setEnded(extrairTexto(bloco, "\"ended\":\""));
                    
                    // cuida pra nao bugar se nao tiver emissora
                    String network = extrairTexto(bloco, "\"network\":{\"id\":");
                    if (!network.equals("N/A")) {
                        s.setNetworkName(extrairTexto(network + bloco.substring(bloco.indexOf("\"network\":{\"id\":") + 10), "\"name\":\""));
                    } else {
                        s.setNetworkName("N/A");
                    }
                    
                    s.setImageUrl(extrairTexto(bloco, "\"medium\":\""));
                    s.setScore(extrairNota(bloco));
                    s.setGenres(extrairGeneros(bloco));
                    
                    seriesEncontradas.add(s);
                }
            }
        } catch (Exception e) {
            // avisa se a internet der ruim (╥﹏╥)
            System.err.println("ops, erro de rede: " + e.getMessage());
        }
        
        return seriesEncontradas;
    }

    // recorta texto do json sujo 
    private String extrairTexto(String json, String chave) {
        int inicio = json.indexOf(chave);
        if (inicio != -1) {
            inicio += chave.length();
            int fim = json.indexOf("\"", inicio);
            if (fim != -1) return json.substring(inicio, fim);
        }
        return "N/A";
    }

    // puxa a nota e converte pra numero 
    private double extrairNota(String json) {
        String chave = "\"rating\":{\"average\":";
        int inicio = json.indexOf(chave);
        if (inicio != -1) {
            inicio += chave.length();
            int fim = json.indexOf("}", inicio);
            String valor = json.substring(inicio, fim).replace("null", "0.0");
            try { return Double.parseDouble(valor); } catch (Exception e) { return 0.0; }
        }
        return 0.0;
    }

    // limpa os generos (ação, drama, etc) 
    private List<String> extrairGeneros(String json) {
        List<String> generos = new ArrayList<>();
        String chave = "\"genres\":[";
        int inicio = json.indexOf(chave);
        if (inicio != -1) {
            inicio += chave.length();
            int fim = json.indexOf("]", inicio);
            String arrayGeneros = json.substring(inicio, fim);
            
            if (!arrayGeneros.isEmpty()) {
                for (String item : arrayGeneros.split(",")) {
                    generos.add(item.replace("\"", "").trim());
                }
            }
        }
        return generos;
    }
}