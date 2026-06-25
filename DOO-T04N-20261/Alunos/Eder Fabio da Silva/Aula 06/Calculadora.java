
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Calculadora {
    // Lista para armazenar as vendas

    private static List<Vendas> registroVendas = new ArrayList<>();
    // Formato para exibir a data no relatório de vendas
    private static DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    // Scanner para ler a entrada do usuário
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Gerador.gerarLoja();
        Gerador.gerarVendedor();
        Gerador.gerarCliente();
        // Exibir o menu da calculadora
        mostrarMenu();

    }

    // Método para exibir o menu da calculadora
    public  static void mostrarMenu() {
        int opcao = 0;
        do {
            System.out.println("---MENU DA CALCULADORA---");
            System.out.println("[1] Calcular Preco Total");
            System.out.println("[2] Calcular Troco");
            System.out.println("[3] Relatorio de Vendas");
            System.out.println("[4] Busca todas as vendas por data");
            System.out.println("[5] Menu da Loja");
            System.out.println("[0] Salir");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner
            validarEscolha(opcao);
        } while (opcao != 0);
    }
// Método para validar a escolha do usuário no menu

    private static void validarEscolha(int opcao) {
        switch (opcao) {
            case 1:
                calcularPrecoTotal();
                break;
            case 2:
                calcularTroco();
                break;
            case 3:
                relatorioVendas();
                break;
            case 4:
                relatorioBuscaPorData();
                break;
            case 5:
                Loja.menuLoja();
                break;
            case 0:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opcao invalida!");
        }
    }
// Método para calcular o preço total da venda, aplicando desconto se necessário

    private static void calcularPrecoTotal() {
        System.out.println("Digite a quantidade:");
        double quantidade = scanner.nextDouble();
        System.out.println("Digite o preco unitario R$ ");
        double precoUnitario = scanner.nextDouble();
        Vendas venda = new Vendas(precoUnitario, (int) quantidade);
        System.out.printf("O preco total R$ %.2f\n", venda.getValorTotal());
        registroVendas.add(venda);
        System.out.println("Venda registrada com sucesso!");

    }
// Método para calcular o troco

    private static void calcularTroco() {
        System.out.println("Digite o valor recebido R$ ");
        double valorPago = scanner.nextDouble();
        System.out.println("Digite o valor total R$ ");
        double valorTotal = scanner.nextDouble();
        double troco = valorPago - valorTotal;
        if (troco < 0) {
            System.out.printf("Valor pago e insuficiente. Faltam R$ %.2f\n", -troco);
        } else {
            System.out.printf("O troco e R$ %.2f\n", troco);
        }

    }

    // Método para exibir o relatório de vendas
    private static void relatorioVendas() {
        if (registroVendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada.");
        } else {
            System.out.println("Relatorio de Vendas:");
            for (Vendas venda : registroVendas) {
                System.out.printf("Quantidade: %d, Preço Unitário: R$ %.2f, Valor Total: R$ %.2f\n",
                        venda.getQuantidade(), venda.getPrecoUnitario(), venda.getValorTotal());
            }
        }
    }

    // Método para exibir o relatório de vendas por data
    private static void relatorioBuscaPorData() {
        System.out.println("Digite a data para busca (dd/MM/yyyy):");
        String dataBusca = scanner.nextLine();
        // Converter a string de data para LocalDate usando o formatador
        LocalDate dataFormatada = LocalDate.parse(dataBusca, formatador);
        System.out.println("\n ------VENDAS NA DATA " + dataBusca);
        boolean vendaEncontrada = false;
        double fatumentoDia = 0.0;

        // Percorrer o registro de vendas e verificar se a data da venda corresponde à data buscada
        for (Vendas venda : registroVendas) {
            if (venda.getDataVenda().equals(dataFormatada)) {
                System.out.printf("Venda encontrada: Quantidade: %d, Preco Unitario: R$ %.2f, Valor Total: R$ % .2f\n",
                        venda.getQuantidade(), venda.getPrecoUnitario(), venda.getValorTotal());

                fatumentoDia += venda.getValorTotal();
                vendaEncontrada = true;
            }
        }
        if (!vendaEncontrada) {
            System.out.println("Nenhuma venda encontrada para a data: " + dataBusca);
        } else {
            System.out.printf("Faturamento total do dia %s: R$ %.2f\n", dataBusca, fatumentoDia);
        }
    }
}
