import java.util.ArrayList;

public class Hotel {
    private ArrayList<Reserva> reservas = new ArrayList<>();
    private final int limite = 10;

    public boolean adicionarReserva(Reserva r) {
        if (reservas.size() < limite) {
            reservas.add(r);
            return true;
        }
        return false;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    public void listarReservasAtivas() {
        for (Reserva r : reservas) {
            if (!r.isRealizado()) {
                r.exibirReserva();
            }
        }
    }

    public void realizarCheckout(int indice) {
        if (indice >= 0 && indice < reservas.size()) {
            reservas.get(indice).realizarCheckout();
        } else {
            System.out.println("Reserva inválida!");
        }
    }
}