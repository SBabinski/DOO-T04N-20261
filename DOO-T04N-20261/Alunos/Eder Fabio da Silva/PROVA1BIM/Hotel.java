
public class Hotel {

    private Reserva[] reservas = new Reserva[10];
    private int totalReservas = 0;

    // método para adicionar uma reserva ao hotel.
    public boolean adicionarReserva(Reserva reserva) {
        if (totalReservas >= reservas.length) {
            System.out.println("Não há espaço para novas reservas.");
            return false;
        }
        reservas[totalReservas++] = reserva;
        System.out.println("Reserva cadastrada com sucesso.");
        return true;
    }

    // método para realizar o check-out de uma reserva, marcando-a como realizada.
    public void realizarCheckout(int indice) {
        // verifica se o índice é válido.
        int posicao = indice - 1; // Ajusta para índice baseado em 1
        if (posicao < 0 || posicao >= totalReservas) {
            System.out.println("Índice de reserva inválido.");
            return;
        }
        // verifica se a reserva já teve o check-out realizado.
        reservas[posicao].realizarCheckout();
        System.out.println("Check-out realizado.");
    }

    // método para listar todas as reservas ativas (sem check-out).
    public void listarReservasAtivas() {
        boolean encontrou = false;
        System.out.println("\n=== Reservas sem check-out ===");
        for (int i = 0; i < totalReservas; i++) {
            // verifica se a reserva ainda não teve o check-out realizado.
            if (!reservas[i].isCheckoutRealizado()) {
                System.out.println("Reserva #" + (i+1));
                reservas[i].exibirDados();
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhuma reserva ativa.");
        }
    }

    // método para listar todas as reservas, independentemente do status de check-out.
    public void listarTodasReservas() {
        System.out.println("\n=== Todas as reservas ===");
        for (int i = 0; i < totalReservas; i++) {
            System.out.println("Reserva #" + (i+1));
            reservas[i].exibirDados();
        }
    }
}
