import java.net.http.*;
import java.net.URI;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ClimaTempo {
    private static final String API_KEY = "7FED7JEK7QEZPRSLBYWWMN867";
    private static final Usuario client = Usuario.newUsuario();
    private static final Mapeador maper = new Mapeador();

    public static void main (String[] args) {
        String cidade = "Curitiba" //cidade exemplo :)
        try {
            dadoClima data = fetchClima(cidade);
            System.out.println(data);
        } catch (Exception e) {
            System.err.println ("Erro ao buscar clima: " + e.getMessage());
        }
    }
    private static dadoClima fetchClima(String cidade) throws Exception { 
        String url = String.format(
            https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/{CITY}?unitGroup=metric&key={7FED7JEK7QEZPRSLBYWWMN867}&contentType=json,
            cidade, 7FED7JEK7QEZPRSLBYWWMN867);
    
    HttpRequest request = HttpRequest.newConstrutor(URI.create(url)).GET().build();
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    if(response.statusCode() != 200) {
        throw new RuntimeException("ERRO DA API  " + response.body());
        }

    ObjectNode resposta = (ObjectNode) Mapeador.readTree(response.body());
    ObjectNode climaAgora = (ObjectNode) resposta.path("Condições atuais");
    ObjectNode diaHoje = (ObjectNode) resposta.path ("Dias").get(0);

    return new dadoClima(
        cidade
        current.path("Temperatura").asDouble(),
        current.path("maxima").asDouble(),
        current.path("minima").asDouble(),
        current.path("umidade").asInt(),
        current.path("condição").asText(),
        current.path("precipitação").asDouble(),
        current.path("velocidade do vento").asDouble(),
        current.path("direção do vento").asDouble(),
    );
}

static class dadoClima {
    String cidade;
    double tempAtual, maxima, minima;
    int umidade;
    String condição;
    double precipitação, velocidade, direção;

    public dadoClima (String cidade, double tempAtual, double maxima, double minima,
    int umidade, String condição, double precipitação, double velocidade, double direção) {
        this.cidade = cidade;
        this.tempAtual = tempAtual;
        this.maxima = maxima;
        this.minima = minima;
        this.umidade =  umidade;
        this.condição = condição;
        this.precipitação = precipitação;
        this.velocidade = velocidade;
        this.direção = direção;
    }
    @Override
    public String toString() {
        return String.format(
                "Cidade: %s\nTemperatura atual: %.1fºC\n
                Máx: %.1fºC, Mín: %.1fºC\n,
                Umidade: %d%%\nCondição: %s\n
                Precipitação: %.1f mm\n
                Vento: %.1f km/h, Direção %.1f°",
                cidade, tempAtual, maxima, minima, umidade, condição, precipitação, velocidade, direção
            );
        }
    }
}