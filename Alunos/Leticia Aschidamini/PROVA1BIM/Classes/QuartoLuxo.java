public class QuartoLuxo extends Quarto {
    private boolean varanda;

    public QuartoLuxo(int numero, double valor, boolean varanda) {
        super(numero, valor);
        this.varanda = varanda;
    }

    public void exibirInfo() {
        System.out.println("Quarto Luxo " + numero +
                " | Varanda: " + varanda +
                " | R$ " + valorDiaria);
    }
}