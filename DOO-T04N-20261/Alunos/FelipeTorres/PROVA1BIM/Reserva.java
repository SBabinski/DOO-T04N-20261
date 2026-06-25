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

    public void realizarCheckOut() {
        this.checkOutRealizado = true;
    }

    public boolean isCheckOutRealizado() {
        return checkOutRealizado;
    }

    public double calcularTotal() {
        long dias = ChronoUnit.DAYS.between(checkIn, checkOut);
        return dias * quarto.getValorDiaria();
    }

    public void exibirDados() {
        hospede.exibirDados();
        quarto.exibirInfo();
        System.out.println("Check-in: " + checkIn);
        System.out.println("Check-out: " + checkOut);
        System.out.println("Status: " + (checkOutRealizado ? "Finalizado" : "Ativo"));
        System.out.println("Total: R$" + calcularTotal());
        System.out.println("----------------------------");
    }
}