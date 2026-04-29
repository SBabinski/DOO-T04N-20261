public class Hotel {
    Reserva[] reservas = new Reserva[10];
    int cont = 0;

    public void adicionar(Reserva r) {
        if (cont < 10) {
            reservas[cont] = r;
            cont++;
        } else {
            System.out.println("Hotel lotado.");
        }
    }

    public void listarAtivas() {
        for (int i = 0; i < cont; i++) {
            if (!reservas[i].realizado) {
                reservas[i].mostrar();
            }
        }
    }
}