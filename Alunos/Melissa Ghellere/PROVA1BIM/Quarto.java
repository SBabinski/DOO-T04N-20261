package Alunos.Melissa_Ghellere.PROVA1BIM;

public abstract class Quarto {
    protected int numero;
    protected double valorDiaria;
    protected boolean disponivel;

    public Quarto(int numero, double valorDiaria, boolean disponivel) {
        this.numero = numero;
        this.valorDiaria = valorDiaria;
        this.disponivel = disponivel;
    }

    public abstract void exibirInformacoes();
    public double getValorDiaria() { return valorDiaria; }
}
