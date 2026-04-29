import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CalculadoraDonaGabriela {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        int opcao = 0;

        String registroVendas = "";

        int[] qtplanta = new int[100];
        double[] vlplanta = new double[100];
        LocalDate[] dtplanta = new LocalDate[100];
        int contadorVendas = 0;

        while (opcao != 5) {
            System.out.println("\n--- Loja da Dona Gabrielinha ---");
            System.out.println("1 - Calcular Preço Total");
            System.out.println("2 - Calcular Troco");
            System.out.println("3 - Ver Registro de Vendas");
            System.out.println("4 - Buscar Vendas por Data"); 
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            if (opcao == 1) {
                System.out.print("Digite a quantidade de plantas: ");
                int quantidade = scanner.nextInt();
                System.out.print("Digite o preço unitário: ");
                double preco = scanner.nextDouble();

                double total = quantidade * preco;
                double desconto = 0;

                if (quantidade > 10) {
                    desconto = total * 0.05;
                    total = total - desconto;
                    System.out.println("Desconto especial de 5% aplicado!");
                }

                System.out.println("O preço total é: R$ " + total);

                registroVendas = registroVendas + "Plantas: " + quantidade + " | Valor: R$ " + total + " | Desconto: R$ " + desconto + "\n";

                qtplanta[contadorVendas] = quantidade;
                vlplanta[contadorVendas] = total;
                dtplanta[contadorVendas] = LocalDate.now();
                
                contadorVendas = contadorVendas + 1;
            } else if (opcao == 2) {
                System.out.print("Digite o valor recebido pelo cliente: ");
                double valorRecebido = scanner.nextDouble();
                System.out.print("Digite o valor total da compra: ");
                double valorCompra = scanner.nextDouble();

                double troco = valorRecebido - valorCompra;
                System.out.println("O troco a ser dado é: R$ " + troco);

            } else if (opcao == 3) {
                System.out.println("\n--- Registro das Vendas ---");
                System.out.println(registroVendas);

            } else if (opcao == 4) {

                System.out.print("Digite a data para buscar (dia/mes/ano): ");
                String textoData = scanner.next();
                
                LocalDate dataBusca = LocalDate.parse(textoData, formatador);
                
                int somaPlantas = 0;
                double somaDinheiro = 0.0;

                int i = 0;
                while (i < contadorVendas) {
                    if (dtplanta[i].isEqual(dataBusca)) {
                        somaPlantas = somaPlantas + qtplanta[i];
                        somaDinheiro = somaDinheiro + vlplanta[i];
                    }
                    i = i + 1;
                }
                
                System.out.println("\n--- Resumo do dia " + textoData + " ---");
                System.out.println("Total de plantas vendidas: " + somaPlantas);
                System.out.println("Dinheiro total que entrou: R$ " + somaDinheiro);

            } else if (opcao == 5) {
                System.out.println("Saindo...");

            } else {
                System.out.println("Opção inválida");
            }
        }

        scanner.close();
    }
}