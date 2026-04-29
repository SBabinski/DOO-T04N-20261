public class QuartoSimples extends Quarto {
    private boolean ventilador;

    public QuartoSimples(int numero, double valor, boolean ventilador) {
        super(numero, valor);
        this.ventilador = ventilador;
    }

    public void exibirInfo() {
        System.out.println("Quarto Simples " + numero +
                " | Ventilador: " + ventilador +
                " | R$ " + valorDiaria);
    }
}