import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CalculadoraDonaGabrielinha {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Loja myPlant = new Loja();
        myPlant.nomeFantasia = "Dona Gabrielinha Plantas";
        myPlant.cnpj = "12.345.678/0001-90";
        myPlant.rua = "Rua das Flores";
        myPlant.bairro = "Jardim Botânico";
        myPlant.cidade = "São Paulo";

        Vendedor v1 = new Vendedor();
        v1.nome = "Victor";
        v1.idade = 23;
        v1.cidade = "Rio de Janeiro";
        v1.bairro = "Copacabana";
        v1.rua = "Rua Atlântica";
        v1.loja = "Dona Gabrielinha Plantas";
        v1.salarioBase = 2000.00;

        v1.salarioRecebido[0] = 2200.00;
        v1.salarioRecebido[1] = 2500.00;
        v1.salarioRecebido[2] = 2100.00;

        myPlant.vendedores[myPlant.qtdVendedores] = v1;
        myPlant.qtdVendedores++;

        int opcao = 0;

        while (opcao !=6){
            System.out.println("MY PLANT - ERP");
            System.out.println("[1] - Realizar Venda");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Histórico de Vendas");
            System.out.println("[4] - Buscar Faturamento por Data");
            System.out.println("[5] - Menu de Gestão (Vendedores/Loja)");
            System.out.println("[6] - Sair");
            System.out.println("Escolha uma opção: ");

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

                    double total = myPlant.caixa.calculoPrecoTotal(quantidade, precoUn, dataVenda);
                    
                    System.out.printf("Venda processada por %s. Total: R$ %.2f\n", v1.nome, total);
                    
                    break;

                case 2:
                    System.out.print("Insira o valor pago pelo cliente: ");
                    double valorPago = scanner.nextDouble();
                    System.out.print("Digite o valor total da compra: ");
                    double valorTotal = scanner.nextDouble();

                    myPlant.caixa.calculoTroco(valorPago, valorTotal);

                    break;
                    
                case 3:
                    myPlant.caixa.exibirHistorico();

                    break;

                case 4:
                    System.out.print("Digite a data para busca (Ex: 22/05/2026): ");
                    String dataEntrada = scanner.next();
                    LocalDate dataBusca = LocalDate.parse(dataEntrada, DateTimeFormatter.ofPattern("dd/MM/yyyy")); // parse serve para ler o texto e converter em data para o sistema buscar na matriz
                    
                    myPlant.caixa.buscarVendasPorData(dataBusca);

                    break;

                case 5:
                    System.out.println("\n--- RELATÓRIO DE GESTÃO ---");
                    myPlant.apresentarse();

                    System.out.println("\n--- Vendedor em Destaque ---");
                    v1.apresentarse();
                    System.out.printf("Média salarial do vendedor: R$%.2f\n", v1.calcularMedia());
                    System.out.printf("Bônus do vendedor: R$%.2f\n", v1.calcularBonus());

                    break;

                case 6:
                    System.out.println("Saindo do My Plant ERP. Até logo..");
                    
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    
                    break;

            }
        }
        scanner.close();
    }

}