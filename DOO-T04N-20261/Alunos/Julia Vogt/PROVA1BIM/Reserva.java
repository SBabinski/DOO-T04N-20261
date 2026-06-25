package hotel;

import java.time.LocalDate;

public class Reserva {

    Hospede hospede;
    Quarto quarto;
    LocalDate entrada;
    LocalDate saida;
    boolean checkOut;

    public Reserva(Hospede hospede, Quarto quarto,
                   LocalDate entrada, LocalDate saida) {

        this.hospede = hospede;
        this.quarto = quarto;
        this.entrada = entrada;
        this.saida = saida;
        checkOut = false;
    }

    public void fazerCheckOut() {
        checkOut = true;
    }

    public double totalPagar() {
        long dias = saida.toEpochDay() - entrada.toEpochDay();
        return dias * quarto.diaria;
    }

    public void mostrar() {
        System.out.println("Hóspede: " + hospede.nome);
        quarto.mostrar();
        System.out.println("Entrada: " + entrada);
        System.out.println("Saída: " + saida);
        System.out.println("Check-out: " + (checkOut ? "Feito" : "Pendente"));
        System.out.println("Total: R$ " + totalPagar());
    }
}