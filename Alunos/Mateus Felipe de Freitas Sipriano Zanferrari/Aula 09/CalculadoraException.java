/**
 * Exceção personalizada para erros específicos da Calculadora.
 * Herda de Exception (checked exception) para forçar tratamento explícito.
 */
public class CalculadoraException extends Exception {

    public enum TipoErro {
        DIVISAO_POR_ZERO,
        ENTRADA_INVALIDA,
        OVERFLOW_NUMERICO,
        CAMPO_VAZIO
    }

    private final TipoErro tipoErro;

    public CalculadoraException(String mensagem, TipoErro tipoErro) {
        super(mensagem);
        this.tipoErro = tipoErro;
    }

    public CalculadoraException(String mensagem, TipoErro tipoErro, Throwable causa) {
        super(mensagem, causa);
        this.tipoErro = tipoErro;
    }

    public TipoErro getTipoErro() {
        return tipoErro;
    }

    public static CalculadoraException divisaoPorZero() {
        return new CalculadoraException(
            "Erro: Divisão por zero não é permitida!",
            TipoErro.DIVISAO_POR_ZERO
        );
    }

    public static CalculadoraException entradaInvalida(String valorInvalido) {
        return new CalculadoraException(
            "Erro: \"" + valorInvalido + "\" não é um número válido!",
            TipoErro.ENTRADA_INVALIDA
        );
    }

    public static CalculadoraException campoVazio(String nomeCampo) {
        return new CalculadoraException(
            "Erro: O campo \"" + nomeCampo + "\" está vazio!",
            TipoErro.CAMPO_VAZIO
        );
    }

    public static CalculadoraException overflowNumerico() {
        return new CalculadoraException(
            "Erro: O resultado é grande demais para ser exibido!",
            TipoErro.OVERFLOW_NUMERICO
        );
    }

    @Override
    public String toString() {
        return "CalculadoraException [tipo=" + tipoErro + ", mensagem=" + getMessage() + "]";
    }
}