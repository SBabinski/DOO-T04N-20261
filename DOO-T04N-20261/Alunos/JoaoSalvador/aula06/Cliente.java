package calculadora_dona_gabrielinha;

public class Cliente {

    // Variáveis
    String nome;
    int idade;
    String cidade;
    String bairro;
    String rua;

    // Molde de dados
    public Cliente(String nome, int idade, String cidade, String bairro, String rua) {
        this.nome = nome;
        this.idade = idade;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
    }

    // Mostragem do cliente
    public void apresentarSe() {
        System.out.println("Nome: " + nome + ".");
        System.out.println("Idade: " + idade + ".");
    }
}