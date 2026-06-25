import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class Aula11Clima {

    private static final String API_KEY = "RGXL94JF4DLCAMGG7ZSDWYPCA";
    private static final String BASE_URL = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Informe a cidade ou localização (ex: Sao Paulo, Brazil): ");
            String location = scanner.nextLine().trim();

            if (location.isEmpty()) {
                System.out.println("Cidade não pode ser vazia. Tente novamente.");
                return;
            }
            if (API_KEY.isBlank() || API_KEY.contains("COLOQUE_SUA_CHAVE")) {
                System.out.println("ERRO: Configure sua chave de API em Aula11Clima.API_KEY antes de executar.");
                return;
            }

            String response = fetchWeather(location);
            if (response == null) {
                System.out.println("Não foi possível obter os dados do clima.");
                return;
            }

            WeatherInfo info = parseWeather(response);
            if (info == null) {
                System.out.println("Resposta inválida da API. Verifique a chave e a cidade informada.");
                return;
            }

            printWeather(info, location);
        } finally {
            scanner.close();
        }
    }

    private static String fetchWeather(String location) {
        try {
            String encodedLocation = URLEncoder.encode(location, StandardCharsets.UTF_8);
            String url = BASE_URL + encodedLocation + "/today?unitGroup=metric&include=current,days&contentType=json&key=" + API_KEY;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return response.body();
            } else {
                System.out.println("Erro HTTP: " + response.statusCode());
                System.out.println("Resposta: " + response.body());
                return null;
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Falha ao consultar a API: " + e.getMessage());
            return null;
        }
    }

    private static WeatherInfo parseWeather(String json) {
        String currentSection = extractSection(json, "currentConditions");
        String daySection = extractFirstDay(json);
        if (currentSection == null || daySection == null) {
            return null;
        }

        WeatherInfo info = new WeatherInfo();
        info.temperature = parseDouble(currentSection, "temp");
        info.humidity = parseDouble(currentSection, "humidity");
        info.conditions = parseString(currentSection, "conditions");
        info.precipitation = parseDouble(currentSection, "precip");
        info.windSpeed = parseDouble(currentSection, "windspeed");
        info.windDirectionDegrees = parseDouble(currentSection, "winddir");

        info.tempMax = parseDouble(daySection, "tempmax");
        info.tempMin = parseDouble(daySection, "tempmin");
        info.dayPrecipitation = parseDouble(daySection, "precip");

        return info;
    }

    private static String extractSection(String json, String key) {
        int start = json.indexOf('"' + key + '"');
        if (start < 0) {
            return null;
        }
        int braceStart = json.indexOf('{', start);
        if (braceStart < 0) {
            return null;
        }
        int braceEnd = findMatchingBrace(json, braceStart);
        if (braceEnd < 0) {
            return null;
        }
        return json.substring(braceStart, braceEnd + 1);
    }

    private static String extractFirstDay(String json) {
        int index = json.indexOf("\"days\"");
        if (index < 0) {
            return null;
        }
        int arrayStart = json.indexOf('[', index);
        if (arrayStart < 0) {
            return null;
        }
        int objectStart = json.indexOf('{', arrayStart);
        if (objectStart < 0) {
            return null;
        }
        int objectEnd = findMatchingBrace(json, objectStart);
        if (objectEnd < 0) {
            return null;
        }
        return json.substring(objectStart, objectEnd + 1);
    }

    private static int findMatchingBrace(String text, int startIndex) {
        int depth = 0;
        for (int i = startIndex; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == '{') {
                depth++;
            } else if (c == '}') {
                depth--;
                if (depth == 0) {
                    return i;
                }
            }
        }
        return -1;
    }

    private static String parseString(String json, String key) {
        String token = '"' + key + '"';
        int idx = json.indexOf(token);
        if (idx < 0) {
            return "";
        }
        int colon = json.indexOf(':', idx);
        if (colon < 0) {
            return "";
        }
        int quoteStart = json.indexOf('"', colon + 1);
        if (quoteStart < 0) {
            return "";
        }
        int quoteEnd = json.indexOf('"', quoteStart + 1);
        if (quoteEnd < 0) {
            return "";
        }
        return json.substring(quoteStart + 1, quoteEnd);
    }

    private static double parseDouble(String json, String key) {
        String token = '"' + key + '"';
        int idx = json.indexOf(token);
        if (idx < 0) {
            return Double.NaN;
        }
        int colon = json.indexOf(':', idx);
        if (colon < 0) {
            return Double.NaN;
        }
        int start = colon + 1;
        while (start < json.length() && (json.charAt(start) == ' ' || json.charAt(start) == '\"')) {
            start++;
        }
        int end = start;
        while (end < json.length() && (Character.isDigit(json.charAt(end)) || json.charAt(end) == '.' || json.charAt(end) == '-' || json.charAt(end) == '+')) {
            end++;
        }
        try {
            return Double.parseDouble(json.substring(start, end));
        } catch (NumberFormatException e) {
            return Double.NaN;
        }
    }

    private static void printWeather(WeatherInfo info, String location) {
        System.out.println("\nClima para: " + location);
        System.out.printf("Temperatura agora: %.1f °C\n", info.temperature);
        System.out.printf("Temperatura máxima do dia: %.1f °C\n", info.tempMax);
        System.out.printf("Temperatura mínima do dia: %.1f °C\n", info.tempMin);
        System.out.printf("Humidade: %.0f%%\n", info.humidity);
        System.out.println("Condição: " + (info.conditions.isBlank() ? "Desconhecida" : info.conditions));
        System.out.printf("Precipitação atual: %.1f mm\n", info.precipitation);
        System.out.printf("Precipitação no dia: %.1f mm\n", info.dayPrecipitation);
        System.out.printf("Velocidade do vento: %.1f km/h\n", info.windSpeed);
        System.out.println("Direção do vento: " + formatWindDirection(info.windDirectionDegrees));
    }

    private static String formatWindDirection(double degrees) {
        if (Double.isNaN(degrees)) {
            return "Desconhecida";
        }
        String[] directions = {"N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE", "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW", "N"};
        int index = (int) Math.round(((degrees % 360 + 360) % 360) / 22.5);
        return (int) degrees + "° (" + directions[index] + ")";
    }

    private static class WeatherInfo {
        double temperature;
        double tempMax;
        double tempMin;
        double humidity;
        String conditions;
        double precipitation;
        double dayPrecipitation;
        double windSpeed;
        double windDirectionDegrees;
    }
}
