public class Loja {

    String nomeFantasia;
    String razaoSocial;
    String cnpj;
    Endereco endereco;
    Vendedor[] vendedores;
    Cliente[] clientes;
    Gerente[] gerentes;

    Loja(String nomeFantasia, String razaoSocial, String cnpj, Endereco endereco,
            Vendedor[] vendedores, Cliente[] clientes, Gerente[] gerentes) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.vendedores = vendedores;
        this.clientes = clientes;
        this.gerentes = gerentes;
    }

    public void apresentarse() {
        System.out.println("Nome Fantasia: " + nomeFantasia);
        System.out.println("Razao Social: " + razaoSocial);
        System.out.println("CNPJ: " + cnpj);
        endereco.apresentarLogradouro();
    }

    public int contarClientes() {
        return clientes.length;
    }

    public int contarVendedores() {
        return vendedores.length;
    }

    public int contarGerentes() {
        return gerentes.length;
    }
}
