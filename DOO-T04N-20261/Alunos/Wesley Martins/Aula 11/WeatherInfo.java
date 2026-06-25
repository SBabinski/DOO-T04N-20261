public class WeatherInfo {
    private final String location;
    private final double temperature;
    private final double tempMax;
    private final double tempMin;
    private final double humidity;
    private final String condition;
    private final double precipitation;
    private final double windSpeed;
    private final double windDirection;

    public WeatherInfo(
        String location,
        double temperature,
        double tempMax,
        double tempMin,
        double humidity,
        String condition,
        double precipitation,
        double windSpeed,
        double windDirection
    ) {
        this.location = location;
        this.temperature = temperature;
        this.tempMax = tempMax;
        this.tempMin = tempMin;
        this.humidity = humidity;
        this.condition = condition;
        this.precipitation = precipitation;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
    }

    public String getLocation() {
        return location;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getTempMax() {
        return tempMax;
    }

    public double getTempMin() {
        return tempMin;
    }

    public double getHumidity() {
        return humidity;
    }

    public String getCondition() {
        return condition;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getWindDirection() {
        return windDirection;
    }
}
