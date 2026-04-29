public class Loja {
    public String nomeFantasia;
    public String razaoSocial;
    public String cnpj;
    public String cidade;
    public String bairro;
    public String rua;

    public Vendedor[] vendedores = new Vendedor[10];
    public Clientes[] clientes = new Clientes[10];

    public SistemaGabrielinha caixa = new SistemaGabrielinha();

    public int qtdVendedores = 0;
    public int qtdClientes = 0;

    public void contarClientes(){
        System.out.println("A loja " + nomeFantasia + " tem " + qtdClientes + " clientes cadastrados.");
    }
    public void contarVendedores(){
        System.out.println("A loja " + nomeFantasia + " tem " + qtdVendedores + " vendedores cadastrados.");
    }
    public void apresentarse(){
        System.out.println("--- Dados da Loja ---");
        System.out.println("Nome Fantasia: " + nomeFantasia);
        System.out.println("CNPJ: " + cnpj);
        System.out.println("Endereço: " + rua + ", " + bairro + " - " + cidade);
    }
}
