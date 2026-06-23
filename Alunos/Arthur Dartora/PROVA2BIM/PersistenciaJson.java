import java.io.*;
import java.util.*;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

//salva e carrega os dados no JSON com jackson
public class PersistenciaJson {

    private String arquivo;
    private ObjectMapper mapper;

    public PersistenciaJson(String arquivo) {
        this.arquivo = arquivo;
        this.mapper = new ObjectMapper();
        //identa o JSON 
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
        //se o arquivo tiver algum campo adicional, ignora para nao dar erro
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public void salvar(Usuario usuario) throws IOException {
        //copia os dados do usuario de uma forma simples, ai o jackson grava sozinho
        Dados dados = new Dados();
        dados.nome = usuario.getNome();
        dados.favoritos = usuario.getFavoritos().getSeries();
        dados.assistidas = usuario.getAssistidas().getSeries();
        dados.desejaAssistir = usuario.getDesejaAssistir().getSeries();

        mapper.writeValue(new File(arquivo), dados);
    }

    public Usuario carregar() throws IOException {
        File f = new File(arquivo);
        if (!f.exists()) {
            return null;
        }

        //o jackson le o arquivo e devolve o objeto preenchido
        Dados dados = mapper.readValue(f, Dados.class);

        String nome = (dados.nome == null || dados.nome.isEmpty()) ? "Visitante" : dados.nome;
        Usuario usuario = new Usuario(nome);

        adicionarTodas(usuario.getFavoritos(), dados.favoritos);
        adicionarTodas(usuario.getAssistidas(), dados.assistidas);
        adicionarTodas(usuario.getDesejaAssistir(), dados.desejaAssistir);

        return usuario;
    }

    private void adicionarTodas(ListaDeSeries lista, List<Serie> series) {
        if (series == null) {
            return;
        }
        for (Serie s : series) {
            lista.adicionar(s);
        }
    }

    //listas que o jackson le e grava
    public static class Dados {
        public String nome;
        public List<Serie> favoritos = new ArrayList<Serie>();
        public List<Serie> assistidas = new ArrayList<Serie>();
        public List<Serie> desejaAssistir = new ArrayList<Serie>();
    }
}
