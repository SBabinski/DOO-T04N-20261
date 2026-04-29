
public class Hotel {

    private Reserva[] reservas = new Reserva[10];
    private int quantidade = 0;

    public void adicionarReserva(Reserva minhaReserva) {
        if (quantidade < reservas.length) {
            reservas[quantidade++] = minhaReserva;
            System.out.println("Reserva Adicionada com sucesso!");
        } else {
            System.out.println("Limite de reservas atingido!!!");
        }
    }

    public void listarReservasAtivas() {
        System.out.println("\n  Reservas ativas:");
        for (int i = 0; i < quantidade; i++) {
            if (reservas[i].isAtiva()) {
                reservas[i].exibir();
            }
        }
    }
}
