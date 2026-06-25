 class Loja {
    String nomeFantasia;
    String razaoSocial;
    String cnpj;
    String cidade;
    String bairro;
    String rua;

    Vendedor[] vendedores;
    Cliente[] clientes;

    int contarClientes() {
        return clientes.length;
    }

    int contarVendedores() {
        return vendedores.length;
    }
    
    void apresentarSe(){
        System.out.println("Nome fantasia:" +nomeFantasia);
        System.out.println("cnpj:" +cnpj);
        System.out.println("Endereco:" +cidade + "," + bairro + "," + rua );
    }

}
