package weatherapp.service;

import weatherapp.exception.CityNotFoundException;
import weatherapp.exception.InvalidApiKeyException;
import weatherapp.exception.WeatherApiException;
import weatherapp.model.WeatherData;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

/**
 * Camada responsavel por se comunicar com a api
 *
 */
public class WeatherService {

    private static final String BASE_URL =
            "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline";

    private final String apiKey;
    private final HttpClient httpClient;

    public WeatherService(String apiKey) {
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalArgumentException("A chave de API nao pode ser vazia.");
        }
        this.apiKey = apiKey;
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();
    }

    /**
     * Busca o clima atual (temperatura no momento, maxima/minima do dia,
     * umidade, condicao, precipitacao e vento) para a cidade informada.
     *
     * @param cidade nome da cidade
     * @return objeto WeatherData com as informacoes ja tratadas
     * @throws WeatherApiException em caso de falha de rede, chave invalida
     *                              ou cidade nao encontrada
     */
    public WeatherData buscarClimaAtual(String cidade) throws WeatherApiException {
        String jsonResposta = executarRequisicao(cidade);
        return WeatherMapper.paraWeatherData(cidade, jsonResposta);
    }

    private String executarRequisicao(String cidade) throws WeatherApiException {
        String url = montarUrl(cidade);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofSeconds(15))
                .header("Accept", "application/json")
                .GET()
                .build();

        HttpResponse<String> response;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new WeatherApiException("Falha de conexao com a API de clima. Verifique sua internet.", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new WeatherApiException("A requisicao foi interrompida.", e);
        }

        return tratarResposta(response, cidade);
    }

    private String tratarResposta(HttpResponse<String> response, String cidade) throws WeatherApiException {
        int status = response.statusCode();
        String corpo = response.body();

        switch (status) {
            case 200:
                return corpo;
            case 400:
                throw new CityNotFoundException(cidade);
            case 401:
                throw new InvalidApiKeyException(
                        "Chave de API invalida ou sem permissao. Verifique sua chave em visualcrossing.com/account.");
            case 429:
                throw new InvalidApiKeyException(
                        "Limite de requisicoes do plano gratuito foi atingido. Tente novamente mais tarde.");
            default:
                throw new WeatherApiException(
                        "Erro inesperado da API (HTTP " + status + "): " + corpo);
        }
    }

    private String montarUrl(String cidade) {
        String cidadeCodificada = URLEncoder.encode(cidade, StandardCharsets.UTF_8);
        return BASE_URL + "/" + cidadeCodificada + "/today"
                + "?unitGroup=metric"
                + "&include=days,current"
                + "&contentType=json"
                + "&key=" + apiKey;
    }
}
