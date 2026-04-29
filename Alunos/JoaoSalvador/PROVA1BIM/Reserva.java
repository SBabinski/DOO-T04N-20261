package hotel;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva {

    private Hospede hospede;
    private Quarto quarto;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private boolean checkoutRealizado;

    public Reserva(Hospede hospede, Quarto quarto, LocalDate checkIn, LocalDate checkOut) {
        this.hospede = hospede;
        this.quarto = quarto;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.checkoutRealizado = false;
    }

    public long calcularDiarias() {
        return ChronoUnit.DAYS.between(checkIn, checkOut);
    }

    public double calcularTotal() {
        return calcularDiarias() * quarto.getValorDiaria();
    }

    public void realizarCheckout() {
        checkoutRealizado = true;
    }

    public boolean isCheckoutRealizado() {
        return checkoutRealizado;
    }

    public void apresentarSe() {
        System.out.println("\n== RESERVA ==\n");
        System.out.println("Hospede: " + hospede.getNome());
        System.out.println("Quarto: " + quarto.getNumero());
        System.out.println("Check-in: " + checkIn);
        System.out.println("Check-out: " + checkOut);
        System.out.println("Diarias: " + calcularDiarias());
        System.out.println("Total: R$ " + calcularTotal());
        System.out.println("Status: " + (checkoutRealizado ? "Finalizada" : "Ativa"));
        System.out.println("\n=== ===\n");
    }
}