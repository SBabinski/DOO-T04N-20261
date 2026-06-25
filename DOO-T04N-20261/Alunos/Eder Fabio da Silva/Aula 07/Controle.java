public class Controle {

    // Método para exibir o menu da calculadora
    public static void mostrarMenu() {
        int opcao = 0;
        do {
            System.out.println("---MENU DA CALCULADORA---");
            System.out.println("[1] Calcular Preco Total");
            System.out.println("[2] Calcular Troco");
            System.out.println("[3] Relatorio de Vendas");
            System.out.println("[4] Busca todas as vendas por data");
            System.out.println("[5] Menu da Loja");
            System.out.println("[6] Criar Pedido");
            System.out.println("[0] Sair");
            opcao = Calculadora.scanner.nextInt();
            Calculadora.scanner.nextLine(); // Limpar o buffer do scanner
            validarEscolha(opcao);
        } while (opcao != 0);
    }
// Método para validar a escolha do usuário no menu

    private static void validarEscolha(int opcao) {
        switch (opcao) {
            case 1:
                Calculadora.calcularPrecoTotal();
                break;
            case 2:
                Calculadora.calcularTroco();
                break;
            case 3:
                Calculadora.relatorioVendas();
                break;
            case 4:
                Calculadora.relatorioBuscaPorData();
                break;
            case 5:
                Controle.menuLoja();
                break;
            case 6:
                criarPedido();
                break;
            case 0:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opcao invalida!");
        }
    }

   // Método para criar pedido com dados fakes
private static void criarPedido() {
    try {
        Cliente cliente = Loja.clientes.get(0);
        Vendedor vendedor = Loja.vendedores.get(0);
        Loja loja = Loja.lojas.get(0);

        java.util.ArrayList<Item> itensDaVenda = new java.util.ArrayList<>();
        itensDaVenda.add(Loja.itens.get(0));

        java.util.Date dataAtual = new java.util.Date();

        ProcessaPedido processa = new ProcessaPedido();
        Vendas venda = processa.processar(dataAtual, cliente, vendedor, loja, itensDaVenda, 3);
        processa.testarConfirmacao(venda);

    } catch (Exception e) {
        System.out.println("Erro ao criar pedido: " + e.getMessage());
    }
}

    // Método para exibir o menu da loja
    public static void menuLoja() {
       int opcaoLoja = 0;
        do {
            System.out.println("------MENU DA LOJA------");
            System.out.println("[1] Contar Clientes");
            System.out.println("[2] Apresentar Clientes");
            System.out.println("[3] Apresentar Loja");
            System.out.println("[4] Menu do Vendedores");
            System.out.println("[5] Menu do Gerente");
            System.out.println("[0] Menu da Calculadora");
            opcaoLoja = Calculadora.scanner.nextInt();
            Calculadora.scanner.nextLine(); // Limpar o buffer do scanner
            validarEscolhaLoja(opcaoLoja);
        } while (opcaoLoja != 0);
        }
        
    // Método para validar a escolha da loja
    public static void validarEscolhaLoja(int opcaoLoja) {
        switch (opcaoLoja) {
            case 1:
                Loja.contarClientes();
                break;
            case 2:
                Cliente.apresentarCliente();
                break;
            case 3:
                Loja.apresentarLojas();
                break;
            case 4:
                Controle.menuVendedor();
                break;
            case 5:
                Controle.menuGerente();
                break;
            case 0:
                System.out.println("Voltando ao Menu da Calculadora...");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }


    // Método para exibir o menu do vendedor
    public static void menuVendedor() {
        int opcaoVendedor = 0;
        do {
            System.out.println("----MENU DO VENDEDOR----");
            System.out.println("[1] Contar Vendedores");
            System.out.println("[2] Apresentar Vendedores");
            System.out.println("[3] Media Salarial dos Vendedores");
            System.out.println("[4] Bonus do Vendedor");
            System.out.println("[0] Menu da Loja");
            opcaoVendedor = Calculadora.scanner.nextInt();
            Calculadora.scanner.nextLine(); // Limpar o buffer do scanner
            validarEscolhaVendedor(opcaoVendedor);
        } while (opcaoVendedor != 0);
    }

    // Método para validar a escolha do vendedor
    public static void validarEscolhaVendedor(int opcaoVendedor) {
        switch (opcaoVendedor) {
            case 1:
                Loja.contarVendedores();
                break;
            case 2:
                Loja.apresentarVendedores();
                break;
            case 3:
                Loja.calcularMediaVendedores();
                break;
            case 4:
                Loja.calcularBonusVendedores();
                break;
                case 0:
                System.out.println("Voltando ao Menu da Loja...");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }

    // Método para exibir o menu do gerente
    public static void menuGerente() {
        int opcaoGerente = 0;
        do {
            System.out.println("----MENU DO GERENTE----");
            System.out.println("[1] Contar Gerentes");
            System.out.println("[2] Apresentar Gerentes");
            System.out.println("[3] Media Salarial dos Gerentes");
            System.out.println("[4] Bonus do Gerente");
            System.out.println("[0] Menu da Loja");
            opcaoGerente = Calculadora.scanner.nextInt();
            Calculadora.scanner.nextLine(); // Limpar o buffer do scanner
            validarEscolhaGerente(opcaoGerente);
        } while (opcaoGerente != 0);
    }

    // Método para validar a escolha do gerente
    public static void validarEscolhaGerente(int opcaoGerente) {
        switch (opcaoGerente) {
            case 1:
                Loja.contarGerentes();
                break;
            case 2:
                Loja.apresentarGerentes();
                break;
            case 3:
                Loja.calcularMediaGerentes();
                break;
            case 4:
                Loja.calcularBonusGerentes();
                break;
            case 0:
                System.out.println("Voltando ao Menu da Loja...");
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
        }
    }
}
