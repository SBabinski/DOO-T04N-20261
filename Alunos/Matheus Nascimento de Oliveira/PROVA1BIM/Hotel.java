public class Hotel {
    private Reserva[] reservas;
    private int qtd;

    public Hotel() {
        reservas = new Reserva[10];
        qtd = 0;
    }

    public void adicionar(Reserva reserva) {
        if (qtd < reservas.length) {
            reservas[qtd] = reserva;
            qtd++;
            System.out.println("Reserva adicionada com sucesso.");
        } else {
            System.out.println("Não há espaço para mais reservas.");
        }
    }

    public void listarAtivas() {
        System.out.println("====== Reservas ativas ======");
        boolean encontrou = false;
        for (int i = 0; i < qtd; i++) {
            if (!reservas[i].isCheckoutRealizado()) {
                reservas[i].mostrar();
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhuma reserva ativa encontrada.");
        }
    }

    public Reserva getReserva(int indice) {
        if (indice >= 0 && indice < qtd) {
            return reservas[indice];
        }
        return null;
    }

    public int getQtd() {
        return qtd;
    }
}
