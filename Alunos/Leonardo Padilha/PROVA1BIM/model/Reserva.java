package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

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

    public Hospede getHospede() {
        return this.hospede;
    }

    public Quarto getQuarto() {
        return this.quarto;
    }

    public LocalDate getCheckIn() {
        return this.checkIn;
    }

    public LocalDate getCheckOut() {
        return this.checkOut;
    }

    public boolean isCheckoutRealizado() {
        return this.checkoutRealizado;
    }

    public void setHospede(Hospede hospede) {
        this.hospede = hospede;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public void setCheckoutRealizado(boolean checkoutRealizado) {
        this.checkoutRealizado = checkoutRealizado;
    }

    public void realizarCheckOut() {
        this.checkoutRealizado = true;
    }

    public double calcularValorTotal() {
        long dias = ChronoUnit.DAYS.between(this.checkIn, this.checkOut);
        return dias * quarto.getValorDiaria();
    }

    public void exibirInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Reserva:");
        System.out.println(this.hospede);
        quarto.exibirInfo();
        System.out.println("Check-in: " + this.checkIn.format(formatter) +
                ", Check-out: " + this.checkOut.format(formatter));
        System.out.println("Checkout realizado: " + (this.checkoutRealizado ? "Sim" : "Não"));
        System.out.println("Valor total: " + calcularValorTotal());
        System.out.println("------------------------------");
    }
}