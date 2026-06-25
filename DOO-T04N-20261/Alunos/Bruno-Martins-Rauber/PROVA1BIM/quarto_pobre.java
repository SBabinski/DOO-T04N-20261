package principal.classes;

public class quarto_pobre extends quarto {
    private boolean temVentilador;

    public quarto_pobre(double valor, int numero_quarto, boolean temVentilador) {
        super(valor, numero_quarto);
        this.temVentilador = temVentilador;
    }

    @Override
    public void mostrarDados() {
        super.mostrarDados();
        System.out.print(" | Ventilador: " + (temVentilador ? "Sim" : "Não") + " [Simples]");
    }
}