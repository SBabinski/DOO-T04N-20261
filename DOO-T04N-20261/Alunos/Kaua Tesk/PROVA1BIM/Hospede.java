//guardar as inf do hospede
public class Hospede {

    
    private String nome;      
    private String cpf;       
    private String telefone;  

    public Hospede(String nome, String cpf, String telefone) {
        this.nome = nome;           
        this.cpf = cpf;        
        this.telefone = telefone;
    }


    public String getNome() {
        return nome; 
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void exibirDados() {
        System.out.println("=== Dados do Hóspede ===");
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Telefone: " + telefone);
    }
}
