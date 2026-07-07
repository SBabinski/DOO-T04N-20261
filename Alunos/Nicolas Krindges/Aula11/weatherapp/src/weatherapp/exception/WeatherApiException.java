package weatherapp.exception;

/**
 * Excecao generica para falhas ao consultar a API de clima
 * (erros de rede, timeout, resposta invalida, etc).
 */
public class WeatherApiException extends Exception {

    public WeatherApiException(String message) {
        super(message);
    }

    public WeatherApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
