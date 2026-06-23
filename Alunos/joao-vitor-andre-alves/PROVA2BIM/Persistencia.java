import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;

public class Persistencia {

    private static final String ARQUIVO = "dados.json";
    private final ObjectMapper mapper = new ObjectMapper();

    public void salvar(Usuario usuario) throws Exception {
        // writerWithDefaultPrettyPrinter() só pra deixar o JSON identado/legivel
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(ARQUIVO), usuario);
    }

    public Usuario carregar() throws Exception {
        File arquivo = new File(ARQUIVO);
        if (!arquivo.exists()) {
            return null;
        }
        // le o arquivo e remonta o Usuario inteiro (com as 3 listas de Serie)
        return mapper.readValue(arquivo, Usuario.class);
    }
}