import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class WeatherService {

    private static final String BASE_URL =
            "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";

    private final String apiKey;
    private final HttpClient httpClient;

    public WeatherService(String apiKey) {
        this.apiKey = apiKey;
        this.httpClient = HttpClient.newHttpClient();
    }

    public WeatherReport findTodayWeather(String city) throws IOException, InterruptedException {
        String url = buildUrl(city);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() < 200 || response.statusCode() >= 300) {
            throw new IOException("API retornou HTTP " + response.statusCode() + ": " + response.body());
        }

        return parseResponse(response.body());
    }

    private String buildUrl(String city) {
        return BASE_URL
                + encodePath(city)
                + "/today"
                + "?unitGroup=metric"
                + "&lang=pt"
                + "&include=current,days"
                + "&elements=datetime,temp,tempmax,tempmin,humidity,conditions,precip,windspeed,winddir"
                + "&contentType=json"
                + "&key="
                + encodeQuery(apiKey);
    }

    private WeatherReport parseResponse(String json) {
        String currentJson = JsonUtils.extractObject(json, "currentConditions");
        String daysJson = JsonUtils.extractArray(json, "days");
        String todayJson = JsonUtils.firstObjectFromArray(daysJson);

        CurrentWeather currentWeather = new CurrentWeather(
                JsonUtils.extractDouble(currentJson, "temp"),
                JsonUtils.extractDouble(currentJson, "humidity"),
                JsonUtils.extractString(currentJson, "conditions"),
                JsonUtils.extractDouble(currentJson, "precip"),
                JsonUtils.extractDouble(currentJson, "windspeed"),
                JsonUtils.extractDouble(currentJson, "winddir")
        );

        DailyWeather todayWeather = new DailyWeather(
                JsonUtils.extractDouble(todayJson, "tempmax"),
                JsonUtils.extractDouble(todayJson, "tempmin")
        );

        return new WeatherReport(
                JsonUtils.extractString(json, "resolvedAddress"),
                JsonUtils.extractString(json, "timezone"),
                currentWeather,
                todayWeather
        );
    }

    private String encodePath(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8).replace("+", "%20");
    }

    private String encodeQuery(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }
}
