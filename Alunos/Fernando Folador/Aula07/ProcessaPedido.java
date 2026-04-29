import java.util.Date;

public class ProcessaPedido {

    public Pedido processar(Pedido pedido) {
        Date agora = new Date();

        if (confirmarPagamento(agora, pedido.dataVencimentoReserva)) {
            pedido.dataPagamento = agora;
            System.out.println("Pagamento aprovado");
        } else {
            System.out.println("Reserva vencida");
        }

        return pedido;
    }

    private boolean confirmarPagamento(Date atual, Date vencimento) {
        return atual.before(vencimento);
    }
}