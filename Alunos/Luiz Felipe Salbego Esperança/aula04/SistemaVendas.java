import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SistemaVendas {

    static class Venda {
        double quantidade;
        double precoUnitario;
        double total;
        double desconto;
        LocalDate data;

        Venda(double quantidade, double precoUnitario, double total, double desconto, LocalDate data) {
            this.quantidade = quantidade;
            this.precoUnitario = precoUnitario;
            this.total = total;
            this.desconto = desconto;
            this.data = data;
        }

        @Override
        public String toString() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return String.format(
                "Data: %s | Quantidade: %.0f | Preco Unitario: R$ %.2f | Total: R$ %.2f | Desconto: R$ %.2f",
                data.format(formatter),
                quantidade,
                precoUnitario,
                total,
                desconto
            );
        }
    }

    private static ArrayList<Venda> registroVendas = new ArrayList<>();

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
                    buscarVendasPorData(scanner);
                    break;
                case 5:
                    System.out.println("Encerrando o sistema.");
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }

            System.out.println();
        } while (opcao != 5);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("===== Loja da Dona Gabrielinha =====");
        System.out.println("1 - Calcular preco total");
        System.out.println("2 - Calcular troco");
        System.out.println("3 - Exibir registro de vendas");
        System.out.println("4 - Buscar vendas por data");
        System.out.println("5 - Sair");
    }

    private static void calcularPrecoTotal(Scanner scanner) {
        double quantidade = lerDouble(scanner, "Informe a quantidade: ");
        double precoUnitario = lerDouble(scanner, "Informe o preco unitario: ");

        double total = quantidade * precoUnitario;
        double desconto = 0;

        if (quantidade > 10) {
            desconto = total * 0.05;
            total -= desconto;
            System.out.println("Desconto de 5% aplicado.");
        }

        System.out.printf("Valor total da venda: R$ %.2f%n", total);

        LocalDate dataAtual = LocalDate.now();
        Venda venda = new Venda(quantidade, precoUnitario, total, desconto, dataAtual);
        registroVendas.add(venda);
    }

    private static void buscarVendasPorData(Scanner scanner) {
        System.out.print("Informe a data (dd/MM/yyyy): ");
        String entrada = scanner.nextLine();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dataBusca;

        try {
            dataBusca = LocalDate.parse(entrada, formatter);
        } catch (Exception e) {
            System.out.println("Data invalida.");
            return;
        }

        int totalVendas = 0;

        for (Venda venda : registroVendas) {
            if (venda.data.equals(dataBusca)) {
                System.out.println(venda);
                totalVendas++;
            }
        }

        System.out.println("Total de vendas na data informada: " + totalVendas);
    }

    private static void exibirRegistroVendas() {
        if (registroVendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
            return;
        }

        for (Venda venda : registroVendas) {
            System.out.println(venda);
        }
    }

    private static void calcularTroco(Scanner scanner) {
        double valorRecebido = lerDouble(scanner, "Informe o valor recebido: ");
        double valorCompra = lerDouble(scanner, "Informe o valor da compra: ");

        double troco = valorRecebido - valorCompra;

        if (troco < 0) {
            System.out.printf("Valor insuficiente. Faltam R$ %.2f%n", Math.abs(troco));
        } else {
            System.out.printf("Troco a ser devolvido: R$ %.2f%n", troco);
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