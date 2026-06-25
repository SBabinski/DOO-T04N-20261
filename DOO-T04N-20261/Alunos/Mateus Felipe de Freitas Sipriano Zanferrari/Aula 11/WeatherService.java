

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

/* 
 * Serviço responsável por interagir com a API de clima, enviar requisições HTTP,
 * processar as respostas JSON e converter os dados em objetos WeatherData. 
 */
public class WeatherService {

    // URL base da API, sem a cidade ou parâmetros. A cidade será adicionada dinamicamente.
    private static final String BASE_URL =
        "https://weather.visualcrossing.com/VisualCrossingWebServices" +
        "/rest/services/timeline/";

    // Timeout padrão para requisições HTTP (10 segundos).
    private static final Duration TIMEOUT = Duration.ofSeconds(10);

    // Cliente HTTP reutilizável para todas as requisições.
    private final HttpClient httpClient;

    // Chave da API, carregada no construtor a partir da classe Config.
    private final String apiKey;

    // Construtor

    // Inicializa o cliente HTTP e carrega a chave da API usando a classe Config.
    public WeatherService() {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(TIMEOUT)
                .build();
        this.apiKey = Config.getApiKey();
    }

    // Método público

    /*
     * Consulta a API para obter os dados climáticos da cidade informada.
     * Retorna um objeto WeatherData com as informações extraídas do JSON.
     * Lança IOException para erros de rede ou resposta inválida, e
     * InterruptedException se a thread for interrompida durante a espera.
     */
    public WeatherData buscarClima(String cidade) throws IOException, InterruptedException {
        String url    = montarUrl(cidade);
        String json   = enviarRequisicao(url);
        return parsearJson(json, cidade);
    }

    // Métodos privados de requisição 

    /*
     * Monta a URL completa para a requisição à API, incluindo a cidade,
     * parâmetros de unidade, idioma e a chave da API.
     */
    private String montarUrl(String cidade) throws IOException {
        String cidadeCodificada = URLEncoder.encode(cidade, StandardCharsets.UTF_8);
        return BASE_URL + cidadeCodificada
                + "?unitGroup=metric"       // temperaturas em °C, vento em km/h
                + "&include=current%2Cdays" // condições atuais + dados do dia
                + "&key=" + apiKey
                + "&contentType=json"
                + "&lang=pt";               // rótulos em português quando disponível
    }

    /*
     * Envia a requisição GET para a URL informada e retorna o corpo
     * da resposta como String. Lança IOException se o servidor
     * retornar um código de erro HTTP.
     */
    private String enviarRequisicao(String url) throws IOException, InterruptedException {
        HttpRequest requisicao = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(TIMEOUT)
                .GET()
                .build();

        HttpResponse<String> resposta = httpClient.send(
                requisicao,
                HttpResponse.BodyHandlers.ofString()
        );

        if (resposta.statusCode() == 400) {
            throw new IOException("Cidade não encontrada. Verifique o nome e tente novamente.");
        }
        if (resposta.statusCode() == 401) {
            throw new IOException("Chave da API inválida. Verifique o arquivo .env.");
        }
        if (resposta.statusCode() != 200) {
            throw new IOException("Erro na API (HTTP " + resposta.statusCode() + ").");
        }

        return resposta.body();
    }

    // Parsing do JSON

    /*
     * Converte o JSON da resposta em um objeto WeatherData,
     * extraindo todos os campos necessários manualmente,
     * sem o uso de bibliotecas externas.
     */
    private WeatherData parsearJson(String json, String cidade) {

        // Bloco "currentConditions" contém os dados atuais do clima
        String blocoAtual  = extrairBloco(json, "currentConditions");

        double tempAtual   = extrairDouble(blocoAtual, "temp");
        double humidade    = extrairDouble(blocoAtual, "humidity");
        double precip      = extrairDouble(blocoAtual, "precip");
        double velVento    = extrairDouble(blocoAtual, "windspeed");
        double dirVento    = extrairDouble(blocoAtual, "winddir");
        String condicao    = extrairString(blocoAtual, "conditions");

        // Bloco "days" contém os dados do dia, incluindo tempmax e tempmin
        String blcoDias    = extrairBloco(json, "days");
        String primeiroDia = extrairBloco(blcoDias, "{");

        double tempMax = extrairDouble(primeiroDia, "tempmax");
        double tempMin = extrairDouble(primeiroDia, "tempmin");

        // Se a condição atual estiver vazia, tenta pegar do resumo do dia
        if (condicao == null || condicao.isEmpty()) {
            condicao = extrairString(primeiroDia, "conditions");
        }

        /*
         * A API pode retornar um nome de cidade mais específico (ex: "Cascavel, PR, Brazil")
         * ou apenas o que foi consultado. Usa o que for mais completo.
         */
        String nomeApi   = extrairString(json, "resolvedAddress");
        String nomeFinal = (nomeApi != null && !nomeApi.isEmpty()) ? nomeApi : cidade;

        return new WeatherData(
            nomeFinal, tempAtual, tempMax, tempMin,
            humidade, condicao, precip, velVento, dirVento
        );
    }

    // Helpers de parsing

    /*
     * Extrai um bloco JSON (objeto ou array) a partir de uma chave.
     * Se a chave for "currentConditions", retorna o objeto associado.
     * Se a chave for "days", retorna o primeiro item do array "days".
     * Se a chave for "{", retorna o primeiro objeto encontrado (usado para arrays).
     */
    private String extrairBloco(String json, String chave) {
        int inicio = json.indexOf("\"" + chave + "\"");
        if (inicio == -1) {
            if (chave.equals("{")) {
                int idx = json.indexOf("{");
                if (idx == -1) return "";
                return json.substring(idx, encontrarFechamento(json, idx) + 1);
            }
            return "";
        }

        int abreObjeto   = json.indexOf("{", inicio);
        int abreArray    = json.indexOf("[", inicio);

        if (abreObjeto == -1 && abreArray == -1) return "";

        // Se o próximo bloco for um array, extrai o primeiro objeto dentro dele
        if (abreArray != -1 && (abreObjeto == -1 || abreArray < abreObjeto)) {
            int primeiroBraco = json.indexOf("{", abreArray);
            if (primeiroBraco == -1) return "";
            return json.substring(primeiroBraco, encontrarFechamento(json, primeiroBraco) + 1);
        }

        return json.substring(abreObjeto, encontrarFechamento(json, abreObjeto) + 1);
    }

    /*
     * Encontra o índice de fechamento correspondente para um bloco JSON
     * que começa com '{' ou '['. Considera blocos aninhados corretamente.
     */
    private int encontrarFechamento(String json, int abre) {
        int profundidade = 0;
        for (int i = abre; i < json.length(); i++) {
            if      (json.charAt(i) == '{') profundidade++;
            else if (json.charAt(i) == '}') {
                profundidade--;
                if (profundidade == 0) return i;
            }
        }
        return json.length() - 1;
    }

    /*
     * Lê o valor numérico (double) de um campo pelo seu nome no JSON.
     * Retorna 0.0 se o campo não for encontrado ou não for numérico.
     */
    private double extrairDouble(String json, String campo) {
        String chave = "\"" + campo + "\":";
        int idx = json.indexOf(chave);
        if (idx == -1) return 0.0;

        int inicio = idx + chave.length();
        while (inicio < json.length() && json.charAt(inicio) == ' ') inicio++;

        int fim = inicio;
        while (fim < json.length() &&
               (Character.isDigit(json.charAt(fim)) ||
                json.charAt(fim) == '.' || json.charAt(fim) == '-')) {
            fim++;
        }

        String valor = json.substring(inicio, fim).trim();
        if (valor.isEmpty()) return 0.0;
        try {
            return Double.parseDouble(valor);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    /*
     * Lê o valor textual (String) de um campo pelo seu nome no JSON.
     * Retorna string vazia se o campo não for encontrado.
     */
    private String extrairString(String json, String campo) {
        String chave = "\"" + campo + "\":";
        int idx = json.indexOf(chave);
        if (idx == -1) return "";

        
        int inicio = json.indexOf("\"", idx + chave.length());
        if (inicio == -1) return "";
        inicio++; // pula a aspas de abertura

        int fim = json.indexOf("\"", inicio);
        if (fim == -1) return "";

        return json.substring(inicio, fim);
    }
}
