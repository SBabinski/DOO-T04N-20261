package doo;
import java.util.ArrayList;

public class Gerente extends Pessoa {

    Loja loja;
    float salarioBase;

    ArrayList<Float> salarioRecebido = new ArrayList<>();

    public Gerente(String nome, int idade, Loja loja, Endereco endereco, float salarioBase) {
        super(nome, idade, endereco);
        this.loja = loja;
        this.salarioBase = salarioBase;

        salarioRecebido.add(3000f);
        salarioRecebido.add(3200f);
        salarioRecebido.add(3500f);
    }

    public void apresentarSe() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Loja: " + loja.nomeFantasia);
    }

    public void calcularMedia() {
        float soma = 0;

        for (float s : salarioRecebido) {
            soma += s;
        }

        System.out.println("Média: " + (soma / salarioRecebido.size()));
    }

    public void calcularBonus() {
        System.out.println("Bônus: " + (salarioBase * 0.35f));
    }
}