package Alunos.Melissa_Ghellere.aula06;

public class Cliente {
    String nome, cidade, bairro, rua;
    int idade;

    public Cliente(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public void apresentarse() {
        System.out.println("Cliente: " + nome + " | Idade: " + idade);
    }
}
