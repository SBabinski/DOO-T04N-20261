public class Reserva {
    private Hospede hospede;
    private Quarto quarto;
    private String checkin;
    private String checkout;
    private boolean realizado;
    private int dias;

    public Reserva(Hospede h, Quarto q, String ci, String co, int dias) {
        this.hospede = h;
        this.quarto = q;
        this.checkin = ci;
        this.checkout = co;
        this.dias = dias;
        this.realizado = false;
    }

    public void realizarCheckout() {
        realizado = true;
    }

    public boolean isCheckoutRealizado() {
        return realizado;
    }

    public double calcularTotal() {
        return dias * quarto.getValorDiaria();
    }

    public void exibirReserva() {
        hospede.exibirDados();
        quarto.exibirInfo();
        System.out.println("Check-in: " + checkin);
        System.out.println("Check-out: " + checkout);
        System.out.println("Total: R$ " + calcularTotal());
        System.out.println("Status: " + (realizado ? "Finalizada" : "Ativa"));
        System.out.println("----------------------");
    }
}