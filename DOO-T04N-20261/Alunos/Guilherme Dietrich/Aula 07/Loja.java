package Objetos;

public class Loja {

    private String nomeFantasia;
    private String razaoSocial;
    private String cnpjLoja;
    private String cidadeLoja;
    private String bairroLoja;
    private String ruaLoja;

    private Vendedor[] vendedoresLoja = new Vendedor[10];
    private Cliente[] clientesLoja = new Cliente[10];

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

    public String getCnpjLoja() {
        return cnpjLoja;
    }

    public void setCnpjLoja(String cnpjLoja) {
        this.cnpjLoja = cnpjLoja;
    }

    public String getCidadeLoja() {
        return cidadeLoja;
    }

    public void setCidadeLoja(String cidadeLoja) {
        this.cidadeLoja = cidadeLoja;
    }

    public String getBairroLoja() {
        return bairroLoja;
    }

    public void setBairroLoja(String bairroLoja) {
        this.bairroLoja = bairroLoja;
    }

    public String getRuaLoja() {
        return ruaLoja;
    }

    public void setRuaLoja(String ruaLoja) {
        this.ruaLoja = ruaLoja;
    }

    public int contadorCliente() {
        return clientesLoja.length;
    }

    public int contadorVendedor() {
        return vendedoresLoja.length;
    }

    public String apresentarSe() {
        return "Somos a loja " + nomeFantasia + " localizada em " + cidadeLoja;
    }

    public void CadastroDaLoja() {
        System.out.println("--------------------------------------------");
        System.out.println("NOME: " + nomeFantasia);
        System.out.println("RAZÃO SOCIAL: " + razaoSocial);
        System.out.println("CNPJ: " + cnpjLoja);
        System.out.println("CIDADE: " + cidadeLoja);
        System.out.println("BAIRRO: " + bairroLoja);
        System.out.println("RUA: " + ruaLoja);
        System.out.println("VENDEDORES: " + contadorVendedor());
        System.out.println("CLIENTES: " + contadorCliente());
        System.out.println("--------------------------------------------");
    }
}