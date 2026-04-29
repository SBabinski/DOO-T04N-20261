import java.util.ArrayList;
import java.util.List;

public class Loja {
    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private String cidade;
    private String bairro;
    private String rua;
    private List<Vendedor> vendedores;
    private List<Cliente> clientes;

    public Loja(String nomeFantasia, String razaoSocial, String cnpj, String cidade, String bairro, String rua) {
        this.nomeFantasia = nomeFantasia;
        this.razaoSocial = razaoSocial;
        this.cnpj = cnpj;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.vendedores = new ArrayList<>();
        this.clientes = new ArrayList<>();
    }

    public Loja(String nomeFantasia, String cnpj) {
        this(nomeFantasia, "", cnpj, "", "", "");
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public List<Vendedor> getVendedores() {
        return vendedores;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void adicionarVendedor(Vendedor v) {
        if (v != null) this.vendedores.add(v);
    }

    public void adicionarCliente(Cliente c) {
        if (c != null) this.clientes.add(c);
    }

    // Conta e mostra a quantidade de clientes
    public void contarClientes() {
        System.out.println("Quantidade de clientes: " + this.clientes.size());
    }

    // Conta e mostra a quantidade de vendedores
    public void contarVendedores() {
        System.out.println("Quantidade de vendedores: " + this.vendedores.size());
    }

    // Método apresentarse: imprime nomeFantasia, cnpj e endereço
    public void apresentarse() {
        System.out.println("Nome Fantasia: " + this.nomeFantasia);
        System.out.println("CNPJ: " + this.cnpj);
        System.out.println("Endereço: " + this.rua + ", " + this.bairro + ", " + this.cidade);
    }
}
