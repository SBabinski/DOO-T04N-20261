public class Cliente extends Pessoa {
 
    public Cliente(String nome, int idade, Endereco endereco) {
        super(nome, idade, endereco);
    }
 
    @Override
    public void apresentarse() {
        apresentarCliente();
    }
 
    // Mantido para compatibilidade com chamadas existentes no MyPlant.java
    public void apresentarCliente() {
        System.out.println("Nome do cliente: " + nome);
        System.out.println("Idade: " + idade + " anos");
        endereco.apresentarLogradouro();
    }
}