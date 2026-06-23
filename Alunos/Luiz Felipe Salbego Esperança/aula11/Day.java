import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Day {

    private double tempmax;
    private double tempmin;

    public Day() {
    }

    public double getTempmax() {
        return tempmax;
    }

    public double getTempmin() {
        return tempmin;
    }
}