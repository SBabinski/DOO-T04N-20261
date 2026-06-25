import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CalculadoraDonaGabrielinha {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaGabrielinha sistema = new SistemaGabrielinha();
        int opcao = 0;

        while (opcao !=5){
            System.out.println("Escolha uma opção:");
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Ver Registro de Vendas");
            System.out.println("[4] - Buscar Faturamento por Dia/Mês");
            System.out.println("[5] - Sair");
                    opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    System.out.print("Coloque a quantidade de plantas: ");
                    int quantidade = scanner.nextInt();
                    System.out.print("Insira o preço unitário das plantas: ");
                    double precoUn = scanner.nextDouble();
                    System.out.print("Digite a data da venda (Ex: 22/05/2026): ");
                    String dataVendaStr = scanner.next();
                    LocalDate dataVenda = LocalDate.parse(dataVendaStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                    double total = sistema.calculoPrecoTotal(quantidade, precoUn, dataVenda);
                    
                    System.out.printf("O preço total é: R$%.2f\n", total);
                    break;

                case 2:
                    System.out.print("Insira o valor pago pelo cliente: ");
                    double valorPago = scanner.nextDouble();
                    System.out.print("Digite o valor total da compra: ");
                    double valorTotal = scanner.nextDouble();

                    sistema.calculoTroco(valorPago, valorTotal);
                    break;
                    
                case 3:
                    sistema.exibirHistorico();
                    break;

                case 4:
                    System.out.print("Digite a data para busca (Ex: 22/05/2026): ");
                    String dataEntrada = scanner.next();
                    LocalDate dataBusca = LocalDate.parse(dataEntrada, DateTimeFormatter.ofPattern("dd/MM/yyyy")); // parse serve para ler o texto e converter em data para o sistema buscar na matriz
                    
                    sistema.buscarVendasPorData(dataBusca);
                    break;

                case 5:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
        scanner.close();
    }

}