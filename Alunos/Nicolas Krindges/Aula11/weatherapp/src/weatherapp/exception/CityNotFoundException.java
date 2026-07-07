package weatherapp.exception;

/**
 * Excecao especifica lancada quando a cidade informada pelo usuario
 * nao e reconhecida pela API (HTTP 400 - localizacao invalida).
 */
public class CityNotFoundException extends WeatherApiException {

    public CityNotFoundException(String city) {
        super("Cidade nao encontrada: \"" + city + "\". Verifique o nome digitado.");
    }
}
