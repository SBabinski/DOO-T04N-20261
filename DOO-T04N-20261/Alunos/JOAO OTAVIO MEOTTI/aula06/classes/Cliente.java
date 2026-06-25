public class Cliente {
    String nome;
    int idade;
    String cidade;
    String bairro;
    String rua;
    public Cliente(String nome, int idade, String cidade, String bairro, String rua) {
        this.nome = nome;
        this.idade = idade;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
    }
    public void apresentarSe() {
        System.out.println("=== Dados do Cliente ===");
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Endereço: " + rua + ", " + bairro + " - " + cidade);
    }
    public void mostrarEndereco() {
        System.out.println("Endereço completo:");
        System.out.println(rua + ", " + bairro + " - " + cidade);
    }
    public boolean maiorDeIdade() {
        return idade >= 18;
    }
}