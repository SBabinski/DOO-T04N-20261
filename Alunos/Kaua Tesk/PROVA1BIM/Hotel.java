public class Hotel {
    
    private Reserva[] reservas;

    private int totalReservas;

    private static final int CAPACIDADE_MAXIMA = 10;
   
    public Hotel() {
        reservas = new Reserva[CAPACIDADE_MAXIMA];
        totalReservas = 0;
    }

    public boolean adicionarReserva(Reserva reserva) {
        //verifica espaço
        if (totalReservas < CAPACIDADE_MAXIMA) {
            reservas[totalReservas] = reserva; 
            totalReservas++;                   
            System.out.println("Reserva adicionada com sucesso!");
            return true; 
        } else {
            
            System.out.println("Não há espaço disponível para novas reservas. Hotel Tesk lotado!");
            return false; 
        }
    }

    public int getTotalReservas() {
        return totalReservas;
    }

    public void listarReservasSemCheckOut() {
        System.out.println("\n===== RESERVAS ATIVAS (sem check-out) =====");

        boolean encontrou = false;

        for (int i = 0; i < totalReservas; i++) {
    
            if (!reservas[i].isCheckOutRealizado()) { 
                reservas[i].exibirDados(); 
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhuma reserva ativa no momento.");
        }
    }
    
    public Reserva[] getReservas() {
        return reservas;
    }
}
