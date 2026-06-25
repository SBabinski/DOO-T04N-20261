package Alunos.Melissa_Ghellere.PROVA1BIM;

public class Hotel {
    private Reserva[] reservas;
    private int totalReservas;

    public Hotel() {
        this.reservas = new Reserva[10];
        this.totalReservas = 0;
    }

    public void adicionarReserva(Reserva r) {
        if (totalReservas < 10) {
            reservas[totalReservas++] = r;
            System.out.println("Reserva registrada!");
        } else {
            System.out.println("Erro: Hotel lotado (máximo 10 reservas)");
        }
    }

    public void listarReservasAtivas() {
        System.out.println("\n=== Reservas Sem Check-out ===");
        boolean encontrou = false;
        for (int i = 0; i < totalReservas; i++) {
            if (!reservas[i].isCheckoutRealizado()) {
                reservas[i].exibirDadosReserva();
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("Nenhuma reserva ativa.");
    }

    public Reserva[] getReservas() { return reservas; }
    public int getTotalReservas() { return totalReservas; }
}
