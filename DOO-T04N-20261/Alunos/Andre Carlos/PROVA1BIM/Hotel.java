public class Hotel {
    private Reserva[] reservas = new Reserva[10];
    private int contador = 0;


    public boolean adicionarReserva(Reserva reserva){
        if (contador < reservas.length){
            reservas[contador++]  = reserva;
            return true;
        }
        return false;
    }

    public void listarReservasAtivas(){
        for (int i = 0; i < contador; i++){
            if(!reservas[1].isCheckoutRealizado()){
                reservas[i].exibirReserva();
            }
        }
    }
    public Reserva getReserva(int index){
        if (index >= 0 && index < contador){
            return reservas[index];

        }
        return null;
    }
}
