package clima;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ClimaApiService {

    // URL base da API do Visual Crossing (Timeline Weather API)
    private static final String BASE_URL =
            "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";

    private final String apiKey;

    public ClimaApiService(String apiKey) {
        if (apiKey == null || apiKey.trim().isEmpty()) {
            throw new IllegalArgumentException("A chave da API não pode ser nula ou vazia.");
        }
        this.apiKey = apiKey;
    }

    public String buscarClimaJson(String cidade) throws Exception {
        if (cidade == null || cidade.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da cidade não pode ser nulo ou vazio.");
        }

        // Encode o nome da cidade para URL segura
        String cidadeCodificada = URLEncoder.encode(cidade.trim(), StandardCharsets.UTF_8);

        // Monta a URL completa com parâmetros:
        // unitGroup=metric   → temperaturas em Celsius
        // include=current    → inclui dados do momento atual
        // lang=pt            → condições em português
        String urlStr = BASE_URL + cidadeCodificada
                + "?unitGroup=metric"
                + "&include=current"
                + "&lang=pt"
                + "&key=" + apiKey
                + "&contentType=json";

        @SuppressWarnings("deprecation")
		URL url = new URL(urlStr);
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestMethod("GET");
        conexao.setConnectTimeout(10_000); // 10 segundos
        conexao.setReadTimeout(10_000);
        conexao.setRequestProperty("Accept", "application/json");

        int codigoResposta = conexao.getResponseCode();

        if (codigoResposta == HttpURLConnection.HTTP_OK) {
            return lerResposta(conexao);
        } else if (codigoResposta == 401) {
            throw new Exception("Chave de API inválida ou não autorizada. Verifique sua chave.");
        } else if (codigoResposta == 404) {
            throw new Exception("Cidade não encontrada: " + cidade);
        } else {
            String erroBody = lerErro(conexao);
            throw new Exception("Erro na API. Código HTTP: " + codigoResposta + ". Detalhe: " + erroBody);
        }
    }

    private String lerResposta(HttpURLConnection conexao) throws Exception {
        try (BufferedReader leitor = new BufferedReader(
                new InputStreamReader(conexao.getInputStream(), StandardCharsets.UTF_8))) {

            StringBuilder sb = new StringBuilder();
            String linha;
            while ((linha = leitor.readLine()) != null) {
                sb.append(linha);
            }
            return sb.toString();
        }
    }

    private String lerErro(HttpURLConnection conexao) {
        try (BufferedReader leitor = new BufferedReader(
                new InputStreamReader(conexao.getErrorStream(), StandardCharsets.UTF_8))) {

            StringBuilder sb = new StringBuilder();
            String linha;
            while ((linha = leitor.readLine()) != null) {
                sb.append(linha);
            }
            return sb.toString();
        } catch (Exception e) {
            return "(sem detalhes)";
        }
    }
}
