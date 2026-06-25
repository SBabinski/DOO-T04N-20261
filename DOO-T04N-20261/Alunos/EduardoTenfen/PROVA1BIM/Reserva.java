package prova01;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Reserva {
    private Hospede hospede;
    private Quarto quarto;
    private LocalDate dataCheckIn;
    private LocalDate dataCheckOut;
    private boolean checkOutRealizado;

    public Reserva(Hospede hospede, Quarto quarto, LocalDate dataCheckIn, LocalDate dataCheckOut) {
        this.hospede = hospede;
        this.quarto = quarto;
        this.dataCheckIn = dataCheckIn;
        this.dataCheckOut = dataCheckOut;
        this.checkOutRealizado = false; // Inicia sempre como ativa
    }

    public double calcularValorTotal() {
        long quantidadeDiarias = ChronoUnit.DAYS.between(dataCheckIn, dataCheckOut);
        if (quantidadeDiarias <= 0) {
            quantidadeDiarias = 1; // Conta no mínimo 1 diária
        }
        return quantidadeDiarias * quarto.getValorDiaria();
    }

    public void realizarCheckOut() {
        this.checkOutRealizado = true;
    }

    public boolean isCheckOutRealizado() {
        return checkOutRealizado;
    }

    public void exibirDadosDaReserva() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("--- Dados da Reserva ---");
        System.out.println("Hóspede: " + hospede.getNome() + " (CPF: " + hospede.getCpf() + ")");
        quarto.exibirInformacoes(); 
        System.out.println("Data Check-in: " + dataCheckIn.format(fmt));
        System.out.println("Data Check-out: " + dataCheckOut.format(fmt));
        System.out.println("Status Check-out: " + (checkOutRealizado ? "Realizado" : "Pendente"));
        System.out.println("Valor Total: R$ " + calcularValorTotal());
        System.out.println("------------------------");
    }
}
