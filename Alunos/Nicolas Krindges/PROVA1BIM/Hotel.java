public class Hotel {
    private static final int MAX_RESERVAS = 10;
    private Reserva[] reservas;
    private int totalReservas;

    public Hotel() {
        reservas = new Reserva[MAX_RESERVAS];
        totalReservas = 0;
    }

    public boolean adicionarReserva(Reserva reserva) {
        if (totalReservas >= MAX_RESERVAS) {
            System.out.println("Hotel lotado! Não há espaço para novas reservas.");
            return false;
        }
        reservas[totalReservas++] = reserva;
        System.out.println("Reserva cadastrada com sucesso!");
        return true;
    }

    public void listarReservasSemCheckOut() {
        System.out.println("\n===== Reservas Ativas (sem check-out) =====");
        boolean encontrou = false;
        for (int i = 0; i < totalReservas; i++) {
            if (!reservas[i].isCheckOutRealizado()) {
                reservas[i].exibirDados();
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhuma reserva ativa encontrada.");
        }
    }

    public Reserva[] getReservas() { return reservas; }
    public int getTotalReservas() { return totalReservas; }
}
