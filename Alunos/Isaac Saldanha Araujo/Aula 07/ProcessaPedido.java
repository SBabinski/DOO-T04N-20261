import java.util.Date;

public class ProcessaPedido {

    public Pedido processar(int id, Cliente cliente, Vendedor vendedor, String loja, Item[] itens) {
        Date agora = new Date();

        // reserva válida por 2 dias
        Date vencimento = new Date(agora.getTime() + (2 * 24 * 60 * 60 * 1000));

        Pedido pedido = new Pedido(id, agora, vencimento, cliente, vendedor, loja, itens);

        if (confirmarPagamento(pedido)) {
            pedido.dataPagamento = new Date();
            System.out.println("Pagamento confirmado!");
        } else {
            System.out.println("Reserva vencida.");
        }

        return pedido;
    }

    private boolean confirmarPagamento(Pedido pedido) {
        Date hoje = new Date();
        return hoje.before(pedido.dataVencimentoReserva);
    }
}