public class Hotel {
    public Reserva[] reservas = new Reserva[10];
    public int total = 0;

    public void adicionarReserva(Reserva r) {
        if (total < 10) {
            reservas[total] = r;
            total++;
        } else {
            System.out.println("Limite atingido");
        }
    }

    public void listarAtivas() {
        for (int i = 0; i < total; i++) {
            if (!reservas[i].finalizada) {
                reservas[i].mostrar();
            }
        }
    }
}