import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
public class Reserva {
    Hospede hospede;
    Quarto quarto;
    LocalDate checkin;
    LocalDate checkout;
    boolean realizado;
    public Reserva(Hospede hospede, Quarto quarto, LocalDate checkin, LocalDate checkout) {
        this.hospede = hospede;
        this.quarto = quarto;
        this.checkin = checkin;
        this.checkout = checkout;
        this.realizado = false;
    }
    public double total() {
        long dias = ChronoUnit.DAYS.between(checkin, checkout);
        return dias * quarto.diaria;
    }
    public void mostrar() {
        hospede.mostrar();
        quarto.mostrar();
        System.out.println("Check-in: " + checkin);
        System.out.println("Check-out: " + checkout);
        System.out.println("Total: " + total());
        System.out.println("Finalizado: " + realizado);
        System.out.println("-------------------");
    }
}