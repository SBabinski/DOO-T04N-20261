import java.time.LocalDate;
import java.util.ArrayList;

public class ProcessaPedido {
    public Pedido processar(int id, LocalDate dataCriacao, LocalDate dataVencimentoReserva, Cliente cliente,
            Vendedor vendedor, Loja loja, ArrayList<Item> itens) {
        Pedido pedido = new Pedido();
        pedido.setId(id);
        pedido.setDataCriacao(dataCriacao);
        pedido.setDataVencimentoReserva(dataVencimentoReserva);
        pedido.setCliente(cliente);
        pedido.setVendedor(vendedor);
        pedido.setLoja(loja);
        pedido.setItens(itens);
        return pedido;
    }

    public boolean confirmarPagamento(Pedido pedido) {
        LocalDate hoje = LocalDate.now();
        return !hoje.isAfter(pedido.getDataVencimentoReserva());
    }
}