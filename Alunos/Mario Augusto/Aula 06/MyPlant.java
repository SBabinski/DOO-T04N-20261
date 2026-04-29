import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyPlant {

    // Classe para registrar cada venda
    static class RegistroVenda {
        int quantidade;
        double valorTotal;
        double desconto;
        LocalDate data;

        RegistroVenda(int quantidade, double valorTotal, double desconto, LocalDate data) {
            this.quantidade = quantidade;
            this.valorTotal = valorTotal;
            this.desconto = desconto;
            this.data = data;
        }
    }

    // Formatador de data padrão: dd/MM/yyyy
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Lista para armazenar as vendas
    static List<RegistroVenda> registroVendas = new ArrayList<>();

    public static double calcularPrecoTotal(int quantidade, double precoUnitario) {
        return quantidade * precoUnitario;
    }

    public static double calcularDesconto(int quantidade, double precoTotal) {
        if (quantidade > 10) {
            return precoTotal * 0.05;
        }
        return 0;
    }

    public static double calcularTroco(double valorRecebido, double valorTotal) {
        return valorRecebido - valorTotal;
    }

    public static void registrarVenda(int quantidade, double valorTotal, double desconto, LocalDate data) {
        registroVendas.add(new RegistroVenda(quantidade, valorTotal, desconto, data));
    }

    public static void exibirRegistroVendas() {
        if (registroVendas.isEmpty()) {
            System.out.println("\nNenhuma venda registrada ainda.");
            return;
        }

        System.out.println("\n--- REGISTRO DE VENDAS ---");
        int numeroVenda = 1;
        for (RegistroVenda venda : registroVendas) {
            System.out.println("\nVenda #" + numeroVenda++);
            System.out.println("Data: " + venda.data.format(formatter));
            System.out.println("Quantidade de plantas: " + venda.quantidade);
            System.out.printf("Desconto aplicado: R$ %.2f%n", venda.desconto);
            System.out.printf("Valor total: R$ %.2f%n", venda.valorTotal);
        }
    }

    public static void salvarVendaPorData(Scanner scanner) {
        System.out.println("\n--- Salvar Venda por Data ---");

        LocalDate data = null;
        while (data == null) {
            System.out.print("Digite a data da venda (dd/MM/yyyy): ");
            String dataStr = scanner.next();
            try {
                data = LocalDate.parse(dataStr, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Data inválida! Use o formato dd/MM/yyyy.");
            }
        }

        System.out.print("Digite a quantidade de plantas vendidas: ");
        int quantidade = scanner.nextInt();
        System.out.print("Digite o preço unitário (R$): ");
        double precoUnitario = scanner.nextDouble();

        double precoTotal = calcularPrecoTotal(quantidade, precoUnitario);
        double desconto = calcularDesconto(quantidade, precoTotal);
        double valorAPagar = precoTotal - desconto;

        registrarVenda(quantidade, valorAPagar, desconto, data);

        System.out.println("\nVenda registrada com sucesso!");
        System.out.println("Data: " + data.format(formatter));
        System.out.println("Quantidade: " + quantidade);
        System.out.printf("Valor a pagar: R$ %.2f%n", valorAPagar);
    }

    public static void buscarVendasPorMesEDia(Scanner scanner) {
        System.out.println("\n--- Buscar Vendas por Mês e Dia ---");

        int mes = 0;
        while (mes < 1 || mes > 12) {
            System.out.print("Digite o mês (1-12): ");
            mes = scanner.nextInt();
            if (mes < 1 || mes > 12) {
                System.out.println("Mês inválido! Digite um valor entre 1 e 12.");
            }
        }

        int dia = 0;
        while (dia < 1 || dia > 31) {
            System.out.print("Digite o dia (1-31): ");
            dia = scanner.nextInt();
            if (dia < 1 || dia > 31) {
                System.out.println("Dia inválido! Digite um valor entre 1 e 31.");
            }
        }

        int totalVendas = 0;
        int totalQuantidade = 0;

        for (RegistroVenda venda : registroVendas) {
            if (venda.data.getMonthValue() == mes && venda.data.getDayOfMonth() == dia) {
                totalVendas++;
                totalQuantidade += venda.quantidade;
            }
        }

        System.out.printf("\nResultado para o dia %02d/%02d:%n", dia, mes);
        System.out.println("Total de vendas realizadas: " + totalVendas);
        System.out.println("Total de plantas vendidas: " + totalQuantidade);
    }

    public static void exibirInfoEmpresa() {
        // Criando vendedores
        Vendedor v1 = new Vendedor("Carlos Silva", 30, "My Plant Centro", "São Paulo", "Centro", "Rua das Flores, 10", 2500.00);
        Vendedor v2 = new Vendedor("Ana Souza", 25, "My Plant Centro", "São Paulo", "Centro", "Rua das Flores, 10", 2200.00);

        // Criando clientes
        Cliente c1 = new Cliente("João Pereira", 40, "São Paulo", "Jardins", "Av. Paulista, 500");
        Cliente c2 = new Cliente("Maria Lima", 35, "São Paulo", "Moema", "Rua Iraí, 200");
        Cliente c3 = new Cliente("Pedro Costa", 28, "São Paulo", "Centro", "Rua XV, 80");

        // Criando loja
        Loja loja = new Loja(
            "My Plant",
            "My Plant Comercio de Plantas LTDA",
            "12.345.678/0001-99",
            "São Paulo",
            "Centro",
            "Rua das Flores, 10",
            new Vendedor[]{v1, v2},
            new Cliente[]{c1, c2, c3}
        );

        System.out.println("\n========== INFORMAÇÕES DA EMPRESA ==========");
        loja.apresentarse();
        System.out.println("Total de vendedores: " + loja.contarVendedores());
        System.out.println("Total de clientes: " + loja.contarClientes());

        System.out.println("\n--- Vendedores ---");
        for (Vendedor v : loja.vendedores) {
            v.apresentarse();
            System.out.printf("Média salarial: R$ %.2f%n", v.calcularMedia());
            System.out.printf("Bônus: R$ %.2f%n", v.calcularBonus());
            System.out.println();
        }

        System.out.println("--- Clientes ---");
        for (Cliente c : loja.clientes) {
            c.apresentarse();
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        System.out.println("============================================");
        System.out.println("     Bem-vindo ao Sistema My Plant!");
        System.out.println("        Loja de Plantas Exóticas ");
        System.out.println("============================================");

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Registro de Vendas");
            System.out.println("[4] - Salvar Venda por Data");
            System.out.println("[5] - Buscar Vendas por Mês e Dia");
            System.out.println("[6] - Informações da Empresa");
            System.out.println("[7] - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("\n--- Calcular Preço Total ---");
                    System.out.print("Digite a quantidade de plantas: ");
                    int quantidade = scanner.nextInt();
                    System.out.print("Digite o preço unitário (R$): ");
                    double precoUnitario = scanner.nextDouble();

                    double precoTotal = calcularPrecoTotal(quantidade, precoUnitario);
                    double desconto = calcularDesconto(quantidade, precoTotal);
                    double valorAPagar = precoTotal - desconto;

                    System.out.printf("Preço Total: R$ %.2f%n", precoTotal);
                    if (desconto > 0) {
                        System.out.printf("Desconto (5%%): R$ %.2f%n", desconto);
                    }
                    System.out.printf("Valor a Pagar: R$ %.2f%n", valorAPagar);

                    registrarVenda(quantidade, valorAPagar, desconto, LocalDate.now());
                    break;

                case 2:
                    System.out.println("\n--- Calcular Troco ---");
                    System.out.print("Digite o valor recebido (R$): ");
                    double valorRecebido = scanner.nextDouble();
                    System.out.print("Digite o valor total da compra (R$): ");
                    double valorTotal = scanner.nextDouble();

                    double troco = calcularTroco(valorRecebido, valorTotal);
                    if (troco < 0) {
                        System.out.printf("Valor insuficiente! Faltam R$ %.2f.%n", Math.abs(troco));
                    } else {
                        System.out.printf("Troco: R$ %.2f%n", troco);
                    }
                    break;

                case 3:
                    exibirRegistroVendas();
                    break;

                case 4:
                    salvarVendaPorData(scanner);
                    break;

                case 5:
                    buscarVendasPorMesEDia(scanner);
                    break;

                case 6:
                    exibirInfoEmpresa();
                    break;

                case 7:
                    System.out.println("\nObrigado por usar o sistema My Plant!");
                    System.out.println("Volte sempre!");
                    break;

                default:
                    System.out.println("Opção inválida! Por favor, escolha entre 1 e 7.");
            }

        } while (opcao != 7);

        scanner.close();
    }
}