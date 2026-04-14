import java.util.Date;

public class ProcessaPedido {

    public Pedido processar(int id, Cliente cliente, Vendedor vendedor, String loja) {
        Date agora = new Date();
        Date vencimento = new Date(agora.getTime() + 86400000); // +1 dia

        Pedido pedido = new Pedido(id, agora, vencimento, cliente, vendedor, loja);

        if (confirmarPagamento(pedido)) {
            pedido.dataPagamento = new Date();
            System.out.println("Pagamento confirmado!");
        } else {
            System.out.println("Reserva vencida!");
        }

        return pedido;
    }

    private boolean confirmarPagamento(Pedido pedido) {
        Date agora = new Date();
        return agora.before(pedido.dataVencimentoReserva);
    }
}