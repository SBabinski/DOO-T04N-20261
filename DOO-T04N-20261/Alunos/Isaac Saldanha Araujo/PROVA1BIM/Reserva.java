public class Reserva {
    private Hospede hospede;
    private Quarto quarto;
    private int dias;
    private String CheckIn;
    private String CheckOut;
    private boolean checkoutRealizado;

    public Reserva(Hospede hospede, Quarto quarto, String CheckIn, String CheckOut, int dias, boolean checkoutRealizado) {
        this.CheckIn = CheckIn;
        this.CheckOut = CheckOut;
        this.checkoutRealizado = checkoutRealizado;
        this.dias = dias;
        this.hospede = hospede;
        this.quarto = quarto;
    }

    public boolean isAtiva() {
        return !checkoutRealizado;
    }

    public double calcularTotal(){
        return dias * quarto.getValorDiarias();
    }

    public void exibir() {
        hospede.exibir();
        quarto.exibirInfo();
        System.out.println("CheckIn: " +CheckIn);
        System.out.println("CheckOut " +CheckOut);
        System.out.println("Diarias: " +dias);
        System.out.println("Total: " + calcularTotal());
        System.out.println("Status " + (checkoutRealizado ? "Encerrado" : "Ativo"));
        System.out.println("-----------------------------------------");

    }

    
}
