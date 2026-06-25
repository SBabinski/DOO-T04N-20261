import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva {
    private Hospede hospede;
    private Quarto quarto;
    private LocalDate checkin;
    private LocalDate checkout;
    private boolean checkoutRealizado;

    public Reserva(Hospede hospede, Quarto quarto, LocalDate checkin, LocalDate checkout) {
        this.hospede = hospede;
        this.quarto = quarto;
        this.checkin = checkin;
        this.checkout = checkout;
        this.checkoutRealizado = false;
    }

    public Hospede getHospede() { return hospede; }
    public Quarto getQuarto() { return quarto; }
    public LocalDate getCheckin() { return checkin; }
    public LocalDate getCheckout() { return checkout; }
    public boolean isCheckoutRealizado() { return checkoutRealizado; }

    public void realizarCheckout() {
        this.checkoutRealizado = true;
    }

    public long getQuantidadeDiarias() {
        return ChronoUnit.DAYS.between(checkin, checkout);
    }

    public double getValorTotal() {
        return getQuantidadeDiarias() * quarto.getValorDiaria();
    }

    public void exibirDados() {
        System.out.println("============================================");
        System.out.println("            DADOS DA RESERVA               ");
        System.out.println("============================================");
        System.out.println(hospede);
        System.out.println(quarto);
        System.out.println("Check-in:    " + checkin);
        System.out.println("Check-out:   " + checkout);
        System.out.println("Diárias:     " + getQuantidadeDiarias());
        System.out.printf("Valor Total: R$ %.2f%n", getValorTotal());
        System.out.println("Status:      " + (checkoutRealizado ? "Check-out realizado" : "Ativa"));
        System.out.println("============================================");
    }
}