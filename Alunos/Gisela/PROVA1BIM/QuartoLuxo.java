public class QuartoLuxo extends Quarto {
    private boolean varanda;

    public QuartoLuxo(int numero, double valorDiaria, boolean varanda) {
        super(numero, valorDiaria);
        this.varanda = varanda;
    }

    @Override
    public void mostrarResumo() {
        System.out.println("Quarto luxo - nº " + getNumero());
        System.out.println("Diária: R$ " + getValorDiaria());
        System.out.println("Varanda: " + (varanda ? "Sim" : "Não"));
    }
}