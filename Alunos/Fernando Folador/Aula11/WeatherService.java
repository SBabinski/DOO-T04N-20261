import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherService {

    private static final String API_KEY = "N6W5V47QQ84TAZGKNPJJLR2Y4";

    public void buscarClima(String cidade) {

        try {

            String endereco =
                    "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                            + cidade
                            + "?unitGroup=metric&key="
                            + API_KEY
                            + "&contentType=json";

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(endereco))
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();

            System.out.println("Cidade: " + cidade);

            String temperatura = json.split("\"temp\":")[1].split(",")[0];
            String tempMax = json.split("\"tempmax\":")[1].split(",")[0];
            String tempMin = json.split("\"tempmin\":")[1].split(",")[0];
            String umidade = json.split("\"humidity\":")[1].split(",")[0];
            String condicao = json.split("\"conditions\":\"")[1].split("\"")[0];
            String precipitacao = json.split("\"precip\":")[1].split(",")[0];
            String vento = json.split("\"windspeed\":")[1].split(",")[0];
            String direcao = json.split("\"winddir\":")[1].split(",")[0];

            System.out.println("Temperatura Atual: " + temperatura + "°C");
            System.out.println("Temperatura Máxima: " + tempMax + "°C");
            System.out.println("Temperatura Mínima: " + tempMin + "°C");
            System.out.println("Umidade: " + umidade + "%");
            System.out.println("Condição do Tempo: " + condicao);
            System.out.println("Precipitação: " + precipitacao);
            System.out.println("Velocidade do Vento: " + vento + " km/h");
            System.out.println("Direção do Vento: " + direcao);

        } catch (IOException | InterruptedException e) {

            System.out.println("Erro ao buscar clima.");
            e.printStackTrace();
        }
    }
}