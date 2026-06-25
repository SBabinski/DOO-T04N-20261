
public class Loja {
    private String nomeFantasia;
    private String razaoSocial;
    private String cpnj;
    private Endereco endereco;
    private Funcionario[] funcionarios;
    private Cliente[] clientes;

    public Loja(String nomeFantasia, String razaoSocial, String cpnj, Endereco endereco, Funcionario[] funcionarios, Cliente[] clientes) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cpnj = cpnj;
        this.endereco = endereco;
        this.funcionarios = funcionarios;
        this.clientes = clientes;
    }

    public int contarClientes() {
        return clientes.length;
    }

    public int contarFuncionarios() {
        return funcionarios.length;
    }

    public void apresentarse() {
        System.out.print("Nome Fantasia: " + nomeFantasia + ", CNPJ: " + cpnj + ", Endereco: ");
        endereco.apresentarLogradouro();
    }

    public Funcionario[] getFuncionarios() { return funcionarios; }
    public Cliente[] getClientes() { return clientes; }
}