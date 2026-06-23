import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;   // ← Java 11+: HttpClient nativo, sem bibliotecas externas!
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;


public class ClimaService {

    // Chave de autenticação da API Visual Crossing
    private static final String API_KEY = "H2PSA7VDQCV9WNUCXT4CUS252";

    
    private static final String BASE_URL =
        "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";

    
    private final HttpClient httpClient = HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(10))
        .build();

    // ──────────────────────────────────────────────────────────────
   
    public DadosClima buscarClima(String cidade) throws ClimaException {

    
        String cidadeCodificada;
        try {
            cidadeCodificada = URLEncoder.encode(cidade.trim(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new ClimaException("Nome de cidade inválido.", ClimaException.CIDADE_NAO_ENCONTRADA);
        }

       
        String url = BASE_URL + cidadeCodificada
            + "/today?unitGroup=metric&include=current%2Cdays&contentType=json&key=" + API_KEY;

        
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .timeout(Duration.ofSeconds(15)) // Timeout para a resposta
            .GET()                            
            .build();

       
        String jsonResposta;
        try {
            
            HttpResponse<String> response = httpClient.send(request,
                HttpResponse.BodyHandlers.ofString());

           
            int statusCode = response.statusCode();

            if (statusCode == 400) {
                
                throw new ClimaException(
                    "Cidade \"" + cidade + "\" não encontrada. Verifique o nome e tente novamente.",
                    ClimaException.CIDADE_NAO_ENCONTRADA
                );
            } else if (statusCode == 401 || statusCode == 403) {
               
                throw new ClimaException(
                    "Chave de API inválida ou sem permissão.",
                    ClimaException.CHAVE_INVALIDA
                );
            } else if (statusCode != 200) {
              
                throw new ClimaException(
                    "Erro ao consultar a API. Código HTTP: " + statusCode,
                    ClimaException.ERRO_CONEXAO
                );
            }

            jsonResposta = response.body(); 

        } catch (ClimaException e) {
            throw e; 
        } catch (Exception e) {
            
            throw new ClimaException(
                "Erro de conexão. Verifique sua internet e tente novamente.\nDetalhe: " + e.getMessage(),
                ClimaException.ERRO_CONEXAO
            );
        }

        // Converte o JSON em objeto DadosClima e retorna
        return parseJson(jsonResposta, cidade);
    }

   
    private DadosClima parseJson(String json, String cidadeOriginal) throws ClimaException {
        try {
            // Extrai o endereço resolvido pela API (ex: "Cascavel, PR, Brazil")
            String cidade = extrairString(json, "resolvedAddress");
            if (cidade == null || cidade.isEmpty()) cidade = cidadeOriginal;

          
            int inicioAtual = json.indexOf("\"currentConditions\"");
            String blocoAtual = extrairBloco(json, inicioAtual);

            double tempAtual       = extrairDouble(blocoAtual, "temp");
            double sensacaoTermica = extrairDouble(blocoAtual, "feelslike");
            double umidade         = extrairDouble(blocoAtual, "humidity");
            double precipitacao    = extrairDouble(blocoAtual, "precip");
            double velocidadeVento = extrairDouble(blocoAtual, "windspeed");
            double direcaoVento    = extrairDouble(blocoAtual, "winddir");
            double uvIndex         = extrairDouble(blocoAtual, "uvindex");
            String condicao        = extrairString(blocoAtual, "conditions");

            // ── days[0]: resumo do DIA (máxima e mínima) ──────────
            int inicioDays = json.indexOf("\"days\"");
            String blocoDia = extrairBloco(json, inicioDays);

            double tempMaxima = extrairDouble(blocoDia, "tempmax");
            double tempMinima = extrairDouble(blocoDia, "tempmin");

            // Cria e retorna o objeto com todos os dados
            return new DadosClima(cidade, condicao, tempAtual, tempMaxima, tempMinima,
                                  umidade, precipitacao, velocidadeVento, direcaoVento,
                                  sensacaoTermica, uvIndex);

        } catch (Exception e) {
            throw new ClimaException(
                "Erro ao processar resposta da API.",
                ClimaException.RESPOSTA_INVALIDA
            );
        }
    }

    // ──────────────────────────────────────────────────────────────
    // MÉTODOS AUXILIARES DE PARSE DE JSON
    // ──────────────────────────────────────────────────────────────

    // Extrai um valor numérico (double) de um campo JSON
    // Ex: em {"temp": 25.3, ...} com chave "temp" retorna 25.3
    private double extrairDouble(String json, String chave) {
        String token = "\"" + chave + "\":";
        int inicio = json.indexOf(token);
        if (inicio == -1) return 0.0; // Campo não encontrado: retorna 0

        inicio += token.length();

        // Pula espaços em branco
        while (inicio < json.length() && json.charAt(inicio) == ' ') inicio++;

        // Verifica se o valor é nulo (null no JSON)
        if (json.startsWith("null", inicio)) return 0.0;

        // Lê os caracteres do número até encontrar vírgula, } ou ]
        int fim = inicio;
        while (fim < json.length() && "0123456789.-".indexOf(json.charAt(fim)) >= 0) fim++;

        try {
            return Double.parseDouble(json.substring(inicio, fim));
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    // Extrai um valor de texto (String) de um campo JSON
    // Ex: em {"conditions": "Partially cloudy"} retorna "Partially cloudy"
    private String extrairString(String json, String chave) {
        String token = "\"" + chave + "\":\"";
        int inicio = json.indexOf(token);
        if (inicio == -1) return "";

        inicio += token.length();
        int fim = json.indexOf("\"", inicio);
        if (fim == -1) return "";

        return json.substring(inicio, fim);
    }

    // Extrai um bloco JSON a partir de uma posição (entre { e })
    // Usado para isolar blocos como "currentConditions" e "days"
    private String extrairBloco(String json, int posicaoInicio) {
        int abreChave = json.indexOf("{", posicaoInicio);
        if (abreChave == -1) return "";

        int profundidade = 0;
        for (int i = abreChave; i < json.length(); i++) {
            char c = json.charAt(i);
            if (c == '{') profundidade++;      // Abre um nível
            else if (c == '}') profundidade--; // Fecha um nível
            if (profundidade == 0) {
                return json.substring(abreChave, i + 1); // Retorna o bloco completo
            }
        }
        return "";
    }
}
