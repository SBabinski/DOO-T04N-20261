import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Endereco enderecoLoja = new Endereco("SP", "São Paulo", "Centro", "100", "Loja 1");
        Endereco enderecoFunc = new Endereco("SP", "São Paulo", "Centro", "200", "Casa");

        Funcionario[] funcionarios = {
            new Vendedor("João", 30, "My Plant", enderecoFunc, 2000.0, new double[]{1800.0, 1900.0, 2000.0}),
            new Gerente("Maria", 35, "My Plant", enderecoFunc, 3000.0, new double[]{2800.0, 2900.0, 3000.0})
        };

        Cliente[] clientes = {
            new Cliente("Carlos", 40, enderecoFunc),
            new Cliente("Ana", 35, enderecoFunc)
        };

        Loja loja = new Loja("My Plant", "Razão Social Ltda", "12.345.678/0001-99", enderecoLoja, funcionarios, clientes);

        ProcessaPedido processador = new ProcessaPedido();

        int opcao;

        do {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    loja.apresentarse();
                    break;
                case 2:
                    System.out.println("Número de funcionários: " + loja.contarFuncionarios());
                    break;
                case 3:
                    System.out.println("Número de clientes: " + loja.contarClientes());
                    break;
                case 4:
                    criarPedidoFake(processador, loja);
                    break;
                case 5:
                    System.out.println("Encerrando a calculadora. Obrigada pela visita!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 5);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n=== Sistema My Plant ===");
        System.out.println("[1] - Apresentar Loja");
        System.out.println("[2] - Contar Funcionários");
        System.out.println("[3] - Contar Clientes");
        System.out.println("[4] - Criar Pedido (Dados Fakes)");
        System.out.println("[5] - Sair");
    }

    private static void criarPedidoFake(ProcessaPedido processador, Loja loja) {
        Date hoje = new Date();
        Date amanha = new Date(hoje.getTime() + 86400000L * 7);
        Cliente cliente = loja.getClientes()[0];
        Vendedor vendedor = (Vendedor) loja.getFuncionarios()[0];
        Item[] itens = {
            new Item(1, "Planta ornamental", "Planta", 25.0),
            new Item(2, "Vaso", "Acessório", 15.0)
        };
        processador.processar(1, hoje, hoje, amanha, cliente, vendedor, loja, itens);
    }
}