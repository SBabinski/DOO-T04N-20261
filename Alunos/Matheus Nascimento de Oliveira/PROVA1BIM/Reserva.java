public class Reserva {
    private Hospede hospede;
    private Quarto quarto;
    private String dataCheckIn;
    private String dataCheckOut;
    private int diarias;
    private boolean checkoutRealizado;

    public Reserva(Hospede hospede, Quarto quarto, String dataCheckIn, String dataCheckOut, int diarias) {
        this.hospede = hospede;
        this.quarto = quarto;
        this.dataCheckIn = dataCheckIn;
        this.dataCheckOut = dataCheckOut;
        this.diarias = diarias;
        this.checkoutRealizado = false;
    }

    public boolean isCheckoutRealizado() {
        return checkoutRealizado;
    }

    public void realizarCheckout() {
        checkoutRealizado = true;
    }

    public double calcularTotal() {
        return diarias * quarto.getValorDiaria();
    }

    public void mostrar() {
        System.out.println("====== Reserva ======");
        hospede.mostrar();
        quarto.mostrar();
        System.out.println("Check-in: " + dataCheckIn);
        System.out.println("Check-out: " + dataCheckOut);
        System.out.println("Diárias: " + diarias);
        System.out.println("Valor total: R$ " + calcularTotal());
        System.out.println("Situação do check-out: " + (checkoutRealizado ? "realizado" : "não realizado"));
    }
}
