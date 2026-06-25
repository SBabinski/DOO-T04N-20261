package com.weather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeatherService {

    // Esta classe é responsável por se comunicar com a API de clima (Visual Crossing) para obter os dados meteorológicos atuais de uma cidade.
    private static final String BASE_URL
            = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
    // Mapeamento de condições meteorológicas em inglês para português, para exibição mais amigável.
    private static final Map<String, String> CONDITION_PT = new HashMap<>();

    static {
        CONDITION_PT.put("Clear", "Limpo / Ensolarado");
        CONDITION_PT.put("Partially cloudy", "Parcialmente nublado");
        CONDITION_PT.put("Overcast", "Nublado");
        CONDITION_PT.put("Rain", "Chuva");
        CONDITION_PT.put("Rain, Partially cloudy", "Chuva, Parcialmente nublado");
        CONDITION_PT.put("Rain, Overcast", "Chuva, Nublado");
        CONDITION_PT.put("Snow", "Neve");
        CONDITION_PT.put("Fog", "Névoa / Neblina");
        CONDITION_PT.put("Wind", "Ventoso");
        CONDITION_PT.put("Thunderstorm", "Tempestade");
        CONDITION_PT.put("Drizzle", "Garoa");
        CONDITION_PT.put("Hail", "Granizo");
    }
    // A chave de API para autenticação com o serviço de clima. Deve ser fornecida ao criar uma instância de WeatherService.
    private final String apiKey;

    public WeatherService(String apiKey) {
        this.apiKey = apiKey;
    }
    // Método principal para buscar os dados meteorológicos de uma cidade. Ele constrói a URL da API, faz a requisição HTTP, trata os erros e parseia a resposta JSON para um objeto WeatherData.
    public WeatherData fetchWeather(String city) throws Exception {
        String encoded = URLEncoder.encode(city, StandardCharsets.UTF_8);
        String urlStr = BASE_URL + encoded + "/today"
                + "?unitGroup=metric"
                + "&include=current,days"
                + "&key=" + apiKey
                + "&contentType=json";

        @SuppressWarnings("deprecation")
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(10_000);
        conn.setReadTimeout(10_000);
        conn.setRequestProperty("Accept", "application/json");

        int status = conn.getResponseCode();
        if (status == 401) {
            throw new Exception("API Key inválida. Verifique sua chave em visualcrossing.com.");
        }
        if (status == 400) {
            throw new Exception("Cidade não encontrada. Verifique o nome e tente novamente.");
        }
        if (status != 200) {
            throw new Exception("Erro na API (código " + status + "). Tente novamente.");
        }

        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }

        return parseResponse(sb.toString());
    }
    // Método para parsear a resposta JSON da API e preencher um objeto WeatherData com os dados relevantes. Ele utiliza expressões regulares para extrair os valores necessários sem depender de bibliotecas externas.
    private WeatherData parseResponse(String json) {
        WeatherData data = new WeatherData();

        data.setResolvedAddress(extractString(json, "resolvedAddress"));

        String current = extractBlock(json, "currentConditions");
        data.setCurrentTemp(extractDouble(current, "temp"));
        data.setHumidity(extractDouble(current, "humidity"));
        data.setPrecipitation(extractDouble(current, "precip"));
        data.setWindSpeed(extractDouble(current, "windspeed"));
        data.setWindDirection(extractDouble(current, "winddir"));
        String rawCondition = extractString(current, "conditions");
        data.setCondition(CONDITION_PT.getOrDefault(rawCondition, rawCondition));

        String firstDay = extractFirstArrayItem(json, "days");
        data.setMaxTemp(extractDouble(firstDay, "tempmax"));
        data.setMinTemp(extractDouble(firstDay, "tempmin"));

        return data;
    }

    // ── Helpers de parsing JSON sem biblioteca externa ───────────────────────────
    /**
     * Extrai o valor de um objeto JSON identificado pela chave (retorna o bloco
     * entre { }).
     */
    private static String extractBlock(String json, String key) {
        String search = "\"" + key + "\"";
        int pos = json.indexOf(search);
        if (pos == -1) {
            return "{}";
        }
        int start = json.indexOf('{', pos + search.length());
        if (start == -1) {
            return "{}";
        }
        int depth = 0;
        for (int i = start; i < json.length(); i++) {
            char c = json.charAt(i);
            if (c == '{') {
                depth++; 
            }else if (c == '}') {
                depth--;
                if (depth == 0) {
                    return json.substring(start, i + 1);
            
                }}
        }
        return "{}";
    }

    /**
     * Extrai o primeiro elemento de um array JSON identificado pela chave.
     */
    private static String extractFirstArrayItem(String json, String key) {
        String search = "\"" + key + "\"";
        int pos = json.indexOf(search);
        if (pos == -1) {
            return "{}";
        }
        int arrStart = json.indexOf('[', pos + search.length());
        if (arrStart == -1) {
            return "{}";
        }
        int start = json.indexOf('{', arrStart);
        if (start == -1) {
            return "{}";
        }
        int depth = 0;
        for (int i = start; i < json.length(); i++) {
            char c = json.charAt(i);
            if (c == '{') {
                depth++; 
            }else if (c == '}') {
                depth--;
                if (depth == 0) {
                    return json.substring(start, i + 1);
            
                }}
        }
        return "{}";
    }

    /**
     * Extrai um valor numérico de um bloco JSON pela chave.
     */
    private static double extractDouble(String json, String key) {
        Pattern p = Pattern.compile("\"" + Pattern.quote(key) + "\"\\s*:\\s*([0-9.\\-]+)");
        Matcher m = p.matcher(json);
        if (m.find()) {
            try {
                return Double.parseDouble(m.group(1));
            } catch (NumberFormatException ignored) {
            }
        }
        return Double.NaN;
    }

    /**
     * Extrai um valor de string de um bloco JSON pela chave.
     */
    private static String extractString(String json, String key) {
        Pattern p = Pattern.compile("\"" + Pattern.quote(key) + "\"\\s*:\\s*\"([^\"]*)\"");
        Matcher m = p.matcher(json);
        if (m.find()) {
            return m.group(1);
        }
        return "";
    }

    /**
     * Converts wind direction in degrees to a Portuguese compass label.
     */
    public static String degreesToCompass(double degrees) {
        if (Double.isNaN(degrees)) {
            return "—";
        }
        String[] points = {"N", "NNE", "NE", "ENE", "L", "ESE", "SE", "SSE",
            "S", "SSO", "SO", "OSO", "O", "ONO", "NO", "NNO"};
        int index = (int) Math.round(degrees / 22.5) % 16;
        return points[index] + " (" + (int) degrees + "°)";
    }
}
