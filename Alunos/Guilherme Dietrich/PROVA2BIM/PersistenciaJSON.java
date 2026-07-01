package objetos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.nio.file.Files;
import java.nio.file.Path;

public class PersistenciaJSON {

    private static final String NOME_ARQUIVO = "seriestv_dados.json";
    private final Gson conversorJson = new GsonBuilder().setPrettyPrinting().create();

    // salva o arquivo json
    public void salvar(Usuario usuario) throws Exception {
        String dadosJson = conversorJson.toJson(usuario);
        Files.writeString(Path.of(NOME_ARQUIVO), dadosJson);
    }

    // carrega o arquivo json
    public Usuario carregar() throws Exception {
        Path caminhoArquivo = Path.of(NOME_ARQUIVO);
        if (!Files.exists(caminhoArquivo)) {
            return null; // primeira vez usando o programa
        }

        String conteudoJson = Files.readString(caminhoArquivo);
        return conversorJson.fromJson(conteudoJson, Usuario.class);
    }

    public boolean existeDadosSalvos() {
        return Files.exists(Path.of(NOME_ARQUIVO));
    }
}
