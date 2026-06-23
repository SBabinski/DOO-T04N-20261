/**
 * Classe que implementa a lógica das operações matemáticas da calculadora
 * com tratamento de exceções personalizado
 */
public class LogicaCalculadora {
    
    /**
     * Valida se uma string é um número válido (inteiro ou decimal)
     * @param texto A string a ser validada
     * @return true se for um número válido, false caso contrário
     */
    private static boolean ehNumeroValido(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return false;
        }
        
        try {
            Double.parseDouble(texto.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    /**
     * Converte uma string para double com validação
     * @param texto A string a converter
     * @return O valor double
     * @throws CalculadoraException Se a string não for um número válido
     */
    private static double converterParaDouble(String texto) throws CalculadoraException {
        if (texto == null || texto.trim().isEmpty()) {
            throw new CalculadoraException(CalculadoraException.CAMPOS_VAZIOS);
        }
        
        if (!ehNumeroValido(texto)) {
            throw new CalculadoraException(CalculadoraException.ENTRADA_INVALIDA);
        }
        
        return Double.parseDouble(texto.trim());
    }
    
    /**
     * Realiza a adição de dois números
     * @param numero1String Primeiro número como string
     * @param numero2String Segundo número como string
     * @return O resultado da adição
     * @throws CalculadoraException Se alguma entrada for inválida
     */
    public static double adicionar(String numero1String, String numero2String) throws CalculadoraException {
        double numero1 = converterParaDouble(numero1String);
        double numero2 = converterParaDouble(numero2String);
        return numero1 + numero2;
    }
    
    /**
     * Realiza a subtração de dois números
     * @param numero1String Primeiro número como string
     * @param numero2String Segundo número como string
     * @return O resultado da subtração
     * @throws CalculadoraException Se alguma entrada for inválida
     */
    public static double subtrair(String numero1String, String numero2String) throws CalculadoraException {
        double numero1 = converterParaDouble(numero1String);
        double numero2 = converterParaDouble(numero2String);
        return numero1 - numero2;
    }
    
    /**
     * Realiza a multiplicação de dois números
     * @param numero1String Primeiro número como string
     * @param numero2String Segundo número como string
     * @return O resultado da multiplicação
     * @throws CalculadoraException Se alguma entrada for inválida
     */
    public static double multiplicar(String numero1String, String numero2String) throws CalculadoraException {
        double numero1 = converterParaDouble(numero1String);
        double numero2 = converterParaDouble(numero2String);
        return numero1 * numero2;
    }
    
    /**
     * Realiza a divisão de dois números
     * @param numero1String Dividendo como string
     * @param numero2String Divisor como string
     * @return O resultado da divisão
     * @throws CalculadoraException Se o divisor for zero ou a entrada for inválida
     */
    public static double dividir(String numero1String, String numero2String) throws CalculadoraException {
        double numero1 = converterParaDouble(numero1String);
        double numero2 = converterParaDouble(numero2String);
        
        if (numero2 == 0) {
            throw new CalculadoraException(CalculadoraException.DIVISAO_POR_ZERO);
        }
        
        return numero1 / numero2;
    }
    
    /**
     * Formata o resultado para exibição com até 2 casas decimais
     * @param resultado O resultado a ser formatado
     * @return A string formatada
     */
    public static String formatarResultado(double resultado) {
        // Se o resultado é um número inteiro, exibe sem casas decimais
        if (resultado == (long) resultado) {
            return String.format("%d", (long) resultado);
        }
        // Caso contrário, exibe com até 2 casas decimais
        return String.format("%.2f", resultado);
    }
}
