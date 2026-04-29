public class Hospede {
    private String nome;
    private String cpf;
    private String telefone;

    public Hospede(String nome, String cpf, String telefone){
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public void exibir() {
        System.out.println("Nome: "+nome + " | CPF: "+ cpf + " | Telefone: "+telefone);
    }

}