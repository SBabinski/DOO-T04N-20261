import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Vendedor[] vendedores = {
            new Vendedor("João", 30, "My Plant", "Cidade A", "Bairro A", "Rua A", 2000.0, new double[]{1800.0, 1900.0, 2000.0}),
            new Vendedor("Maria", 25, "My Plant", "Cidade A", "Bairro A", "Rua A", 2200.0, new double[]{2000.0, 2100.0, 2200.0})
        };

        Cliente[] clientes = {
            new Cliente("Carlos", 40, "Cidade A", "Bairro A", "Rua A"),
            new Cliente("Ana", 35, "Cidade A", "Bairro A", "Rua A")
        };

        Loja loja = new Loja("My Plant", "Razão Social Ltda", "12.345.678/0001-99", "Cidade A", "Bairro A", "Rua A", vendedores, clientes);

        int opcao;

        do {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    loja.calcularPrecoTotal(scanner);
                    break;
                case 2:
                    loja.calcularTroco(scanner);
                    break;
                case 3:
                    loja.exibirRegistroDeVendas();
                    break;
                case 4:
                    loja.calcularQuantidadeTotalDiaMenu(scanner);
                    break;
                case 5:
                    loja.buscarQuantidadeTotalMesDiaMenu(scanner);
                    break;
                case 6:
                    loja.apresentarse();
                    break;
                case 7:
                    System.out.println("Número de vendedores: " + loja.contarVendedores());
                    break;
                case 8:
                    System.out.println("Número de clientes: " + loja.contarClientes());
                    break;
                case 9:
                    System.out.println("Encerrando a calculadora. Obrigada pela visita!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 9);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n=== Sistema My Plant ===");
        System.out.println("[1] - Calcular Preço Total");
        System.out.println("[2] - Calcular Troco");
        System.out.println("[3] - Ver Registro de Vendas");
        System.out.println("[4] - Calcular Quantidade Total de Vendas em um Dia");
        System.out.println("[5] - Buscar Quantidade Total pelo Mês e Dia");
        System.out.println("[6] - Apresentar Loja");
        System.out.println("[7] - Contar Vendedores");
        System.out.println("[8] - Contar Clientes");
        System.out.println("[9] - Sair");
    }
}