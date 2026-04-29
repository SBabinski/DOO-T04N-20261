public class Endereco {
    String estado;
    String cidade;
    String bairro;
    int numero;
    String complemento;

    public Endereco(String estado, String cidade, String bairro, int numero, String complemento) {
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
    }

    public void apresentarLogradouro() {
        System.out.println("Endereco Completo: " + this.bairro + ", " + this.numero + " (" + this.complemento + ") - " + this.cidade + "/" + this.estado);
    }
}