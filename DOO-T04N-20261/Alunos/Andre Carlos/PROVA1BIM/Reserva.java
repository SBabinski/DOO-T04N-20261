import java.time.LocalDate;

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

    public double calcularTotal(){
        double dias = checkOut.toEpochDay() - checkIn.toEpochDay();
        return dias = quarto.getValorDiaria();

    }

    public void exibirReserva(){
        System.out.println("Hospede: " + hospede.getNome());
        quarto.exibirInfo();
        System.out.println("Data de CheckIn: " + checkIn);
        System.out.println("Total de Diarias: " + calcularTotal());
        System.out.println("Status de CheckOut: " + (checkoutRealizado ? "Realizado" : "Ativo"));

    }
}


