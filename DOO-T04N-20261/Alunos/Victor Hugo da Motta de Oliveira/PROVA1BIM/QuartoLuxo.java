public class QuartoLuxo extends Quarto {
    private boolean temVaranda;

    public QuartoLuxo(int numero, double valorDiaria, boolean temVaranda) {
        super(numero, valorDiaria);
        this.temVaranda = temVaranda;
    }

    @Override
    public void exibirInfo() {
        System.out.println("Quarto Luxo - Número: " + numero + ", Valor da Diária: R$ " + valorDiaria + " / Varanda: " + (temVaranda ? "Sim" : "Não"));
    }
}