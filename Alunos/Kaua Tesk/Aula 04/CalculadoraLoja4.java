import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CalculadoraLoja {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int opcao = 0;

        int[] quantidades = new int[100];
        double[] valoresVenda = new double[100];
        double[] descontos = new double[100];
        int totalVendas = 0;

  
        LocalDate[] datas = new LocalDate[100];
        int[] vendasPorDia = new int[100];
        int totalRegistros = 0;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (opcao != 6) {

            System.out.println("\n===== Loja da Dona Gabrielinha =====");
            System.out.println("1 - Calcular Preço Total");
            System.out.println("2 - Calcular Troco");
            System.out.println("3 - Ver Registro de Vendas");
            System.out.println("4 - Registrar Vendas por Data");
            System.out.println("5 - Buscar Vendas por Data");
            System.out.println("6 - Sair");

            opcao = scanner.nextInt();

            if (opcao == 1) {

                System.out.println("Digite a quantidade:");
                int quantidade = scanner.nextInt();

                System.out.println("Digite o preço da planta:");
                double preco = scanner.nextDouble();

                double total = quantidade * preco;
                double desconto = 0;

                if (quantidade > 10) {
                    desconto = total * 0.05;
                    total = total - desconto;
                }

                System.out.println("Valor total da compra: " + total);
                System.out.println("Desconto aplicado: " + desconto);

                if (totalVendas < 100) {
                    quantidades[totalVendas] = quantidade;
                    valoresVenda[totalVendas] = total;
                    descontos[totalVendas] = desconto;
                    totalVendas++;
                }

            }

            else if (opcao == 2) {

                System.out.println("Digite o valor pago:");
                double valorPago = scanner.nextDouble();

                System.out.println("Digite o valor da compra:");
                double valorCompra = scanner.nextDouble();

                double troco = valorPago - valorCompra;

                System.out.println("Troco: " + troco);

            }

            else if (opcao == 3) {

                System.out.println("\n===== Registro de Vendas =====");

                for (int i = 0; i < totalVendas; i++) {
                    System.out.println("\nVenda " + (i + 1));
                    System.out.println("Quantidade: " + quantidades[i]);
                    System.out.println("Valor: " + valoresVenda[i]);
                    System.out.println("Desconto: " + descontos[i]);
                }

                if (totalVendas == 0) {
                    System.out.println("Nenhuma venda registrada.");
                }

            }

    
            else if (opcao == 4) {

                System.out.println("Digite a data (dd/MM/yyyy):");
                String dataTexto = scanner.next();

                LocalDate data = LocalDate.parse(dataTexto, formatter);

                System.out.println("Digite a quantidade de vendas do dia:");
                int quantidadeDia = scanner.nextInt();

                if (totalRegistros < 100) {
                    datas[totalRegistros] = data;
                    vendasPorDia[totalRegistros] = quantidadeDia;
                    totalRegistros++;
                }

                System.out.println("Registro salvo com sucesso!");

            }

        
            else if (opcao == 5) {

                System.out.println("Digite a data para busca (dd/MM/yyyy):");
                String dataTexto = scanner.next();

                LocalDate dataBusca = LocalDate.parse(dataTexto, formatter);

                boolean encontrado = false;

                for (int i = 0; i < totalRegistros; i++) {
                    if (datas[i].equals(dataBusca)) {
                        System.out.println("Quantidade de vendas: " + vendasPorDia[i]);
                        encontrado = true;
                    }
                }

                if (!encontrado) {
                    System.out.println("Nenhum registro encontrado para essa data.");
                }

            }

            else if (opcao == 6) {

                System.out.println("Obrigado por usar o sistema!");

            }

            else {

                System.out.println("Opção inválida!");

            }

        }

        scanner.close();
    }
}