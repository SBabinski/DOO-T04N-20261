import java.util.ArrayList;

public class Hotel {
    ArrayList<Reserva> reservas = new ArrayList<>();
    ArrayList<Hospede> hospedes = new ArrayList<>();
    ArrayList<Quarto> quartos = new ArrayList<>();

    public void adicionarReserva(Reserva r) {
        if (reservas.size() < 10) reservas.add(r);
    }

    public void listarReservas() {
        for (Reserva r : reservas) r.exibir();
    }
}