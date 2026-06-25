import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva {
    private Hospede   hospede;
    private Quarto    quarto;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private boolean   checkOutRealizado;

    public Reserva(Hospede hospede, Quarto quarto,
                   LocalDate checkIn, LocalDate checkOut) {
        this.hospede           = hospede;
        this.quarto            = quarto;
        this.checkIn           = checkIn;
        this.checkOut          = checkOut;
        this.checkOutRealizado = false;
    }

    public boolean isCheckOutRealizado() { return checkOutRealizado; }
    public Quarto getQuarto() { return quarto; }

    public void realizarCheckOut() {
        this.checkOutRealizado = true;
        System.out.println("\nCheck-out realizado para quarto " + quarto.getNumero());
    }

    public void exibirDados() {
        long diarias    = ChronoUnit.DAYS.between(checkIn, checkOut);
        double total    = diarias * quarto.getValorDiaria();

        System.out.println("-----------------------------");
        hospede.exibirInformacoes();
        quarto.exibirInformacoes();
        System.out.println("Check-in:  " + checkIn);
        System.out.println("Check-out: " + checkOut);
        System.out.println("Diárias:   " + diarias);
        System.out.printf ("Total:     R$ %.2f%n", total);
        System.out.println("Situação:  " + (checkOutRealizado ? "Check-out realizado" : "Ativa"));
        System.out.println("-----------------------------");
    }
}
