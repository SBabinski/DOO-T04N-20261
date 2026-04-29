import java.util.ArrayList;

public class Loja {

    String nomeFantasia;
    String razaoSocial;
    String cnpj;
    String cidade;
    String bairro;
    String rua;

    ArrayList<Vendedor> vendedores = new ArrayList<>();
    ArrayList<Cliente> clientes = new ArrayList<>();

    public int contarClientes() {
        return clientes.size();
    }

    public int contarVendedores() {
        return vendedores.size();
    }

    public void apresentarSe() {
        System.out.println("Nome Fantasia: " + nomeFantasia);
        System.out.println("CNPJ: " + cnpj);
        System.out.println("Endereço: " + rua + ", " + bairro + ", " + cidade);
    }
}
