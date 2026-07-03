package atividadeAPI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

public class BuscaDados {

    private static final String API_KEY = "SuaChaveAPI"; // colocar a chave da API aqui

    public void buscarClima(String cidade) {

        try {
        	String cidadeURL = cidade.trim().replace(" ", "%20");
            String endereco = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/"
                    + cidadeURL
                    + "?unitGroup=metric&key="
                    + API_KEY
                    + "&contentType=json&lang=pt-br";

            URL url = new URL(endereco);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
            conexao.setRequestMethod("GET");

            int codigoResposta = conexao.getResponseCode();

            if (codigoResposta != 200) {
                System.out.println("Cidade não encontrada!");
                return;
            }

            BufferedReader leitor = new BufferedReader(
                    new InputStreamReader(conexao.getInputStream()));

            String linha;
            StringBuilder resposta = new StringBuilder();

            while ((linha = leitor.readLine()) != null) {
                resposta.append(linha);
            }

            leitor.close();

            JSONObject json = new JSONObject(resposta.toString());

            // Dados atuais
            JSONObject atual = json.getJSONObject("currentConditions");

            // Dados do dia
            JSONObject hoje = json.getJSONArray("days").getJSONObject(0);

            double temperatura = atual.getDouble("temp");
            double tempMax = hoje.getDouble("tempmax");
            double tempMin = hoje.getDouble("tempmin");
            double humidade = atual.getDouble("humidity");
            String condicao = atual.getString("conditions");
            double precipitacao = hoje.getDouble("precip");
            double velocidadeVento = atual.getDouble("windspeed");
            double direcaoVento = atual.getDouble("winddir");
            String icone = atual.getString("icon");
            System.out.println("Cidade: " + cidade);
            System.out.println("Temperatura atual: " + temperatura + "°C");
            System.out.println("Máxima do dia: " + tempMax + "°C");
            System.out.println("Mínima do dia: " + tempMin + "°C");
            System.out.println("Humidade do ar: " + humidade + "%");
            System.out.println("Condição do tempo: " + condicao);
            System.out.println("Precipitação: " + precipitacao + " mm");
            System.out.println("Velocidade do vento: " + velocidadeVento + " km/h");
            System.out.println("Direção do vento: " + direcaoVento + "°");
            javax.swing.SwingUtilities.invokeLater(() -> {
                new JanelaClima(
                        cidade,
                        temperatura,
                        tempMax,
                        tempMin,
                        humidade,
                        condicao,
                        precipitacao,
                        velocidadeVento,
                        icone);
            });
        } catch (Exception e) {
            System.out.println("Erro ao buscar dados da API");
            e.printStackTrace();
        }
    }
}