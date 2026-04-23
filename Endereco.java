public class Endereco {
    String cidade;
    String bairro;
    String rua;

    public Endereco(String cidade, String bairro, String rua) {
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
    }

    public void apresentarLogradouro() {
        System.out.println(rua + ", " + bairro + ", " + cidade);
    }
}