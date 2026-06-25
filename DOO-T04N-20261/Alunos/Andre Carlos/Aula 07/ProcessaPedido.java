import java.util.Date;

public class ProcessaPedido {

    public void processar(Pedido pedido) {
        if (confirmarPagamento(pedido)) {
            pedido.setDataPagamento(new Date());
            System.out.println("Pagamento aprovado!");
        } else {
            System.out.println("Reserva vencida!");
        }
    }

    private boolean confirmarPagamento(Pedido pedido) {
        return new Date().before(pedido.getDataVencimentoReserva());
    }
}