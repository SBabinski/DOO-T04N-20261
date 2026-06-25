public class Vendedor extends Pessoa {
    double salarioBase;

    public Vendedor(String n, int i, Endereco e, double s) {
        super(n, i, e);
        salarioBase = s;
    }
}