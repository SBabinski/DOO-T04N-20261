package Prova;

public class Hotel {
    private Reserva[] reservas = new Reserva[10];
    private int totalReservas = 0;

    public boolean adicionarReserva(Reserva r) {
        if (totalReservas < 10) {
            reservas[totalReservas++] = r;
            return true;
        }
        return false;
    }

    public void listarReservasAtivas() {
        System.out.println("\n=== RESERVAS ATIVAS ===");
        for (int i = 0; i < totalReservas; i++) {
            if (!reservas[i].isCheckoutRealizado()) {
                System.out.println("Índice: " + i);
                reservas[i].exibirReserva();
            }
        }
    }

    // ✅ NOVO MÉTODO
    public void listarTodasReservas() {
        System.out.println("\n=== TODAS AS RESERVAS ===");
        for (int i = 0; i < totalReservas; i++) {
            System.out.println("Índice: " + i);
            reservas[i].exibirReserva();
        }
    }

    public Reserva getReserva(int i) {
        if (i >= 0 && i < totalReservas) {
            return reservas[i];
        }
        return null;
    }
}