//pedidos e fakes
import java.util.Date;

public class ProcessaPedido {
    private static int contadorPedidos = 0;

    // método para criar pedido com itens fakes
    public Pedido processar(Cliente cliente, Vendedor vendedor, Loja loja) {
        contadorPedidos++; // id, pq n queria deixar manual a contagem
        Date agora = new Date();
        Date vencimento = new Date(agora.getTime() + (2 * 24 * 60 * 60 * 1000)); 

        Pedido pedido = new Pedido(contadorPedidos, agora, vencimento, cliente, vendedor, loja);

        pedido.adicionaItem(new Item(1, "Orquídea", "Planta", 50.0));
        pedido.adicionaItem(new Item(2, "Vaso Decorativo", "Acessório", 30.0));
        pedido.adicionaItem(new Item(3, "Terra Adubada", "Suprimento", 20.0));

        return pedido;
    }

    private boolean confirmarPagamento(Pedido pedido) {
        Date agora = new Date();
        return agora.before(pedido.getDataVencimentoReserva());
    }

    public void testar(Pedido pedido) {
        if(confirmarPagamento(pedido)) {
            pedido.setDataPagamento(new Date());
            System.out.println("Pagamento confirmado para o pedido " + pedido.getId());
        } else {
            System.out.println("Reserva vencida, pagamento não confirmado.");
        }
    }
}
