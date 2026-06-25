import java.util.Scanner;
import java.util.HashMap;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CalculadoraLoja {

    static int totalVendas = 0;
    static int totalPlantasVendidas = 0;
    static double totalDescontos = 0;

    static HashMap<String, Integer> vendasPorData = new HashMap<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        while (opcao != 4) {

            System.out.println(" Calculadora Loja da Dona Gabrielinha ");
            System.out.println("1 - Realizar Venda");
            System.out.println("2 - Ver Registro Geral");
            System.out.println("3 - Buscar Vendas por Data");
            System.out.println("4 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            if (opcao == 1) {

                System.out.print("Quantidade de plantas: ");
                int quantidade = scanner.nextInt();

                System.out.print("Preço da planta: ");
                double preco = scanner.nextDouble();

                double total = quantidade * preco;
                double desconto = 0;

                if (quantidade > 10) {
                    desconto = total * 0.05;
                    total -= desconto;
                }

                System.out.println("Total: R$ " + total);
                System.out.println("Desconto: R$ " + desconto);

                totalVendas++;
                totalPlantasVendidas += quantidade;
                totalDescontos += desconto;

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String dataAtual = LocalDate.now().format(formatter);

                vendasPorData.put(dataAtual,
                        vendasPorData.getOrDefault(dataAtual, 0) + 1);
            }

            else if (opcao == 2) {

                System.out.println("Total vendas: " + totalVendas);
                System.out.println("Total plantas: " + totalPlantasVendidas);
                System.out.println("Total descontos: R$ " + totalDescontos);
            }

            else if (opcao == 3) {

                scanner.nextLine();

                System.out.print("Digite a data (dd/MM/yyyy): ");
                String dataBusca = scanner.nextLine();

                int vendas = vendasPorData.getOrDefault(dataBusca, 0);

                System.out.println("Vendas nesse dia: " + vendas);
            }

            else if (opcao == 4) {
                System.out.println("Saindo...");
            }

            else {
                System.out.println("Opção inválida!");
            }

            System.out.println();
        }

        scanner.close();
    }
}