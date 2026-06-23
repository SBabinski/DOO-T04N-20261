package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import config.ApiConfig;
import model.Clima;
import model.Day;
import model.WeatherResponse;

public class ClimaService {

    public Clima buscarClima(String cidade) {

        Clima clima = new Clima();

        try {

            if (!cidade.matches("[a-zA-ZÀ-ÿ\\s]+")) {

                clima.setCidadeEncontrada(false);

                return clima;
            }

            cidade = cidade.replace(" ", "%20");

            String endereco =
                    "https://weather.visualcrossing.com/"
                    + "VisualCrossingWebServices/"
                    + "rest/services/timeline/"
                    + cidade
                    + "?unitGroup=metric"
                    + "&lang=pt"
                    + "&include=current,days"
                    + "&key=" + ApiConfig.API_KEY
                    + "&contentType=json";

            URL url = new URL(endereco);

            HttpURLConnection conexao =
                    (HttpURLConnection)
                            url.openConnection();

            conexao.setRequestMethod("GET");

            int codigoResposta =
                    conexao.getResponseCode();

            if (codigoResposta != 200) {

                clima.setCidadeEncontrada(false);

                return clima;
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

            ObjectMapper mapper =
                    new ObjectMapper();

            mapper.configure(
                    DeserializationFeature
                            .FAIL_ON_UNKNOWN_PROPERTIES,
                    false);

            WeatherResponse weather =
                    mapper.readValue(
                            resposta.toString(),
                            WeatherResponse.class);

            if (weather.getDays() != null
                    && !weather.getDays().isEmpty()) {

                Day hoje =
                        weather.getDays().get(0);

                clima.setTemperaturaAtual(
                        weather.getCurrentConditions()
                                .getTemp());

                clima.setTemperaturaMaxima(
                        hoje.getTempmax());

                clima.setTemperaturaMinima(
                        hoje.getTempmin());

                clima.setUmidade(
                        weather.getCurrentConditions()
                                .getHumidity());

                clima.setCondicaoTempo(
                        weather.getCurrentConditions()
                                .getConditions());

                clima.setPrecipitacao(
                        weather.getCurrentConditions()
                                .getPrecip());

                clima.setVelocidadeVento(
                        weather.getCurrentConditions()
                                .getWindspeed());

                clima.setDirecaoVento(
                        weather.getCurrentConditions()
                                .getWinddir());
            }

        } catch (Exception e) {

            clima.setCidadeEncontrada(false);
        }

        return clima;
    }
}