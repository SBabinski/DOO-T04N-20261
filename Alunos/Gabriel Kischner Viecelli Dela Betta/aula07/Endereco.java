public class Endereco {
    public String estado;
    public String cidade;
    public String bairro;
    public int numero;
    public String rua;
    public String complemento;

  
    public Endereco(String estado, String cidade, String bairro, int numero, String rua, String complemento) {
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.numero = numero;
        this.rua = rua;
        this.complemento = complemento;
    }

    public void apresentarLogradouro() {
        System.out.println(rua + ", " + numero + " - " + bairro + " (" + cidade + "/" + estado + ")");
    }
}