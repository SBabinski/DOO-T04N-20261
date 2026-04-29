public class QuartoSimples extends Quarto {
    private boolean ventilador;

    public QuartoSimples(int numero, double valorDiaria, boolean ventilador) {
        super(numero, valorDiaria);
        this.ventilador = ventilador;
    }

    @Override
    public void exibirInfo() {
        System.out.println("Quarto Simples Nº " + numero +
                " | Diária: R$" + valorDiaria +
                " | Ventilador: " + (ventilador ? "Sim" : "Não"));
    }
}