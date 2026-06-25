package Objetos;

import java.util.Date;

public class ProcessarPedido {

    public Pedido processar(Cliente cliente, Vendedor vendedor, Item[] itens) {

        Pedido pedido = new Pedido();

        pedido.setCliente(cliente);
        pedido.setVendedor(vendedor);
        pedido.setItens(itens);

        Date agora = new Date();
        pedido.setDataCriacao(agora);

        Date vencimento = new Date(agora.getTime() + (1000 * 60 * 60 * 24));
        pedido.setDataVencimentoReserva(vencimento);

        if (confirmarPagamento(vencimento)) {
            System.out.println("Pagamento confirmado!");
        } else {
            System.out.println("Reserva vencida!");
        }

        return pedido;
    }

    private boolean confirmarPagamento(Date vencimento) {
        return new Date().before(vencimento);
    }
}