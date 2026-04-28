public class Hotel {
    private Reserva[] reservas = new Reserva[10];
    private int totalReservas = 0;

    public boolean adicionarReserva(Reserva reserva) {
        if (totalReservas < reservas.length) {
            reservas[totalReservas++] = reserva;
            return true;
        }
        return false;
    }

    public void listarReservasAtivas() {
        System.out.println("Reservas ativas:");
        for (int i = 0; i < totalReservas; i++) {
            if (!reservas[i].isCheckOutRealizado()) {
                System.out.println("Índice: " + i);
                reservas[i].exibirDados();
                
            }
        }
    }

    public void realizarCheckOut(int index) {
        if (index >= 0 && index < totalReservas) {
            reservas[index].realizarCheckOut();
        }
    }
}