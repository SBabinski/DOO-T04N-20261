package hotel;

public class QuartoLuxo extends Quarto {

    private boolean temVaranda;

    public QuartoLuxo(int numero, double valorDiaria, boolean temVaranda) {
        super(numero, valorDiaria);
        this.temVaranda = temVaranda;
    }

    @Override
    public void apresentarSe() {
        System.out.println("\n=== QUARTO DE LUXO ===\n");
        System.out.println("Quarto Luxo #" + numero);
        System.out.println("Valor diária: R$ " + valorDiaria);
        System.out.println("Varanda: " + (temVaranda ? "Sim" : "Não"));
        System.out.println("\n=== === === ===\n");
    }
}