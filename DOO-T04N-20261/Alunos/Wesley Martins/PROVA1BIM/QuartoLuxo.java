public class QuartoLuxo extends Quarto {
    private boolean varanda;

    public QuartoLuxo(int numero, double valorDiaria, boolean varanda) {
        super(numero, valorDiaria);
        this.varanda = varanda;
    }

    public boolean isVaranda() {
        return varanda;
    }

    public void setVaranda(boolean varanda) {
        this.varanda = varanda;
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("Quarto Luxo - Número: " + numero + ", Valor Diária: R$ " + valorDiaria + ", Varanda: " + (varanda ? "Sim" : "Não"));
    }
}