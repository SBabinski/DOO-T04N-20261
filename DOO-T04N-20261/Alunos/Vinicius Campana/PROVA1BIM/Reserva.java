package gerenciarHotel;

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

    public void realizarCheckout() {
        checkoutRealizado = true;
    }

    public boolean isCheckoutRealizado() {
        return checkoutRealizado;
    }

    public double calcularTotal() {
        long dias = ChronoUnit.DAYS.between(checkIn, checkOut);
        return dias * quarto.getValorDiaria();
    }

    public void exibirReserva() {
        System.out.println("\n--- Reserva ---");
        hospede.exibirDados();
        quarto.exibirInformacoes();
        System.out.println("Check-in: " + checkIn);
        System.out.println("Check-out: " + checkOut);
        System.out.println("Checkout realizado: " + (checkoutRealizado ? "Sim" : "Não"));
        System.out.println("Total: " + calcularTotal());
    }
}




