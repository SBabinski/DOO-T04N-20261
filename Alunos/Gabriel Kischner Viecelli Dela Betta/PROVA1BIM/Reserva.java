import java.util.Date;


public class Reserva {

    public Hospedes hospedes;
    public Quartos quartos;
    public Date checkIn;
    public Date checkOut;
    public boolean checkOutRealizado;

    public Reserva(Hospedes hospedes, Quartos quartos, Date checkIn, Date checkOut) {
        this.hospedes = hospedes;
        this.quartos = quartos;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.checkOutRealizado = false;
    }

    public double calcularValorTotal() {
        long diff = checkOut.getTime() - checkIn.getTime();
        long dias = diff / (1000 * 60 * 60 * 24);
        //aqui deu errado antes, meti esse long, pesquisei e falo que com ele dava certo
        // em vez do int
        
        if (dias <= 0) dias = 1; 
        
        return dias * quartos.vlrDiaria;
    }

    
    public void exibirReserva() {
        hospedes.apresentarse();
        quartos.exibirInfo();
        System.out.println("Valor:" + calcularValorTotal());
        System.out.println("status: " + (checkOutRealizado ? "realizado" : "Ativa"));
    }
}
    
