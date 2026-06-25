package hotel;

import java.util.ArrayList;

public class Hotel {

    private static ArrayList<Reserva> reservas = new ArrayList<>();

    public void adicionarReserva(Reserva reserva) {
        if (reservas.size() < 10) {
            reservas.add(reserva);
            System.out.println("Reserva adicionada com sucesso!");
        } else {
            System.out.println("Limite de reservas atingido!");
        }
    }

    public void listarReservasAtivas() {
        System.out.println("\n=== RESERVAS ATIVAS ===\n");
        for (Reserva r : reservas) {
            if (!r.isCheckoutRealizado()) {
                r.apresentarSe();
            }
        }
        System.out.println("\n=== === === ===\n");
    }
    
    public static ArrayList<Reserva> getReserva() {
        return reservas;
    }
}