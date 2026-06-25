import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva {
    private Hospede hospede;
    private Quarto quarto;
    private LocalDate dataCheckin;
    private LocalDate dataCheckout;
    private boolean checkoutRealizado;

    public Reserva(Hospede hospede, Quarto quarto, LocalDate dataCheckin, LocalDate dataCheckout) {
        this.hospede = hospede;
        this.quarto = quarto;
        this.dataCheckin = dataCheckin;
        this.dataCheckout = dataCheckout;
        this.checkoutRealizado = false;
    }

    public Hospede getHospede() {
        return hospede;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public LocalDate getDataCheckin() {
        return dataCheckin;
    }

    public LocalDate getDataCheckout() {
        return dataCheckout;
    }

    public boolean isCheckoutRealizado() {
        return checkoutRealizado;
    }

    public void realizarCheckout() {
        if (this.checkoutRealizado) {
            System.out.println("O check-out desta reserva já foi realizado.");
            return;
        }
        this.checkoutRealizado = true;
        System.out.printf("Check-out realizado com sucesso. Total a pagar: R$ %.2f%n", calcularValorTotal());
    }

    public double calcularValorTotal() {
        long diarias = ChronoUnit.DAYS.between(dataCheckin, dataCheckout);
        return diarias * quarto.getValorDiaria();
    }

    public void exibirDados() {
        System.out.println("========== Reserva ==========");
        System.out.println("Hóspede   : " + hospede.getNome());
        System.out.println("CPF       : " + hospede.getCpf());
        System.out.println("Telefone  : " + hospede.getTelefone());
        System.out.println("-----------------------------");
        quarto.exibirInformacoes();
        System.out.println("-----------------------------");
        System.out.println("Check-in  : " + dataCheckin);
        System.out.println("Check-out : " + dataCheckout);
        System.out.println("Diárias   : " + ChronoUnit.DAYS.between(dataCheckin, dataCheckout));
        System.out.printf("Total     : R$ %.2f%n", calcularValorTotal());
        System.out.println("Situação  : " + (checkoutRealizado ? "Check-out realizado" : "Ativa"));
        System.out.println("=============================");
    }
}
