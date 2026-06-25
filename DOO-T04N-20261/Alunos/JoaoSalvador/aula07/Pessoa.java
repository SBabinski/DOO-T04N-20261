package calculadora_dona_gabrielinha;

public abstract class Pessoa {

    // Atributos comuns a qualquer pessoa
    protected String nome;
    protected int idade;
    protected Endereco endereco;

    public Pessoa(String nome, int idade, Endereco endereco) {
        this.nome = nome;
        this.idade = idade;
        this.endereco = endereco;
    }

    // Cada subclasse define sua própria apresentação
    public abstract void apresentarSe();

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }
}
