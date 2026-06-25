public class QuartoSimples extends Quarto {
    boolean ventilador;

    public QuartoSimples(int n, double v, boolean vent) {
        super(n, v);
        ventilador = vent;
    }
}