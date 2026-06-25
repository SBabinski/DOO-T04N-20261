public class CurrentWeather {

    private final Double temperature;
    private final Double humidity;
    private final String condition;
    private final Double precipitation;
    private final Double windSpeed;
    private final Double windDirection;

    public CurrentWeather(
            Double temperature,
            Double humidity,
            String condition,
            Double precipitation,
            Double windSpeed,
            Double windDirection
    ) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.condition = condition;
        this.precipitation = precipitation == null ? 0.0 : precipitation;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
    }

    public Double getTemperature() {
        return temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public String getCondition() {
        return condition;
    }

    public Double getPrecipitation() {
        return precipitation;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public Double getWindDirection() {
        return windDirection;
    }
}
