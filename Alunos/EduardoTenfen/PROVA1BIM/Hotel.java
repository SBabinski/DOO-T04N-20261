package prova01;

public class Hotel {
    // Array com limite de 10 reservas, conforme a regra de negócio da prova
    private Reserva[] reservas;
    private int contadorReservas;

    public Hotel() {
        this.reservas = new Reserva[10];
        this.contadorReservas = 0;
    }

    public boolean adicionarReserva(Reserva reserva) {
        if (contadorReservas < 10) {
            reservas[contadorReservas] = reserva;
            contadorReservas++;
            return true;
        } else {
            System.out.println("Erro: Limite máximo de 10 reservas atingido!");
            return false;
        }
    }

    public Reserva[] getReservas() {
        return reservas;
    }

    public int getContadorReservas() {
        return contadorReservas;
    }

    public void listarReservasAtivas() {
        boolean encontrou = false;
        for (int i = 0; i < contadorReservas; i++) {
            if (!reservas[i].isCheckOutRealizado()) {
                System.out.println("\n[Reserva ID: " + i + "]");
                reservas[i].exibirDadosDaReserva();
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhuma reserva ativa (sem check-out) no momento.");
        }
    }

    public void realizarCheckOut(int indiceReserva) {
        if (indiceReserva >= 0 && indiceReserva < contadorReservas) {
            if (!reservas[indiceReserva].isCheckOutRealizado()) {
                reservas[indiceReserva].realizarCheckOut();
                System.out.println("Check-out realizado com sucesso! Valor a pagar: R$ " 
                                   + reservas[indiceReserva].calcularValorTotal());
            } else {
                System.out.println("O check-out desta reserva já havia sido feito.");
            }
        } else {
            System.out.println("ID de reserva inválido.");
        }
    }
}
