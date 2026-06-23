package climatempo.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @JsonIgnoreProperties(ignoreUnknown = true) -> ignora os campos do JSON que
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Dia {

    @JsonProperty("tempmax")
    private double temperaturaMaxima;

    @JsonProperty("tempmin")
    private double temperaturaMinima;

    public double getTemperaturaMaxima() {
        return temperaturaMaxima;
    }

    public void setTemperaturaMaxima(double temperaturaMaxima) {
        this.temperaturaMaxima = temperaturaMaxima;
    }

    public double getTemperaturaMinima() {
        return temperaturaMinima;
    }

    public void setTemperaturaMinima(double temperaturaMinima) {
        this.temperaturaMinima = temperaturaMinima;
    }
}
