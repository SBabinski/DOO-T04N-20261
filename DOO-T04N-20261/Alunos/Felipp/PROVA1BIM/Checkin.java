package prova.objetos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Checkin {
    private Hospede hospede;
    private TipoQuarto quarto;
    private LocalDateTime dataCheckin;
    private LocalDateTime dataCheckout;
    private int quantidadeDiarias;
    private boolean situacaoCheckout;
    private double valorTotal;

    public Checkin(Hospede hospede, TipoQuarto quarto, LocalDateTime dataCheckin, LocalDateTime dataCheckout, int quantidadeDiarias) {
        this.hospede = hospede;
        this.quarto = quarto;
        this.dataCheckin = dataCheckin;
        this.dataCheckout = dataCheckout;
        this.quantidadeDiarias = quantidadeDiarias;
        this.situacaoCheckout = false;
        this.valorTotal = quantidadeDiarias * quarto.getValorDiaria();
    }

    public boolean getSituacaoCheckout() {
        return situacaoCheckout;
    }

    public void realizarCheckout() {
        this.situacaoCheckout = true;
    }

    public void listarCheckin() {
        System.out.println("Hóspede: " + hospede.getNome());
        System.out.println("Quarto: " + quarto.getNumeroQuarto());
        System.out.println("Data de Check-in: " + dataCheckin.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println("Data de Check-out: " + dataCheckout.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        System.out.println("Quantidade de Diárias: " + quantidadeDiarias);
        System.out.println("Valor Total: R$ " + valorTotal);
        System.out.println("Situação do Check-out: " + (situacaoCheckout ? "Realizado" : "Não realizado"));
    }
}
