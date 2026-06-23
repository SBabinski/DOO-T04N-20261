package service;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.Serie;

public class TvMazeService {
    private static final HttpClient client = HttpClient.newHttpClient();

    public static List<Serie> buscarSeries(String query) {
        List<Serie> resultado = new ArrayList<>();
        try {
            String url = "https://api.tvmaze.com/search/shows?q=" + URLEncoder.encode(query, StandardCharsets.UTF_8);
            HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            
            if (response.statusCode() == 200) {
                resultado = parseJsonTvMaze(response.body());
            }
        } catch (Exception e) {
            System.err.println("Erro ao conectar com a API: " + e.getMessage());
        }
        return resultado;
    }

    private static List<Serie> parseJsonTvMaze(String json) {
        List<Serie> lista = new ArrayList<>();
        String[] blocos = json.split("\\{\"score\":");
        
        for (String bloco : blocos) {
            if (!bloco.contains("\"show\":")) continue;
            
            String nome = extrairRegex(bloco, "\"name\"\\s*:\\s*\"([^\"]+)\"");
            String idioma = extrairRegex(bloco, "\"language\"\\s*:\\s*\"([^\"]+)\"");
            String estado = extrairRegex(bloco, "\"status\"\\s*:\\s*\"([^\"]+)\"");
            String estreia = extrairRegex(bloco, "\"premiered\"\\s*:\\s*\"([^\"]+)\"");
            String termino = extrairRegex(bloco, "\"ended\"\\s*:\\s*\"([^\"]+)\"");
            String emissora = extrairRegex(bloco, "\"network\"\\s*:\\s*\\{[^}]*\"name\"\\s*:\\s*\"([^\"]+)\"");
            
            double nota = 0.0;
            String notaStr = extrairRegex(bloco, "\"rating\"\\s*:\\s*\\{\\s*\"average\"\\s*:\\s*([0-9.]+)");
            if (!notaStr.equals("N/A")) {
                try { nota = Double.parseDouble(notaStr); } catch (Exception ignored) {}
            }

            List<String> generos = new ArrayList<>();
            Pattern p = Pattern.compile("\"genres\"\\s*:\\s*\\[([^\\]]*)\\]");
            Matcher m = p.matcher(bloco);
            if (m.find()) {
                Matcher mGen = Pattern.compile("\"([^\"]+)\"").matcher(m.group(1));
                while (mGen.find()) generos.add(mGen.group(1));
            }
            if (generos.isEmpty()) generos.add("N/A");

            lista.add(new Serie(nome, idioma, generos, nota, estado, estreia, termino, emissora));
        }
        return lista;
    }

    private static String extrairRegex(String texto, String regex) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(texto);
        return m.find() ? m.group(1) : "N/A";
    }
}