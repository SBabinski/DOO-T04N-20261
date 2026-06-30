package Fag.services;

import Fag.Usuario;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;

// servico responsavel por salvar e carregar os dados do usuario em json.
// concentra os conteudos de persistencia em json com jackson e excecoes.
public class PersistenciaService {

    private static final String ARQUIVO = "dados.json";

    private final ObjectMapper mapper;
    private final File arquivo;

    public PersistenciaService() {
        this.mapper = new ObjectMapper();
        // deixa o json identado, fica mais facil de ler e de conferir.
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
        this.arquivo = new File(ARQUIVO);
    }

    // grava o usuario inteiro (apelido e as tres listas) no arquivo json.
    public void salvar(Usuario usuario) throws PersistenciaException {
        try {
            mapper.writeValue(arquivo, usuario);
        } catch (IOException e) {
            throw new PersistenciaException("não foi possível salvar os dados.", e);
        }
    }

    // le o arquivo json. se ele ainda nao existir, devolve um usuario vazio.
    public Usuario carregar() throws PersistenciaException {
        if (!arquivo.exists()) {
            return new Usuario();
        }
        try {
            return mapper.readValue(arquivo, Usuario.class);
        } catch (IOException e) {
            throw new PersistenciaException("não foi possível ler os dados salvos.", e);
        }
    }
}
