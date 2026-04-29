import java.util.Date;

public class Reserva {
    public Hospede hospede;
    public Quarto quarto;
    public Date checkin;
    public Date checkout;
    public boolean finalizada;

    public long calcularDiarias() {
        long diff = checkout.getTime() - checkin.getTime();
        return diff / (1000 * 60 * 60 * 24);
    }

    public double calcularTotal() {
        return calcularDiarias() * quarto.valorDiaria;
    }

    public void mostrar() {
        System.out.println("Hospede: " + hospede.nome);
        quarto.exibirInfo();
        System.out.println("Checkin: " + checkin);
        System.out.println("Checkout: " + checkout);
        System.out.println("Total: " + calcularTotal());
        System.out.println("Finalizada: " + finalizada);
    }
}