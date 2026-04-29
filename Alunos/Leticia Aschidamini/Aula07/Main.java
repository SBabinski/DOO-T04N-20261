import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        Loja loja = new Loja("My Plant", "My Plant LTDA", "123456789", "PR", "Cascavel", "Centro", "Rua Erechim", 100);
        Vendedor vendedor = new Vendedor("João", 30, "My Plant", "PR", "Cascavel", "Centro", "Rua Presidente Kennedy", 250, 2000);
        Cliente cliente = new Cliente("Maria", 25, "PR", "Cascavel", "Centro", "Rua Sem criatividade", 450);
        Gerente gerente = new Gerente("Carlos", 45, "My Plant", "PR", "Cascavel", "Centro", "Rua Central", 1000, 5000);
        
        loja.getVendedores().add(vendedor);
        loja.getClientes().add(cliente);
        
        boolean executando = true;
        
        while (executando) {
            exibirMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1:
                    exibirInformacoesLoja(loja);
                    break;
                case 2:
                    exibirInformacoesVendedor(vendedor);
                    break;
                case 3:
                    exibirInformacoesCliente(cliente);
                    break;
                case 4:
                    exibirInformacoesGerente(gerente);
                    break;
                case 5:
                    criarETestPedido(loja, cliente, vendedor);
                    break;
                case 6:
                    System.out.println("Encerrando aplicação...");
                    executando = false;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
            System.out.println();
        }
        
        scanner.close();
    }
    
    private static void exibirMenu() {
        System.out.println("========== MENU PRINCIPAL ==========");
        System.out.println("1 - Exibir informações da Loja");
        System.out.println("2 - Exibir informações do Vendedor");
        System.out.println("3 - Exibir informações do Cliente");
        System.out.println("4 - Exibir informações do Gerente");
        System.out.println("5 - Criar e testar um Pedido");
        System.out.println("6 - Sair");
        System.out.print("Escolha uma opção: ");
    }
    
    private static void exibirInformacoesLoja(Loja loja) {
        System.out.println("\n========== INFORMAÇÕES DA LOJA ==========");
        loja.apresentarSe();
        loja.contarVendedores();
        loja.contarClientes();
    }
    
    private static void exibirInformacoesVendedor(Vendedor vendedor) {
        System.out.println("\n========== INFORMAÇÕES DO VENDEDOR ==========");
        vendedor.apresentarSe();
        System.out.println("Média de Salários: R$ " + String.format("%.2f", vendedor.calcularMedia()));
        System.out.println("Bônus (20%): R$ " + String.format("%.2f", vendedor.calcularBonus()));
        System.out.println("\nEndereço:");
        vendedor.getEndereco().apresentarLogradouro();
    }
    
    private static void exibirInformacoesCliente(Cliente cliente) {
        System.out.println("\n========== INFORMAÇÕES DO CLIENTE ==========");
        cliente.apresentarSe();
        System.out.println("\nEndereço:");
        cliente.getEndereco().apresentarLogradouro();
    }
    
    private static void exibirInformacoesGerente(Gerente gerente) {
        System.out.println("\n========== INFORMAÇÕES DO GERENTE ==========");
        gerente.apresentarSe();
        System.out.println("Média de Salários: R$ " + String.format("%.2f", gerente.calcularMedia()));
        System.out.println("Bônus (35%): R$ " + String.format("%.2f", gerente.calcularBonus()));
        System.out.println("\nEndereço:");
        gerente.getEndereco().apresentarLogradouro();
    }
    
    private static void criarETestPedido(Loja loja, Cliente cliente, Vendedor vendedor) {
        System.out.println("\n========== CRIAR E TESTAR PEDIDO ==========");
        
        Item item1 = new Item(1, "Planta Suculenta", "Planta", 45.90);
        Item item2 = new Item(2, "Produto 2", "Acessório", 25.50);
        Item item3 = new Item(3, "Produto sem crtiatividade", "Insumo", 35.00);
        
        ArrayList<Item> itens = new ArrayList<>();
        itens.add(item1);
        itens.add(item2);
        itens.add(item3);
        
        System.out.println("Itens do Pedido:");
        for (Item item : itens) {
            item.gerarDescricao();
        }
        
        Date dataCriacao = new Date();
        Date dataVencimentoReserva = new Date(dataCriacao.getTime() + (30 * 24 * 60 * 60 * 1000));
        Date dataPagamento = new Date();

        ProcessaPedido processador = new ProcessaPedido();
        Pedido pedido = processador.processar(1001, dataCriacao, dataPagamento, dataVencimentoReserva, cliente, vendedor, loja, itens);
        
        if (pedido != null) {
            System.out.println();
            pedido.gerarDescricaoVenda();
            System.out.println("\nDetalhes dos Itens:");
            for (Item item : pedido.getItens()) {
                System.out.println("- " + item.getNome() + ": R$ " + String.format("%.2f", item.getValor()));
            }
            System.out.println("\nValor Total do Pedido: R$ " + String.format("%.2f", pedido.calcularValorTotal()));
        } else {
            System.out.println("Falha na criação do pedido.");
        }
    }
}