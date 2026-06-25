import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SistemaVendas sistema = new SistemaVendas();

        while (true) {
            System.out.println("\n=== MENU ===");
            System.out.println("1 - Calcular Preço");
            System.out.println("2 - Calcular Troco");
            System.out.println("3 - Consultar vendas do dia");
            System.out.println("4 - Criar pedido");
            System.out.println("5 - Sair");

            int op = sc.nextInt();

            if (op == 1) {
                System.out.print("Quantidade: ");
                int q = sc.nextInt();

                System.out.print("Preço: ");
                double p = sc.nextDouble();

                System.out.println("Total: R$ " + sistema.calcularPrecoTotal(q, p));
            }

            else if (op == 2) {
                System.out.print("Pago: ");
                double pago = sc.nextDouble();

                System.out.print("Total: ");
                double total = sc.nextDouble();

                System.out.println("Troco: R$ " + sistema.calcularTroco(pago, total));
            }

            else if (op == 3) {
                sistema.consultarVendasDia(java.time.LocalDate.now());
            }

            else if (op == 4) {
                System.out.println("Criando pedido fake...");

                Endereco e = new Endereco("PR", "Maringá", "Centro", "123", null);

                Cliente c = new Cliente("João", 30, e);
                Vendedor v = new Vendedor("Maria", 25, e, "My Plant", 2000);

                Loja loja = new Loja("My Plant", "My Plant LTDA", "123", e);

                Item i1 = new Item(1, "Samambaia", "Planta", 50);
                Item i2 = new Item(2, "Cacto", "Planta", 30);

                List<Item> itens = Arrays.asList(i1, i2);

                Calendar cal = Calendar.getInstance();
                Date hoje = cal.getTime();
                cal.add(Calendar.DATE, 1);

                Pedido pedido = new Pedido(1, hoje, cal.getTime(), c, v, loja, itens);

                pedido.gerarDescricaoVenda();

                new ProcessaPedido().processar(pedido);
            }

            else if (op == 5) {
                break;
            }
        }
    }
}