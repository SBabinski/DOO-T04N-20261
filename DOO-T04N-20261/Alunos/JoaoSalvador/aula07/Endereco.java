package calculadora_dona_gabrielinha;

public class Endereco {

    // Atributos
    String estado;
    String cidade;
    String bairro;
    String rua;
    int numero;
    String complemento;

    public Endereco(String estado, String cidade, String bairro, String rua, int numero, String complemento) {
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
    }

    // Exibe os dados de endereço de forma formatada
    public void apresentarLogradouro() {
        System.out.println("Endereço: " + rua + ", Nº " + numero + " - " + complemento);
        System.out.println("Bairro: " + bairro);
        System.out.println("Cidade: " + cidade + " - " + estado);
    }
}
