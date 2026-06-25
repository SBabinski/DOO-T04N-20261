package hotel;

public class Hotel {

    Reserva[] reservas = new Reserva[10];
    Quarto[] quartos = new Quarto[10];

    int total = 0;
    int totalQuartos = 0;

    public void adicionarReserva(Reserva reserva) {

        if (total < 10) {
            reservas[total] = reserva;
            total++;
            System.out.println("Reserva cadastrada.");
        } else {
            System.out.println("Hotel lotado.");
        }
    }

    public void adicionarQuarto(Quarto quarto) {

        if (totalQuartos < 10) {
            quartos[totalQuartos] = quarto;
            totalQuartos++;
            System.out.println("Quarto cadastrado.");
        } else {
            System.out.println("Limite de quartos atingido.");
        }
    }

    public void listarQuartos() {

        for (int i = 0; i < totalQuartos; i++) {
            System.out.println("Quarto " + (i + 1));
            quartos[i].mostrar();
        }
    }

    public void listarSemCheckOut() {

        System.out.println("\nRESERVAS ATIVAS:");

        for (int i = 0; i < total; i++) {
            if (!reservas[i].checkOut) {
                System.out.println("Reserva " + (i + 1));
                reservas[i].mostrar();
            }
        }
    }

    public void listarTodas() {

        for (int i = 0; i < total; i++) {
            System.out.println("Reserva " + (i + 1));
            reservas[i].mostrar();
        }
    }

    public void fazerCheckOut(int indice) {

        if (indice >= 0 && indice < total) {
            reservas[indice].fazerCheckOut();
            System.out.println("Check-out realizado.");
        } else {
            System.out.println("Reserva inválida.");
        }
    }
}