package hotel;

public class Hospede {

    private String nome;
    private String cpf;
    private String telefone;

    public Hospede(String nome, String cpf, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public void apresentarSe() {
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Telefone: " + telefone);
    }

    public String getNome() {
        return nome;
    }
}