package aula07;

public class Gerente extends Pessoa {
    private double salarioBase;
    private double[] salarioRecebido = {3500.0, 3800.0, 4200.0};

    public Gerente(String nome, int idade, String loja, Endereco endereco, double salarioBase) {
        super(nome, idade, loja, endereco);
        this.salarioBase = salarioBase;
    }

    @Override
    public void apresentarse() {
        System.out.println("Gerente: " + nome + " | Idade: " + idade + " | Loja: " + loja);
    }

    public double calcularMedia() {
        double soma = 0;
        for (double s : salarioRecebido) soma += s;
        return soma / salarioRecebido.length;
    }

    public double calcularBonus() {
        return salarioBase * 0.35;
    }
}
