package metodos;

public class loja {

    String nomeFantasia, razaoSocial, cnpj, cidade, bairro, rua;
    vendedor[] vendedores;
    cliente[] clientes;

    public void contarClientes() {
        int qtd = (clientes != null) ? clientes.length : 0;
        System.out.println("Quantidade de clientes: " + qtd);
    }

    public void contarVendedores() {
        int qtd = (vendedores != null) ? vendedores.length : 0;
        System.out.println("Quantidade de vendedores: " + qtd);
    }

    public void apresentarse() {
        System.out.println("Loja: " + nomeFantasia + " | CNPJ: " + cnpj);
        System.out.println("Endereço: " + rua + ", " + bairro + " - " + cidade);
    }
}
