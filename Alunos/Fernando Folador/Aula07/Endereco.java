public class Endereco {
    public String estado;
    public String cidade;
    public String bairro;
    public String numero;
    public String complemento;

    public void apresentarLogradouro() {
        System.out.println(bairro + ", " + numero + " - " + cidade + "/" + estado + " (" + complemento + ")");
    }
}