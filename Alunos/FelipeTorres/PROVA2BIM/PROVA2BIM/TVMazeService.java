package PROVA2BIM;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class TVMazeService {

    private static final String BASE_URL = "https://api.tvmaze.com";
    private static final int TIMEOUT_MS = 10000;

    public List<Serie> buscarPorNome(String nomeSerie) throws Exception {
        if (nomeSerie == null || nomeSerie.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da série não pode ser vazio.");
        }

        String encoded = URLEncoder.encode(nomeSerie.trim(), StandardCharsets.UTF_8.name());
        String urlStr = BASE_URL + "/search/shows?q=" + encoded;
        String json = fazerRequisicao(urlStr);
        return parsearResultadosBusca(json);
    }

    private String fazerRequisicao(String urlStr) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(TIMEOUT_MS);
        conn.setReadTimeout(TIMEOUT_MS);
        conn.setRequestProperty("User-Agent", "SeriesTracker/1.0");

        int responseCode = conn.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new Exception("Erro na API TVMaze. Código HTTP: " + responseCode);
        }

        BufferedReader reader = new BufferedReader(
            new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8)
        );
        StringBuilder sb = new StringBuilder();
        String linha;
        while ((linha = reader.readLine()) != null) {
            sb.append(linha);
        }
        reader.close();
        conn.disconnect();
        return sb.toString();
    }

    private List<Serie> parsearResultadosBusca(String json) {
        List<Serie> series = new ArrayList<>();
        if (json == null || json.trim().equals("[]")) return series;

        List<String> itens = extrairObjetosRaiz(json);
        for (String item : itens) {
            try {
                String showJson = extrairCampoObjeto(item, "show");
                if (showJson != null && !showJson.isEmpty()) {
                    Serie s = parsearShow(showJson);
                    if (s != null) series.add(s);
                }
            } catch (Exception e) {
            }
        }
        return series;
    }

    private List<String> extrairObjetosRaiz(String json) {
        List<String> objetos = new ArrayList<>();
        String content = json.trim();
        if (content.startsWith("[")) {
            content = content.substring(1, content.length() - 1).trim();
        }

        int depth = 0;
        int start = -1;
        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            if (c == '{') {
                if (depth == 0) start = i;
                depth++;
            } else if (c == '}') {
                depth--;
                if (depth == 0 && start != -1) {
                    objetos.add(content.substring(start, i + 1));
                    start = -1;
                }
            }
        }
        return objetos;
    }

    private Serie parsearShow(String json) {
        try {
            int id = parsearInt(json, "id");
            String nome = parsearString(json, "name");
            String idioma = parsearString(json, "language");
            String estado = parsearString(json, "status");
            String dataEstreia = parsearString(json, "premiered");
            String dataTermino = parsearString(json, "ended");


            double nota = 0.0;
            String ratingObj = extrairCampoObjeto(json, "rating");
            if (ratingObj != null) {
                nota = parsearDouble(ratingObj, "average");
            }

            List<String> generos = parsearArrayStrings(json, "genres");

            String emissora = "N/A";
            String networkObj = extrairCampoObjeto(json, "network");
            if (networkObj != null && !networkObj.equals("null")) {
                String netName = parsearString(networkObj, "name");
                if (netName != null && !netName.isEmpty()) emissora = netName;
            } else {
                String webObj = extrairCampoObjeto(json, "webChannel");
                if (webObj != null && !webObj.equals("null")) {
                    String webName = parsearString(webObj, "name");
                    if (webName != null && !webName.isEmpty()) emissora = webName;
                }
            }

            String resumo = parsearString(json, "summary");
            if (resumo != null) resumo = resumo.replaceAll("<[^>]*>", "").trim();

            String imagemUrl = null;
            String imageObj = extrairCampoObjeto(json, "image");
            if (imageObj != null && !imageObj.equals("null")) {
                imagemUrl = parsearString(imageObj, "medium");
            }

            if (nome == null || nome.isEmpty()) return null;

            return new Serie(id, nome, idioma != null ? idioma : "N/A",
                    generos, nota, estado != null ? estado : "N/A",
                    dataEstreia != null ? dataEstreia : "N/A",
                    dataTermino != null ? dataTermino : "N/A",
                    emissora, resumo != null ? resumo : "", imagemUrl);
        } catch (Exception e) {
            return null;
        }
    }

    private String extrairCampoObjeto(String json, String campo) {
        String chave = "\"" + campo + "\"";
        int idx = json.indexOf(chave);
        if (idx < 0) return null;

        int colon = json.indexOf(':', idx + chave.length());
        if (colon < 0) return null;

        int start = colon + 1;
        while (start < json.length() && Character.isWhitespace(json.charAt(start))) start++;

        if (start >= json.length()) return null;

        char first = json.charAt(start);
        if (first == '{') {
            int depth = 0;
            for (int i = start; i < json.length(); i++) {
                char c = json.charAt(i);
                if (c == '{') depth++;
                else if (c == '}') {
                    depth--;
                    if (depth == 0) return json.substring(start, i + 1);
                }
            }
        } else if (first == 'n' && json.startsWith("null", start)) {
            return "null";
        }
        return null;
    }

    private int parsearInt(String json, String campo) {
        String chave = "\"" + campo + "\"";
        int idx = json.indexOf(chave);
        if (idx < 0) return 0;
        int colon = json.indexOf(':', idx + chave.length());
        if (colon < 0) return 0;
        int start = colon + 1;
        while (start < json.length() && Character.isWhitespace(json.charAt(start))) start++;
        int end = start;
        while (end < json.length() && (Character.isDigit(json.charAt(end)) || json.charAt(end) == '-')) end++;
        try {
            return Integer.parseInt(json.substring(start, end).trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private double parsearDouble(String json, String campo) {
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
        try {
            return Double.parseDouble(json.substring(start, end).trim());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    private String parsearString(String json, String campo) {
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
                    case '/': sb.append('/'); break;
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

    private List<String> parsearArrayStrings(String json, String campo) {
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

        String arrayContent = json.substring(start + 1, end);
        String[] parts = arrayContent.split(",");
        for (String part : parts) {
            part = part.trim();
            if (part.startsWith("\"") && part.endsWith("\"")) {
                lista.add(part.substring(1, part.length() - 1));
            }
        }
        return lista;
    }
}
