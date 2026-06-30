package model;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PersistenciaDados {
    private static final String CAMINHO_ARQUIVO = "dados.json";
    private final ObjectMapper objectMapper;

    public PersistenciaDados(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void salvarDadosUsuario(Usuario usuario) throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(CAMINHO_ARQUIVO), usuario);
    }

    public Usuario carregarDadosUsuario() throws IOException {
        File arquivo = new File(CAMINHO_ARQUIVO);
        if (!arquivo.exists()) {
            return null;
        }
        return objectMapper.readValue(arquivo, Usuario.class);
    }
}