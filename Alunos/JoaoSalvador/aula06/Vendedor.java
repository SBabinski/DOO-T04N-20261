package calculadora_dona_gabrielinha;

import java.util.ArrayList;

public class Vendedor {

    // Variáveis
    String nome;
    int idade;
    Loja loja;
    String cidade;
    String bairro;
    String rua;
    double salarioBase;

    // Arrays
    ArrayList<Double> salarioRecebido = new ArrayList<>();

    // Molde de dados
    public Vendedor(String nome, int idade, Loja loja, String cidade, String bairro, String rua, double salarioBase) {
        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.salarioBase = salarioBase;

        // Histórico de salarios
        salarioRecebido.add(29000.0);
        salarioRecebido.add(3200.0);
        salarioRecebido.add(3600.0);
    }

    // Printa os dados do vendedor
    public void apresentarSe() {
        System.out.println("Nome: " + nome + ".");
        System.out.println("Idade: " + idade + ".");
        System.out.println("Loja: " + loja.nomeFantasia + ".");
    }

    // Calcula media salarial
    public double calcularMedia() {
        double soma = 0;
        for (double salario : salarioRecebido) {
            soma += salario;
        }
        return soma / salarioRecebido.size();
    }

    // Calcula o bonus de venda + salario
    public double calcularBonus() {
        return salarioBase * 0.2;
    }
}