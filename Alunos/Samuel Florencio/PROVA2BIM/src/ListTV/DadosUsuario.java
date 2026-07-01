package ListTV;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DadosUsuario {

    private static final String ARQUIVO = "usuario.json";
    private final Path caminho;

    public DadosUsuario() {
        this.caminho = Paths.get(ARQUIVO);
    }

    public boolean arquivoExiste() {
        return Files.exists(caminho);
    }

    public void salvar(PerfilUsuario usuario) throws PersistenciaException {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("{\n");
            sb.append("  \"nome\": \"").append(JsonUtil.escape(usuario.getNome())).append("\",\n");
            sb.append("  \"favoritos\": ").append(listaParaJson(usuario.getFavoritos())).append(",\n");
            sb.append("  \"assistidas\": ").append(listaParaJson(usuario.getAssistidas())).append(",\n");
            sb.append("  \"desejoAssistir\": ").append(listaParaJson(usuario.getDesejoAssistir())).append("\n");
            sb.append("}\n");
            Files.write(caminho, sb.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new PersistenciaException("Nao foi possivel salvar os dados em " + ARQUIVO + ".", e);
        }
    }
    public PerfilUsuario carregar() throws PersistenciaException {
        if (!arquivoExiste()) {
            PerfilUsuario perfilPadrao = criarDadosPreCarregados();
            salvar(perfilPadrao);
            return perfilPadrao;
        }

        try {
            String conteudo = new String(Files.readAllBytes(caminho), StandardCharsets.UTF_8);
            Object parsed = JsonUtil.parse(conteudo);
            Map<String, Object> raiz = JsonUtil.asMap(parsed);
            if (raiz.isEmpty()) {
                throw new PersistenciaException("O arquivo " + ARQUIVO + " esta vazio ou em formato invalido.");
            }
            PerfilUsuario usuario = new PerfilUsuario(JsonUtil.getString(raiz, "nome"));

            // monta as 3 listas de novo a partir do que ta salvo no arquivo
            for (SerieTV s : jsonParaListaSeries(raiz.get("favoritos"))) usuario.adicionarFavorito(s);
            for (SerieTV s : jsonParaListaSeries(raiz.get("assistidas"))) usuario.adicionarAssistida(s);
            for (SerieTV s : jsonParaListaSeries(raiz.get("desejoAssistir"))) usuario.adicionarDesejo(s);

            return usuario;

        } catch (IOException e) {
            throw new PersistenciaException("Nao foi possivel ler o arquivo " + ARQUIVO + ".", e);
        } catch (PersistenciaException e) {
            throw e; 
        } catch (Exception e) {

            throw new PersistenciaException("O arquivo " + ARQUIVO + " esta corrompido (JSON invalido).", e);
        }
    }

    private String listaParaJson(List<SerieTV> series) {
        StringBuilder sb = new StringBuilder("[\n");
        for (int i = 0; i < series.size(); i++) {
            sb.append("    ").append(serieParaJson(series.get(i)));
            if (i < series.size() - 1) sb.append(",");
            sb.append("\n");
        }
        sb.append("  ]");
        return sb.toString();
    }

    private String serieParaJson(SerieTV s) {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        sb.append("\"id\": ").append(s.getId()).append(", ");
        sb.append("\"nome\": \"").append(JsonUtil.escape(s.getNome())).append("\", ");
        sb.append("\"idioma\": \"").append(JsonUtil.escape(vazio(s.getIdioma()))).append("\", ");
        sb.append("\"generos\": [");
        List<String> generos = s.getGeneros();
        for (int i = 0; i < generos.size(); i++) {
            sb.append("\"").append(JsonUtil.escape(generos.get(i))).append("\"");
            if (i < generos.size() - 1) sb.append(", ");
        }
        sb.append("], ");
        sb.append("\"nota\": ").append(s.getNota() == null ? "null" : s.getNota()).append(", ");
        sb.append("\"status\": \"").append(JsonUtil.escape(vazio(s.getStatus()))).append("\", ");
        sb.append("\"estreia\": \"").append(JsonUtil.escape(vazio(s.getEstreia()))).append("\", ");
        sb.append("\"termino\": \"").append(JsonUtil.escape(vazio(s.getTermino()))).append("\", ");
        sb.append("\"emissora\": \"").append(JsonUtil.escape(vazio(s.getEmissora()))).append("\"");
        sb.append(" }");
        return sb.toString();
    }

    // troca null por string vazia (usado ao salvar)
    private String vazio(String s) {
        return s == null ? "" : s;
    }
    // troca string vazia por null (usado ao carregar, faz o caminho inverso de vazio())
    private String nuloSeVazio(String s) {
        return (s == null || s.isEmpty()) ? null : s;
    }

    @SuppressWarnings("unchecked")
    private List<SerieTV> jsonParaListaSeries(Object obj) {
        List<SerieTV> resultado = new ArrayList<>();
        for (Object item : JsonUtil.asList(obj)) {
            Map<String, Object> map = JsonUtil.asMap(item);

            List<String> generos = new ArrayList<>();
            Object generosObj = map.get("generos");
            if (generosObj instanceof List) {
                for (Object g : (List<Object>) generosObj) generos.add(String.valueOf(g));
            }
            SerieTV s = new SerieTV(
                    JsonUtil.getInt(map, "id"),
                    JsonUtil.getString(map, "nome"),
                    JsonUtil.getString(map, "idioma"),
                    generos,
                    JsonUtil.getDouble(map, "nota"),
                    JsonUtil.getString(map, "status"),
                    nuloSeVazio(JsonUtil.getString(map, "estreia")),
                    nuloSeVazio(JsonUtil.getString(map, "termino")),
                    JsonUtil.getString(map, "emissora")
            );
            resultado.add(s);
        }
        return resultado;
    }

    private PerfilUsuario criarDadosPreCarregados() {
        PerfilUsuario perfil = new PerfilUsuario("Convidado");

        SerieTV breakingBad = new SerieTV(169, "Breaking Bad", "English",
                List.of("Drama", "Crime", "Thriller"), 9.3, "Ended",
                "2008-01-20", "2013-09-29", "AMC");

        SerieTV friends = new SerieTV(431, "Friends", "English",
                List.of("Comedy", "Romance"), 8.7, "Ended",
                "1994-09-22", "2004-05-06", "NBC");

        SerieTV got = new SerieTV(82, "Game of Thrones", "English",
                List.of("Drama", "Adventure", "Fantasy"), 9.0, "Ended",
                "2011-04-17", "2019-05-19", "HBO");

        SerieTV strangerThings = new SerieTV(2993, "Stranger Things", "English",
                List.of("Drama", "Fantasy", "Horror"), 8.6, "Running",
                "2016-07-15", "2025-12-31", "Netflix"); 

        perfil.adicionarFavorito(breakingBad);
        perfil.adicionarFavorito(got);
        perfil.adicionarAssistida(breakingBad);
        perfil.adicionarAssistida(friends);
        perfil.adicionarDesejo(strangerThings);

        return perfil;
    }
}
class PersistenciaException extends Exception {
    public PersistenciaException(String mensagem) {
        super(mensagem);
    }
    public PersistenciaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}