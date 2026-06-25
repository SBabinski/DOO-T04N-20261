package prova01;

public abstract class Quarto {
    protected int numero;
    protected double valorDiaria;

    public Quarto(int numero, double valorDiaria) {
        this.numero = numero;
        this.valorDiaria = valorDiaria;
    }

    public int getNumero() { return numero; }
    public double getValorDiaria() { return valorDiaria; }

   
    public abstract void exibirInformacoes();
}
