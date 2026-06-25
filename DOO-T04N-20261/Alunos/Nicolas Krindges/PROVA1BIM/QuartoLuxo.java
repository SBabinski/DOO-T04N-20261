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
        System.out.println("Número : " + numero);
        System.out.println("Diária : R$ " + String.format("%.2f", valorDiaria));
        System.out.println("Varanda: " + (temVaranda ? "Sim" : "Não"));
    }
}
