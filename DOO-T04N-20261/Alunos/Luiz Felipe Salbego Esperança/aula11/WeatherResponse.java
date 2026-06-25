import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {

    private List<Day> days;
    private CurrentConditions currentConditions;

    public WeatherResponse() {
    }

    public List<Day> getDays() {
        return days;
    }

    public CurrentConditions getCurrentConditions() {
        return currentConditions;
    }
}