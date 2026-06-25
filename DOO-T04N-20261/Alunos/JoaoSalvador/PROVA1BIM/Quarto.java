package hotel;

public abstract class Quarto {

    protected int numero;
    protected double valorDiaria;

    public Quarto(int numero, double valorDiaria) {
        this.numero = numero;
        this.valorDiaria = valorDiaria;
    }

    public abstract void apresentarSe();

    public double getValorDiaria() {
        return valorDiaria;
    }

    public int getNumero() {
        return numero;
    }
}
