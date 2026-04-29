import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MyPlant {

    static class RegistroVenda {
        int quantidade;
        double valorTotal;
        double desconto;
        LocalDate data;

        RegistroVenda(int quantidade, double valorTotal, double desconto, LocalDate data) {
            this.quantidade = quantidade;
            this.valorTotal = valorTotal;
            this.desconto = desconto;
            this.data = data;
        }
    }

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static List<RegistroVenda> registroVendas = new ArrayList<>();

    public static double calcularPrecoTotal(int quantidade, double precoUnitario) {
        return quantidade * precoUnitario;
    }

    public static double calcularDesconto(int quantidade, double precoTotal) {
        if (quantidade > 10) {
            return precoTotal * 0.05;
        }
        return 0;
    }

    public static double calcularTroco(double valorRecebido, double valorTotal) {
        return valorRecebido - valorTotal;
    }

    public static void registrarVenda(int quantidade, double valorTotal, double desconto, LocalDate data) {
        registroVendas.add(new RegistroVenda(quantidade, valorTotal, desconto, data));
    }

    public static void exibirRegistroVendas() {
        if (registroVendas.isEmpty()) {
            System.out.println("\nNenhuma venda registrada ainda.");
            return;
        }

        System.out.println("\n--- REGISTRO DE VENDAS ---");
        int numeroVenda = 1;
        for (RegistroVenda venda : registroVendas) {
            System.out.println("\nVenda #" + numeroVenda++);
            System.out.println("Data: " + venda.data.format(formatter));
            System.out.println("Quantidade de plantas: " + venda.quantidade);
            System.out.printf("Desconto aplicado: R$ %.2f%n", venda.desconto);
            System.out.printf("Valor total: R$ %.2f%n", venda.valorTotal);
        }
    }

    public static void salvarVendaPorData(Scanner scanner) {
        System.out.println("\n--- Salvar Venda por Data ---");

        LocalDate data = null;
        while (data == null) {
            System.out.print("Digite a data da venda (dd/MM/yyyy): ");
            String dataStr = scanner.next();
            try {
                data = LocalDate.parse(dataStr, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Data invalida! Use o formato dd/MM/yyyy.");
            }
        }

        System.out.print("Digite a quantidade de plantas vendidas: ");
        int quantidade = scanner.nextInt();
        System.out.print("Digite o preco unitario (R$): ");
        double precoUnitario = scanner.nextDouble();

        double precoTotal = calcularPrecoTotal(quantidade, precoUnitario);
        double desconto = calcularDesconto(quantidade, precoTotal);

        System.out.print("Deseja aplicar um desconto adicional? (s/n): ");
        String aplicarDesconto = scanner.next();
        if (aplicarDesconto.equalsIgnoreCase("s")) {
            System.out.print("Digite o percentual de desconto (ex: 10 para 10%): ");
            double percentual = scanner.nextDouble();
            double descontoManual = precoTotal * (percentual / 100);
            desconto += descontoManual;
            System.out.printf("Desconto adicional de %.0f%% aplicado: R$ %.2f%n", percentual, descontoManual);
        }

        double valorAPagar = precoTotal - desconto;
        registrarVenda(quantidade, valorAPagar, desconto, data);

        System.out.println("\nVenda registrada com sucesso!");
        System.out.println("Data: " + data.format(formatter));
        System.out.println("Quantidade: " + quantidade);
        System.out.printf("Desconto total aplicado: R$ %.2f%n", desconto);
        System.out.printf("Valor a pagar: R$ %.2f%n", valorAPagar);
    }

    public static void buscarVendasPorMesEDia(Scanner scanner) {
        System.out.println("\n--- Buscar Vendas por Mes e Dia ---");

        int mes = 0;
        while (mes < 1 || mes > 12) {
            System.out.print("Digite o mes (1-12): ");
            mes = scanner.nextInt();
            if (mes < 1 || mes > 12) {
                System.out.println("Mes invalido! Digite um valor entre 1 e 12.");
            }
        }

        int dia = 0;
        while (dia < 1 || dia > 31) {
            System.out.print("Digite o dia (1-31): ");
            dia = scanner.nextInt();
            if (dia < 1 || dia > 31) {
                System.out.println("Dia invalido! Digite um valor entre 1 e 31.");
            }
        }

        int totalVendas = 0;
        int totalQuantidade = 0;

        for (RegistroVenda venda : registroVendas) {
            if (venda.data.getMonthValue() == mes && venda.data.getDayOfMonth() == dia) {
                totalVendas++;
                totalQuantidade += venda.quantidade;
            }
        }

        System.out.printf("\nResultado para o dia %02d/%02d:%n", dia, mes);
        System.out.println("Total de vendas realizadas: " + totalVendas);
        System.out.println("Total de plantas vendidas: " + totalQuantidade);
    }

    public static void exibirInfoEmpresa() {
        Endereco endLoja = new Endereco("SP", "Sao Paulo", "Centro", "10", "Rua das Flores");
        Endereco endV1 = new Endereco("SP", "Sao Paulo", "Centro", "10", "Rua das Flores");
        Endereco endV2 = new Endereco("SP", "Sao Paulo", "Centro", "10", "Rua das Flores");
        Endereco endG1 = new Endereco("SP", "Sao Paulo", "Centro", "10", "Rua das Flores");
        Endereco endC1 = new Endereco("SP", "Sao Paulo", "Jardins", "500", "Av. Paulista");
        Endereco endC2 = new Endereco("SP", "Sao Paulo", "Moema", "200", "Rua Irai");
        Endereco endC3 = new Endereco("SP", "Sao Paulo", "Centro", "80", "Rua XV");

        Vendedor v1 = new Vendedor("Carlos Silva", 30, "My Plant Centro", endV1, 2500.00);
        Vendedor v2 = new Vendedor("Ana Souza", 25, "My Plant Centro", endV2, 2200.00);
        Gerente g1 = new Gerente("Roberto Alves", 45, "My Plant Centro", endG1, 5000.00);
        Cliente c1 = new Cliente("Joao Pereira", 40, endC1);
        Cliente c2 = new Cliente("Maria Lima", 35, endC2);
        Cliente c3 = new Cliente("Pedro Costa", 28, endC3);

        Loja loja = new Loja(
            "My Plant",
            "My Plant Comercio de Plantas LTDA",
            "12.345.678/0001-99",
            endLoja,
            new Vendedor[]{v1, v2},
            new Cliente[]{c1, c2, c3},
            new Gerente[]{g1}
        );

        System.out.println("\n========== INFORMACOES DA EMPRESA ==========");
        loja.apresentarse();
        System.out.println("Total de vendedores: " + loja.contarVendedores());
        System.out.println("Total de clientes: " + loja.contarClientes());
        System.out.println("Total de gerentes: " + loja.contarGerentes());

        System.out.println("\n--- Vendedores ---");
        for (Vendedor v : loja.vendedores) {
            v.apresentarse();
            System.out.printf("Media salarial: R$ %.2f%n", v.calcularMedia());
            System.out.printf("Bonus: R$ %.2f%n", v.calcularBonus());
            System.out.println();
        }

        System.out.println("--- Gerentes ---");
        for (Gerente g : loja.gerentes) {
            g.apresentarse();
            System.out.printf("Media salarial: R$ %.2f%n", g.calcularMedia());
            System.out.printf("Bonus: R$ %.2f%n", g.calcularBonus());
            System.out.println();
        }

        System.out.println("--- Clientes ---");
        for (Cliente c : loja.clientes) {
            c.apresentarse();
            System.out.println();
        }
    }

    public static void criarPedido() {
        System.out.println("\n--- Criar Pedido ---");

        Endereco endLoja = new Endereco("SP", "Sao Paulo", "Centro", "10", "Rua das Flores");
        Endereco endVendedor = new Endereco("SP", "Sao Paulo", "Centro", "10", "Rua das Flores");
        Endereco endCliente = new Endereco("SP", "Sao Paulo", "Jardins", "500", "Av. Paulista");

        Loja loja = new Loja("My Plant", "My Plant Comercio de Plantas LTDA", "12.345.678/0001-99",
                endLoja, new Vendedor[]{}, new Cliente[]{}, new Gerente[]{});

        Vendedor vendedor = new Vendedor("Carlos Silva", 30, "My Plant Centro", endVendedor, 2500.00);
        Cliente cliente = new Cliente("Joao Pereira", 40, endCliente);

        Item[] itens = {
            new Item(1, "Samambaia Gigante", "Planta", 80.00),
            new Item(2, "Orquidea Phalaenopsis", "Planta", 120.00),
            new Item(3, "Vaso de Ceramica Premium", "Acessorio", 55.00)
        };

        Date agora = new Date();
        Date vencimento = new Date(System.currentTimeMillis() + 2 * 86400000L);

        ProcessaPedido processador = new ProcessaPedido();
        Pedido pedido = processador.processar(
            (int)(Math.random() * 1000) + 1,
            agora, agora, vencimento,
            cliente, vendedor, loja, itens
        );

        System.out.println("\n--- Itens do Pedido ---");
        for (Item item : pedido.itens) {
            item.gerarDescricao();
        }

        double totalBruto = pedido.calcularValorTotal();
        double desconto = 0;

        if (totalBruto > 200) {
            desconto = totalBruto * 0.10;
            System.out.printf("%nDesconto especial (10%% para compras acima de R$ 200,00): - R$ %.2f%n", desconto);
        }

        double totalComDesconto = totalBruto - desconto;
        System.out.printf("Valor total com desconto: R$ %.2f%n", totalComDesconto);

        pedido.gerarDescricaoVenda();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        System.out.println("============================================");
        System.out.println("     Bem-vindo ao Sistema My Plant!");
        System.out.println("        Loja de Plantas Exoticas ");
        System.out.println("============================================");

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("[1] - Calcular Preco Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Registro de Vendas");
            System.out.println("[4] - Salvar Venda por Data");
            System.out.println("[5] - Buscar Vendas por Mes e Dia");
            System.out.println("[6] - Informacoes da Empresa");
            System.out.println("[7] - Criar Pedido");
            System.out.println("[8] - Sair");
            System.out.print("Escolha uma opcao: ");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("\n--- Calcular Preco Total ---");
                    System.out.print("Digite a quantidade de plantas: ");
                    int quantidade = scanner.nextInt();
                    System.out.print("Digite o preco unitario (R$): ");
                    double precoUnitario = scanner.nextDouble();

                    double precoTotal = calcularPrecoTotal(quantidade, precoUnitario);
                    double desconto = calcularDesconto(quantidade, precoTotal);

                    System.out.printf("Preco Total: R$ %.2f%n", precoTotal);
                    if (desconto > 0) {
                        System.out.printf("Desconto automatico (5%% para qtd > 10): R$ %.2f%n", desconto);
                    }

                    System.out.print("Deseja aplicar um desconto adicional? (s/n): ");
                    String aplicarDesc = scanner.next();
                    if (aplicarDesc.equalsIgnoreCase("s")) {
                        System.out.print("Digite o percentual de desconto (ex: 10 para 10%): ");
                        double percentual = scanner.nextDouble();
                        double descontoManual = precoTotal * (percentual / 100);
                        desconto += descontoManual;
                        System.out.printf("Desconto adicional de %.0f%%: R$ %.2f%n", percentual, descontoManual);
                    }

                    double valorAPagar = precoTotal - desconto;
                    System.out.printf("Desconto total: R$ %.2f%n", desconto);
                    System.out.printf("Valor a Pagar: R$ %.2f%n", valorAPagar);

                    registrarVenda(quantidade, valorAPagar, desconto, LocalDate.now());
                    break;

                case 2:
                    System.out.println("\n--- Calcular Troco ---");
                    System.out.print("Digite o valor recebido (R$): ");
                    double valorRecebido = scanner.nextDouble();
                    System.out.print("Digite o valor total da compra (R$): ");
                    double valorTotal = scanner.nextDouble();

                    double troco = calcularTroco(valorRecebido, valorTotal);
                    if (troco < 0) {
                        System.out.printf("Valor insuficiente! Faltam R$ %.2f.%n", Math.abs(troco));
                    } else {
                        System.out.printf("Troco: R$ %.2f%n", troco);
                    }
                    break;

                case 3:
                    exibirRegistroVendas();
                    break;

                case 4:
                    salvarVendaPorData(scanner);
                    break;

                case 5:
                    buscarVendasPorMesEDia(scanner);
                    break;

                case 6:
                    exibirInfoEmpresa();
                    break;

                case 7:
                    criarPedido();
                    break;

                case 8:
                    System.out.println("\nObrigado por usar o sistema My Plant!");
                    System.out.println("Volte sempre!");
                    break;

                default:
                    System.out.println("Opcao invalida! Por favor, escolha entre 1 e 8.");
            }

        } while (opcao != 8);

        scanner.close();
    }
}
