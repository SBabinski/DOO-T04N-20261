public class QuartoSimples extends Quarto {
    private boolean temVentilador;

    public QuartoSimples(int numero, double valorDiaria, boolean temVentilador) {
        super(numero, valorDiaria);
        this.temVentilador = temVentilador;
    }

    public boolean isTemVentilador() {
        return temVentilador;
    }

    public void setTemVentilador(boolean temVentilador) {
        this.temVentilador = temVentilador;
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("=== Quarto Simples ===");
        System.out.println("Número   : " + getNumero());
        System.out.println("Ventilador: " + (temVentilador ? "Sim" : "Não"));
        System.out.printf("Diária   : R$ %.2f%n", getValorDiaria());
    }

    @Override
    public String toString() {
        return "Quarto Simples nº " + getNumero()
                + " | Ventilador: " + (temVentilador ? "Sim" : "Não")
                + " | Diária: R$ " + String.format("%.2f", getValorDiaria());
    }
}
