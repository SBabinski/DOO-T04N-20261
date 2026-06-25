public class Hotel {
    private Reserva[] reservas;
    private int total;

    public Hotel() {
        this.reservas = new Reserva[10];
        this.total    = 0;
    }

    public boolean adicionarReserva(Reserva r) {
        if (total >= 10) {
            System.out.println("Limite de reservas atingido!");
            return false;
        }
        reservas[total] = r;
        total++;
        System.out.println("Reserva cadastrada com sucesso. Quarto: " + r.getQuarto().getNumero());
        return true;
    }

    public void listarSemCheckOut() {
        System.out.println("\n=== Reservas Ativas (sem check-out) ===");
        boolean achou = false;
        for (int i = 0; i < total; i++) {
            if (!reservas[i].isCheckOutRealizado()) {
                reservas[i].exibirDados();
                achou = true;
            }
        }
        if (!achou) System.out.println("Nenhuma reserva ativa.");
    }

    public void listarAtivaPorIndice() {
        System.out.println("\n=== Reservas Ativas para Check-out ===");
        boolean achou = false;
        for (int i = 0; i < total; i++) {
            if (!reservas[i].isCheckOutRealizado()) {
                System.out.println("[Índice " + i + "]");
                reservas[i].exibirDados();
                achou = true;
            }
        }
        if (!achou) System.out.println("Nenhuma reserva ativa.");
    }

    public Reserva getReserva(int index) {
        if (index >= 0 && index < total) return reservas[index];
        return null;
    }

    public int getTotal() { return total; }
}