package Objetos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherService {

    private static final String API_KEY = "DSCFFNBFBYUMAXNP5VAVEXM4Q";

    public WeatherData buscarClima(String cidade) throws Exception {

        String endereco =
                "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                        + cidade
                        + "?unitGroup=metric"
                        + "&key=" + API_KEY
                        + "&contentType=json";

        URL url = new URL(endereco);

        HttpURLConnection conexao =
                (HttpURLConnection) url.openConnection();

        conexao.setRequestMethod("GET");

        int codigoResposta = conexao.getResponseCode();

        if (codigoResposta == 401) {
            throw new Exception("Erro 401 - API KEY inválida.");
        }

        if (codigoResposta != 200) {
            throw new Exception("Erro HTTP: " + codigoResposta);
        }

        BufferedReader leitor =
                new BufferedReader(
                        new InputStreamReader(conexao.getInputStream()));

        String linha;
        StringBuilder resposta = new StringBuilder();

        while ((linha = leitor.readLine()) != null) {
            resposta.append(linha);
        }

        leitor.close();

        String json = resposta.toString();

        return new WeatherData(
                extrair(json, "\"temp\":", ","),
                extrair(json, "\"tempmax\":", ","),
                extrair(json, "\"tempmin\":", ","),
                extrair(json, "\"humidity\":", ","),
                extrairTexto(json, "\"conditions\":\"", "\""),
                extrair(json, "\"precip\":", ","),
                extrair(json, "\"windspeed\":", ","),
                extrair(json, "\"winddir\":", ",")
        );
    }

    private String extrair(String texto,
                           String inicio,
                           String fim) {

        int posInicio = texto.indexOf(inicio);

        if (posInicio == -1) {
            return "N/A";
        }

        posInicio += inicio.length();

        int posFim = texto.indexOf(fim, posInicio);

        return texto.substring(posInicio, posFim);
    }

    private String extrairTexto(String texto,
                                String inicio,
                                String fim) {

        int posInicio = texto.indexOf(inicio);

        if (posInicio == -1) {
            return "N/A";
        }

        posInicio += inicio.length();

        int posFim = texto.indexOf(fim, posInicio);

        return texto.substring(posInicio, posFim);
    }
}