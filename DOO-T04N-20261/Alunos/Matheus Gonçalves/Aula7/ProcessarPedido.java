package fag;

import java.util.Date;

public class ProcessarPedido extends Pedido {

    public void processar(Cliente cliente, Vendedor vendedor, Loja loja) {

        this.id = (int) (Math.random() * 1000);
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;

        Date agora = new Date();
        this.dataCriacao = agora;

        this.dataVencimentoReserva = new Date(agora.getTime() + (1000 * 60 * 60 * 24));

        if (confirmarPagamento()) {
            this.dataPagamento = new Date();
            System.out.println("Pagamento confirmado!");
        } else {
            System.out.println("Reserva vencida!");
        }
    }

    private boolean confirmarPagamento() {
        Date agora = new Date();
        return agora.before(this.dataVencimentoReserva);
    }
}