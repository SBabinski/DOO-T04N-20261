import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva {
    private Hospede hospede;
    private Quarto quarto;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private boolean checkOutFeito;

    public Reserva(Hospede hospede, Quarto quarto, LocalDate checkIn, LocalDate checkOut) {
        this.hospede = hospede;
        this.quarto = quarto;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.checkOutFeito = false;
    }

    public double calculoValorTotal() {
        long dias = ChronoUnit.DAYS.between(checkIn, checkOut);
        if (dias == 0) dias = 1;
        return dias * quarto.getValorDiaria();
    }

    public void realizarCheckOut() {
        this.checkOutFeito = true;
    }

    public boolean isCheckOutFeito() {
        return checkOutFeito;
    }

    public void exibirReserva() {
        System.out.println("\n--- Detalhes da Reserva ---");
        System.out.println("Hóspede: " + hospede.getNome());
        quarto.exibirInfo();
        System.out.println("Check-in: " + checkIn + " | Check-out: " + checkOut);
        System.out.println("Valor Total: R$ " + calculoValorTotal());
        System.out.println("Status: " + (checkOutFeito ? "Check-out realizado" : "Check-out pendente"));
    }
}