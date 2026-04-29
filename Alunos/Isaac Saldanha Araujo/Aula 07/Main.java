import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("1 - Criar pedido");
        int op = sc.nextInt();

        if (op == 1) {
            Endereco end = new Endereco("PR", "Cascavel", "Centro", 123, "Casa");

            Cliente cliente = new Cliente("Isaac", 20, end);
            Vendedor vendedor = new Vendedor("João", 30, end, "Loja Central");

            Item[] itens = {
                new Item(1, "Planta A", "Ornamental", 50),
                new Item(2, "Planta B", "Medicinal", 30)
            };

            ProcessaPedido processador = new ProcessaPedido();
            Pedido pedido = processador.processar(1, cliente, vendedor, "My Plant", itens);

            pedido.gerarDescricaoVenda();
        }

        sc.close();
    }
}