package model;

public abstract class Quarto {
    protected int numero;
    protected double valorDiaria;

    public Quarto(int numero, double valorDiaria) {
        this.numero = numero;
        this.valorDiaria = valorDiaria;
    }

    public int getNumero() {
        return this.numero;
    }

    public double getValorDiaria() {
        return this.valorDiaria;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public abstract void exibirInfo();
}