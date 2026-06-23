import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class ClimaService {

    private static final String API_KEY = "MXWEPG7G3HRCJEWQFJJLFX78Y";

    public Clima buscarClima(String cidade) {

        try {
            String cidadeFormatada = cidade.trim();
            
            cidadeFormatada = URLEncoder.encode(cidadeFormatada, StandardCharsets.UTF_8);

            String endpoint
                    = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                    + cidadeFormatada
                    + "?unitGroup=metric&key="
                    + API_KEY
                    + "&contentType=json";

            URL url = new URL(endpoint);

            HttpURLConnection conexao
                    = (HttpURLConnection) url.openConnection();

            conexao.setRequestMethod("GET");

            BufferedReader leitor
                    = new BufferedReader(
                            new InputStreamReader(conexao.getInputStream()));

            StringBuilder resposta = new StringBuilder();
            String linha;

            while ((linha = leitor.readLine()) != null) {
                resposta.append(linha);
            }

            leitor.close();

            String json = resposta.toString();

            String blocoAtual = extrairBloco(json, "currentConditions");
            String blocoDia = extrairPrimeiroDia(json);

            double temperaturaAtual
                    = Double.parseDouble(obterValor(blocoAtual, "temp"));

            double umidade
                    = Double.parseDouble(obterValor(blocoAtual, "humidity"));

            String condicao
                    = obterValorTexto(blocoAtual, "conditions");

            String precipStr = obterValor(blocoAtual, "precip");

            double precipitacao = 0;

            if (!precipStr.equals("null") && !precipStr.isEmpty()) {
                precipitacao = Double.parseDouble(precipStr);
            }

            double velocidadeVento
                    = Double.parseDouble(obterValor(blocoAtual, "windspeed"));

            double direcaoVento
                    = Double.parseDouble(obterValor(blocoAtual, "winddir"));

            double temperaturaMaxima
                    = Double.parseDouble(obterValor(blocoDia, "tempmax"));

            double temperaturaMinima
                    = Double.parseDouble(obterValor(blocoDia, "tempmin"));

            return new Clima(
                    temperaturaAtual,
                    temperaturaMaxima,
                    temperaturaMinima,
                    umidade,
                    condicao,
                    precipitacao,
                    velocidadeVento,
                    direcaoVento
            );

        } catch (Exception e) {

            System.out.println("Erro ao consultar API:");
            System.out.println(e.getMessage());

            return null;
        }
    }

    private String extrairBloco(String json, String nomeBloco) {
        int inicio = json.indexOf("\"" + nomeBloco + "\"");
        inicio = json.indexOf("{", inicio);
        int contador = 1;
        int fim = inicio + 1;
        while (contador > 0) {
            char c = json.charAt(fim);
            if (c == '{') { contador++; }
            if (c == '}') { contador--; }
            fim++;
        }
        return json.substring(inicio, fim);
    }

    private String extrairPrimeiroDia(String json) {
        int inicioDays = json.indexOf("\"days\"");
        int inicioObjeto = json.indexOf("{", inicioDays);
        int contador = 1;
        int fim = inicioObjeto + 1;
        while (contador > 0) {
            char c = json.charAt(fim);
            if (c == '{') { contador++; }
            if (c == '}') { contador--; }
            fim++;
        }
        return json.substring(inicioObjeto, fim);
    }

    private String obterValor(String json, String chave) {
        int inicio = json.indexOf("\"" + chave + "\"");
        inicio = json.indexOf(":", inicio) + 1;
        int fimVirgula = json.indexOf(",", inicio);
        int fimChave = json.indexOf("}", inicio);
        int fim;
        if (fimVirgula == -1) { fim = fimChave; } 
        else if (fimChave == -1) { fim = fimVirgula; } 
        else { fim = Math.min(fimVirgula, fimChave); }
        return json.substring(inicio, fim).trim();
    }

    private String obterValorTexto(String json, String chave) {
        int inicio = json.indexOf("\"" + chave + "\"");
        inicio = json.indexOf(":", inicio);
        int primeiraAspa = json.indexOf("\"", inicio + 1);
        int segundaAspa = json.indexOf("\"", primeiraAspa + 1);
        return json.substring(primeiraAspa + 1, segundaAspa);
    }
}