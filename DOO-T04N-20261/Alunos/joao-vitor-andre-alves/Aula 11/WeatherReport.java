public class WeatherReport {

    private final String resolvedAddress;
    private final String timezone;
    private final CurrentWeather currentWeather;
    private final DailyWeather todayWeather;

    public WeatherReport(
            String resolvedAddress,
            String timezone,
            CurrentWeather currentWeather,
            DailyWeather todayWeather
    ) {
        this.resolvedAddress = resolvedAddress;
        this.timezone = timezone;
        this.currentWeather = currentWeather;
        this.todayWeather = todayWeather;
    }

    public String getResolvedAddress() {
        return resolvedAddress;
    }

    public String getTimezone() {
        return timezone;
    }

    public CurrentWeather getCurrentWeather() {
        return currentWeather;
    }

    public DailyWeather getTodayWeather() {
        return todayWeather;
    }
}
