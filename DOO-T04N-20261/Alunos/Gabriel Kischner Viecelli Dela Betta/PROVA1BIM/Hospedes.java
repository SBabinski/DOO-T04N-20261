public class Hospedes {

    public String nome;
    public String cpf;
    public String telefone;


    public Hospedes(String nome, String cpf , String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
      
    }

    public void apresentarse() {
        System.out.println("Nome: " + nome + " | Cpf: " + cpf + "Telefone: " + telefone);
    }
    
}
