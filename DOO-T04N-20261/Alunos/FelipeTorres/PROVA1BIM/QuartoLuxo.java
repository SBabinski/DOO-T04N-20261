public class QuartoLuxo extends Quarto {
    private boolean varanda;

    public QuartoLuxo(int numero, double valorDiaria, boolean varanda) {
        super(numero, valorDiaria);
        this.varanda = varanda;
    }

    @Override
    public void exibirInfo() {
        System.out.println("Quarto Luxo Nº " + numero +
                " | Diária: R$" + valorDiaria +
                " | Varanda: " + (varanda ? "Sim" : "Não"));
    }
}