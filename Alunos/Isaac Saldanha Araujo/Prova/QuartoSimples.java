
public class QuartoSimples extends Quarto {

    private boolean ventilador;

    public QuartoSimples(int numero, double valorDiarias, boolean ventilador) {
        super(numero, valorDiarias);
        this.ventilador = ventilador;

    }

    @Override
    public void exibirInfo() {
        System.out.println("Quarto Simples | Nr: " + numero + "Ventilador: " + (ventilador ? "sim" : "nao") + "Diarias: " + valorDiarias);

    }
}
