import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // ---- Endereços ----
        Endereco endLoja = new Endereco("PR", "Cascavel", "Centro",   "Rua XV de Novembro", "200", "Loja 1");
        Endereco endV1   = new Endereco("PR", "Cascavel", "Centro",   "Rua das Flores",     "10",  "");
        Endereco endV2   = new Endereco("PR", "Cascavel", "Jardim",   "Av. das Palmeiras",  "5",   "");
        Endereco endG1   = new Endereco("PR", "Cascavel", "Centro",   "Rua dos Pinheiros",  "33",  "");
        Endereco endC1   = new Endereco("PR", "Cascavel", "Centro",   "Rua das Orquídeas",  "22",  "");
        Endereco endC2   = new Endereco("PR", "Cascavel", "Jardim",   "Av. Brasil",         "100", "");

        // ---- Funcionários ----
        Vendedor v1 = new Vendedor("Carlos Silva",  28, "My Plant", endV1, 3000.00);
        Vendedor v2 = new Vendedor("Ana Souza",     35, "My Plant", endV2, 3200.00);
        Gerente  g1 = new Gerente("Beatriz Lima",   40, "My Plant", endG1, 5000.00);

        // ---- Clientes ----
        Cliente c1 = new Cliente("Maria Lima",    45, endC1);
        Cliente c2 = new Cliente("João Ferreira", 30, endC2);

        // ---- Loja ----
        Loja loja = new Loja(
            "My Plant",
            "My Plant Comércio de Plantas LTDA",
            "12.345.678/0001-99",
            endLoja,
            new Vendedor[]{v1, v2},
            new Gerente[]{g1},
            new Cliente[]{c1, c2}
        );

        // ---- Itens disponíveis ----
        Item[] itensFake = {
            new Item(1, "Samambaia",     "Planta",    45.00),
            new Item(2, "Vaso Cerâmica", "Acessório", 30.00),
            new Item(3, "Substrato 5kg", "Insumo",    25.00)
        };

        // ---- Menu ----
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n========= MY PLANT - MENU =========");
            System.out.println("1. Apresentar Loja");
            System.out.println("2. Listar Vendedores");
            System.out.println("3. Listar Gerentes");
            System.out.println("4. Listar Clientes");
            System.out.println("5. Criar Pedido");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    loja.apresentarse();
                    System.out.println("Vendedores: " + loja.contarVendedores());
                    System.out.println("Gerentes  : " + loja.contarGerentes());
                    System.out.println("Clientes  : " + loja.contarClientes());
                    break;

                case 2:
                    for (Vendedor v : loja.vendedores) {
                        v.apresentarse();
                        System.out.printf("Média: R$ %.2f | Bônus: R$ %.2f%n",
                                v.calcularMedia(), v.calcularBonus());
                    }
                    break;

                case 3:
                    for (Gerente g : loja.gerentes) {
                        g.apresentarse();
                        System.out.printf("Média: R$ %.2f | Bônus: R$ %.2f%n",
                                g.calcularMedia(), g.calcularBonus());
                    }
                    break;

                case 4:
                    for (Cliente c : loja.clientes) {
                        c.apresentarse();
                    }
                    break;

                case 5:
                    Date hoje       = new Date();
                    Date vencimento = new Date(hoje.getTime() + 7L * 24 * 60 * 60 * 1000);
                    ProcessaPedido processador = new ProcessaPedido();
                    Pedido pedido = processador.processar(
                            1, hoje, null, vencimento,
                            c1, v1, loja, itensFake
                    );
                    pedido.gerarDescricaoVenda();
                    break;

                case 0:
                    System.out.println("Encerrando sistema. Até logo!");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }
}
