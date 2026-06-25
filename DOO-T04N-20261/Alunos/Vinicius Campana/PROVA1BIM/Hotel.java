package gerenciarHotel;

public class Hotel {
    private Reserva[] reservas = new Reserva[10];
    private int quantidade = 0;

    public boolean adicionarReserva(Reserva reserva) {
        if (quantidade < 10) {
            reservas[quantidade] = reserva;
            quantidade++;
            return true;
        }
        return false;
    }

    public void listarReservasAtivas() {
        for (int i = 0; i < quantidade; i++) {
            if (!reservas[i].isCheckoutRealizado()) {
                reservas[i].exibirReserva();
            }
        }
    }

    public Reserva getReserva(int index) {
        if (index >= 0 && index < quantidade) {
            return reservas[index];
        }
        return null;
    }

    public int getQuantidade() {
        return quantidade;
    }
}