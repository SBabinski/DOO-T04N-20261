public class DailyWeather {

    private final Double maxTemperature;
    private final Double minTemperature;

    public DailyWeather(Double maxTemperature, Double minTemperature) {
        this.maxTemperature = maxTemperature;
        this.minTemperature = minTemperature;
    }

    public Double getMaxTemperature() {
        return maxTemperature;
    }

    public Double getMinTemperature() {
        return minTemperature;
    }
}
