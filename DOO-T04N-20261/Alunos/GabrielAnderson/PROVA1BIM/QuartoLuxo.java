public class QuartoLuxo extends Quarto {
    private boolean temVaranda;

    public QuartoLuxo(int numero, double valorDiaria, boolean temVaranda) {
        super(numero, valorDiaria);
        this.temVaranda = temVaranda;
    }

    public boolean isTemVaranda() { return temVaranda; }

    @Override
    public void exibirInformacoes() {
        System.out.println("=== Quarto Luxo ===");
        System.out.println("Número: " + getNumero());
        System.out.printf("Valor da diária: R$ %.2f%n", getValorDiaria());
        System.out.println("Varanda: " + (temVaranda ? "Sim" : "Não"));
    }

    @Override
    public String toString() {
        return "Quarto Luxo #" + getNumero() +
               " | Diária: R$ " + String.format("%.2f", getValorDiaria()) +
               " | Varanda: " + (temVaranda ? "Sim" : "Não");
    }
}