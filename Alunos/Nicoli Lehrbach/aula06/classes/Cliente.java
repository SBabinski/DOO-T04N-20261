public class Cliente {

    String nome;
    int idade;
    String cidade;
    String bairro;
    String rua;

    public Cliente(String nomeCliente, int idadeCliente, String cidadeCliente, String bairroCliente, String ruaCliente) {
        nome = nomeCliente;
        idade = idadeCliente;
        cidade = cidadeCliente;
        bairro = bairroCliente;
        rua = ruaCliente;
    }

    public void apresentarSe() {
        String mensagem = "Nome: " + nome + " | Idade: " + idade;
        System.out.println(mensagem);
    }
}