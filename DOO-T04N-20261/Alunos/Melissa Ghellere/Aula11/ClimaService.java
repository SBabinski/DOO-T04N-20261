import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClimaService {
    
   
    private static final String API_KEY = "M4N5SDUHLBBYP2GZKNL5WX9R7";
    private static final String BASE_URL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";

    public ClimaInfo buscarClima(String cidade) {
        try {
            
            String cidadeFormatada = cidade.replace(" ", "%20");
            
           
            String url = BASE_URL + cidadeFormatada + "?unitGroup=metric&key=" + API_KEY + "&contentType=json";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                System.out.println("Erro ao buscar dados. Verifique o nome da cidade ou sua API Key.");
                return null;
            }

            String json = response.body();

           
            String tempAtual = extrairValor(json, "\"currentConditions\"", "\"temp\"");
            String condicao = extrairValor(json, "\"currentConditions\"", "\"conditions\"");
            String umidade = extrairValor(json, "\"currentConditions\"", "\"humidity\"");
            String precipitacao = extrairValor(json, "\"currentConditions\"", "\"precip\"");
            String velVento = extrairValor(json, "\"currentConditions\"", "\"windspeed\"");
            String dirVento = extrairValor(json, "\"currentConditions\"", "\"winddir\"");
            
            String tempMax = extrairValor(json, "\"days\"", "\"tempmax\"");
            String tempMin = extrairValor(json, "\"days\"", "\"tempmin\"");

            return new ClimaInfo(tempAtual, tempMax, tempMin, umidade, condicao, precipitacao, velVento, dirVento);

        } catch (IOException | InterruptedException e) {
            System.out.println("Ocorreu um erro na requisição: " + e.getMessage());
            return null;
        }
    }

    
    private String extrairValor(String jsonCompleto, String bloco, String chave) {
        int indiceBloco = jsonCompleto.indexOf(bloco);
        if (indiceBloco == -1) return "0.0";
        
        String subJson = jsonCompleto.substring(indiceBloco);
        String regex = chave + "\\s*:\\s*(\"[^\"]+\"|[\\d\\.-]+)";
        Matcher matcher = Pattern.compile(regex).matcher(subJson);
        
        if (matcher.find()) {
         
            return matcher.group(1).replace("\"", "");
        }
        return "N/D";
    }
}