package model;

public class QuartoSimples extends Quarto {
    private boolean ventilador;

    public QuartoSimples(int numero, double valorDiaria, boolean ventilador) {
        super(numero, valorDiaria);
        this.ventilador = ventilador;
    }

    public boolean hasVentilador() {
        return this.ventilador;
    }

    public void setVentilador(boolean ventilador) {
        this.ventilador = ventilador;
    }
    
    @Override
    public void exibirInfo() {
        System.out.println("Quarto Simples - Número: " + this.numero + ", Diária: " + this.valorDiaria + ", Ventilador: " + (this.ventilador ? "Sim" : "Não"));
    }
}
