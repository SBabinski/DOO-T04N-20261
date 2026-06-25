import java.util.Date;

public class ProcessaPedido {

    Pedido processar(int id, Date dataCriacao, Date dataPagamento, Date dataVencimentoReserva,
                     Cliente cliente, Vendedor vendedor, Loja loja, Item[] itens) {

        Pedido pedido = new Pedido(id, dataCriacao, dataPagamento, dataVencimentoReserva,
                                   cliente, vendedor, loja, itens);

        if (confirmarPagamento(pedido)) {
            System.out.println("✅ Pedido #" + id + " processado com sucesso!");
        } else {
            System.out.println("❌ Pedido #" + id + " recusado: reserva vencida.");
        }

        return pedido;
    }

    private boolean confirmarPagamento(Pedido pedido) {
        Date hoje = new Date();
        // Confirma se a data atual NÃO é superior à dataVencimentoReserva
        return !hoje.after(pedido.dataVencimentoReserva);
    }

    // ---- Teste do método ----
    public static void main(String[] args) {
        ProcessaPedido processador = new ProcessaPedido();

        // Endereços fake
        Endereco endCliente  = new Endereco("PR", "Cascavel", "Centro", "Rua das Orquídeas", "22", "");
        Endereco endVendedor = new Endereco("PR", "Cascavel", "Centro", "Rua das Flores", "10", "");
        Endereco endLoja     = new Endereco("PR", "Cascavel", "Centro", "Rua XV de Novembro", "200", "");

        // Objetos fake
        Cliente  cliente  = new Cliente("Maria Lima", 45, endCliente);
        Vendedor vendedor = new Vendedor("Carlos Silva", 28, "My Plant", endVendedor, 3000.00);
        Loja     loja     = new Loja("My Plant", "My Plant LTDA", "12.345.678/0001-99",
                                      endLoja,
                                      new Vendedor[]{vendedor},
                                      new Gerente[]{},
                                      new Cliente[]{cliente});

        Item[] itens = {
            new Item(1, "Samambaia", "Planta", 45.00),
            new Item(2, "Vaso Cerâmica", "Acessório", 30.00)
        };

        // Teste 1: reserva válida (vence daqui 7 dias)
        Date hoje        = new Date();
        Date vencValido  = new Date(hoje.getTime() + 7L * 24 * 60 * 60 * 1000);
        Date vencVencido = new Date(hoje.getTime() - 1L * 24 * 60 * 60 * 1000); // ontem

        System.out.println("-- Teste 1: reserva válida --");
        Pedido p1 = processador.processar(1, hoje, null, vencValido, cliente, vendedor, loja, itens);
        p1.gerarDescricaoVenda();

        System.out.println();

        System.out.println("-- Teste 2: reserva vencida --");
        Pedido p2 = processador.processar(2, hoje, null, vencVencido, cliente, vendedor, loja, itens);
        p2.gerarDescricaoVenda();
    }
}
