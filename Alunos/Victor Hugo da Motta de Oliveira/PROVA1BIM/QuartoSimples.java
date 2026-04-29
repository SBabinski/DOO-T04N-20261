public class QuartoSimples extends Quarto {
    private boolean temVentilador;

    public QuartoSimples(int numero, double valorDiaria, boolean temVentilador) {
        super(numero, valorDiaria);
        this.temVentilador = temVentilador;
    }

    @Override
    public void exibirInfo() {
        System.out.println("Quarto Simples - Número: " + numero + ", Valor da Diária: R$ " + valorDiaria + " / Ventilador: " + (temVentilador ? "Sim" : "Não"));
    }
}