//classe q vai ser extentida
public abstract class Pessoa {
    private String nome;
    private int idade;
    private Endereco endereco;

    public Pessoa() {}

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public int getIdade() { return idade; }
    public void setIdade(int idade) { this.idade = idade; }

    public Endereco getEndereco() { return endereco; }
    public void setEndereco(Endereco endereco) { this.endereco = endereco; }

    public void apresentarse() {
        System.out.println("Nome: " + nome + ", Idade: " + idade);
        if(endereco != null) endereco.apresentarLogradouro();
    }
}
