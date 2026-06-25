public class Loja {
    public String nomeFantasia;
    public String cnpj;
    public Endereco enderecoLoja;


    public Vendedor[] vendedores = new Vendedor[10];
    public Cliente[] clientes = new Cliente[10];
    public Gerente[] gerentes = new Gerente[5];
    
    public int qtdVendedores = 0;
    public int qtdClientes = 0;


    public SistemaGabrielinha caixa = new SistemaGabrielinha();

    public void contarClientes() {
        System.out.println("Total de Clientes: " + qtdClientes);
    }

    public void contarVendedores() {
        System.out.println("Total de Vendedores: " + qtdVendedores);
    }

    public void apresentarse() {
        System.out.println("--- Dados da Empresa ---");
        System.out.println("Empresa: " + nomeFantasia + " | CNPJ: " + cnpj);
        if(enderecoLoja != null) enderecoLoja.apresentarEndereco();
    }
}