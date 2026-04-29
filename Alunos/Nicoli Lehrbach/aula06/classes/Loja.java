import java.util.ArrayList;
import java.util.List;

public class Loja {

    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private String cidade;
    private String bairro;
    private String rua;

    private List<Vendedor> listaVendedores;
    private List<Cliente> listaClientes;

    public Loja(String nomeFantasia, String razaoSocial, String cnpj,
                String cidade, String bairro, String rua) {

        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;

        this.listaVendedores = new ArrayList<>();
        this.listaClientes = new ArrayList<>();
    }

    public void contarClientes() {
        int total = listaClientes.size();
        System.out.println("Quantidade de clientes: " + total);
    }

    public void contarVendedores() {
        int total = listaVendedores.size();
        System.out.println("Quantidade de vendedores: " + total);
    }

    public void apresentarSe() {
        String endereco = rua + ", " + bairro + ", " + cidade;

        System.out.println("Loja: " + nomeFantasia);
        System.out.println("CNPJ: " + cnpj);
        System.out.println("Endereço: " + endereco);
    }
}