package Prova;

public abstract class Quarto {
    protected int numero;
    protected double valorDiaria;

    public Quarto(int numero, double valorDiaria) {
        this.numero = numero;
        this.valorDiaria = valorDiaria;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public abstract void exibirInformacoes();
}