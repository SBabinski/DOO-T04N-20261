

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Config {

    // Nome do arquivo de configuração local.
    private static final String ENV_FILE = ".env";

    // Chave em cache para evitar leituras repetidas do arquivo. 
    private static String apiKey = null;

    // Lê a chave da API do arquivo .env. Se o arquivo ou a chave não existirem, lança uma RuntimeException com instruções claras.
    public static String getApiKey() {
        if (apiKey != null) return apiKey; // usa o cache se já foi lida

        try (BufferedReader reader = new BufferedReader(new FileReader(ENV_FILE))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                linha = linha.trim();
                // Ignora linhas vazias e comentários
                if (linha.isEmpty() || linha.startsWith("#")) continue;

                if (linha.startsWith("WEATHER_API_KEY=")) {
                    apiKey = linha.substring("WEATHER_API_KEY=".length()).trim();
                    if (!apiKey.isEmpty()) return apiKey;
                }
            }
        } catch (IOException e) {
        // Se o arquivo .env não existir, trataremos isso como chave ausente
        }

        throw new RuntimeException(
            "Chave da API não encontrada!\n\n" +
            "Crie um arquivo '.env' na raiz do projeto com o conteúdo:\n" +
            "  WEATHER_API_KEY=SUA_CHAVE_AQUI\n\n" +
            "E certifique-se de que '.env' está no .gitignore."
        );
    }
}
