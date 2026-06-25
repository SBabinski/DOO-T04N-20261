public class Loja {

    String nomeFantasia;
    String razaoSocial;
    String cnpj;
    String cidade;
    String bairro;
    String rua;
    Vendedor[] vendedores;
    Cliente[]  clientes;

    Loja(String nomeFantasia, String razaoSocial, String cnpj,
         String cidade, String bairro, String rua,
         Vendedor[] vendedores, Cliente[] clientes) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial  = razaoSocial;
        this.cnpj         = cnpj;
        this.cidade       = cidade;
        this.bairro       = bairro;
        this.rua          = rua;
        this.vendedores   = vendedores;
        this.clientes     = clientes;
    }

    int contarClientes() {
        return clientes.length;
    }

    int contarVendedores() {
        return vendedores.length;
    }

    void apresentarse() {
        System.out.println("=== Loja ===");
        System.out.println("Nome Fantasia: " + nomeFantasia);
        System.out.println("CNPJ        : " + cnpj);
        System.out.println("Endereço    : " + rua + ", " + bairro + ", " + cidade);
    }
}
