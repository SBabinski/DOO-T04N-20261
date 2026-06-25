package weather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WeatherApiClient {

    private final String apiKey;

    public WeatherApiClient(String apiKey) {
        this.apiKey = apiKey;
    }

    public WeatherInfo buscarClimaAtual(String cidade) throws Exception {
        String url = montarUrl(cidade);

        String json = fazerRequisicao(url);

        return converterJsonParaWeatherInfo(json);
    }

    private String montarUrl(String cidade) throws Exception {
        String cidadeFormatada = encode(cidade);
        String chaveFormatada = encode(apiKey);

        return "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                + cidadeFormatada
                + "/today"
                + "?unitGroup=metric"
                + "&lang=pt"
                + "&include=current,days"
                + "&elements=datetime,temp,tempmax,tempmin,humidity,conditions,precip,windspeed,winddir"
                + "&contentType=json"
                + "&key=" + chaveFormatada;
    }

    private String fazerRequisicao(String enderecoUrl) throws Exception {
        URL url = new URL(enderecoUrl);
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

        conexao.setRequestMethod("GET");
        conexao.setConnectTimeout(10000);
        conexao.setReadTimeout(15000);
        conexao.setRequestProperty("Accept", "application/json");

        int codigo = conexao.getResponseCode();

        BufferedReader leitor;

        if (codigo >= 200 && codigo < 300) {
            leitor = new BufferedReader(
                    new InputStreamReader(conexao.getInputStream(), StandardCharsets.UTF_8)
            );
        } else {
            leitor = new BufferedReader(
                    new InputStreamReader(conexao.getErrorStream(), StandardCharsets.UTF_8)
            );
        }

        StringBuilder resposta = new StringBuilder();
        String linha;

        while ((linha = leitor.readLine()) != null) {
            resposta.append(linha);
        }

        leitor.close();

        if (codigo != 200) {
            throw new RuntimeException("Erro HTTP " + codigo + ": " + resposta);
        }

        return resposta.toString();
    }

    private WeatherInfo converterJsonParaWeatherInfo(String json) {
        String currentConditions = JsonUtils.extrairObjeto(json, "currentConditions");
        String hoje = JsonUtils.extrairPrimeiroObjetoDoArray(json, "days");

        if (currentConditions == null || hoje == null) {
            throw new RuntimeException("Não foi possível ler os dados retornados pela API.");
        }

        WeatherInfo info = new WeatherInfo();

        info.setLocal(JsonUtils.extrairTexto(json, "resolvedAddress"));
        info.setData(JsonUtils.extrairTexto(hoje, "datetime"));
        info.setHoraConsulta(JsonUtils.extrairTexto(currentConditions, "datetime"));

        info.setTemperaturaAtual(JsonUtils.extrairNumero(currentConditions, "temp"));
        info.setTemperaturaMaxima(JsonUtils.extrairNumero(hoje, "tempmax"));
        info.setTemperaturaMinima(JsonUtils.extrairNumero(hoje, "tempmin"));
        info.setUmidade(JsonUtils.extrairNumero(currentConditions, "humidity"));
        info.setCondicaoTempo(JsonUtils.extrairTexto(currentConditions, "conditions"));

        Double precipitacaoAtual = JsonUtils.extrairNumero(currentConditions, "precip");

        if (precipitacaoAtual == null) {
            precipitacaoAtual = JsonUtils.extrairNumero(hoje, "precip");
        }

        info.setPrecipitacao(precipitacaoAtual);
        info.setVelocidadeVento(JsonUtils.extrairNumero(currentConditions, "windspeed"));
        info.setDirecaoVentoGraus(JsonUtils.extrairNumero(currentConditions, "winddir"));

        return info;
    }

    private String encode(String texto) throws Exception {
        return URLEncoder.encode(texto, "UTF-8")
                .replace("+", "%20");
    }
}