package calculadora_dona_gabrielinha;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Lista de vendas avulsas (fluxo antigo)
        ArrayList<Venda> vendas = new ArrayList<>();

        // Formatador de data
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // ===== SETUP INICIAL =====

        // Endereços
        Endereco enderecoLoja = new Endereco("PR", "Cascavel", "Centro",      "Rua A", 100, "Sala 1");
        Endereco enderecoVendedor1 = new Endereco("PR", "Cascavel", "Centro",      "Rua B", 200, "Casa");
        Endereco enderecoVendedor2 = new Endereco("PR", "Cascavel", "Batel",       "Rua C", 300, "Apto 4");
        Endereco enderecoGerente = new Endereco("PR", "Cascavel", "São Cristóvão","Rua F", 50,  "Casa");
        Endereco enderecoCliente1 = new Endereco("PR", "Cascavel", "Centro",      "Rua D", 10,  "");
        Endereco enderecoCliente2 = new Endereco("PR", "Cascavel", "Universitário","Rua E", 20, "Bloco B");

        // Loja
        Loja loja = new Loja("My Plant", "My Plant LTDA", "123456789", enderecoLoja);

        // Vendedores
        Vendedor vendedor1 = new Vendedor("Joao", 25, enderecoVendedor1, loja, 2000.0);
        Vendedor vendedor2 = new Vendedor("Ana",  28, enderecoVendedor2, loja, 2200.0);

        // Gerente
        Gerente gerente1 = new Gerente("Lucia", 38, enderecoGerente, loja, 5000.0);

        // Clientes
        Cliente cliente1 = new Cliente("Maria",  30, enderecoCliente1);
        Cliente cliente2 = new Cliente("Carlos", 35, enderecoCliente2);

        // Registra na loja
        loja.vendedores.add(vendedor1);
        loja.vendedores.add(vendedor2);
        loja.gerentes.add(gerente1);
        loja.clientes.add(cliente1);
        loja.clientes.add(cliente2);

        // Contador de pedidos
        int proximoIdPedido = 1;

        // ===== MENU =====
        boolean sair = false;

        while (!sair) {

            System.out.println("\n===== MENU DE OPCOES =====\n");
            System.out.println("[1] - Vender");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Ver Relatorio");
            System.out.println("[4] - Consultar Vendas por Dia");
            System.out.println("[5] - Consultar Vendas por Mes");
            System.out.println("[6] - Dados da Loja");
            System.out.println("[7] - Criar Pedido");
            System.out.println("[8] - Executar Teste ProcessaPedido");
            System.out.println("[9] - Sair");
            System.out.println("\n===== ===== ===== =====\n");
            System.out.print("Escolha uma opcao: ");

            int opcao = scanner.nextInt();

            switch (opcao) {

                // ── Registrar venda avulsa ──────────────────────────────
                case 1:
                    try {
                        System.out.print("Informe a quantidade de plantas: ");
                        int quantidade = scanner.nextInt();

                        System.out.print("Informe o preco unitario da planta: ");
                        float valorUn = scanner.nextFloat();
                        scanner.nextLine();

                        System.out.print("Informe a data da venda (dd/MM/yyyy): ");
                        String dataTexto = scanner.nextLine();

                        LocalDate dataVenda = LocalDate.parse(dataTexto, formatter);
                        Venda venda = new Venda(quantidade, valorUn, dataVenda);
                        vendas.add(venda);

                        System.out.println("\nVenda registrada com sucesso em: " + venda.getDataFormatada());
                        System.out.printf("Preco total: R$ %.2f%n", venda.getTotal());
                        System.out.printf("Desconto concedido: R$ %.2f%n", venda.getDesconto());

                    } catch (DateTimeParseException e) {
                        System.out.println("\nData invalida! Use o formato dd/MM/yyyy.");
                    }
                    break;

                // ── Calcular troco ──────────────────────────────────────
                case 2:
                    System.out.print("Informe o valor recebido: ");
                    float valorRecebido = scanner.nextFloat();
                    System.out.print("Informe o valor total da compra: ");
                    float valorCompra = scanner.nextFloat();
                    System.out.printf("Troco devido: R$ %.2f%n", valorRecebido - valorCompra);
                    break;

                // ── Relatório geral ─────────────────────────────────────
                case 3:
                    int totalPlantas = 0;
                    float totalVendas = 0;
                    float totalDescontos = 0;

                    for (Venda v : vendas) {
                        totalPlantas += v.getQuantidade();
                        totalVendas += v.getTotal();
                        totalDescontos += v.getDesconto();
                    }

                    System.out.println("\n===== RELATORIO =====");
                    System.out.println("Quantidade de vendas: " + vendas.size());
                    System.out.println("Total de plantas: " + totalPlantas);
                    System.out.printf("Total vendido: R$ %.2f%n", totalVendas);
                    System.out.printf("Total descontos: R$ %.2f%n", totalDescontos);
                    break;

                // ── Vendas por dia ──────────────────────────────────────
                case 4:
                    try {
                        scanner.nextLine();
                        System.out.print("Informe a data (dd/MM/yyyy): ");
                        LocalDate dataConsulta = LocalDate.parse(scanner.nextLine(), formatter);

                        long qtdDia = vendas.stream()
                                .filter(v -> v.getDataVenda().equals(dataConsulta))
                                .count();

                        System.out.println("Vendas em " + dataConsulta.format(formatter) + ": " + qtdDia);

                    } catch (DateTimeParseException e) {
                        System.out.println("Data invalida! Use o formato dd/MM/yyyy.");
                    }
                    break;

                // ── Vendas por mês ──────────────────────────────────────
                case 5:
                    System.out.print("Informe o mes (01-12): ");
                    int mes = scanner.nextInt();
                    System.out.print("Informe o ano: ");
                    int ano = scanner.nextInt();

                    long qtdMes = vendas.stream()
                            .filter(v -> v.getDataVenda().getMonthValue() == mes
                                      && v.getDataVenda().getYear() == ano)
                            .count();

                    System.out.println("Vendas em " + mes + "/" + ano + ": " + qtdMes);
                    break;

                // ── Dados da loja ───────────────────────────────────────
                case 6:
                    loja.apresentarSe();

                    System.out.println("--- Gerentes ---");
                    for (Gerente g : loja.gerentes) {
                        g.apresentarSe();
                        System.out.println("------------------");
                    }

                    System.out.println("--- Vendedores ---");
                    for (Vendedor v : loja.vendedores) {
                        v.apresentarSe();
                        System.out.println("------------------");
                    }

                    System.out.println("--- Clientes ---");
                    for (Cliente c : loja.clientes) {
                        c.apresentarSe();
                        System.out.println("------------------");
                    }

                    loja.contarGerentes();
                    loja.contarVendedores();
                    loja.contarClientes();
                    break;

                // ── Criar pedido (dados fake) ───────────────────────────
                case 7:
                    System.out.println("\n[ Criando pedido com dados de demonstracao... ]\n");

                    // Itens fake
                    ArrayList<Item> itensPedido = new ArrayList<>();
                    itensPedido.add(new Item(1, "Orquidea Phalaenopsis", "Planta",    89.90));
                    itensPedido.add(new Item(2, "Vaso Rustico G",        "Acessorio", 49.90));
                    itensPedido.add(new Item(3, "Fertilizante 500g",     "Insumo",    19.90));

                    // Datas fake
                    LocalDate criacao = LocalDate.now();
                    LocalDate pagamento = LocalDate.now();
                    LocalDate vencimentoReserva = LocalDate.now().plusDays(3);

                    ProcessaPedido processador = new ProcessaPedido();
                    processador.processar(
                            proximoIdPedido++,
                            criacao,
                            pagamento,
                            vencimentoReserva,
                            cliente1,
                            vendedor1,
                            loja,
                            itensPedido
                    );
                    break;

                // ── Teste ProcessaPedido ────────────────────────────────
                case 8:
                    ProcessaPedido.testar(loja, cliente1, vendedor1);
                    break;

                // ── Sair ────────────────────────────────────────────────
                case 9:
                    System.out.println("Encerrando o sistema. Ate logo!");
                    sair = true;
                    break;

                default:
                    System.out.println("Opcao invalida! Tente novamente.");
            }
        }
        scanner.close();
    }
}
