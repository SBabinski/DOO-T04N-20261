package service;

import model.Reserva;
import java.util.ArrayList;
import java.util.List;

public class HotelService {
    private List<Reserva> reservas;

    public HotelService() {
        this.reservas = new ArrayList<>();
    }

    public List<Reserva> getReservas() {
        return this.reservas;
    }

    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    public boolean addReserva(Reserva reserva) {
        if (reservas.size() >= 10) {
            System.out.println("Limite de reservas atingido.");

            return false;
        }

        reservas.add(reserva);
        return true;
    }

    public void realizarCheckOut(int indice) {
        if (indice < 0 || indice >= reservas.size()) {
            System.out.println("Reserva inválida.");
            return;
        }

        reservas.get(indice).realizarCheckOut();
        System.out.println("Check-out realizado.");
    }

    public void listarReservasAtivas() {
        System.out.println("Reservas ativas:");

        for (Reserva reserva : this.reservas) {
            if (!reserva.isCheckoutRealizado()) {
                reserva.exibirInfo();
            }
        }
    }

}