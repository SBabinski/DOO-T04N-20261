package prova01;

public class QuartoLuxo extends Quarto {
    private boolean temVaranda;

    public QuartoLuxo(int numero, double valorDiaria, boolean temVaranda) {
        super(numero, valorDiaria);
        this.temVaranda = temVaranda;
    }

    @Override
    public void exibirInformacoes() {
        String varanda = temVaranda ? "Sim" : "Não";
        System.out.println("Quarto Luxo -> Número: " + numero + 
                           " | Diária: R$ " + valorDiaria + 
                           " | Varanda: " + varanda);
    }
}