import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class CalculadoraMelhorada {

    // Lista para armazenar vendas
    private static ArrayList<String> registroVendas = new ArrayList<>();

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro(scanner, "Escolha uma opcao: ");

            switch (opcao) {
                case 1:
                    calcularPrecoTotal(scanner);
                    break;
                case 2:
                    calcularTroco(scanner);
                    break;
                case 3:
                    exibirRegistroVendas();
                    break;
                case 4:
                    System.out.println("Saindo da calculadora... Ate logo!");
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }

            System.out.println();
        } while (opcao != 4);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("==== Calculadora da Loja da Dona Gabrielinha ====");
        System.out.println("[1] - Calcular Preco Total (com desconto)");
        System.out.println("[2] - Calcular Troco");
        System.out.println("[3] - Ver Registro de Vendas");
        System.out.println("[4] - Sair");
    }

    private static void calcularPrecoTotal(Scanner scanner) {
        double quantidade = lerDouble(scanner, "Digite a quantidade da planta: ");
        double precoUnitario = lerDouble(scanner, "Digite o preco unitario da planta: ");

        double total = quantidade * precoUnitario;
        double desconto = 0;

        // 🔥 NOVA REGRA DE DESCONTO
        if (quantidade > 10) {
            desconto = total * 0.05;
            total -= desconto;
            System.out.println("Desconto de 5% aplicado!");
        }

        System.out.printf("Preco final da venda: R$ %.2f%n", total);

        // 🔥 REGISTRAR VENDA
        String venda = String.format(
            "Qtd: %.0f | Unit: R$ %.2f | Total: R$ %.2f | Desconto: R$ %.2f",
            quantidade, precoUnitario, total, desconto
        );

        registroVendas.add(venda);
    }

    private static void calcularTroco(Scanner scanner) {
        double valorRecebido = lerDouble(scanner, "Digite o valor recebido do cliente: ");
        double valorCompra = lerDouble(scanner, "Digite o valor total da compra: ");

        double troco = valorRecebido - valorCompra;
        if (troco < 0) {
            System.out.printf("Valor insuficiente. Faltam R$ %.2f%n", Math.abs(troco));
        } else {
            System.out.printf("Troco a ser devolvido: R$ %.2f%n", troco);
        }
    }

    // 🔥 NOVA FUNÇÃO: EXIBIR VENDAS
    private static void exibirRegistroVendas() {
        System.out.println("==== REGISTRO DE VENDAS ====");

        if (registroVendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
        } else {
            for (String venda : registroVendas) {
                System.out.println(venda);
            }
        }
    }

    private static int lerInteiro(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            if (scanner.hasNextInt()) {
                int valor = scanner.nextInt();
                scanner.nextLine();
                return valor;
            }
            System.out.println("Entrada invalida. Digite um numero inteiro.");
            scanner.nextLine();
        }
    }

    private static double lerDouble(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            if (scanner.hasNextDouble()) {
                double valor = scanner.nextDouble();
                scanner.nextLine();
                return valor;
            }
            System.out.println("Entrada invalida. Digite um numero valido.");
            scanner.nextLine();
        }
    }
}