import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class JsonMapper {

    private JsonMapper() {
    }

    public static Map<String, Object> usuarioParaMap(Usuario usuario) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("nome", usuario.getNome());
        map.put("favoritos", listaParaJson(usuario.getFavoritos()));
        map.put("assistidas", listaParaJson(usuario.getAssistidas()));
        map.put("desejaAssistir", listaParaJson(usuario.getDesejaAssistir()));
        return map;
    }

    private static List<Object> listaParaJson(List<Serie> series) {
        List<Object> lista = new ArrayList<>();
        for (Serie s : series) {
            lista.add(serieParaMap(s));
        }
        return lista;
    }

    public static Map<String, Object> serieParaMap(Serie s) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("id", s.getId());
        map.put("nome", s.getNome());
        map.put("idioma", s.getIdioma());
        map.put("generos", new ArrayList<Object>(s.getGeneros()));
        map.put("notaGeral", s.getNotaGeral());
        map.put("status", s.getStatus().name());
        map.put("statusOriginal", s.getStatusOriginal());
        map.put("dataEstreia", s.getDataEstreia());
        map.put("dataTermino", s.getDataTermino());
        map.put("emissora", s.getEmissora());
        map.put("urlImagem", s.getUrlImagem());
        map.put("resumo", s.getResumo());
        return map;
    }

    @SuppressWarnings("unchecked")
    public static Usuario mapParaUsuario(Object parsed) {
        Usuario usuario = new Usuario();
        if (!(parsed instanceof Map)) {
            return usuario;
        }
        Map<String, Object> map = (Map<String, Object>) parsed;
        usuario.setNome(asString(map.get("nome")));
        usuario.setFavoritos(jsonParaLista(map.get("favoritos")));
        usuario.setAssistidas(jsonParaLista(map.get("assistidas")));
        usuario.setDesejaAssistir(jsonParaLista(map.get("desejaAssistir")));
        return usuario;
    }

    @SuppressWarnings("unchecked")
    private static List<Serie> jsonParaLista(Object obj) {
        List<Serie> series = new ArrayList<>();
        if (obj instanceof List) {
            for (Object item : (List<Object>) obj) {
                if (item instanceof Map) {
                    series.add(mapParaSerie((Map<String, Object>) item));
                }
            }
        }
        return series;
    }

    @SuppressWarnings("unchecked")
    public static Serie mapParaSerie(Map<String, Object> map) {
        Serie s = new Serie();
        Object id = map.get("id");
        if (id instanceof Number) {
            s.setId(((Number) id).intValue());
        }
        s.setNome(asString(map.get("nome")));
        s.setIdioma(asString(map.get("idioma")));

        Object generos = map.get("generos");
        List<String> listaGeneros = new ArrayList<>();
        if (generos instanceof List) {
            for (Object g : (List<Object>) generos) {
                if (g != null) {
                    listaGeneros.add(g.toString());
                }
            }
        }
        s.setGeneros(listaGeneros);

        Object nota = map.get("notaGeral");
        if (nota instanceof Number) {
            s.setNotaGeral(((Number) nota).doubleValue());
        }

        String statusStr = asString(map.get("status"));
        if (statusStr != null) {
            try {
                s.setStatus(StatusSerie.valueOf(statusStr));
            } catch (IllegalArgumentException e) {
                s.setStatus(StatusSerie.DESCONHECIDO);
            }
        }
        s.setStatusOriginal(asString(map.get("statusOriginal")));
        s.setDataEstreia(asString(map.get("dataEstreia")));
        s.setDataTermino(asString(map.get("dataTermino")));
        s.setEmissora(asString(map.get("emissora")));
        s.setUrlImagem(asString(map.get("urlImagem")));
        s.setResumo(asString(map.get("resumo")));
        return s;
    }

    private static String asString(Object o) {
        return (o != null) ? o.toString() : null;
    }
}
