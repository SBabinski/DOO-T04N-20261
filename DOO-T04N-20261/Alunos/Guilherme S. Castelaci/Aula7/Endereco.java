public class Endereco {
    String estado, cidade, bairro, complemento;
    int numero;

    public Endereco(String e, String c, String b, int n, String comp) {
        estado = e;
        cidade = c;
        bairro = b;
        numero = n;
        complemento = comp;
    }

    public void apresentarLogradouro() {
        System.out.println(bairro + " " + cidade + " " + estado + " " + numero);
    }
}