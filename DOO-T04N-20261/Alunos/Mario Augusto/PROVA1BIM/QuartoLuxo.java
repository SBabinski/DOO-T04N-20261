public class QuartoLuxo extends Quarto {
    private boolean temVaranda;

    public QuartoLuxo(int numero, double valorDiaria, boolean temVaranda) {
        super(numero, valorDiaria);
        this.temVaranda = temVaranda;
    }

    public boolean isTemVaranda() {
        return temVaranda;
    }

    public void setTemVaranda(boolean temVaranda) {
        this.temVaranda = temVaranda;
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("=== Quarto Luxo ===");
        System.out.println("Número  : " + getNumero());
        System.out.println("Varanda : " + (temVaranda ? "Sim" : "Não"));
        System.out.printf("Diária  : R$ %.2f%n", getValorDiaria());
    }

    @Override
    public String toString() {
        return "Quarto Luxo nº " + getNumero()
                + " | Varanda: " + (temVaranda ? "Sim" : "Não")
                + " | Diária: R$ " + String.format("%.2f", getValorDiaria());
    }
}
