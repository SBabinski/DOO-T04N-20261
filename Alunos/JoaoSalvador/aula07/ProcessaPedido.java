package calculadora_dona_gabrielinha;

import java.time.LocalDate;
import java.util.ArrayList;

public class ProcessaPedido {

    public Pedido processar(int id, LocalDate dataCriacao, LocalDate dataPagamento, LocalDate dataVencimentoReserva, Cliente cliente, Vendedor vendedor, Loja loja, ArrayList<Item> itens) {

        Pedido pedido = new Pedido(id, dataCriacao, dataPagamento, dataVencimentoReserva, cliente, vendedor, loja);
        pedido.getItens().addAll(itens);

        if (confirmarPagamento(pedido)) {
            System.out.println("V - Pagamento confirmado para o Pedido #" + id + ".");
            pedido.gerarDescricaoVenda();
        } else {
            System.out.println("F - Pedido #" + id + " recusado: a reserva está vencida. Nenhum pagamento foi processado.");
        }

        return pedido;
    }

    private boolean confirmarPagamento(Pedido pedido) {
        return !LocalDate.now().isAfter(pedido.getDataVencimentoReserva());
    }

    public static void testar(Loja loja, Cliente cliente, Vendedor vendedor) {
        System.out.println("\n===== TESTE ProcessaPedido =====\n");

        ArrayList<Item> itens = new ArrayList<>();
        itens.add(new Item(1, "Samambaia Pendente", "Planta", 45.90));
        itens.add(new Item(2, "Vaso Ceramica M", "Acessorio", 29.90));

        ProcessaPedido processador = new ProcessaPedido();

        // Cenario 1: reserva valida (vence amanha)
        System.out.println("-- Cenario 1: reserva valida --");
        processador.processar(101, LocalDate.now(), LocalDate.now(), LocalDate.now().plusDays(1), cliente, vendedor, loja, itens);

        // Cenario 2: reserva ja vencida (venceu ontem)
        System.out.println("-- Cenario 2: reserva vencida --");
        processador.processar(102, LocalDate.now().minusDays(3), LocalDate.now(), LocalDate.now().minusDays(1), cliente, vendedor, loja, itens);

        System.out.println("===== FIM DO TESTE =====\n");
    }
}
