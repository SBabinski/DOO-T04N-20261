class Loja {
    String nomeFantasia;
    String razaoSocial;
    String cnpj;
    String cidade;
    String bairro;
    String rua;

    Vendedor[] vendedores;
    Cliente[] clientes;
    
    void contarClientes() {
    System.out.println("Quantidade de clientes: " + clientes.length);
    }

    void contarVendedores() {
    System.out.println("Quantidade de vendedores: " + vendedores.length);
    }

    void apresentarSe(){
        System.out.println("Nome fantasia:" +nomeFantasia);
        System.out.println("cnpj:" +cnpj);
        System.out.println("Endereco:" +cidade + "," + bairro + "," + rua );
    }

}