package Prova;

public class QuartoLuxo extends Quarto {
    private boolean varanda;

    public QuartoLuxo(int numero, double valorDiaria, boolean varanda) {
        super(numero, valorDiaria);
        this.varanda = varanda;
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("Quarto Luxo - Nº: " + numero);
        System.out.println("Diária: R$ " + valorDiaria);
        System.out.println("Varanda: " + (varanda ? "Sim" : "Não"));
    }
}