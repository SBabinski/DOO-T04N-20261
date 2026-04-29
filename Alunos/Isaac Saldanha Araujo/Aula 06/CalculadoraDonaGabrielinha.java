import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;

public class CalculadoraDonaGabrielinha {

    static int totalPlantasVendidas = 0;
    static double totalVendas = 0;
    static double totalDescontos = 0;

   
    static HashMap<LocalDate, Integer> vendasPorDia = new HashMap<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Vendedor v1 = new Vendedor();
        v1.nome = "Isaac";
        v1.idade = 18;
        v1.loja = "myPlant";
        v1.salarioBase = 1500;

        Cliente c1 = new Cliente();
        c1.nome = "Luana";
        c1.idade = 27;

        Loja loja = new Loja();
        loja.nomeFantasia = " myPlant";
        loja.cnpj = " 123456789";
        loja.cidade =  " Cascavel";
        loja.bairro = " Centro";
        loja.rua = " Rua dos Sordadinho";

        loja.vendedores = new Vendedor[]{v1};
        loja.clientes = new Cliente[]{c1};



        while (opcao != 6) {
            System.out.println("\n Loja de Plantas da Dona Gabrielinha");
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Mostrar Registro de Vendas");
            System.out.println("[4] - Registrar Vendas por Data");
            System.out.println("[5] - Buscar Vendas por Data");
            System.out.println("[6] - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {

                case 1:
                    System.out.println("Digite a quantidade de plantas:");
                    int quantidade = scanner.nextInt();

                    System.out.println("Digite o preço da planta:");
                    double preco = scanner.nextDouble();

                    double total = preco * quantidade;
                    double desconto = 0;

                    if (quantidade > 10) {
                        desconto = total * 0.05;
                        total -= desconto;
                    }

                    totalPlantasVendidas += quantidade;
                    totalVendas += total;
                    totalDescontos += desconto;

                    System.out.println("Preço total da compra: R$ " + total);
                    System.out.println("Desconto aplicado: R$ " + desconto);
                    break;

                case 2:
                    System.out.println("Digite o valor pago pelo cliente:");
                    double pago = scanner.nextDouble();

                    System.out.println("Digite o valor total da compra:");
                    double valorCompra = scanner.nextDouble();

                    double troco = pago - valorCompra;

                    System.out.println("Valor do troco: R$ " + troco);
                    break;

                case 3:
                    System.out.println("\n=== REGISTRO DE VENDAS ===");
                    System.out.println("Total de plantas vendidas: " + totalPlantasVendidas);
                    System.out.println("Total em vendas: R$ " + totalVendas);
                    System.out.println("Total de descontos: R$ " + totalDescontos);
                    
                    loja.apresentarSe();
                    System.out.println("clientes:" +loja.contarClientes());
                    System.out.println("Vendedor:" +loja.contarVendedores());
                    break;

                case 4:
                    System.out.print("Digite a data (dd/MM/yyyy): ");
                    String dataStr = scanner.nextLine();

                    LocalDate data = LocalDate.parse(dataStr, formatter);

                    System.out.print("Digite a quantidade de vendas do dia: ");
                    int vendas = scanner.nextInt();

                    scanner.nextLine();
                    vendasPorDia.put(data, vendasPorDia.getOrDefault(data, 0) + vendas);

                    System.out.println("Vendas registradas com sucesso!");
                    break;

                case 5:
                    System.out.print("Digite a data (dd/MM/yyyy): ");
                    String buscaStr = scanner.nextLine();

                    LocalDate dataBusca = LocalDate.parse(buscaStr, formatter);

                    if (vendasPorDia.containsKey(dataBusca)) {
                        System.out.println("Total de vendas no dia: " + vendasPorDia.get(dataBusca));
                    } else {
                        System.out.println("Nenhuma venda registrada para essa data.");
                    }
                    break;

                case 6:
                    System.out.println("Encerrando Sistema. Obrigado!");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }
}