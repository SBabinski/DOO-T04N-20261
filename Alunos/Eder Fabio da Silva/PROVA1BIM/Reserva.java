
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class Reserva {

    private Hospede hospede;
    private Quartos quarto;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private boolean checkoutRealizado;

    // construtor.
    public Reserva(Hospede hospede, Quartos quarto, LocalDate checkIn, LocalDate checkOut) {
        this.hospede = hospede;
        this.quarto = quarto;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.checkoutRealizado = false;
    }

    // getters.
    public boolean isCheckoutRealizado() {
        return checkoutRealizado;
    }
    // método para realizar o check-out da reserva.
    public void realizarCheckout() {
        this.checkoutRealizado = true;
    }

    // método para calcular o valor total da reserva.
    public double calcularValorTotal() {
        long diarias = ChronoUnit.DAYS.between(checkIn, checkOut);
        if (diarias <= 0) {
            diarias = 1;
        }
        return diarias * quarto.getPreco();
    }

    // método para exibir os dados da reserva.
    public void exibirDados() {

        DateTimeFormatter br = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        hospede.exibirDados();
        quarto.exibirDetalhes();
        System.out.println("Check-in: " + checkIn.format(br) + " | Check-out: " + checkOut.format(br)
                + " | Status: " + (checkoutRealizado ? "Realizado" : "Pendente"));
        System.out.println("Valor total: R$ " + calcularValorTotal());
        System.out.println("------------------------------------------------");
    }
}
