import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ProcessaPedidoTest {

    static int testesPassaram = 0;
    static int testesFalharam = 0;

    public static void main(String[] args) {
        System.out.println("==========================================");
        System.out.println("   TESTES - ProcessaPedido");
        System.out.println("==========================================\n");

        // Objetos de suporte reutilizáveis
        Endereco endCliente = new Endereco("PR", "Cascavel", "Centro", "Rua das Flores", "10", "");
        Endereco endVendedor = new Endereco("PR", "Cascavel", "Norte", "Av. Brasil", "500", "");

        Cliente cliente = new Cliente("Ana Oliveira", 30, endCliente);
        Vendedor vendedor = new Vendedor("João Silva", 25, "My Plant - Cascavel", endVendedor, 2200.00);

        List<Item> itens = Arrays.asList(
                new Item(1, "Samambaia", "Planta", 89.90),
                new Item(2, "Vaso Cerâmico", "Acessório", 45.00)
        );

        
        // TESTE 1: Reserva válida (vencimento daqui a 3 dias) → deve processar
        
        System.out.println("TESTE 1: Reserva dentro do prazo");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 3);
        Date vencimentoFuturo = cal.getTime();

        ProcessaPedido processador1 = new ProcessaPedido();
        Pedido resultado1 = processador1.processar(
                1, new Date(), new Date(), vencimentoFuturo,
                cliente, vendedor, "My Plant - Cascavel", itens
        );
        assertNotNull("Pedido deve ser retornado quando reserva está válida", resultado1);

        
        // TESTE 2: Reserva vencida (vencimento há 1 dia) → deve cancelar
        
        System.out.println("\nTESTE 2: Reserva vencida");
        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.DAY_OF_MONTH, -1);
        Date vencimentoPassado = cal2.getTime();

        ProcessaPedido processador2 = new ProcessaPedido();
        Pedido resultado2 = processador2.processar(
                2, new Date(), new Date(), vencimentoPassado,
                cliente, vendedor, "My Plant - Cascavel", itens
        );
        assertNull("Pedido deve ser NULL quando reserva está vencida", resultado2);

        
        // TESTE 3: Verificar valor total dos itens calculado corretamente
        
        System.out.println("\nTESTE 3: Valor total do pedido");
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, 5);
        ProcessaPedido processador3 = new ProcessaPedido();
        Pedido resultado3 = processador3.processar(
                3, new Date(), new Date(), cal.getTime(),
                cliente, vendedor, "My Plant - Cascavel", itens
        );
        double totalEsperado = 89.90 + 45.00;
        assertEquals("Valor total do pedido deve ser R$ " + totalEsperado,
                totalEsperado, resultado3.calcularValorTotal());

        
        // RESULTADO FINAL
        
        System.out.println("\n==========================================");
        System.out.printf("  RESULTADO: %d passou(aram) | %d falhou(aram)%n",
                testesPassaram, testesFalharam);
        System.out.println("==========================================");
    }


    static void assertNotNull(String mensagem, Object obj) {
        if (obj != null) {
            System.out.println("PASSOU: " + mensagem);
            testesPassaram++;
        } else {
            System.out.println("FALHOU: " + mensagem);
            testesFalharam++;
        }
    }

    static void assertNull(String mensagem, Object obj) {
        if (obj == null) {
            System.out.println("PASSOU: " + mensagem);
            testesPassaram++;
        } else {
            System.out.println("FALHOU: " + mensagem);
            testesFalharam++;
        }
    }

    static void assertEquals(String mensagem, double esperado, double atual) {
        if (Math.abs(esperado - atual) < 0.001) {
            System.out.println("PASSOU: " + mensagem);
            testesPassaram++;
        } else {
            System.out.printf("FALHOU: %s (esperado=%.2f, atual=%.2f)%n", mensagem, esperado, atual);
            testesFalharam++;
        }
    }
}