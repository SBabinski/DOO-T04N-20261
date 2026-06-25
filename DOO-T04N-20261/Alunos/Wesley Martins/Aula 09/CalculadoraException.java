/**
 * Exceção personalizada para tratamento de erros específicos da Calculadora
 * Trata situações como divisão por zero e entrada inválida
 */
public class CalculadoraException extends Exception {
    
    // Tipos de erros da calculadora
    public static final String ENTRADA_INVALIDA = "Entrada inválida: um ou ambos os números contêm caracteres não numéricos.";
    public static final String DIVISAO_POR_ZERO = "Erro: Divisão por zero não é permitida.";
    public static final String CAMPOS_VAZIOS = "Erro: Os campos de entrada não podem estar vazios.";
    public static final String OPERACAO_INVALIDA = "Erro: Operação inválida selecionada.";
    
    /**
     * Construtor que recebe uma mensagem de erro
     * @param mensagem A mensagem descritiva do erro
     */
    public CalculadoraException(String mensagem) {
        super(mensagem);
    }
    
    /**
     * Construtor que recebe uma mensagem e uma causa
     * @param mensagem A mensagem descritiva do erro
     * @param causa A exceção que causou este erro
     */
    public CalculadoraException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
