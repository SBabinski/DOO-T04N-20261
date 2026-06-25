package principal.classes;

public class quarto_luxo extends quarto {
    private boolean temVaranda;

    public quarto_luxo(double valor, int numero_quarto, boolean temVaranda) {
        super(valor, numero_quarto);
        this.temVaranda = temVaranda;
    }

    @Override
    public void mostrarDados() {
        super.mostrarDados();
        System.out.print(" | Varanda: " + (temVaranda ? "Sim" : "Não") + " [Luxo]");
    }
}