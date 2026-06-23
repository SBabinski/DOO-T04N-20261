package service;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Serie;
import model.Usuario;

public class JsonPersistence {
    private static final String BASE_FILE_NAME = "seriestracker_data";

    public static void salvar(Usuario usuario) {
        Path path = getPathForUser(usuario.getNome());
        try {
            if (path.getParent() != null) Files.createDirectories(path.getParent());
            StringBuilder sb = new StringBuilder();
            sb.append("{\n  \"nome\": \"").append(usuario.getNome()).append("\",\n");
            sb.append("  \"favoritos\": ").append(converterLista(usuario.getFavoritos())).append(",\n");
            sb.append("  \"jaAssistidas\": ").append(converterLista(usuario.getJaAssistidas())).append(",\n");
            sb.append("  \"desejaAssistir\": ").append(converterLista(usuario.getDesejaAssistir())).append("\n}");
            Files.writeString(path, sb.toString(), StandardCharsets.UTF_8);
            System.out.println("Dados salvos com sucesso em: " + path.toAbsolutePath());
        } catch (Exception e) {
            System.err.println("Erro ao salvar JSON: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static Usuario carregar(String nomeUsuario) {
        Path path = getPathForUser(nomeUsuario);
        if (!Files.exists(path)) return null;
        try {
            String json = Files.readString(path, StandardCharsets.UTF_8);
            String nome = "Usuário";
            Matcher mNome = Pattern.compile("\"nome\"\\s*:\\s*\"([^\"]+)\"").matcher(json);
            if (mNome.find()) nome = mNome.group(1);

            Usuario usuario = new Usuario(nome);
            usuario.getFavoritos().addAll(extrairLista(json, "favoritos"));
            usuario.getJaAssistidas().addAll(extrairLista(json, "jaAssistidas"));
            usuario.getDesejaAssistir().addAll(extrairLista(json, "desejaAssistir"));
            System.out.println("Dados carregados com sucesso de: " + path.toAbsolutePath());
            return usuario;
        } catch (Exception e) {
            System.err.println("Erro ao carregar JSON de: " + path.toAbsolutePath() + " - " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private static Path getPathForUser(String nomeUsuario) {
        if (nomeUsuario == null || "mateus".equalsIgnoreCase(nomeUsuario)) {
            return Paths.get(BASE_FILE_NAME + ".json");
        }
        String safeName = nomeUsuario.toLowerCase().replaceAll("[^a-z0-9_-]", "_");
        return Paths.get(BASE_FILE_NAME + "_" + safeName + ".json");
    }

    private static List<Serie> extrairLista(String json, String chaveLista) {
        List<Serie> lista = new ArrayList<>();
        Matcher mLista = Pattern.compile("\"" + chaveLista + "\"\\s*:\\s*\\[").matcher(json);
        if (!mLista.find()) return lista;

        int arrayStart = mLista.end() - 1;
        int arrayEnd = findMatchingBracket(json, arrayStart);
        if (arrayEnd < 0) return lista;

        String conteudo = json.substring(arrayStart + 1, arrayEnd);
        Matcher mItem = Pattern.compile("\\{(.*?)\\}", Pattern.DOTALL).matcher(conteudo);
        while (mItem.find()) {
            String item = mItem.group(1);
            String nome = e(item, "nome");
            String idioma = e(item, "idioma");
            String estado = e(item, "estado");
            String estreia = e(item, "dataEstreia");
            String termino = e(item, "dataTermino");
            String emissora = e(item, "emissora");

            double nota = 0.0;
            Matcher mNota = Pattern.compile("\"notaGeral\"\\s*:\\s*([0-9.]+)").matcher(item);
            if (mNota.find()) nota = Double.parseDouble(mNota.group(1));

            List<String> generos = new ArrayList<>();
            int idxGeneros = item.indexOf('[');
            if (idxGeneros != -1) {
                int endGeneros = item.indexOf(']', idxGeneros);
                if (endGeneros > idxGeneros) {
                    String generosTexto = item.substring(idxGeneros + 1, endGeneros);
                    Matcher mGen = Pattern.compile("\"([^\"]+)\"").matcher(generosTexto);
                    while (mGen.find()) generos.add(mGen.group(1));
                }
            }

            lista.add(new Serie(nome, idioma, generos, nota, estado, estreia, termino, emissora));
        }
        return lista;
    }

    private static int findMatchingBracket(String texto, int startIndex) {
        int depth = 0;
        for (int i = startIndex; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (c == '[') depth++;
            else if (c == ']') {
                depth--;
                if (depth == 0) return i;
            }
        }
        return -1;
    }

    private static String converterLista(List<Serie> lista) {
        StringBuilder sb = new StringBuilder().append("[\n");
        for (int i = 0; i < lista.size(); i++) {
            Serie s = lista.get(i);
            sb.append("    {\n");
            sb.append("      \"nome\": \"").append(s.getNome()).append("\",\n");
            sb.append("      \"idioma\": \"").append(s.getIdioma()).append("\",\n");
            sb.append("      \"estado\": \"").append(s.getEstado()).append("\",\n");
            sb.append("      \"dataEstreia\": \"").append(s.getDataEstreia()).append("\",\n");
            sb.append("      \"dataTermino\": \"").append(s.getDataTermino()).append("\",\n");
            sb.append("      \"emissora\": \"").append(s.getEmissora()).append("\",\n");
            sb.append("      \"notaGeral\": ").append(s.getNotaGeral()).append(",\n");
            sb.append("      \"generos\": [");
            for (int j = 0; j < s.getGeneros().size(); j++) {
                sb.append("\"").append(s.getGeneros().get(j)).append("\"");
                if (j < s.getGeneros().size() - 1) sb.append(",");
            }
            sb.append("]\n    }");
            if (i < lista.size() - 1) sb.append(",");
            sb.append("\n");
        }
        return sb.append("  ]").toString();
    }

    private static String e(String txt, String c) {
        Matcher m = Pattern.compile("\"" + c + "\"\\s*:\\s*\"([^\"]+)\"").matcher(txt);
        return m.find() ? m.group(1) : "N/A";
    }
}