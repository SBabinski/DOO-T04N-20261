import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        SistemaLoja sistema = new SistemaLoja();

        int opcao = 0;

        while (opcao != 5) {

            System.out.println("Bem vindo a Loja da Dano Gabrielinha");
            System.out.println("[1] Calcular Preço Total");
            System.out.println("[2] Calcular Troco");
            System.out.println("[3] Registrar Venda");
            System.out.println("[4] Buscar Vendas por Data");
            System.out.println("[5] Sair");

            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {

                case 1:

                    System.out.print("Quantidade de plantas: ");
                    int quantidade = scanner.nextInt();

                    System.out.print("Preço unitário: ");
                    double preco = scanner.nextDouble();

                    double total = sistema.calcularPrecoTotal(quantidade, preco);

                    double desconto = sistema.calcularDesconto(quantidade, preco);

                    System.out.println("Valor total: R$ " + total);
                    System.out.println("Desconto aplicado: R$ " + desconto);

                    break;

                case 2:

                    System.out.print("Valor da compra: ");
                    double valorCompra = scanner.nextDouble();

                    System.out.print("Valor pago: ");
                    double valorPago = scanner.nextDouble();

                    double troco = sistema.calcularTroco(valorPago, valorCompra);

                    System.out.println("Troco: R$ " + troco);

                    break;

                case 3:

                    System.out.print("Quantidade de plantas: ");
                    int qtd = scanner.nextInt();

                    System.out.print("Preço unitário: ");
                    double valor = scanner.nextDouble();

                    double valorTotal = sistema.calcularPrecoTotal(qtd, valor);

                    double valorDesconto = sistema.calcularDesconto(qtd, valor);

                    sistema.registrarVenda(qtd, valorTotal, valorDesconto);

                    break;

                case 4:

                    scanner.nextLine();

                    System.out.print("Digite a data (dd/MM/yyyy): ");
                    String data = scanner.nextLine();

                    sistema.buscarVendasPorData(data);

                    break;

                case 5:

                    System.out.println("Sistema encerrado.");

                    break;

                default:

                    System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }
}