import java.util.Date;
import java.util.ArrayList;

public class ProcessaPedido {
    
    public Pedido processar(int id, Date dataCriacao, Date dataPagamento, Date dataVencimentoReserva, Cliente cliente, Vendedor vendedor, Loja loja, ArrayList<Item> itens) {
        
        if (!confirmarPagamento(dataVencimentoReserva)) {
            System.out.println("ERRO: Pagamento não pode ser realizado! Data de hoje é superior à data de vencimento da reserva.");
            return null;
        }

        Pedido pedido = new Pedido(id, dataCriacao, dataPagamento, dataVencimentoReserva, cliente, vendedor, loja);
        
        for (Item item : itens) {
            pedido.adicionarItem(item);
        }

        System.out.println("Pedido processado com sucesso!");
        return pedido;
    }

    private boolean confirmarPagamento(Date dataVencimentoReserva) {
        Date dataAtual = new Date();
        return !dataAtual.after(dataVencimentoReserva);
    }
}
