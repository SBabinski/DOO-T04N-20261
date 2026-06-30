import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UsuarioRepository {

    private final Path arquivo;

    public UsuarioRepository() {
        String home = System.getProperty("user.home", ".");
        Path dir = Paths.get(home, ".serietracker");
        this.arquivo = dir.resolve("dados.json");
    }

    public boolean existeDados() {
        return Files.exists(arquivo);
    }

    public void salvar(Usuario usuario) throws PersistenceException {
        try {
            Path dir = arquivo.getParent();
            if (dir != null && !Files.exists(dir)) {
                Files.createDirectories(dir);
            }
            String json = JsonWriter.write(JsonMapper.usuarioParaMap(usuario));
            Files.write(arquivo, json.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new PersistenceException("Não foi possível salvar os dados: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new PersistenceException("Erro inesperado ao salvar os dados.", e);
        }
    }

    public Usuario carregar() throws PersistenceException {
        if (!existeDados()) {
            return null;
        }
        try {
            byte[] bytes = Files.readAllBytes(arquivo);
            String json = new String(bytes, StandardCharsets.UTF_8);
            if (json.trim().isEmpty()) {
                return null;
            }
            Object parsed = JsonParser.parse(json);
            return JsonMapper.mapParaUsuario(parsed);
        } catch (IOException e) {
            throw new PersistenceException("Não foi possível ler os dados salvos: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new PersistenceException("Arquivo de dados corrompido ou inválido.", e);
        }
    }

    public Path getCaminhoArquivo() {
        return arquivo;
    }
}
