package weatherapp.config;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

/**
 * aq busca a chave da api do visualcrossing
 *
 */
public final class ApiKeyProvider {

    private static final String ENV_VAR_NAME = "VISUALCROSSING_API_KEY";
    private static final String CONFIG_FILE_NAME = "config.properties";
    private static final String CONFIG_PROPERTY_NAME = "api.key";

    private ApiKeyProvider() {
        // classe utilitaria, nao deve ser instanciada
    }

    public static String obterChave() {
        String chaveDoAmbiente = System.getenv(ENV_VAR_NAME);
        if (chaveDoAmbiente != null && !chaveDoAmbiente.isBlank()) {
            return chaveDoAmbiente.trim();
        }

        String chaveDoArquivo = lerChaveDoArquivo();
        if (chaveDoArquivo != null && !chaveDoArquivo.isBlank()) {
            return chaveDoArquivo.trim();
        }

        return null;
    }

    private static String lerChaveDoArquivo() {
        Path caminho = Path.of(CONFIG_FILE_NAME);
        if (!Files.exists(caminho)) {
            return null;
        }
        try (InputStream input = Files.newInputStream(caminho)) {
            Properties props = new Properties();
            props.load(input);
            return props.getProperty(CONFIG_PROPERTY_NAME);
        } catch (IOException e) {
            return null;
        }
    }
}
