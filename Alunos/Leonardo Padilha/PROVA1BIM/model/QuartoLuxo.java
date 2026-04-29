package model;

public class QuartoLuxo extends Quarto {
    private boolean varanda;

    public QuartoLuxo(int numero, double valorDiaria, boolean varanda) {
        super(numero, valorDiaria);
        this.varanda = varanda;
    }

     public boolean hasVaranda() {
        return this.varanda;
    }

    public void setVaranda(boolean varanda) {
        this.varanda = varanda;
    }

    @Override
    public void exibirInfo() {
        System.out.println("Quarto Luxo - Número: " + this.numero + ", Diária: " + this.valorDiaria + ", Varanda: " + (this.varanda ? "Sim" : "Não"));
    }
}