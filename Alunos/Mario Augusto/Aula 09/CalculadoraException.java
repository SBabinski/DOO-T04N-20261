/**
 * Exceção personalizada para tratar erros específicos da Calculadora.
 * Cobre situações como divisão por zero e entrada inválida.
 */
public class CalculadoraException extends Exception {

    // Tipos de erro possíveis
    public enum TipoErro {
        DIVISAO_POR_ZERO,
        ENTRADA_INVALIDA,
        CAMPO_VAZIO,
        OVERFLOW
    }

    private final TipoErro tipoErro;

    /**
     * Construtor que recebe mensagem e tipo do erro.
     *
     * @param mensagem Mensagem amigável ao usuário
     * @param tipoErro Categoria do erro ocorrido
     */
    public CalculadoraException(String mensagem, TipoErro tipoErro) {
        super(mensagem);
        this.tipoErro = tipoErro;
    }

    /**
     * Construtor com causa raiz encadeada.
     *
     * @param mensagem Mensagem amigável ao usuário
     * @param tipoErro Categoria do erro ocorrido
     * @param causa    Exceção original que gerou este erro
     */
    public CalculadoraException(String mensagem, TipoErro tipoErro, Throwable causa) {
        super(mensagem, causa);
        this.tipoErro = tipoErro;
    }

    /**
     * Retorna o tipo do erro.
     *
     * @return TipoErro correspondente ao problema identificado
     */
    public TipoErro getTipoErro() {
        return tipoErro;
    }

    @Override
    public String toString() {
        return "CalculadoraException [tipo=" + tipoErro + "] -> " + getMessage();
    }
}
