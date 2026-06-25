public class Endereco {

    String estado;
    String cidade;
    String bairro;
    String rua;
    String numero;
    String complemento;

    Endereco(String estado, String cidade, String bairro, String rua, String numero, String complemento) {
        this.estado      = estado;
        this.cidade      = cidade;
        this.bairro      = bairro;
        this.rua         = rua;
        this.numero      = numero;
        this.complemento = complemento;
    }

    void apresentarLogradouro() {
        System.out.println("📍 " + rua + ", Nº " + numero + " - " + bairro + ", " + cidade + " - " + estado
                + (complemento != null && !complemento.isEmpty() ? " (" + complemento + ")" : ""));
    }
}
