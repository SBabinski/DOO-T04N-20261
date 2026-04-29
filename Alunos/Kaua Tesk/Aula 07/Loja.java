public class Loja {

    String nomeFantasia;
    String razaoSocial;
    String cnpj;
    Endereco endereco;
    Vendedor[] vendedores;
    Gerente[]  gerentes;
    Cliente[]  clientes;

    Loja(String nomeFantasia, String razaoSocial, String cnpj,
         Endereco endereco,
         Vendedor[] vendedores, Gerente[] gerentes, Cliente[] clientes) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial  = razaoSocial;
        this.cnpj         = cnpj;
        this.endereco     = endereco;
        this.vendedores   = vendedores;
        this.gerentes     = gerentes;
        this.clientes     = clientes;
    }

    int contarClientes() {
        return clientes.length;
    }

    int contarVendedores() {
        return vendedores.length;
    }

    int contarGerentes() {
        return gerentes.length;
    }

    void apresentarse() {
        System.out.println("=== Loja ===");
        System.out.println("Nome Fantasia: " + nomeFantasia);
        System.out.println("CNPJ        : " + cnpj);
        endereco.apresentarLogradouro();
    }
}
