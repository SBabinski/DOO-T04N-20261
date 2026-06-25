public class QuartoSimples extends Quarto {
    private boolean ventilador;

    public QuartoSimples(int numero, double valorDiaria, boolean ventilador) {
        super(numero, valorDiaria);
        this.ventilador = ventilador;
    }

    public boolean isVentilador() {
        return ventilador;
    }

    public void setVentilador(boolean ventilador) {
        this.ventilador = ventilador;
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("Quarto Simples - Número: " + numero + ", Valor Diária: R$ " + valorDiaria + ", Ventilador: " + (ventilador ? "Sim" : "Não"));
    }
}