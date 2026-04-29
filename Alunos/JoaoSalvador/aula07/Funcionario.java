package calculadora_dona_gabrielinha;

import java.util.ArrayList;

public abstract class Funcionario extends Pessoa {

    // Atributos comuns a qualquer funcionário da loja
    protected Loja loja;
    protected double salarioBase;
    protected ArrayList<Double> salarioRecebido = new ArrayList<>();

    public Funcionario(String nome, int idade, Endereco endereco, Loja loja, double salarioBase) {
        super(nome, idade, endereco);
        this.loja = loja;
        this.salarioBase = salarioBase;
    }

    // Calcula a média dos salários recebidos — lógica igual para todos os funcionários
    public double calcularMedia() {
        double soma = 0;
        for (double salario : salarioRecebido) {
            soma += salario;
        }
        return soma / salarioRecebido.size();
    }

    // Cada cargo tem sua própria fórmula de bônus
    public abstract double calcularBonus();
}
