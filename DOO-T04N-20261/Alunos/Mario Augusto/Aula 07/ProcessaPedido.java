import java.util.Date;

public class ProcessaPedido {

    public Pedido processar(int id, Date dataCriacao, Date dataPagamento, Date dataVencimentoReserva,
            Cliente cliente, Vendedor vendedor, Loja loja, Item[] itens) {

        Pedido pedido = new Pedido(id, dataCriacao, dataPagamento, dataVencimentoReserva,
                cliente, vendedor, loja, itens);

        if (confirmarPagamento(pedido)) {
            System.out.println("Pagamento confirmado! Pedido #" + id + " processado com sucesso.");
        } else {
            System.out.println("Pagamento nao confirmado! A reserva do pedido #" + id + " esta vencida.");
        }

        return pedido;
    }

    private boolean confirmarPagamento(Pedido pedido) {
        Date hoje = new Date();
        return !hoje.after(pedido.dataVencimentoReserva);
    }

    public static void main(String[] args) {
        System.out.println("=== TESTE ProcessaPedido ===\n");

        Endereco endLoja = new Endereco("SP", "Sao Paulo", "Centro", "10", "Rua das Flores");
        Endereco endVendedor = new Endereco("SP", "Sao Paulo", "Centro", "10", "Rua das Flores");
        Endereco endCliente = new Endereco("SP", "Sao Paulo", "Jardins", "500", "Av. Paulista");

        Loja loja = new Loja("My Plant", "My Plant Comercio de Plantas LTDA", "12.345.678/0001-99",
                endLoja, new Vendedor[]{}, new Cliente[]{}, new Gerente[]{});

        Vendedor vendedor = new Vendedor("Carlos Silva", 30, "My Plant Centro", endVendedor, 2500.00);
        Cliente cliente = new Cliente("Joao Pereira", 40, endCliente);

        Item[] itens = {
            new Item(1, "Samambaia", "Planta", 45.00),
            new Item(2, "Vaso Ceramica", "Acessorio", 30.00)
        };

        ProcessaPedido processador = new ProcessaPedido();

        System.out.println("-- Teste 1: Reserva valida --");
        Date amanha = new Date(System.currentTimeMillis() + 86400000L);
        Pedido p1 = processador.processar(1, new Date(), new Date(), amanha, cliente, vendedor, loja, itens);
        p1.gerarDescricaoVenda();

        System.out.println();

        System.out.println("-- Teste 2: Reserva vencida --");
        Date ontem = new Date(System.currentTimeMillis() - 86400000L);
        Pedido p2 = processador.processar(2, new Date(), new Date(), ontem, cliente, vendedor, loja, itens);
        p2.gerarDescricaoVenda();
    }
}
