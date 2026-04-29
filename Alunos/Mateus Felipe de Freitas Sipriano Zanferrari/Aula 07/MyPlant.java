import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
 
public class MyPlant {
 
    static List<Venda> registroVendas = new ArrayList<>();
    static DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
 
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<Loja> lojas = Gerador.criarLojas();
 
        System.out.println("=== BEM-VINDO AO GRUPO MY PLANT ===");
        System.out.println("Selecione a unidade que deseja gerenciar:");
        for (int i = 0; i < lojas.size(); i++) {
            System.out.println(i + " - " + lojas.get(i).nomeLoja);
        }
 
        int indiceLoja = scan.nextInt();
        Loja lojaAtual = lojas.get(indiceLoja);
 
        int escolha;
 
        do {
            System.out.println("\nMENU LOJA");
            System.out.println("1 - Calcular Preço");
            System.out.println("2 - Calcular Troco");
            System.out.println("3 - Registro de Vendas");
            System.out.println("4 - Buscar por Data");
            System.out.println("5 - Consultar Vendedores");
            System.out.println("6 - Consultar Clientes");
            System.out.println("7 - Consultar dados da Loja");
            System.out.println("8 - Calcular Média Salarial Vendedores");
            System.out.println("9 - Calcular Bonus Vendedores");
            System.out.println("10 - Gerente da Loja");
            System.out.println("11 - Criar Pedido");
            System.out.println("12 - Listar Pedidos da Loja");
            System.out.println("13 - Voltar ao Menu de Seleção de Loja");
            System.out.println("0 - Encerrar Programa");
 
            escolha = scan.nextInt();
 
            switch (escolha) {
                case 1:
                    System.out.println("Digite a quantidade de itens:");
                    int quant = scan.nextInt();
                    System.out.println("Digite o valor do item:");
                    double valunid = scan.nextDouble();
                    double precototal = quant * valunid;
                    if (quant > 10) {
                        System.out.println("Preço total R$" + precototal * 0.95);
                    } else {
                        System.out.println("Preço total R$" + precototal);
                    }
                    registroVendas.add(new Venda(quant, valunid));
                    System.out.println("Venda Registrada!");
                    break;
 
                case 2:
                    System.out.println("Digite o valor recebido:");
                    double valrecebido = scan.nextDouble();
                    System.out.println("Digite o valor total da compra:");
                    double valcompra = scan.nextDouble();
                    double troco = calculoTroco(valrecebido, valcompra);
                    System.out.println("Troco da Compra R$" + troco);
                    break;
 
                case 3:
                    System.out.println("\n ---  REGISTRO DE VENDAS  ---");
                    if (registroVendas.isEmpty()) {
                        System.out.println("Nenhuma venda registrada.");
                    } else {
                        for (Venda venda : registroVendas) {
                            System.out.println("Data da Venda: "
                                    + venda.getDataVenda().format(formatador)
                                    + ", Quantidade: " + venda.getQuant()
                                    + ", Valor Final: R$ " + venda.getValorFinal());
                        }
                    }
                    break;
 
                case 4:
                    System.out.print("Digite a data para busca (dd/MM/yyyy): ");
                    String dataBusca = scan.next();
                    try {
                        LocalDate dataBuscaFormatada = LocalDate.parse(dataBusca, formatador);
                        System.out.println("\n ------- VENDAS NO PERÍODO " + dataBusca + " -------");
                        boolean encontrouVendas = false;
                        double faturamentoDia = 0.0;
                        for (Venda venda : registroVendas) {
                            if (venda.getDataVenda().equals(dataBuscaFormatada)) {
                                System.out.println("Quantidade: " + venda.getQuant()
                                        + ", Preço Unitário: R$ " + venda.getValunid()
                                        + ", Valor Final: R$ " + venda.getValorFinal());
                                faturamentoDia += venda.getValorFinal();
                                encontrouVendas = true;
                            }
                        }
                        if (!encontrouVendas) {
                            System.out.println("Nenhuma venda encontrada neste período.");
                        } else {
                            System.out.println("Faturamento total R$ " + faturamentoDia);
                        }
                    } catch (Exception e) {
                        System.out.println("Data inválida! Use o formato dd/MM/yyyy.");
                    }
                    break;
 
                case 5:
                    System.out.println("\n--- Lista de Vendedores ---");
                    for (Vendedor v : lojaAtual.vendedores) {
                        v.apresentarVendedor();
                    }
                    break;
 
                case 6:
                    System.out.println("\n--- Lista de Clientes ---");
                    for (Cliente c : lojaAtual.clientes) {
                        c.apresentarCliente();
                    }
                    break;
 
                case 7:
                    lojaAtual.apresentarLoja();
                    break;
 
                case 8:
                    System.out.println("\n--- Média Salarial dos Vendedores ---");
                    for (Vendedor v : lojaAtual.vendedores) {
                        System.out.println("\nVendedor: " + v.nome);
                        v.calcularMedia();
                    }
                    break;
 
                case 9:
                    System.out.println("\n--- Bonus dos Vendedores ---");
                    for (Vendedor v : lojaAtual.vendedores) {
                        System.out.println("\nVendedor: " + v.nome);
                        v.calcularBonus();
                    }
                    break;
 
                case 10:
                    System.out.println("\n--- Gerente da Loja ---");
                    if (lojaAtual.gerente == null) {
                        System.out.println("Nenhum gerente definido para esta loja.");
                    } else {
                        Gerente g = lojaAtual.gerente;
                        g.apresentarse();
                        g.calcularMedia();
                        g.calcularBonus();
                    }
                    break;
 
                case 11:
                    criarPedidoFake(lojaAtual);
                    break;
 
                case 12:
                    System.out.println("\n--- Pedidos da Loja ---");
                    if (lojaAtual.pedidos.isEmpty()) {
                        System.out.println("Nenhum pedido registrado.");
                    } else {
                        for (Pedido p : lojaAtual.pedidos) {
                            p.gerarDescricaoVenda();
                        }
                    }
                    break;
 
                case 13:
                    System.out.println("Voltando ao menu de seleção de loja...\n");
                    main(args);
                    return;
 
                case 0:
                    System.out.println("Encerrando o programa. Obrigado por usar o Sistema!");
                    break;
 
                default:
                    System.out.println("Opção inválida! Por favor, escolha uma opção válida.");
                    break;
            }
 
        } while (escolha != 13 && escolha != 0);
 
        scan.close();
    }
 
    // Cria um pedido com dados fakes e processa via ProcessaPedido
    
    private static void criarPedidoFake(Loja loja) {
        System.out.println("\n--- Criando Pedido (dados de demonstração) ---");
 
        // Itens fake
        List<Item> itens = Arrays.asList(
                new Item(1, "Samambaia Imperial", "Planta", 89.90),
                new Item(2, "Vaso Cerâmico Bege", "Acessório", 45.00),
                new Item(3, "Substrato Universal 5L", "Insumo", 29.99)
        );
 
        System.out.println("Itens do pedido:");
        for (Item item : itens) {
            item.gerarDescricao();
        }
 
        // Cliente e Vendedor da loja atual
        Cliente cliente = loja.clientes.get(0);
        Vendedor vendedor = loja.vendedores.get(0);
 
        // Datas: criação agora, vencimento da reserva daqui a 2 dias
        Date dataCriacao = new Date();
        Date dataPagamento = new Date();
 
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 2);
        Date dataVencimentoReserva = cal.getTime();
 
        // Processar
        ProcessaPedido processador = new ProcessaPedido();
        int idPedido = loja.pedidos.size() + 1;
 
        Pedido pedido = processador.processar(
                idPedido, dataCriacao, dataPagamento,
                dataVencimentoReserva, cliente, vendedor,
                loja.nomeLoja, itens
        );
 
        if (pedido != null) {
            loja.pedidos.add(pedido);
        }
    }
 
    public static double calculoTroco(double valorRecebido, double valorCompra) {
        if (valorRecebido < valorCompra) {
            System.out.println("Valor recebido é menor que o valor da compra. Troco não calculado.");
            return 0.0;
        }
        return valorRecebido - valorCompra;
    }
}