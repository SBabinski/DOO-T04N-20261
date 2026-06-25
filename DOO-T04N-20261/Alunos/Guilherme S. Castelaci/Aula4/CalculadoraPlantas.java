import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CalculadoraPlantas {

    static class Venda {
        int quantidade;
        double valorFinal;
        LocalDate data;

        public Venda(int quantidade, double valorFinal, LocalDate data) {
            this.quantidade = quantidade;
            this.valorFinal = valorFinal;
            this.data = data;
        }
    }

    static List<Venda> vendas = new ArrayList<>();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Registrar venda");
            System.out.println("2 - Buscar vendas por dia");
            System.out.println("3 - Buscar vendas por mês");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {

                case 1:
                    registrarVenda(scanner);
                    break;

                case 2:
                    buscarPorDia(scanner);
                    break;

                case 3:
                    buscarPorMes(scanner);
                    break;

                case 0:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);

        scanner.close();
    }

    public static void registrarVenda(Scanner scanner) {

        System.out.print("Quantidade de plantas: ");
        int quantidade = scanner.nextInt();

        System.out.print("Preço unitário: ");
        double preco = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Data da venda (dd/MM/yyyy): ");
        String dataTexto = scanner.nextLine();

        LocalDate data = LocalDate.parse(dataTexto, formatter);

        double total = quantidade * preco;
        double desconto = 0;

        if (quantidade > 10) {
            desconto = total * 0.05;
            System.out.println("Desconto de 5% aplicado!");
        }

        double valorFinal = total - desconto;

        vendas.add(new Venda(quantidade, valorFinal, data));

        System.out.println("Venda registrada!");
        System.out.println("Valor final: R$ " + valorFinal);
    }

    public static void buscarPorDia(Scanner scanner) {

        System.out.print("Digite a data (dd/MM/yyyy): ");
        String dataTexto = scanner.nextLine();

        LocalDate dataBusca = LocalDate.parse(dataTexto, formatter);

        int totalVendas = 0;

        for (Venda v : vendas) {
            if (v.data.equals(dataBusca)) {
                totalVendas++;
            }
        }

        System.out.println("Total de vendas no dia: " + totalVendas);
    }

    public static void buscarPorMes(Scanner scanner) {

        System.out.print("Digite mês e ano (MM/yyyy): ");
        String entrada = scanner.nextLine();

        String[] partes = entrada.split("/");
        int mes = Integer.parseInt(partes[0]);
        int ano = Integer.parseInt(partes[1]);

        int totalVendas = 0;

        for (Venda v : vendas) {
            if (v.data.getMonthValue() == mes && v.data.getYear() == ano) {
                totalVendas++;
            }
        }

        System.out.println("Total de vendas no mês: " + totalVendas);
    }
}