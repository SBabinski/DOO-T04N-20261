import java.util.ArrayList;
public class Loja {
    String nomeFantasia;
    String razaoSocial;
    String cnpj;
    String cidade;
    String bairro;
    String rua;
    ArrayList<Vendedor> vendedores;
    ArrayList<Cliente> clientes;
    public Loja(String nomeFantasia, String razaoSocial, String cnpj, String cidade, String bairro, String rua) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        vendedores = new ArrayList<>();
        clientes = new ArrayList<>();
    }
    public void apresentarSe() {
        System.out.println("=== Dados da Loja ===");
        System.out.println("Nome Fantasia: " + nomeFantasia);
        System.out.println("Razão Social: " + razaoSocial);
        System.out.println("CNPJ: " + cnpj);
        System.out.println("Endereço: " + rua + ", " + bairro + " - " + cidade);
    }
    public void contarClientes() {
        System.out.println("Quantidade de clientes: " + clientes.size());
    }
    public void contarVendedores() {
        System.out.println("Quantidade de vendedores: " + vendedores.size());
    }
    public void adicionarCliente(Cliente cliente) {
        clientes.add(cliente);
    }
    public void adicionarVendedor(Vendedor vendedor) {
        vendedores.add(vendedor);
    }
    public void listarClientes() {
        System.out.println("=== Lista de Clientes ===");
        for (Cliente c : clientes) {
            c.apresentarSe();
        }
    }
    public void listarVendedores() {
        System.out.println("=== Lista de Vendedores ===");
        for (Vendedor v : vendedores) {
            v.apresentarSe();
        }
    }
}