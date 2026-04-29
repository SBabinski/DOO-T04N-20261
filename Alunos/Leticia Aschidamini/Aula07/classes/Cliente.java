public class Cliente extends Pessoa {
    private Endereco endereco;

    public Cliente(String nome, int idade, String estado, String cidade, String bairro, String rua, int numero) {
        super(nome, idade);
        this.endereco = new Endereco(estado, cidade, bairro, rua, numero, "");
    }

    @Override
    public void apresentarSe() {
        System.out.println("Cliente - Nome: " + nome + ", Idade: " + idade);
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}