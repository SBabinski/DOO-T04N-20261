package doo;

public class Vendedor extends Pessoa {

    Loja loja;
    float salarioBase;
    float[] salarioRecebido = {1500f, 1600f, 1700f};

    public Vendedor(String nome, int idade, Loja loja, Endereco endereco, float salarioBase) {
        super(nome, idade, endereco);
        this.loja = loja;
        this.salarioBase = salarioBase;
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

        System.out.println("Média: " + (soma / salarioRecebido.length));
    }

    public void calcularBonus() {
        System.out.println("Bônus: " + (salarioBase * 0.2f));
    }
}