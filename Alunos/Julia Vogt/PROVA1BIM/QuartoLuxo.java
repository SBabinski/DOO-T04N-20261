package hotel;

public class QuartoLuxo extends Quarto {

    boolean varanda;

    public QuartoLuxo(int numero, double diaria, boolean varanda) {
        super(numero, diaria);
        this.varanda = varanda;
    }

    @Override
    public void mostrar() {
        System.out.println("Quarto Luxo");
        System.out.println("Número: " + numero);
        System.out.println("Diária: R$ " + diaria);
        System.out.println("Varanda: " + (varanda ? "Sim" : "Não"));
    }
}