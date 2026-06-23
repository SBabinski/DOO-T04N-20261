package climatempo;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ServicoClima {

    private static final String API_KEY =
            "6LMSX6B5FF89DYU4BHWYC6ALD";

    public DadosClima buscarClima(String cidade) throws Exception {

        String endereco =
                "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                        + cidade
                        + "?unitGroup=metric&key="
                        + API_KEY
                        + "&contentType=json";

        URL url = new URL(endereco);

        HttpURLConnection conexao =
                (HttpURLConnection) url.openConnection();

        conexao.setRequestMethod("GET");

        if (conexao.getResponseCode() != 200) {
            throw new Exception(
                    "Não foi possível localizar a cidade informada.");
        }

        BufferedReader leitor =
                new BufferedReader(
                        new InputStreamReader(
                                conexao.getInputStream()));

        StringBuilder resposta =
                new StringBuilder();

        String linha;

        while ((linha = leitor.readLine()) != null) {
            resposta.append(linha);
        }

        leitor.close();
        conexao.disconnect();

        JsonObject json =
                JsonParser.parseString(
                        resposta.toString())
                        .getAsJsonObject();

        JsonObject atual =
                json.getAsJsonObject(
                        "currentConditions");

        JsonObject dia =
                json.getAsJsonArray("days")
                        .get(0)
                        .getAsJsonObject();

        double precipitacao = 0.0;

        if (atual.has("precip")
                && !atual.get("precip").isJsonNull()) {

            precipitacao =
                    atual.get("precip")
                            .getAsDouble();
        }

        return new DadosClima(
                atual.get("temp").getAsDouble(),
                dia.get("tempmax").getAsDouble(),
                dia.get("tempmin").getAsDouble(),
                atual.get("humidity").getAsDouble(),
                atual.get("conditions").getAsString(),
                precipitacao,
                atual.get("windspeed").getAsDouble(),
                atual.get("winddir").getAsDouble()
        );
    }
}