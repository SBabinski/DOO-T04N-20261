
public class QuartoSimples extends Quarto {
    private boolean ventilador;

    public QuartoSimples(int numero, double diaria, boolean ventilador) {
        super(numero, diaria);
        this.ventilador = ventilador;
    }

    public boolean hasVentilador() {
        return ventilador;
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("Quarto Simples");
        System.out.println("Número: " + getNumero());
        System.out.println("Valor da diária: R$ " + String.format("%.2f", getValorDiaria()));
        System.out.println("Ventilador: " + (ventilador ? "Sim" : "Não"));
    }

    @Override
    public String toString() {
        return "Quarto Simples " + getNumero() + " - R$ " + String.format("%.2f", getValorDiaria()) + " - Ventilador: " + (ventilador ? "Sim" : "Não");
    }
}
