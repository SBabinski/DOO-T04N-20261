package hotel;

public class QuartoSimples extends Quarto {

    boolean ventilador;

    public QuartoSimples(int numero, double diaria, boolean ventilador) {
        super(numero, diaria);
        this.ventilador = ventilador;
    }

    @Override
    public void mostrar() {
        System.out.println("Quarto Simples");
        System.out.println("Número: " + numero);
        System.out.println("Diária: R$ " + diaria);
        System.out.println("Ventilador: " + (ventilador ? "Sim" : "Não"));
    }
}