package tvtracker.persistence;

import tvtracker.model.PerfilUsuario;
import tvtracker.model.Serie;
import tvtracker.service.JsonUtil;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// Salva e carrega o perfil do usuário em ~/.tvtracker/perfil.json
public class Repositorio {

    private static final String PASTA   = System.getProperty("user.home") + File.separator + ".tvtracker";
    private static final String ARQUIVO = PASTA + File.separator + "perfil.json";

    // Cria a pasta de dados se não existir
    public Repositorio() {
        try {
            Files.createDirectories(Path.of(PASTA));
        } catch (Exception e) {
            System.err.println("Aviso: não foi possível criar pasta de dados: " + e.getMessage());
        }
    }

    // Converte o perfil para JSON e grava no disco
    public void salvar(PerfilUsuario perfil) throws ErroPersistencia {
        try {
            String json = serializarPerfil(perfil);
            Files.writeString(Path.of(ARQUIVO), json, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (Exception e) {
            throw new ErroPersistencia("Erro ao salvar dados: " + e.getMessage(), e);
        }
    }

    // Lê o arquivo JSON e reconstrói o perfil; retorna null se o arquivo não existir
    public PerfilUsuario carregar() throws ErroPersistencia {
        Path caminho = Path.of(ARQUIVO);
        if (!Files.exists(caminho)) return null;
        try {
            String json = Files.readString(caminho, StandardCharsets.UTF_8);
            return desserializarPerfil(json);
        } catch (Exception e) {
            throw new ErroPersistencia("Arquivo de dados corrompido. Um novo perfil será criado.", e);
        }
    }

    // --- Serialização (Java → JSON) ---

    private String serializarPerfil(PerfilUsuario p) {
        StringBuilder sb = new StringBuilder("{");
        sb.append("\"nome\":\"").append(JsonUtil.escapar(p.getNome())).append("\",");
        sb.append("\"favoritos\":").append(serializarLista(p.getFavoritos())).append(",");
        sb.append("\"assistidas\":").append(serializarLista(p.getAssistidas())).append(",");
        sb.append("\"querVer\":").append(serializarLista(p.getQuerVer()));
        sb.append("}");
        return sb.toString();
    }

    private String serializarLista(List<Serie> lista) {
        StringBuilder sb = new StringBuilder("[");
        boolean primeiro = true;
        for (Serie s : lista) {
            if (!primeiro) sb.append(",");
            sb.append(serializarSerie(s));
            primeiro = false;
        }
        return sb.append("]").toString();
    }

    private String serializarSerie(Serie s) {
        StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":").append(s.getId()).append(",");
        sb.append("\"nome\":\"").append(JsonUtil.escapar(s.getNome())).append("\",");
        sb.append("\"idioma\":\"").append(JsonUtil.escapar(s.getIdioma())).append("\",");
        sb.append("\"generos\":").append(serializarListaStrings(s.getGeneros())).append(",");
        sb.append("\"nota\":").append(s.getNota() != null ? s.getNota() : "null").append(",");
        sb.append("\"estado\":\"").append(JsonUtil.escapar(s.getEstado())).append("\",");
        sb.append("\"estreia\":\"").append(JsonUtil.escapar(s.getEstreia())).append("\",");
        sb.append("\"termino\":\"").append(JsonUtil.escapar(s.getTermino())).append("\",");
        sb.append("\"emissora\":\"").append(JsonUtil.escapar(s.getEmissora())).append("\",");
        sb.append("\"sinopse\":\"").append(JsonUtil.escapar(s.getSinopse())).append("\",");
        if (s.getUrlImagem() != null)
            sb.append("\"urlImagem\":\"").append(JsonUtil.escapar(s.getUrlImagem())).append("\"");
        else
            sb.append("\"urlImagem\":null");
        return sb.append("}").toString();
    }

    private String serializarListaStrings(List<String> lista) {
        if (lista == null) return "[]";
        StringBuilder sb = new StringBuilder("[");
        boolean primeiro = true;
        for (String s : lista) {
            if (!primeiro) sb.append(",");
            sb.append("\"").append(JsonUtil.escapar(s)).append("\"");
            primeiro = false;
        }
        return sb.append("]").toString();
    }

    // --- Desserialização (JSON → Java) ---

    @SuppressWarnings("unchecked")
    private PerfilUsuario desserializarPerfil(String json) {
        Map<String, Object> mapa = (Map<String, Object>) JsonUtil.analisar(json);
        PerfilUsuario perfil = new PerfilUsuario();
        perfil.setNome(JsonUtil.getString(mapa, "nome"));

        List<Object> favArr     = JsonUtil.getArray(mapa, "favoritos");
        List<Object> assistArr  = JsonUtil.getArray(mapa, "assistidas");
        List<Object> querVerArr = JsonUtil.getArray(mapa, "querVer");

        if (favArr     != null) for (Object o : favArr)     perfil.getFavoritos().add(desserializarSerie((Map<String,Object>) o));
        if (assistArr  != null) for (Object o : assistArr)  perfil.getAssistidas().add(desserializarSerie((Map<String,Object>) o));
        if (querVerArr != null) for (Object o : querVerArr) perfil.getQuerVer().add(desserializarSerie((Map<String,Object>) o));

        return perfil;
    }

    private Serie desserializarSerie(Map<String, Object> m) {
        Serie s = new Serie();
        Integer id = JsonUtil.getInt(m, "id");
        s.setId(id != null ? id : 0);
        s.setNome(JsonUtil.getString(m, "nome"));
        s.setIdioma(JsonUtil.getString(m, "idioma"));
        s.setNota(JsonUtil.getDouble(m, "nota"));
        s.setEstado(JsonUtil.getString(m, "estado"));
        s.setEstreia(JsonUtil.getString(m, "estreia"));
        s.setTermino(JsonUtil.getString(m, "termino"));
        s.setEmissora(JsonUtil.getString(m, "emissora"));
        s.setSinopse(JsonUtil.getString(m, "sinopse"));
        s.setUrlImagem(JsonUtil.getString(m, "urlImagem"));

        List<String> generos = new ArrayList<>();
        List<Object> ga = JsonUtil.getArray(m, "generos");
        if (ga != null) for (Object g : ga) if (g instanceof String) generos.add((String) g);
        s.setGeneros(generos);

        return s;
    }
}