public class Endereco {

    String estado;
    String cidade;
    String bairro;
    String numero;
    String complemento;

    Endereco(String estado, String cidade, String bairro, String numero, String complemento) {
        this.estado = estado;
        this.cidade = cidade;
        this.bairro = bairro;
        this.numero = numero;
        this.complemento = complemento;
    }

    public void apresentarLogradouro() {
        System.out.println("Endereco: " + complemento + ", No " + numero +
                " | Bairro: " + bairro +
                " | Cidade: " + cidade +
                " | Estado: " + estado);
    }
}
