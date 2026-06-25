import java.util.Scanner;


public class CalculadoraDonaGabrielinha {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        while (opcao !=3){
            System.out.println("Escolha uma opção:");
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Sair");
                    opcao = scanner.nextInt();
            switch (opcao) {
                case 1:
                    System.out.print("Coloque a quantidade de plantas: ");
                    int quantidade = scanner.nextInt();

                    System.out.print("Insira o preço unitário das plantas: ");
                    double precoUn = scanner.nextDouble();

                    double precoTotal = quantidade * precoUn;

                    System.out.printf("O preço total é: R$%.2f\n", precoTotal);
                    break;

                case 2:
                    System.out.print("Insira o valor pago pelo cliente: ");
                    double valorPago = scanner.nextDouble();

                    System.out.print("Digite o valor total da compra: ");
                    double valorTotal = scanner.nextDouble();

                    double troco = valorPago - valorTotal;
                    if (troco < 0) {
                        System.out.println("O valor inserido é inválido e não é suficiente para o troco");
                    } else {
                        System.out.printf("O troco a ser devolvido é: R$%.2f\n", troco);
                    }
                case 3:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
        scanner.close();
    }

}