package doo;

import java.util.Date;

public class ProcessaPedido {

    public Pedido processar(int id, Cliente cliente, Vendedor vendedor, Loja loja) {

        Date agora = new Date();
        Date vencimento = new Date(agora.getTime() + (1000 * 60 * 60 * 24));

        Pedido pedido = new Pedido(id, agora, vencimento, cliente, vendedor, loja);

        pedido.itens[0] = new Item(1, "Planta", "Natural", 50f);
        pedido.itens[1] = new Item(2, "Vaso", "Decoração", 30f);

        if (confirmarPagamento(pedido)) {
            pedido.dataPagamento = new Date();
            System.out.println("Pagamento aprovado!");
        } else {
            System.out.println("Reserva vencida.");
        }

        return pedido;
    }

    private boolean confirmarPagamento(Pedido pedido) {
        Date hoje = new Date();
        return hoje.before(pedido.dataVencimentoReserva);
    }

    public static void testar() {

        Endereco e = new Endereco("PR", "Cascavel", "Centro", 123, "Casa");

        Loja loja = new Loja("My Plant", "LTDA", "123", e);

        Cliente c = new Cliente("Ana", 20, e);
        Vendedor v = new Vendedor("João", 30, loja, e, 2000);

        ProcessaPedido pp = new ProcessaPedido();

        Pedido p = pp.processar(1, c, v, loja);

        System.out.println(p.gerarDescricaoVenda());
    }
}
