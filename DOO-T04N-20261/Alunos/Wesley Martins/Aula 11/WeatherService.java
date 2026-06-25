import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class WeatherService {
    private static final String CONFIG_FILE = "config.properties";
    private static final String BASE_URL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";

    public static WeatherInfo fetchWeather(String city) throws IOException, InterruptedException {
        String apiKey = readApiKey();
        if (apiKey.isBlank()) {
            throw new IOException("API key não encontrada.");
        }

        String url = buildUrl(city, apiKey);
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .GET()
            .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Falha ao consultar clima: HTTP " + response.statusCode() + "\n" + response.body());
        }

        return parseWeather(response.body());
    }

    private static String readApiKey() {
        try {
            Path path = Path.of(CONFIG_FILE);
            if (Files.exists(path)) {
                Properties props = new Properties();
                try (InputStream in = new FileInputStream(CONFIG_FILE)) {
                    props.load(in);
                }
                String key = props.getProperty("api.key");
                if (key != null && !key.isBlank()) {
                    return key.trim();
                }
            }
        } catch (IOException ignored) {
        }

        String envKey = System.getenv("WEATHER_API_KEY");
        return envKey == null ? "" : envKey.trim();
    }

    private static String buildUrl(String city, String apiKey) {
        String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
        return BASE_URL + encodedCity + "?unitGroup=metric&include=current&key=" + apiKey + "&contentType=json";
    }

    private static WeatherInfo parseWeather(String json) throws IOException {
        String location = extractJsonString(json, "resolvedAddress");
        String currentBlock = extractJsonSection(json, "currentConditions");
        String dayBlock = extractFirstArrayObject(json, "days");

        double temperature = extractJsonNumber(currentBlock, "temp");
        double humidity = extractJsonNumber(currentBlock, "humidity");
        String condition = extractJsonString(currentBlock, "conditions");
        double precipitation = extractJsonNumber(currentBlock, "precip");
        double windSpeed = extractJsonNumber(currentBlock, "windspeed");
        double windDirection = extractJsonNumber(currentBlock, "winddir");

        double tempMax = extractJsonNumber(dayBlock, "tempmax");
        double tempMin = extractJsonNumber(dayBlock, "tempmin");

        return new WeatherInfo(
            location,
            temperature,
            tempMax,
            tempMin,
            humidity,
            condition,
            precipitation,
            windSpeed,
            windDirection
        );
    }

    private static String extractJsonSection(String json, String key) throws IOException {
        int index = json.indexOf('"' + key + '"');
        if (index < 0) {
            throw new IOException("Campo JSON '" + key + "' não encontrado.");
        }

        int braceStart = json.indexOf('{', index);
        if (braceStart < 0) {
            throw new IOException("Bloco JSON para '" + key + "' não encontrado.");
        }

        int depth = 0;
        for (int i = braceStart; i < json.length(); i++) {
            char c = json.charAt(i);
            if (c == '{') {
                depth++;
            } else if (c == '}') {
                depth--;
                if (depth == 0) {
                    return json.substring(braceStart, i + 1);
                }
            }
        }

        throw new IOException("Não foi possível extrair o bloco JSON para '" + key + "'.");
    }

    private static String extractFirstArrayObject(String json, String key) throws IOException {
        int index = json.indexOf('"' + key + '"');
        if (index < 0) {
            throw new IOException("Array JSON '" + key + "' não encontrado.");
        }

        int bracketStart = json.indexOf('[', index);
        if (bracketStart < 0) {
            throw new IOException("Array JSON para '" + key + "' não encontrado.");
        }

        int braceStart = json.indexOf('{', bracketStart);
        if (braceStart < 0) {
            throw new IOException("Objeto dentro do array '" + key + "' não encontrado.");
        }

        int depth = 0;
        for (int i = braceStart; i < json.length(); i++) {
            char c = json.charAt(i);
            if (c == '{') {
                depth++;
            } else if (c == '}') {
                depth--;
                if (depth == 0) {
                    return json.substring(braceStart, i + 1);
                }
            }
        }

        throw new IOException("Não foi possível extrair o primeiro objeto do array '" + key + "'.");
    }

    private static String extractJsonString(String json, String key) throws IOException {
        int index = json.indexOf('"' + key + '"');
        if (index < 0) {
            throw new IOException("Campo JSON '" + key + "' não encontrado.");
        }

        int colon = json.indexOf(':', index);
        if (colon < 0) {
            throw new IOException("Delimitador ':' não encontrado para o campo '" + key + "'.");
        }

        int quoteStart = json.indexOf('"', colon);
        if (quoteStart < 0) {
            throw new IOException("Aspas de abertura não encontradas para o campo '" + key + "'.");
        }

        int quoteEnd = json.indexOf('"', quoteStart + 1);
        if (quoteEnd < 0) {
            throw new IOException("Aspas de fechamento não encontradas para o campo '" + key + "'.");
        }

        return json.substring(quoteStart + 1, quoteEnd);
    }

    private static double extractJsonNumber(String json, String key) throws IOException {
        int index = json.indexOf('"' + key + '"');
        if (index < 0) {
            throw new IOException("Campo JSON '" + key + "' não encontrado.");
        }

        int colon = json.indexOf(':', index);
        if (colon < 0) {
            throw new IOException("Delimitador ':' não encontrado para o campo '" + key + "'.");
        }

        int start = colon + 1;
        while (start < json.length() && Character.isWhitespace(json.charAt(start))) {
            start++;
        }

        int end = start;
        while (end < json.length()) {
            char c = json.charAt(end);
            if ((c >= '0' && c <= '9') || c == '.' || c == '-' || c == '+') {
                end++;
            } else {
                break;
            }
        }

        if (start == end) {
            return 0.0;
        }

        String numberText = json.substring(start, end);
        try {
            return Double.parseDouble(numberText);
        } catch (NumberFormatException e) {
            throw new IOException("Não foi possível converter o valor numérico do campo '" + key + "'.", e);
        }
    }
}
