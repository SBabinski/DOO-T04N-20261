package service;

import exception.CalculadoraException;
import model.CalculadoraModel;

public class CalculadoraService {

    public double calcular(CalculadoraModel model, String operacao) throws CalculadoraException {
        double n1 = model.getPrimeiroNumero();
        double n2 = model.getSegundoNumero();

        switch (operacao) {
            case "+":
                return n1 + n2;

            case "-":
                return n1 - n2;

            case "*":
                return n1 * n2;

            case "/":
                if (n2 == 0) {
                    throw new CalculadoraException("Não é possível dividir por zero.");
                }
                return n1 / n2;

            default:
                throw new CalculadoraException("Operação inválida.");
        }
    }

    public double converterNumero(String texto) throws CalculadoraException {
        try {
            if (texto == null || texto.trim().isEmpty()) {
                throw new CalculadoraException("Preencha os dois campos numéricos.");
            }

            return Double.parseDouble(texto.trim().replace(",", "."));

        } catch (NumberFormatException e) {
            throw new CalculadoraException("Digite apenas números válidos.");
        }
    }
}