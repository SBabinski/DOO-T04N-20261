package Alunos.Melissa_Ghellere.PROVA1BIM;

public class Hospede {
    private String nome;
    private String cpf;
    private String telefone;

    public Hospede(String nome, String cpf, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Hóspede: " + nome + " | CPF: " + cpf;
    }
}
