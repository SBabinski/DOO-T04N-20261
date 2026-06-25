import java.time.LocalDate;

public class Reserva {
    private Hospede hospede;
    private Quarto quarto;
    private LocalDate checkin;
    private LocalDate checkout;
    private boolean realizado;

    public Reserva(Hospede hospede, Quarto quarto, LocalDate checkin, LocalDate checkout) {
        this.hospede = hospede;
        this.quarto = quarto;
        this.checkin = checkin;
        this.checkout = checkout;
        this.realizado = false;
    }

    public double calcularTotal() {
        int dias = checkout.getDayOfMonth() - checkin.getDayOfMonth();
        return dias * quarto.getDiaria();
    }

    public void exibirReserva() {
        System.out.println("Hóspede: " + hospede.getNome());
        System.out.println("Quarto: " + quarto.getNumero());
        System.out.println("Check-in: " + checkin);
        System.out.println("Check-out: " + checkout);
        System.out.println("Status: " + (realizado ? "Finalizado" : "Em andamento"));
        System.out.println("Total: R$ " + calcularTotal());
    }

    public boolean isRealizado() {
        return realizado;
    }

    public void realizarCheckout() {
        this.realizado = true;
    }
}