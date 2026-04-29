public class Cliente {
    String nome;
    int idade;
    String cidade;
    String bairro;
    String rua;

    public static void apresentarCliente() {
        if (Loja.clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        for (Cliente c : Loja.clientes) {
            c.exibirDadosCliente();
        }
    }

    public void exibirDadosCliente() {  
        System.out.println("Nome do cliente: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("-----------------------------");
    }   
}
