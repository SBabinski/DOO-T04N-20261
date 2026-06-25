import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class CalculadoraLoja {
    static int[] quantidades = new int[100];
    static double[] totais = new double[100];
    static double[] descontos = new double[100];
    static LocalDate[] datas = new LocalDate[100];
    static int contador = 0;
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao = 0;
        while (opcao != 6) {
            System.out.println("=== Loja da Dona Gabrielinha ===");
            System.out.println("[1] - Calcular compra");
            System.out.println("[2] - Calcular troco");
            System.out.println("[3] - Ver registro de vendas");
            System.out.println("[4] - Consultar vendas por dia");
            System.out.println("[5] - Consultar vendas por mês");
            System.out.println("[6] - Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();
            if (opcao == 1) {
                calcularCompra(sc);
            } else if (opcao == 2) {
                calcularTroco(sc);
            } else if (opcao == 3) {
                mostrarVendas();
            } else if (opcao == 4) {
                consultarPorDia(sc);
            } else if (opcao == 5) {
                consultarPorMes(sc);
            } else if (opcao == 6) {
                System.out.println("Saindo...");
            } else {
                System.out.println("Opcao invalida!\n");
            }
        }
        sc.close();
    }
    public static void calcularCompra(Scanner sc) {
        System.out.print("Digite a data da venda (dd/MM/yyyy): ");
        String dataStr = sc.nextLine();
        LocalDate data = LocalDate.parse(dataStr, formatter);
        System.out.print("Quantidade de plantas: ");
        int quantidade = sc.nextInt();
        System.out.print("Preco da planta: ");
        double preco = sc.nextDouble();
        double total = quantidade * preco;
        double desconto = 0;
        if (quantidade > 10) {
            desconto = total * 0.05;
            total -= desconto;
            System.out.println("Desconto de 5% aplicado!");
        }
        System.out.println("Total a pagar: R$ " + total + "\n");
        quantidades[contador] = quantidade;
        totais[contador] = total;
        descontos[contador] = desconto;
        datas[contador] = data;
        contador++;
    }
    public static void calcularTroco(Scanner sc) {
        System.out.print("Valor pago: ");
        double pago = sc.nextDouble();
        System.out.print("Valor da compra: ");
        double total = sc.nextDouble();
        double troco = pago - total;
        if (troco < 0) {
            System.out.println("Faltam R$ " + (-troco));
        } else {
            System.out.println("Troco: R$ " + troco);
        }
        System.out.println();
    }
    public static void mostrarVendas() {
        if (contador == 0) {
            System.out.println("Nenhuma venda registrada.\n");
            return;
        }
        System.out.println("=== Registro de Vendas ===");
        for (int i = 0; i < contador; i++) {
            System.out.println("Venda " + (i + 1));
            System.out.println("Data: " + datas[i].format(formatter));
            System.out.println("Quantidade: " + quantidades[i]);
            System.out.println("Desconto: R$ " + descontos[i]);
            System.out.println("Total: R$ " + totais[i]);
            System.out.println("----------------------");
        }
        System.out.println();
    }
    public static void consultarPorDia(Scanner sc) {
        System.out.print("Digite a data (dd/MM/yyyy): ");
        String dataStr = sc.nextLine();
        LocalDate dataBusca = LocalDate.parse(dataStr, formatter);
        int totalVendas = 0;
        for (int i = 0; i < contador; i++) {
            if (datas[i].equals(dataBusca)) {
                totalVendas++;
            }
        }
        System.out.println("Total de vendas nesse dia: " + totalVendas + "\n");
    }
    public static void consultarPorMes(Scanner sc) {
        System.out.print("Digite o mês e ano (MM/yyyy): ");
        String entrada = sc.nextLine();
        int mes = Integer.parseInt(entrada.split("/")[0]);
        int ano = Integer.parseInt(entrada.split("/")[1]);
        int totalVendas = 0;
        for (int i = 0; i < contador; i++) {
            if (datas[i].getMonthValue() == mes && datas[i].getYear() == ano) {
                totalVendas++;
            }
        }
        System.out.println("Total de vendas no mês: " + totalVendas + "\n");
    }
}