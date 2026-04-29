public class Cliente {

    String nome;
    int idade;
    String cidade;
    String bairro;
    String rua;

    Cliente(String nome, int idade, String cidade, String bairro, String rua) {
        this.nome   = nome;
        this.idade  = idade;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua    = rua;
    }

    void apresentarse() {
        System.out.println("=== Cliente ===");
        System.out.println("Nome : " + nome);
        System.out.println("Idade: " + idade);
    }
}
