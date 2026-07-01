package PROVA2BIM;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaService {

    private static final String ARQUIVO_DADOS = "series_tracker_dados.json";

    public void salvar(Usuario usuario) throws IOException {
        if (usuario == null) throw new IllegalArgumentException("Usuário não pode ser nulo.");

        StringBuilder sb = new StringBuilder();
        sb.append("{\n");
        sb.append("  \"nome\": ").append(jsonString(usuario.getNome())).append(",\n");
        sb.append("  \"favoritos\": ").append(serializarLista(usuario.getFavoritos())).append(",\n");
        sb.append("  \"jaAssistidas\": ").append(serializarLista(usuario.getJaAssistidas())).append(",\n");
        sb.append("  \"desejassistir\": ").append(serializarLista(usuario.getDesejassistir())).append("\n");
        sb.append("}");

        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(ARQUIVO_DADOS), StandardCharsets.UTF_8))) {
            writer.write(sb.toString());
        }
    }

    public Usuario carregar() throws IOException {
        File arquivo = new File(ARQUIVO_DADOS);
        if (!arquivo.exists()) return null;

        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(arquivo), StandardCharsets.UTF_8))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                sb.append(linha).append("\n");
            }
        }

        String json = sb.toString().trim();
        return desserializarUsuario(json);
    }

    public boolean existeDadosSalvos() {
        return new File(ARQUIVO_DADOS).exists();
    }

    
    private String serializarLista(List<Serie> lista) {
        if (lista == null || lista.isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[\n");
        for (int i = 0; i < lista.size(); i++) {
            sb.append(serializarSerie(lista.get(i)));
            if (i < lista.size() - 1) sb.append(",");
            sb.append("\n");
        }
        sb.append("  ]");
        return sb.toString();
    }

    private String serializarSerie(Serie s) {
        StringBuilder sb = new StringBuilder("    {\n");
        sb.append("      \"id\": ").append(s.getId()).append(",\n");
        sb.append("      \"nome\": ").append(jsonString(s.getNome())).append(",\n");
        sb.append("      \"idioma\": ").append(jsonString(s.getIdioma())).append(",\n");
        sb.append("      \"generos\": ").append(serializarListaStrings(s.getGeneros())).append(",\n");
        sb.append("      \"nota\": ").append(s.getNota()).append(",\n");
        sb.append("      \"estado\": ").append(jsonString(s.getEstado())).append(",\n");
        sb.append("      \"dataEstreia\": ").append(jsonString(s.getDataEstreia())).append(",\n");
        sb.append("      \"dataTermino\": ").append(jsonString(s.getDataTermino())).append(",\n");
        sb.append("      \"emissora\": ").append(jsonString(s.getEmissora())).append(",\n");
        sb.append("      \"resumo\": ").append(jsonString(s.getResumo())).append(",\n");
        sb.append("      \"imagemUrl\": ").append(jsonString(s.getImagemUrl())).append("\n");
        sb.append("    }");
        return sb.toString();
    }

    private String serializarListaStrings(List<String> lista) {
        if (lista == null || lista.isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < lista.size(); i++) {
            sb.append(jsonString(lista.get(i)));
            if (i < lista.size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    private String jsonString(String s) {
        if (s == null) return "null";
        return "\"" + s.replace("\\", "\\\\")
                       .replace("\"", "\\\"")
                       .replace("\n", "\\n")
                       .replace("\r", "\\r")
                       .replace("\t", "\\t") + "\"";
    }

    
    private Usuario desserializarUsuario(String json) {
        if (json == null || json.isEmpty()) return null;
        try {
            Usuario u = new Usuario();
            u.setNome(lerCampoString(json, "nome"));
            u.setFavoritos(lerListaSeries(json, "favoritos"));
            u.setJaAssistidas(lerListaSeries(json, "jaAssistidas"));
            u.setDesejassistir(lerListaSeries(json, "desejassistir"));
            return u;
        } catch (Exception e) {
            return null;
        }
    }

    private List<Serie> lerListaSeries(String json, String campo) {
        List<Serie> lista = new ArrayList<>();
        String chave = "\"" + campo + "\"";
        int idx = json.indexOf(chave);
        if (idx < 0) return lista;

        int colon = json.indexOf(':', idx + chave.length());
        if (colon < 0) return lista;

        int start = colon + 1;
        while (start < json.length() && Character.isWhitespace(json.charAt(start))) start++;
        if (start >= json.length() || json.charAt(start) != '[') return lista;

        int depth = 0;
        int arrayEnd = -1;
        for (int i = start; i < json.length(); i++) {
            char c = json.charAt(i);
            if (c == '[' || c == '{') depth++;
            else if (c == ']' || c == '}') {
                depth--;
                if (depth == 0) { arrayEnd = i; break; }
            }
        }
        if (arrayEnd < 0) return lista;

        String arrayContent = json.substring(start + 1, arrayEnd);
        List<String> objetos = extrairObjetos(arrayContent);
        for (String obj : objetos) {
            try {
                Serie s = desserializarSerie(obj);
                if (s != null) lista.add(s);
            } catch (Exception ignored) {}
        }
        return lista;
    }

    private List<String> extrairObjetos(String conteudo) {
        List<String> lista = new ArrayList<>();
        int depth = 0;
        int start = -1;
        for (int i = 0; i < conteudo.length(); i++) {
            char c = conteudo.charAt(i);
            if (c == '{') {
                if (depth == 0) start = i;
                depth++;
            } else if (c == '}') {
                depth--;
                if (depth == 0 && start >= 0) {
                    lista.add(conteudo.substring(start, i + 1));
                    start = -1;
                }
            }
        }
        return lista;
    }

    private Serie desserializarSerie(String json) {
        Serie s = new Serie();
        s.setId(lerCampoInt(json, "id"));
        s.setNome(lerCampoString(json, "nome"));
        s.setIdioma(lerCampoString(json, "idioma"));
        s.setGeneros(lerListaStrings(json, "generos"));
        s.setNota(lerCampoDouble(json, "nota"));
        s.setEstado(lerCampoString(json, "estado"));
        s.setDataEstreia(lerCampoString(json, "dataEstreia"));
        s.setDataTermino(lerCampoString(json, "dataTermino"));
        s.setEmissora(lerCampoString(json, "emissora"));
        s.setResumo(lerCampoString(json, "resumo"));
        s.setImagemUrl(lerCampoString(json, "imagemUrl"));
        return s;
    }

    private String lerCampoString(String json, String campo) {
        String chave = "\"" + campo + "\"";
        int idx = json.indexOf(chave);
        if (idx < 0) return null;
        int colon = json.indexOf(':', idx + chave.length());
        if (colon < 0) return null;
        int start = colon + 1;
        while (start < json.length() && Character.isWhitespace(json.charAt(start))) start++;
        if (start >= json.length()) return null;
        if (json.startsWith("null", start)) return null;
        if (json.charAt(start) != '"') return null;
        start++;
        StringBuilder sb = new StringBuilder();
        boolean escape = false;
        for (int i = start; i < json.length(); i++) {
            char c = json.charAt(i);
            if (escape) {
                switch (c) {
                    case '"': sb.append('"'); break;
                    case '\\': sb.append('\\'); break;
                    case 'n': sb.append('\n'); break;
                    case 'r': sb.append('\r'); break;
                    case 't': sb.append('\t'); break;
                    default: sb.append(c);
                }
                escape = false;
            } else if (c == '\\') {
                escape = true;
            } else if (c == '"') {
                return sb.toString();
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private int lerCampoInt(String json, String campo) {
        String chave = "\"" + campo + "\"";
        int idx = json.indexOf(chave);
        if (idx < 0) return 0;
        int colon = json.indexOf(':', idx + chave.length());
        if (colon < 0) return 0;
        int start = colon + 1;
        while (start < json.length() && Character.isWhitespace(json.charAt(start))) start++;
        int end = start;
        while (end < json.length() && (Character.isDigit(json.charAt(end)) || json.charAt(end) == '-')) end++;
        try { return Integer.parseInt(json.substring(start, end).trim()); }
        catch (NumberFormatException e) { return 0; }
    }

    private double lerCampoDouble(String json, String campo) {
        String chave = "\"" + campo + "\"";
        int idx = json.indexOf(chave);
        if (idx < 0) return 0.0;
        int colon = json.indexOf(':', idx + chave.length());
        if (colon < 0) return 0.0;
        int start = colon + 1;
        while (start < json.length() && Character.isWhitespace(json.charAt(start))) start++;
        if (start < json.length() && json.startsWith("null", start)) return 0.0;
        int end = start;
        while (end < json.length() && (Character.isDigit(json.charAt(end))
                || json.charAt(end) == '.' || json.charAt(end) == '-')) end++;
        try { return Double.parseDouble(json.substring(start, end).trim()); }
        catch (NumberFormatException e) { return 0.0; }
    }

    private List<String> lerListaStrings(String json, String campo) {
        List<String> lista = new ArrayList<>();
        String chave = "\"" + campo + "\"";
        int idx = json.indexOf(chave);
        if (idx < 0) return lista;
        int colon = json.indexOf(':', idx + chave.length());
        if (colon < 0) return lista;
        int start = colon + 1;
        while (start < json.length() && Character.isWhitespace(json.charAt(start))) start++;
        if (start >= json.length() || json.charAt(start) != '[') return lista;
        int end = json.indexOf(']', start);
        if (end < 0) return lista;
        String content = json.substring(start + 1, end).trim();
        if (content.isEmpty()) return lista;
        String[] parts = content.split(",");
        for (String part : parts) {
            part = part.trim();
            if (part.startsWith("\"") && part.endsWith("\"")) {
                lista.add(part.substring(1, part.length() - 1));
            }
        }
        return lista;
    }
}
