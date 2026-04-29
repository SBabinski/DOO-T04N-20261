public class QuartoSimples extends Quarto {
    private boolean ventilador;

    public QuartoSimples(int numero, double valorDiaria, boolean ventilador) {// coitado, só um ventilador T-T
        super(numero, valorDiaria);
        this.ventilador = ventilador;
    }

    @Override
    public void mostrarResumo() {
        System.out.println("Quarto simples - nº " + getNumero());
        System.out.println("Diária: R$ " + getValorDiaria());
        System.out.println("Ventilador: " + (ventilador ? "Sim" : "Não"));
    }