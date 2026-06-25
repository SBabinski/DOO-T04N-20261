import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class CalculadoraLoja {

    static Scanner sc = new Scanner(System.in);
    static Map<LocalDate, Integer> vendasPorDia = new HashMap<>();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        int opcao;

        do {
            mostrarMenu();
            opcao = lerInt("Escolha uma opcao: ");

            switch (opcao) {
                case 1 -> calcularPrecoTotal();
                case 2 -> calcularTroco();
                case 3 -> registrarVendas();
                case 4 -> consultarPorData();
                case 5 -> consultarPorMes();
                case 6 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opcao invalida!\n");
            }

        } while (opcao != 6);

        sc.close();
    }

    static void mostrarMenu() {
        System.out.println("=== Calculadora da Loja de Plantas da Dona Gabrielinha ===");
        System.out.println("[1] - Calculo do preco total");
        System.out.println("[2] - Calculo do Troco");
        System.out.println("[3] - Registrar vendas do dia");
        System.out.println("[4] - Consultar vendas por data");
        System.out.println("[5] - Consultar total de vendas no mes");
        System.out.println("[6] - Sair");
    }

    static void calcularPrecoTotal() {
        int quantidade = lerInt("Digite a quantidade da planta: ");
        double preco = lerDouble("Digite o preco da planta: ");

        double total = quantidade * preco;
        System.out.printf("Preco total: R$ %.2f\n\n", total);
    }

    static void calcularTroco() {
        double valorPago = lerDouble("Digite o valor pago: ");
        double totalCompra = lerDouble("Digite o total da compra: ");

        double troco = valorPago - totalCompra;

        if (troco < 0) {
            System.out.printf("Valor insuficiente! Faltam R$ %.2f\n\n", -troco);
        } else {
            System.out.printf("Troco: R$ %.2f\n\n", troco);
        }
    }

    static void registrarVendas() {
        LocalDate data = lerData();
        int qtd = lerInt("Quantidade de vendas do dia: ");

        vendasPorDia.put(data, qtd);
        System.out.println("Vendas registradas com sucesso!\n");
    }

    static void consultarPorData() {
        LocalDate data = lerData();

        Integer vendas = vendasPorDia.get(data);

        if (vendas != null) {
            System.out.println("Total no dia: " + vendas + "\n");
        } else {
            System.out.println("Nenhuma venda registrada.\n");
        }
    }

    static void consultarPorMes() {
        int mes = lerInt("Digite o mes (1-12): ");
        int ano = lerInt("Digite o ano: ");

        int total = vendasPorDia.entrySet().stream()
                .filter(e -> e.getKey().getMonthValue() == mes && e.getKey().getYear() == ano)
                .mapToInt(Map.Entry::getValue)
                .sum();

        System.out.println("Total de vendas no mes: " + total + "\n");
    }

    static int lerInt(String msg) {
        System.out.print(msg);
        while (!sc.hasNextInt()) {
            System.out.print("Digite um numero valido: ");
            sc.next();
        }
        return sc.nextInt();
    }

    static double lerDouble(String msg) {
        System.out.print(msg);
        while (!sc.hasNextDouble()) {
            System.out.print("Digite um valor valido: ");
            sc.next();
        }
        return sc.nextDouble();
    }

    static LocalDate lerData() {
        while (true) {
            try {
                sc.nextLine();
                System.out.print("Digite a data (dd/MM/yyyy): ");
                String dataStr = sc.nextLine();
                return LocalDate.parse(dataStr, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Data invalida! Tente novamente.");
            }
        }
    }
}