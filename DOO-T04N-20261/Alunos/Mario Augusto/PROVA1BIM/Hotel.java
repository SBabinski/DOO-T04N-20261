public class Hotel {
    private static final int MAX_RESERVAS = 10;
    private Reserva[] reservas;
    private int totalReservas;

    public Hotel() {
        this.reservas = new Reserva[MAX_RESERVAS];
        this.totalReservas = 0;
    }

    public boolean quartoDisponivel(int numeroQuarto) {
        for (int i = 0; i < totalReservas; i++) {
            if (!reservas[i].isCheckoutRealizado()
                    && reservas[i].getQuarto().getNumero() == numeroQuarto) {
                return false;
            }
        }
        return true;
    }

    public boolean adicionarReserva(Reserva reserva) {
        if (totalReservas >= MAX_RESERVAS) {
            System.out.println("Capacidade máxima de reservas atingida (10 reservas).");
            return false;
        }
        if (!quartoDisponivel(reserva.getQuarto().getNumero())) {
            System.out.println("Quarto " + reserva.getQuarto().getNumero() + " já possui reserva ativa!");
            return false;
        }
        reservas[totalReservas] = reserva;
        totalReservas++;
        System.out.println("Reserva registrada com sucesso!");
        return true;
    }

    public void listarReservasAtivas() {
        System.out.println("\n===== Reservas sem Check-out =====");
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

    public void listarTodasReservas() {
        if (totalReservas == 0) {
            System.out.println("Nenhuma reserva cadastrada.");
            return;
        }
        System.out.println("\n===== Todas as Reservas =====");
        for (int i = 0; i < totalReservas; i++) {
            System.out.println("[" + (i + 1) + "] Hóspede: " + reservas[i].getHospede().getNome()
                    + " | Quarto: " + reservas[i].getQuarto().getNumero()
                    + " | Situação: " + (reservas[i].isCheckoutRealizado() ? "Check-out realizado" : "Ativa"));
        }
    }

    public Reserva getReserva(int indice) {
        if (indice >= 0 && indice < totalReservas) {
            return reservas[indice];
        }
        return null;
    }

    public int getTotalReservas() {
        return totalReservas;
    }
}
