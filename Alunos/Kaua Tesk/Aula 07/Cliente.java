public class Cliente {

    String nome;
    int idade;
    Endereco endereco;

    Cliente(String nome, int idade, Endereco endereco) {
        this.nome     = nome;
        this.idade    = idade;
        this.endereco = endereco;
    }

    void apresentarse() {
        System.out.println("=== Cliente ===");
        System.out.println("Nome : " + nome);
        System.out.println("Idade: " + idade);
        endereco.apresentarLogradouro();
    }
}
