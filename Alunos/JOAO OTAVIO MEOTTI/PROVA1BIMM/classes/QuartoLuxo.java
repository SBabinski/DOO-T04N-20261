public class QuartoLuxo extends Quarto {
    boolean varanda;
    public QuartoLuxo(int numero, double diaria, boolean varanda) {
        super(numero, diaria);
        this.varanda = varanda;
    }
    public void mostrar() {
        super.mostrar();
        System.out.println("Varanda: " + varanda);
    }
}