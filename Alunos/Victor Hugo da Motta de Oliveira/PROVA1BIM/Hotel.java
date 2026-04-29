public class Hotel {
    private Reserva[] reservas;
    private int totalReservas;

    public Hotel() {
        this.reservas = new Reserva[10];
        this.totalReservas = 0;
    }

    public void adicionarReserva(Reserva r) {
        if (totalReservas < reservas.length) {
            reservas[totalReservas] = r;
            totalReservas++;
        } else {
            System.out.println("Hotel lotado! Limite de " + reservas.length + " reservas atingido!");
        }
    }

    public void realizarCheckOut(int indice) {
        if (indice >= 0 && indice < totalReservas && reservas[indice] != null) {
            reservas[indice].realizarCheckOut();
            System.out.println("Check-out realizado com sucesso! Valor: R$ " + reservas[indice].calculoValorTotal());
        } else {
            System.out.println("Número de reserva inválido.");
        }
    }

    public void listarAtivas() {
        System.out.println("\n=== Reservas Ativas ===");
        boolean achou = false;
        for (int i = 0; i < totalReservas; i++) {
            if (reservas[i] != null && !reservas[i].isCheckOutFeito()) {
                reservas[i].exibirReserva();
                achou = true;
            }
        }
        if (!achou) {
            System.out.println("Nenhuma reserva ativa encontrada.");
        }
    }
}