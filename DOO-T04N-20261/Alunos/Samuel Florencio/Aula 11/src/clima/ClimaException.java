package clima;
public class ClimaException extends Exception {

    public ClimaException(String mensagem) {
        super(mensagem);
    }

    public ClimaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}