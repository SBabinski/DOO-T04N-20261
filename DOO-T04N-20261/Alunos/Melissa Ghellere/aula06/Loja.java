package Alunos.Melissa_Ghellere.aula06;

public class Loja {
    String nomeFantasia, cnpj, cidade, bairro, rua;
    Vendedor[] vendedores;
    Cliente[] clientes;

    public Loja(String nomeFantasia, String cnpj, String cidade) {
        this.nomeFantasia = nomeFantasia;
        this.cnpj = cnpj;
        this.cidade = cidade;
    }

    public void contarClientes() { System.out.println("Total de clientes: " + (clientes != null ? clientes.length : 0)); }
    public void contarVendedores() { System.out.println("Total de vendedores: " + (vendedores != null ? vendedores.length : 0)); }

    public void apresentarse() {
        System.out.println("Loja: " + nomeFantasia + " | CNPJ: " + cnpj + " | Cidade: " + cidade);
    }
}
