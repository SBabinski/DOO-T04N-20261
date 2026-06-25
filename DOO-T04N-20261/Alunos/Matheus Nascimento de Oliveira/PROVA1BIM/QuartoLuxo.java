public class QuartoLuxo extends Quarto {
    private boolean varanda;

    public QuartoLuxo(int numero, double valorDiaria, boolean varanda) {
        super(numero, valorDiaria);
        this.varanda = varanda;
    }

    @Override
    public void mostrar() {
        System.out.println("--- Quarto Luxo ---");
        System.out.println("Número do quarto: " + getNumero());
        System.out.println("Valor da diária: R$ " + getValorDiaria());
        System.out.println("Varanda: " + (varanda ? "sim" : "não"));
    }
}
