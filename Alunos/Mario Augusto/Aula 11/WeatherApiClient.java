import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Cliente HTTP responsável por consultar a API Timeline da Visual Crossing
 * e converter a resposta JSON em um objeto {@link WeatherData}.
 *
 * <p>A chave de autenticação é obtida exclusivamente por meio da variável de
 * ambiente {@code VISUALCROSSING_API_KEY}, garantindo que ela nunca seja
 * embutida no código-fonte.</p>
 */
public class WeatherApiClient {

    private static final String BASE_URL =
            "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";

    /** Nome da variável de ambiente que deve conter a chave da API. */
    private static final String ENV_KEY = "VISUALCROSSING_API_KEY";

    private final String apiKey;

    /**
     * Cria o cliente lendo a chave de API da variável de ambiente.
     *
     * @throws IllegalStateException se a variável de ambiente não estiver definida.
     */
    public WeatherApiClient() {
        String key = System.getenv(ENV_KEY);
        if (key == null || key.isBlank()) {
            throw new IllegalStateException(
                    "A variável de ambiente '" + ENV_KEY + "' não está definida. "
                    + "Defina-a antes de executar o programa.");
        }
        this.apiKey = key;
    }

    /**
     * Consulta o clima atual para a cidade informada.
     *
     * @param cidade Nome da cidade (ex.: "Cascavel,PR,Brasil").
     * @return Objeto {@link WeatherData} preenchido com os dados da API.
     * @throws Exception em caso de erros de rede ou resposta inesperada.
     */
    public WeatherData buscarClima(String cidade) throws Exception {
        String cidadeCodificada = URLEncoder.encode(cidade, StandardCharsets.UTF_8);

        // Consulta para "hoje" com dados atuais + diários (mínimo de records = economia de cota)
        String urlStr = BASE_URL + cidadeCodificada + "/today"
                + "?key=" + apiKey
                + "&unitGroup=metric"
                + "&lang=pt"
                + "&include=current,days"
                + "&elements=temp,tempmax,tempmin,humidity,conditions,precip,windspeed,winddir";

        String json = fazerRequisicao(urlStr);
        return parsearResposta(json, cidade);
    }

    // ── Métodos privados ──────────────────────────────────────────────────────

    private String fazerRequisicao(String urlStr) throws Exception {
        URL url = URI.create(urlStr).toURL();
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        conexao.setRequestMethod("GET");
        conexao.setConnectTimeout(10_000);
        conexao.setReadTimeout(10_000);

        int statusCode = conexao.getResponseCode();
        if (statusCode != HttpURLConnection.HTTP_OK) {
            String mensagemErro = lerFluxo(new BufferedReader(
                    new InputStreamReader(conexao.getErrorStream(), StandardCharsets.UTF_8)));
            throw new RuntimeException(
                    "Erro na requisição HTTP " + statusCode + ": " + mensagemErro);
        }

        return lerFluxo(new BufferedReader(
                new InputStreamReader(conexao.getInputStream(), StandardCharsets.UTF_8)));
    }

    private String lerFluxo(BufferedReader leitor) throws Exception {
        StringBuilder sb = new StringBuilder();
        String linha;
        while ((linha = leitor.readLine()) != null) {
            sb.append(linha);
        }
        leitor.close();
        return sb.toString();
    }

    /**
     * Faz o parse manual do JSON retornado pela API sem uso de bibliotecas externas,
     * mantendo o projeto compatível com ambientes sem dependências adicionais.
     */
    private WeatherData parsearResposta(String json, String cidade) {
        // currentConditions — temperatura atual, umidade, condição, vento
        double tempAtual    = extrairDouble(json, "currentConditions", "temp");
        double umidade      = extrairDouble(json, "currentConditions", "humidity");
        double velVento     = extrairDouble(json, "currentConditions", "windspeed");
        double dirVento     = extrairDouble(json, "currentConditions", "winddir");
        String condicao     = extrairString(json, "currentConditions", "conditions");

        // days[0] — máxima, mínima e precipitação do dia
        double tempMax      = extrairDoubleArray(json, "days", "tempmax");
        double tempMin      = extrairDoubleArray(json, "days", "tempmin");
        double precipitacao = extrairDoubleArray(json, "days", "precip");

        // Usa "resolvedAddress" como nome da cidade se disponível
        String nomeCidade = extrairCampoRaiz(json, "resolvedAddress");
        if (nomeCidade.isEmpty()) {
            nomeCidade = cidade;
        }

        return new WeatherData(
                nomeCidade,
                tempAtual,
                tempMax,
                tempMin,
                umidade,
                condicao,
                precipitacao,
                velVento,
                dirVento);
    }

    /** Extrai um campo numérico dentro de um objeto JSON específico. */
    private double extrairDouble(String json, String objeto, String campo) {
        int inicioObjeto = json.indexOf("\"" + objeto + "\"");
        if (inicioObjeto == -1) return 0;
        int abreChave = json.indexOf('{', inicioObjeto);
        if (abreChave == -1) return 0;
        int fechaChave = encontrarFechamentoChave(json, abreChave);
        String bloco = json.substring(abreChave, fechaChave);
        return extrairNumero(bloco, campo);
    }

    /** Extrai um campo textual dentro de um objeto JSON específico. */
    private String extrairString(String json, String objeto, String campo) {
        int inicioObjeto = json.indexOf("\"" + objeto + "\"");
        if (inicioObjeto == -1) return "";
        int abreChave = json.indexOf('{', inicioObjeto);
        if (abreChave == -1) return "";
        int fechaChave = encontrarFechamentoChave(json, abreChave);
        String bloco = json.substring(abreChave, fechaChave);
        return extrairTexto(bloco, campo);
    }

    /** Extrai um campo numérico do primeiro objeto dentro de um array JSON. */
    private double extrairDoubleArray(String json, String array, String campo) {
        int inicioArray = json.indexOf("\"" + array + "\"");
        if (inicioArray == -1) return 0;
        int abreColchete = json.indexOf('[', inicioArray);
        if (abreColchete == -1) return 0;
        int abreChave = json.indexOf('{', abreColchete);
        if (abreChave == -1) return 0;
        int fechaChave = encontrarFechamentoChave(json, abreChave);
        String bloco = json.substring(abreChave, fechaChave);
        return extrairNumero(bloco, campo);
    }

    /** Extrai um campo textual diretamente na raiz do JSON. */
    private String extrairCampoRaiz(String json, String campo) {
        String chave = "\"" + campo + "\"";
        int pos = json.indexOf(chave);
        if (pos == -1) return "";
        int doisPontos = json.indexOf(':', pos);
        if (doisPontos == -1) return "";
        int aspasAbre = json.indexOf('"', doisPontos);
        if (aspasAbre == -1) return "";
        int aspasFecha = json.indexOf('"', aspasAbre + 1);
        if (aspasFecha == -1) return "";
        return json.substring(aspasAbre + 1, aspasFecha);
    }

    private double extrairNumero(String bloco, String campo) {
        String chave = "\"" + campo + "\"";
        int pos = bloco.indexOf(chave);
        if (pos == -1) return 0;
        int doisPontos = bloco.indexOf(':', pos);
        if (doisPontos == -1) return 0;
        int inicio = doisPontos + 1;
        while (inicio < bloco.length() && bloco.charAt(inicio) == ' ') inicio++;
        int fim = inicio;
        while (fim < bloco.length() && (Character.isDigit(bloco.charAt(fim))
                || bloco.charAt(fim) == '.' || bloco.charAt(fim) == '-')) {
            fim++;
        }
        String valor = bloco.substring(inicio, fim).trim();
        try {
            return Double.parseDouble(valor);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private String extrairTexto(String bloco, String campo) {
        String chave = "\"" + campo + "\"";
        int pos = bloco.indexOf(chave);
        if (pos == -1) return "";
        int doisPontos = bloco.indexOf(':', pos);
        if (doisPontos == -1) return "";
        int aspasAbre = bloco.indexOf('"', doisPontos);
        if (aspasAbre == -1) return "";
        int aspasFecha = bloco.indexOf('"', aspasAbre + 1);
        if (aspasFecha == -1) return "";
        return bloco.substring(aspasAbre + 1, aspasFecha);
    }

    /** Encontra o índice do '}' de fechamento que corresponde ao '{' em {@code abre}. */
    private int encontrarFechamentoChave(String json, int abre) {
        int profundidade = 0;
        for (int i = abre; i < json.length(); i++) {
            if (json.charAt(i) == '{') profundidade++;
            else if (json.charAt(i) == '}') {
                profundidade--;
                if (profundidade == 0) return i + 1;
            }
        }
        return json.length();
    }
}
