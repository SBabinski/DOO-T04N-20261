import java.util.ArrayList;
import java.util.List;

public class controleReserva {
    private static final int LIMITE_RESERVAS = 10;
    private List<Reserva> reservas;

    public controleReserva() {
        this.reservas = new ArrayList<>();
    }

    public boolean adicionarReserva(Reserva reserva) {
        if (reservas.size() < LIMITE_RESERVAS) {
            reservas.add(reserva);
            return true;
        } else {
            System.out.println("Limite de 10 reservas atingido!");
            return false;
        }
    }

    public void listarReservasAtivas() {
        System.out.println(" Reservas Ativas (sem check-out) ");
        boolean temAtivas = false;
        for (Reserva r : reservas) {
            if (!r.isSaidaRealizada()) {
                r.mostrarResumo();
                System.out.println("");
                temAtivas = true;
            }
        }
        if (!temAtivas) {
            System.out.println("Nenhuma reserva ativa encontrada.");
        }
    }

    public boolean realizarCheckOut(int indice) {
        if (indice >= 0 && indice < reservas.size()) {
            Reserva r = reservas.get(indice);
            if (!r.isSaidaRealizada()) {
                r.realizarSaida();
                System.out.println("Check-out realizado com sucesso!");
                return true;
            } else {
                System.out.println("Esta reserva já teve check-out realizado.");
                return false;
            }
        } else {
            System.out.println("Índice inválido!");
            return false;
        }
    }

    public int getQuantidadeReservas() {
        return reservas.size();
    }

    public Reserva getReserva(int indice) {
        if (indice >= 0 && indice < reservas.size()) {
            return reservas.get(indice);
        }
        return null;
    }
}