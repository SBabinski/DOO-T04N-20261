public class Cliente extends Pessoa {
    
    // metodo para apresentar os clientes cadastrados, verificando se a lista de clientes da 
    // loja está vazia e exibindo os dados de cada cliente utilizando o método exibirDadosCliente
    public static void apresentarCliente() {
        if (Loja.clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        for (Cliente c : Loja.clientes) {
            c.exibirDadosCliente();
        }
    }
    //metodo para exibir os dados de um cliente, incluindo nome, idade e endereço, utilizando o
    //método apresentarLogradouro da classe Endereco para formatar o endereço de forma legível
    public void exibirDadosCliente() {  
        System.out.println("Nome do cliente: " + nome);
        System.out.println("Idade: " + idade);
        if (endereco != null) {
            endereco.apresentarLogradouro();
        } else {
            System.out.println("Endereço não cadastrado.");
        }
        System.out.println("-----------------------------");
    }   
}
