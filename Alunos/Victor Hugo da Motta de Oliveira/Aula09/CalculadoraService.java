public class CalculadoraService {
    
    public double calcular(String N1, String N2, String operacao) throws CalculadoraException, NumberFormatException {
        double num1 = validarEntrada(N1);
        double num2 = validarEntrada(N2);

        switch (operacao) {
            case "+": 
                return num1 + num2;
            case "-": 
                return num1 - num2;
            case "*": 
                return num1 * num2;
            case "/": 
                if (num2 == 0) {
                    throw new CalculadoraException("Erro: Divisão por zero!");
                }   
                return num1 / num2;
            default: 
                throw new CalculadoraException("Operação inválida!");
        }
    }
    private double validarEntrada(String texto) throws NumberFormatException {
        if (texto == null || texto.trim().isEmpty()) {
            throw new NumberFormatException("Erro: Campo Vazio!");
        } 
        return Double.parseDouble(texto.replace(",", "."));
    }
}