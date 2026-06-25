public class Hotel {
    private static final int CAPACIDADE_MAXIMA = 10;
    private Reserva[] reservas;
    private int totalReservas;

    public Hotel() {
        this.reservas = new Reserva[CAPACIDADE_MAXIMA];
        this.totalReservas = 0;
    }

    public boolean adicionarReserva(Reserva reserva) {
        if (totalReservas >= CAPACIDADE_MAXIMA) {
            System.out.println("Hotel lotado! Não é possível adicionar mais reservas.");
            return false;
        }
        reservas[totalReservas] = reserva;
        totalReservas++;
        System.out.println("Reserva cadastrada com sucesso!");
        return true;
    }

    public void listarReservasAtivas() {
        System.out.println("\n======== RESERVAS SEM CHECK-OUT ========");
        boolean encontrou = false;
        for (int i = 0; i < totalReservas; i++) {
            if (!reservas[i].isCheckoutRealizado()) {
                reservas[i].exibirDados();
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhuma reserva ativa no momento.");
        }
    }

    public Reserva[] getReservas() { return reservas; }
    public int getTotalReservas() { return totalReservas; }
    public int getCapacidadeMaxima() { return CAPACIDADE_MAXIMA; }
}