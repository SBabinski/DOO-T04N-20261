public abstract class Pessoa {
    protected String nome;
    protected int idade;
    protected Endereco endereco;

    public void apresentarSe() {
        System.out.println("Nome: " + nome + " | Idade: " + idade);
        if (endereco != null) {
            endereco.apresentarEndereco();
        }
    }
}