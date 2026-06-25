package Aula09;

public class Operacoes {

    public static double somar(double a, double b) {
        return a + b;
    }

    public static double subtrair(double a, double b) {
        return a - b;
    }

    public static double multiplicar(double a, double b) {
        return a * b;
    }

    public static double dividir(double a, double b) throws CalculadoraException {
        if (b == 0) {
            throw new CalculadoraException("Erro: divisão por zero não é permitida.");
        }
        return a / b;
    }

    public static double converter(String valor) throws CalculadoraException {
        try {
            return Double.parseDouble(valor);
        } catch (NumberFormatException e) {
            throw new CalculadoraException("Entrada inválida! Digite apenas números.");
        }
    }
}