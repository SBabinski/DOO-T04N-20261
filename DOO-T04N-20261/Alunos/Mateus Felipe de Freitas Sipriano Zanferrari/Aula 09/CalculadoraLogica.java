
/**
 * Lógica das operações matemáticas da Calculadora.
 * Separação de responsabilidades: model separado da view (MVC).
 */
public class CalculadoraLogica {

    private static final double VALOR_MAXIMO = 1e15;

    /**
     * Realiza a operação matemática entre dois valores em String.
     *
     * @param valor1   Primeiro operando
     * @param valor2   Segundo operando
     * @param operacao Símbolo: +  -  ×  ÷
     * @return Resultado formatado como String
     * @throws CalculadoraException em caso de erro nos dados ou operação
     */
    public String calcular(String valor1, String valor2, String operacao)
            throws CalculadoraException {

        if (valor1 == null || valor1.trim().isEmpty()) {
            throw CalculadoraException.campoVazio("Número 1");
        }
        if (valor2 == null || valor2.trim().isEmpty()) {
            throw CalculadoraException.campoVazio("Número 2");
        }

        double num1 = parseDouble(valor1.trim());
        double num2 = parseDouble(valor2.trim());

        double resultado;

        switch (operacao) {
            case "+": resultado = num1 + num2; break;
            case "-": resultado = num1 - num2; break;
            case "×": resultado = num1 * num2; break;
            case "÷": resultado = dividir(num1, num2); break;
            default:
                throw new CalculadoraException(
                    "Operação desconhecida: " + operacao,
                    CalculadoraException.TipoErro.ENTRADA_INVALIDA
                );
        }

        if (Double.isInfinite(resultado) || Math.abs(resultado) > VALOR_MAXIMO) {
            throw CalculadoraException.overflowNumerico();
        }
        if (Double.isNaN(resultado)) {
            throw new CalculadoraException(
                "Resultado indefinido!",
                CalculadoraException.TipoErro.OVERFLOW_NUMERICO
            );
        }

        return formatarResultado(resultado);
    }

    private double parseDouble(String valor) throws CalculadoraException {
        try {
            return Double.parseDouble(valor);
        } catch (NumberFormatException e) {
            throw CalculadoraException.entradaInvalida(valor);
        }
    }

    private double dividir(double a, double b) throws CalculadoraException {
        if (b == 0.0) {
            throw CalculadoraException.divisaoPorZero();
        }
        return a / b;
    }

    private String formatarResultado(double resultado) {
        if (resultado == Math.floor(resultado) && Math.abs(resultado) < 1e12) {
            return String.valueOf((long) resultado);
        }
        String formatado = String.format("%.10f", resultado);
        formatado = formatado.replaceAll("0+$", "").replaceAll("\\.$", "");
        return formatado;
    }
}