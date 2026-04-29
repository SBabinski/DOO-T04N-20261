
public class QuartoLuxo extends Quarto {
    private boolean varanda;

    public QuartoLuxo(int numero, double diaria, boolean varanda) {
        super(numero, diaria);
        this.varanda = varanda;
    }

    public boolean hasVaranda() {
        return varanda;
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("Quarto Luxo");
        System.out.println("Número: " + getNumero());
        System.out.println("Valor da diária: R$ " + String.format("%.2f", getValorDiaria()));
        System.out.println("Varanda: " + (varanda ? "Sim" : "Não"));
    }

    @Override
    public String toString() {
        return "Quarto Luxo " + getNumero() + " - R$ " + String.format("%.2f", getValorDiaria()) + " - Varanda: " + (varanda ? "Sim" : "Não");
    }
}
