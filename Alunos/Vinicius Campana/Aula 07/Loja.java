package doo;

import java.util.ArrayList;

public class Loja {

    String nomeFantasia;
    String razaoSocial;
    String cnpj;
    Endereco endereco;

    ArrayList<Vendedor> vendedores = new ArrayList<>();
    ArrayList<Cliente> clientes = new ArrayList<>();

    public Loja(String nomeFantasia, String razaoSocial, String cnpj, Endereco endereco) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.endereco = endereco;
    }

    public void contarClientes() {
        System.out.println("Quantidade de clientes: " + clientes.size());
    }

    public void contarVendedores() {
        System.out.println("Quantidade de vendedores: " + vendedores.size());
    }

    public void apresentarSe() {
        System.out.println("Nome Fantasia: " + nomeFantasia);
        System.out.println("CNPJ: " + cnpj);
        endereco.apresentarLogradouro();
    }
}