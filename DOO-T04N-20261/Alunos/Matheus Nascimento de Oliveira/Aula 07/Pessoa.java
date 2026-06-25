public class Pessoa {
    String nome;
    int idade;
    String loja;
    Endereco endereco;

    public Pessoa(String nome, int idade, String loja, Endereco endereco) {
        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.endereco = endereco;
    }

    public void apresentarse() {
        System.out.println("Nome: " + nome + " | Loja: " + loja);
    }
}