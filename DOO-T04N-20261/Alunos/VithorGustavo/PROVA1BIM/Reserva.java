
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Reserva {
    private Hospede hospede;
    private Quarto quarto;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private boolean checkoutRealizado;

    public Reserva(Hospede hospede, Quarto quarto, LocalDate checkIn, LocalDate checkOut) {
        if (checkOut.isBefore(checkIn) || checkOut.equals(checkIn)) {
            throw new IllegalArgumentException("A data de check-out deve ser posterior ao check-in.");
        }
        this.hospede = hospede;
        this.quarto = quarto;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.checkoutRealizado = false;
    }

    public Hospede getHospede() {
        return hospede;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public boolean isCheckoutRealizado() {
        return checkoutRealizado;
    }

    public void realizarCheckout() {
        this.checkoutRealizado = true;
    }

    public long getQuantidadeDiarias() {
        return ChronoUnit.DAYS.between(checkIn, checkOut);
    }

    public double getValorTotal() {
        return getQuantidadeDiarias() * quarto.getValorDiaria();
    }

    public String getSituacao() {
        return checkoutRealizado ? "Realizado" : "Não realizado";
    }

    public String getResumo() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Hóspede: " + hospede.getNome() + " | Quarto: " + quarto.getNumero() + " | Check-in: " + checkIn.format(formato)
                + " | Check-out: " + checkOut.format(formato) + " | Situação: " + getSituacao();
    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Reserva\n" +
                "Hóspede: " + hospede + "\n" +
                "Quarto: " + quarto + "\n" +
                "Check-in: " + checkIn.format(formato) + "\n" +
                "Check-out: " + checkOut.format(formato) + "\n" +
                "Diárias: " + getQuantidadeDiarias() + "\n" +
                "Valor total: R$ " + String.format("%.2f", getValorTotal()) + "\n" +
                "Situação do check-out: " + getSituacao();
    }
}
