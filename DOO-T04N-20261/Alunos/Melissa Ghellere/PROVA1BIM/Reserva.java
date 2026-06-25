import java.time.LocalDate; //coração do sistema (Hospede + Reserva)
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

    public void realizarCheckout() {
        this.checkoutRealizado = true;
    }

    public boolean isCheckoutRealizado() {
        return checkoutRealizado;
    }

    public void exibirDadosReserva() {
        long dias = ChronoUnit.DAYS.between(checkIn, checkOut);
        if (dias <= 0) dias = 1; // Mínimo de 1 diária
        double valorTotal = dias * quarto.getValorDiaria();

        System.out.println("\n--- Detalhes da Reserva ---");
        System.out.println(hospede.toString());
        quarto.exibirInformacoes();
        System.out.println("Check-in: " + checkIn + " | Check-out: " + checkOut);
        System.out.println("Situação: " + (checkoutRealizado ? "Realizado" : "Ativa"));
        System.out.println("Valor Total (" + dias + " diárias): R$" + valorTotal);
    }
}
