package calculadora_dona_gabrielinha;

import java.util.ArrayList;

public class Loja {

    // Variáveis
    String nomeFantasia;
    String razaoSocial;
    String cnpj;
    String cidade;
    String bairro;
    String rua;

    // Arrays
    ArrayList<Vendedor> vendedores = new ArrayList<>();
    ArrayList<Cliente> clientes = new ArrayList<>();

    // Molde de dados
    public Loja(String nomeFantasia, String razaoSocial, String cnpj, String cidade, String bairro, String rua) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
    }

    // Mostra quantidade atual de clientes
    public void contarClientes() {
        System.out.println("Quantidade de clientes: " + clientes.size());
    }

    // Mostra quantidade atual de vendedores
    public void contarVendedores() {
        System.out.println("Quantidade de vendedores: " + vendedores.size());
    }

    // Mostra os dados da loja
    public void apresentarSe() {
        System.out.println("\n===== DADOS DA LOJA =====\n");
        System.out.println("Loja: " + nomeFantasia + ".");
        System.out.println("CNPJ: " + cnpj + ".");
        System.out.println("Endereco: " + rua + ", " + bairro + ", " + cidade + ".");
        System.out.println("\n===== ===== ===== =====\n");
    }
}