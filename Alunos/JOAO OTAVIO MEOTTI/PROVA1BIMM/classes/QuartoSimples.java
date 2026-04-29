public class QuartoSimples extends Quarto {
    boolean ventilador;
    public QuartoSimples(int numero, double diaria, boolean ventilador) {
        super(numero, diaria);
        this.ventilador = ventilador;
    }
    public void mostrar() {
        super.mostrar();
        System.out.println("Ventilador: " + ventilador);
    }
}