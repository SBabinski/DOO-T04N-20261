package Aula07;

public class Pessoa {

    String nome;
    int idade;
    Endereco endereco;

    public Pessoa(String nome, int idade, Endereco endereco) {
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
    }

    public void apresentarSe() {
        System.out.println(nome + " - " + idade);
        endereco.apresentarLogradouro();
    }
}
