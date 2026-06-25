import java.util.Date;
import java.util.List;

public class ProcessaPedido {

    // Método principal para processar um pedido, incluindo validação de pagamento e geração de descrição.

    public Pedido processar(int id, Date dataCriacao, Date dataPagamento,
                            Date dataVencimentoReserva, Cliente cliente,
                            Vendedor vendedor, String loja, List<Item> itens) {

        Pedido pedido = new Pedido(id, dataCriacao, dataPagamento,
                dataVencimentoReserva, cliente, vendedor, loja, itens);

        if (confirmarPagamento(pedido)) {
            System.out.println("Pagamento confirmado! Pedido processado com sucesso.");
            pedido.gerarDescricaoVenda();
            return pedido;
        } else {
            System.out.println("Pagamento NÃO confirmado: reserva vencida. Pedido cancelado.");
            return null;
        }
    }

    
    // Confirma pagamento apenas se a data atual não ultrapassou o vencimento da reserva.
    
    private boolean confirmarPagamento(Pedido pedido) {
        Date agora = new Date();
        return !agora.after(pedido.dataVencimentoReserva);
    }
}