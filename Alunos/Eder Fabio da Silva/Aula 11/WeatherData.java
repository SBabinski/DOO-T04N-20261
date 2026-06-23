package com.weather;

public class WeatherData {
    
    // Esta classe representa os dados meteorológicos atuais para um local específico.
    private String resolvedAddress;
    private double currentTemp;
    private double maxTemp;
    private double minTemp;
    private double humidity;
    private String condition;
    private double precipitation;
    private double windSpeed;
    private double windDirection;
    
    // Métodos getters e setters para acessar e modificar os campos privados da classe.
    public String getResolvedAddress() { return resolvedAddress; }
    public void setResolvedAddress(String resolvedAddress) { this.resolvedAddress = resolvedAddress; }

    public double getCurrentTemp() { return currentTemp; }
    public void setCurrentTemp(double currentTemp) { this.currentTemp = currentTemp; }

    public double getMaxTemp() { return maxTemp; }
    public void setMaxTemp(double maxTemp) { this.maxTemp = maxTemp; }

    public double getMinTemp() { return minTemp; }
    public void setMinTemp(double minTemp) { this.minTemp = minTemp; }

    public double getHumidity() { return humidity; }
    public void setHumidity(double humidity) { this.humidity = humidity; }

    public String getCondition() { return condition; }
    public void setCondition(String condition) { this.condition = condition; }

    public double getPrecipitation() { return precipitation; }
    public void setPrecipitation(double precipitation) { this.precipitation = precipitation; }

    public double getWindSpeed() { return windSpeed; }
    public void setWindSpeed(double windSpeed) { this.windSpeed = windSpeed; }

    public double getWindDirection() { return windDirection; }
    public void setWindDirection(double windDirection) { this.windDirection = windDirection; }
}
