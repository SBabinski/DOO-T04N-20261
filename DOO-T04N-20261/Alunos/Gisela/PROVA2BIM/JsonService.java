package PROVA2BIM;

import com.google.gson.Gson;
import java.io.FileReader;
import java.io.FileWriter;

public class JsonService {
    private static final String ARQUIVO = "usuario.json";
    private static final Gson gson = new Gson();

    public static void salvar(Usuario usuario) {
        try (FileWriter writer = new FileWriter(ARQUIVO)) {
            gson.toJson(usuario, writer);
        } catch (Exception e) {
            System.err.println("Erro ao salvar usuário: " + e.getMessage());
        }
    }

    public static Usuario carregar() {
        try (FileReader reader = new FileReader(ARQUIVO)) {
            return gson.fromJson(reader, Usuario.class);
        } catch (Exception e) {
            return null; // se não existir arquivo ou der erro
        }
    }
}
