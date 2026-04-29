import java.util.Date;
import java.util.ArrayList;

public class ProcessaPedido {

    public Pedido processar(int id, Cliente cliente, Vendedor vendedor, Loja loja, ArrayList<Item> itens) {
    
        Pedido novoPedido = new Pedido(id, cliente, vendedor, loja, itens);
        return novoPedido;
    }

    public void confirmarPagamento(Pedido pedido) {
        Date dataAtual = new Date();

       
        if (dataAtual.before(pedido.dataVencimentoReserva)) {
            pedido.dataPagamento = dataAtual;
            System.out.println("Pagamento confirmado com sucesso!");
        } else {
            System.out.println("Erro: A reserva do pedido venceu em " + pedido.dataVencimentoReserva);
        }
    }
}