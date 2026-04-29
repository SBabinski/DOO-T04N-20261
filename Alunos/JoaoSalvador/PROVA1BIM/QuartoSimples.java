package hotel;

public class QuartoSimples extends Quarto {

    private boolean temVentilador;

    public QuartoSimples(int numero, double valorDiaria, boolean temVentilador) {
        super(numero, valorDiaria);
        this.temVentilador = temVentilador;
    }

    @Override
    public void apresentarSe() {
        System.out.println("\n=== QUARTO SIMPLES ===\n");
        System.out.println("Numero: #" + numero);
        System.out.println("Valor diária: R$ " + valorDiaria);
        System.out.println("Ventilador: " + (temVentilador ? "Sim" : "Não"));
        System.out.println("\n=== === === ===\n");
    }
}