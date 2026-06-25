/**
 * Exceção personalizada para erros específicos da calculadora.
 */
public class CalculatorException extends Exception {

    public enum ErrorType {
        DIVISION_BY_ZERO,
        INVALID_INPUT,
        EMPTY_FIELD
    }

    private final ErrorType errorType;

    public CalculatorException(ErrorType errorType, String message) {
        super(message);
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    /**
     * Fábrica: lança exceção de divisão por zero.
     */
    public static CalculatorException divisionByZero() {
        return new CalculatorException(
            ErrorType.DIVISION_BY_ZERO,
            "Erro: Divisão por zero não é permitida."
        );
    }

    /**
     * Fábrica: lança exceção de entrada inválida.
     *
     * @param field nome do campo que contém o valor inválido
     */
    public static CalculatorException invalidInput(String field) {
        return new CalculatorException(
            ErrorType.INVALID_INPUT,
            "Erro: O campo \"" + field + "\" contém um valor não numérico."
        );
    }

    /**
     * Fábrica: lança exceção de campo vazio.
     *
     * @param field nome do campo vazio
     */
    public static CalculatorException emptyField(String field) {
        return new CalculatorException(
            ErrorType.EMPTY_FIELD,
            "Erro: O campo \"" + field + "\" não pode estar vazio."
        );
    }
}
