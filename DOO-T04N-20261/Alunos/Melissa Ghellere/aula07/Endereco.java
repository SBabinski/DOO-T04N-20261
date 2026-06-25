package aula07;

public class Endereco {
    private String estado, cidade, bairro, rua, complemento;
    private int numero;

    public Endereco(String estado, String cidade, String bairro, int numero, String complemento) {
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
    }

    public void apresentarLogradouro() {
        System.out.println("Endereço: " + estado + ", " + cidade + ", " + bairro + ", nº " + numero + " - " + complemento);
    }
}
