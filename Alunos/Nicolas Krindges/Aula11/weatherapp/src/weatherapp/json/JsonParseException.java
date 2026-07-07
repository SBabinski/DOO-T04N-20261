package weatherapp.json;

/**
 * Excecao lancada quando o texto JSON recebido esta mal formado
 * ou nao pode ser interpretado pelo {@link JsonParser}.
 */
public class JsonParseException extends RuntimeException {

    public JsonParseException(String message) {
        super(message);
    }
}
