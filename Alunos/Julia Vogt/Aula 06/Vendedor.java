package Calculadora;

import java.util.ArrayList;

public class Vendedor {

    String nome;
    int idade;
    Loja loja;
    String cidade;
    String bairro;
    String rua;
    float salarioBase;

    ArrayList<Float> salarioRecebido = new ArrayList<>();

    public Vendedor(String nome, int idade, Loja loja, String cidade, String bairro, String rua, float salarioBase) {

        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.salarioBase = salarioBase;

        salarioRecebido.add(1500f);
        salarioRecebido.add(1600f);
        salarioRecebido.add(1700f);
    }

    public void apresentarSe() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Loja: " + loja.nomeFantasia);
    }

    public void calcularMedia() {

        float soma = 0;

        for (float salario : salarioRecebido) {
            soma += salario;
        }

        float media = soma / salarioRecebido.size();

        System.out.println("Média dos salários: " + media);
    }

    public void calcularBonus() {

        float bonus = salarioBase * 0.2f;

        System.out.println("Bônus: " + bonus);
    }
}