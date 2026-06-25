package Alunos.Melissa_Ghellere.aula06;

public class Vendedor {
    String nome, loja, cidade, bairro, rua;
    int idade;
    double salarioBase;
    double[] salarioRecebido = {2000.0, 2500.0, 2200.0};

    public Vendedor(String nome, int idade, String loja, double salarioBase) {
        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.salarioBase = salarioBase;
    }

    public void apresentarse() {
        System.out.println("Vendedor: " + nome + " | Idade: " + idade + " | Loja: " + loja);
    }

    public double calcularMedia() {
        return (salarioRecebido[0] + salarioRecebido[1] + salarioRecebido[2]) / 3;
    }

    public double calcularBonus() {
        return salarioBase * 0.2;
    }
}
