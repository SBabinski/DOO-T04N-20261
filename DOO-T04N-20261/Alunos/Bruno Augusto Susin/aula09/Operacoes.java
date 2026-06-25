package objects;

public class Operacoes {

    public static double somar(double num1, double num2) {
        return num1 + num2;
    }

    public static double subtrair(double num1, double num2) {
        return num1 - num2;
    }

    public static double multiplicar(double num1, double num2) {
        return num1 * num2;
    }

    public static double dividir(double num1, double num2) throws CalculadoraException {
        if (num2 == 0) {
            throw new CalculadoraException("Nao e possivel dividir por zero.");
        }

        return num1 / num2;
    }
}