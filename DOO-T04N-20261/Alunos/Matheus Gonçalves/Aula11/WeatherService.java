package fag;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WeatherService {

    public WeatherData buscarClima(String cidade) throws Exception {

        String endpoint = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/" + cidade + "?unitGroup=metric&key=" + ApiConfig.API_KEY + "&contentType=json";

        URL url = new URL(endpoint);

        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

        conexao.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader( new InputStreamReader(conexao.getInputStream()));

        StringBuilder resposta = new StringBuilder();
        String linha;

        while ((linha = reader.readLine()) != null) {
            resposta.append(linha);
        }

        reader.close();

        ObjectMapper mapper = new ObjectMapper();

        JsonNode root = mapper.readTree(resposta.toString());

        JsonNode currentConditions =
                root.get("currentConditions");

        JsonNode today =
                root.get("days").get(0);

        WeatherData clima = new WeatherData();

        clima.setTemperatura(currentConditions.get("temp").asDouble());

        clima.setUmidade(currentConditions.get("humidity").asDouble());

        clima.setCondicao(currentConditions.get("conditions").asText());

        clima.setVelocidadeVento(currentConditions.get("windspeed").asDouble());

        clima.setDirecaoVento(currentConditions.get("winddir").asDouble());

        clima.setPrecipitacao(currentConditions.get("precip").asDouble());

        clima.setTemperaturaMaxima(today.get("tempmax").asDouble());

        clima.setTemperaturaMinima(today.get("tempmin").asDouble());

        return clima;
    }
}
