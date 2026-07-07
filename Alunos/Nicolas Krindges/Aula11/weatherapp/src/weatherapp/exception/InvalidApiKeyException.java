package weatherapp.exception;

/**
 * Excecao especifica lancada quando a chave de API e invalida,
 * expirou ou o limite gratuito de requisicoes foi atingido (HTTP 401/429).
 */
public class InvalidApiKeyException extends WeatherApiException {

    public InvalidApiKeyException(String message) {
        super(message);
    }
}
