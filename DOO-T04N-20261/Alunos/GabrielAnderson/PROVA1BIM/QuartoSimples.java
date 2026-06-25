public class QuartoSimples extends Quarto {
    private boolean temVentilador;

    public QuartoSimples(int numero, double valorDiaria, boolean temVentilador) {
        super(numero, valorDiaria);
        this.temVentilador = temVentilador;
    }

    public boolean isTemVentilador() { return temVentilador; }

    @Override
    public void exibirInformacoes() {
        System.out.println("=== Quarto Simples ===");
        System.out.println("Número: " + getNumero());
        System.out.printf("Valor da diária: R$ %.2f%n", getValorDiaria());
        System.out.println("Ventilador: " + (temVentilador ? "Sim" : "Não"));
    }

    @Override
    public String toString() {
        return "Quarto Simples #" + getNumero() +
               " | Diária: R$ " + String.format("%.2f", getValorDiaria()) +
               " | Ventilador: " + (temVentilador ? "Sim" : "Não");
    }
}