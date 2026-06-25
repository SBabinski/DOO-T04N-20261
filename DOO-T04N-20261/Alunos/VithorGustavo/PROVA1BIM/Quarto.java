
public abstract class Quarto {
    private int numero;
    private double diaria;

    public Quarto(int numero, double diaria) {
        this.numero = numero;
        this.diaria = diaria;
    }

    public int getNumero() {
        return numero;
    }

    public double getValorDiaria() {
        return diaria;
    }

    public abstract void exibirInformacoes();

    @Override
    public String toString() {
        return "Quarto " + numero + " - R$ " + String.format("%.2f", diaria);
    }
}
