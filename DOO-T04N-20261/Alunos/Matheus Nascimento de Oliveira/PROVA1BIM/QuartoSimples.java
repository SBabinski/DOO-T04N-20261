public class QuartoSimples extends Quarto {
    private boolean ventilador;

    public QuartoSimples(int numero, double valorDiaria, boolean ventilador) {
        super(numero, valorDiaria);
        this.ventilador = ventilador;
    }

    @Override
    public void mostrar() {
        System.out.println("--- Quarto Simples ---");
        System.out.println("Número do quarto: " + getNumero());
        System.out.println("Valor da diária: R$ " + getValorDiaria());
        System.out.println("Ventilador: " + (ventilador ? "sim" : "não"));
    }
}
