
public class Calculadora {
   
    public double validarEntrada(String texto, String nomeCampo) throws CalculadoraException {
  
        if (texto == null || texto.trim().isEmpty()) {
            throw new CalculadoraException(
                "O campo '" + nomeCampo + "' está vazio. Por favor, digite um número.",
                CalculadoraException.TipoErro.CAMPO_VAZIO
            );
        }

        try {
            return Double.parseDouble(texto.trim());

        } catch (NumberFormatException e) {
            throw new CalculadoraException(
                "'" + texto + "' não é um número válido no campo '" + nomeCampo + "'.",
                CalculadoraException.TipoErro.ENTRADA_INVALIDA
            );
        }
    }


    public double calcular(double num1, double num2, String operacao) throws CalculadoraException {
        return switch (operacao) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "×" -> num1 * num2;
            case "÷" -> {
                if (num2 == 0) {
                    throw new CalculadoraException(
                        "Divisão por zero não é permitida!",
                        CalculadoraException.TipoErro.DIVISAO_POR_ZERO
                    );
                }
                yield num1 / num2; 
            }
            default -> throw new CalculadoraException(
                "Operação desconhecida: " + operacao,
                CalculadoraException.TipoErro.ENTRADA_INVALIDA
            );
        };
    }
    public String formatarResultado(double resultado) {
        if (resultado == Math.floor(resultado) && !Double.isInfinite(resultado)) {
            return String.valueOf((long) resultado); 
        }
        return String.format("%.10f", resultado).replaceAll("0+$", "").replaceAll("\\.$", "");
    }
}
