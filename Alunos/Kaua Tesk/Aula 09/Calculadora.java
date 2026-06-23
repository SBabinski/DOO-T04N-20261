
public class Calculadora {

    public double converterNumero(String texto, String nomeCampo) throws CalculadoraException {

      
        if (texto == null || texto.trim().isEmpty()) {
          
            throw new CalculadoraException(
                "O campo \"" + nomeCampo + "\" não pode estar vazio!",
                CalculadoraException.CAMPO_VAZIO
            );
        }

        try {
            
            return Double.parseDouble(texto.trim()); 

        } catch (NumberFormatException e) {
         
            throw new CalculadoraException(
                "\"" + texto.trim() + "\" não é um número válido! Use apenas dígitos.",
                CalculadoraException.ENTRADA_INVALIDA
            );
        }
    }

    public double calcular(double num1, double num2, String operacao) throws CalculadoraException {

        
        switch (operacao) {
            case "+":
                return num1 + num2;

            case "-":
                return num1 - num2;

            case "×":
                return num1 * num2;

            case "÷":
                
                if (num2 == 0) {
                    throw new CalculadoraException(
                        "Divisão por zero não é permitida!\nO segundo número não pode ser 0.",
                        CalculadoraException.DIVISAO_POR_ZERO
                    );
                }
                return num1 / num2;

            default:
              
                throw new CalculadoraException(
                    "Operação desconhecida: " + operacao,
                    "ERR_OPERACAO_INVALIDA"
                );
        }
    }

  
    public String formatarResultado(double valor) {
      
        if (valor == Math.floor(valor) && !Double.isInfinite(valor)) {
            return String.valueOf((long) valor); 
        }
    
        return String.format("%.6f", valor).replaceAll("0+$", "").replaceAll("\\.$", "");
    }
}
