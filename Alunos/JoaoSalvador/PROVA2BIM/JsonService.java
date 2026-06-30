package Fag.services;

import Fag.Serie;
import Fag.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * Serviço responsável por carregar e salvar o JSON local.
 * Isso garante persistência entre fechar e abrir o programa.
 */
public class JsonService {

    private final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private final File arquivo = new File("dados/usuario.json");

    public Usuario carregarUsuario() {
        try {
            if (!arquivo.exists()) {
                Usuario usuarioNovo = criarUsuarioPadrao();
                salvarUsuario(usuarioNovo);
                return usuarioNovo;
            }

            return mapper.readValue(arquivo, Usuario.class);
        } catch (Exception e) {
            // Se der erro no JSON, o sistema não fecha. Ele cria um usuário padrão.
            return criarUsuarioPadrao();
        }
    }

    public void salvarUsuario(Usuario usuario) throws Exception {
        File pastaDados = new File("dados");

        if (!pastaDados.exists()) {
            pastaDados.mkdir();
        }

        mapper.writeValue(arquivo, usuario);
    }

    private Usuario criarUsuarioPadrao() {
        Usuario usuario = new Usuario("Aluno");

        Serie breakingBad = new Serie(
                169,
                "Breaking Bad",
                "English",
                new ArrayList<String>(Arrays.asList("Drama", "Crime", "Thriller")),
                9.3,
                "Ended",
                "2008-01-20",
                "2013-09-29",
                "AMC"
        );

        Serie theOffice = new Serie(
                526,
                "The Office",
                "English",
                new ArrayList<String>(Arrays.asList("Comedy")),
                8.5,
                "Ended",
                "2005-03-24",
                "2013-05-16",
                "NBC"
        );

        usuario.getFavoritos().add(breakingBad);
        usuario.getAssistir().add(theOffice);

        return usuario;
    }
}
