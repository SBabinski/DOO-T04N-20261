public class QuartoLuxo extends Quarto { //quarto de luxo do hotel
    private boolean temVaranda;

    public QuartoLuxo(int numero, double valorDiaria, boolean temVaranda) {
        super(numero, valorDiaria);
        this.temVaranda = temVaranda;
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("Quarto Luxo #" + numero + " | Diária: R$" + valorDiaria + " | Varanda: " + (temVaranda ? "Sim" : "Não"));
    }
}
