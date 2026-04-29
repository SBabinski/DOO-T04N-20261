import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva {
    private Hospede hospede;
    private Quarto quarto;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private boolean checkOutRealizado;

    public Reserva(Hospede hospede, Quarto quarto, LocalDate checkIn, LocalDate checkOut) {
        this.hospede = hospede;
        this.quarto = quarto;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.checkOutRealizado = false;
    }

    public boolean isCheckOutRealizado() { return checkOutRealizado; }
    public void realizarCheckOut() { this.checkOutRealizado = true; }
    public Hospede getHospede() { return hospede; }
    public Quarto getQuarto() { return quarto; }

    public long getQuantidadeDiarias() {
        return ChronoUnit.DAYS.between(checkIn, checkOut);
    }

    public double getValorTotal() {
        return getQuantidadeDiarias() * quarto.getValorDiaria();
    }

    public void exibirDados() {
        System.out.println("========== Reserva ==========");
        System.out.println(hospede);
        quarto.exibirInformacoes();
        System.out.println("Check-in : " + checkIn);
        System.out.println("Check-out: " + checkOut);
        System.out.println("Diárias  : " + getQuantidadeDiarias());
        System.out.println("Total    : R$ " + String.format("%.2f", getValorTotal()));
        System.out.println("Situação : " + (checkOutRealizado ? "Check-out realizado" : "Ativa"));
        System.out.println("=============================");
    }
}
