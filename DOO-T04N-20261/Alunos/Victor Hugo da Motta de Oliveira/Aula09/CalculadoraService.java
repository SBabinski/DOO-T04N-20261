public class CalculadoraService {
    public double calcular(double n1, double n2, String operacao) throws CalculadoraException {
        switch (operacao) {
            case "+": return n1 + n2;
            case "-": return n1 - n2;
            case "*": return n1 * n2;
            case "/":
                if (n2 == 0) throw new CalculadoraException("Impossível dividir por zero!");
                return n1 / n2;
            default:
                throw new CalculadoraException("Operação inválida!");
        }
    }
}