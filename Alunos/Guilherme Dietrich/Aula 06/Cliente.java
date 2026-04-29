package objetos;

public class Cliente {

    private String nomeCliente;
    private int idadeCliente;
    private String cidadeCliente;
    private String bairroCliente;
    private String ruaCliente;

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public int getIdadeCliente() {
        return idadeCliente;
    }

    public void setIdadeCliente(int idadeCliente) {
        this.idadeCliente = idadeCliente;
    }

    public String getCidadeCliente() {
        return cidadeCliente;
    }

    public void setCidadeCliente(String cidadeCliente) {
        this.cidadeCliente = cidadeCliente;
    }

    public String getBairroCliente() {
        return bairroCliente;
    }

    public void setBairroCliente(String bairroCliente) {
        this.bairroCliente = bairroCliente;
    }

    public String getRuaCliente() {
        return ruaCliente;
    }

    public void setRuaCliente(String ruaCliente) {
        this.ruaCliente = ruaCliente;
    }

    public void apresentarSe() {
        System.out.println("Seu nome é: " + nomeCliente + " e você tem " + idadeCliente + " anos.");
    }

    public void CadastroDoCliente() {
        System.out.println("--------------------------------------------");
        System.out.println("NOME: " + nomeCliente);
        System.out.println("IDADE: " + idadeCliente);
        System.out.println("CIDADE: " + cidadeCliente);
        System.out.println("BAIRRO: " + bairroCliente);
        System.out.println("RUA: " + ruaCliente);
        System.out.println("--------------------------------------------");
    }
}