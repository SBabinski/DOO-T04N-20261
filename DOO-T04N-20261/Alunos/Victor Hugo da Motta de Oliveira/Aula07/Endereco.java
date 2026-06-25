public class Endereco {
    public String rua;
    public String numero;
    public String bairro;
    public String cidade;
    public String estado;

    public void apresentarEndereco() {
        System.out.println("Endereço: " + rua + ", " + numero + " | " + bairro + " - " + cidade + "/" + estado);
    }
}