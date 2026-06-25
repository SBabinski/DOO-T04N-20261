package objetos;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Principal {

    static Scanner scanner = new Scanner(System.in);

    static Cliente cliente = new Cliente();
    static Vendedor vendedor = new Vendedor();
    static Loja loja = new Loja();

    public static void main(String[] args) {

        System.out.println("---------------------------------------------");
        System.out.println("     Loja de plantas da Dona Gabrielinha    ");
        System.out.println("---------------------------------------------");

        double precoTotal = 0, troco, dinheiroPago;
        int quantidadeDePlantas;
        int console = 0;
        int totalVendas = 0;
        int totalPlantasVendidas = 0;
        double valorTotalArrecadado = 0;
        double totalDescontos = 0;
        Map<LocalDate, Integer> procuraData = new HashMap<>();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        while (console != 7) {
            System.out.println("\nEscolha uma das opções:");
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Registro de Vendas");
            System.out.println("[4] - Salvar venda por data");
            System.out.println("[5] - Buscar venda por data");
            System.out.println("[6] - Gerenciamento");
            System.out.println("[7] - Sair");

            console = scanner.nextInt();

            switch (console) {
                case 1:
                    System.out.println("Quantidade de plantas:");
                    quantidadeDePlantas = scanner.nextInt();

                    double precoSemDesconto = quantidadeDePlantas * 3.99;
                    double desconto = 0;

                    if (quantidadeDePlantas > 10) {
                        desconto = precoSemDesconto * 0.05;
                        precoTotal = precoSemDesconto - desconto;
                    } else {
                        precoTotal = precoSemDesconto;
                    }

                    System.out.println("Preço total: R$ " + precoTotal);

                    totalDescontos += desconto;
                    totalVendas++;
                    totalPlantasVendidas += quantidadeDePlantas;
                    valorTotalArrecadado += precoTotal;
                    break;

                case 2:
                    System.out.println("Valor pago:");
                    dinheiroPago = scanner.nextDouble();

                    if (dinheiroPago < precoTotal) {
                        System.out.println("Valor insuficiente!");
                    } else {
                        troco = dinheiroPago - precoTotal;
                        System.out.println("Troco: R$ " + troco);
                    }
                    break;

                case 3:
                    System.out.println("Total vendas: " + totalVendas);
                    System.out.println("Plantas vendidas: " + totalPlantasVendidas);
                    System.out.println("Total arrecadado: R$ " + valorTotalArrecadado);
                    System.out.println("Descontos: R$ " + totalDescontos);
                    break;

                case 4:
                    scanner.nextLine();
                    System.out.println("Data (dd/MM/yyyy):");
                    LocalDate data = LocalDate.parse(scanner.nextLine(), formato);

                    System.out.println("Quantidade de vendas:");
                    int vendasDia = scanner.nextInt();

                    procuraData.put(data, vendasDia);
                    System.out.println("Salvo!");
                    break;

                case 5:
                    scanner.nextLine();
                    System.out.println("Buscar data:");
                    LocalDate busca = LocalDate.parse(scanner.nextLine(), formato);

                    if (procuraData.containsKey(busca)) {
                        System.out.println("Vendas: " + procuraData.get(busca));
                    } else {
                        System.out.println("Nenhuma venda.");
                    }
                    break;

                case 6:
                    gerenciamento();
                    break;

                case 7:
                    System.out.println("Sistema encerrado!");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }

        scanner.close();
    }

    public static void gerenciamento() {
        int op;

        do {
            System.out.println("\n--- GERENCIAMENTO ---");
            System.out.println("[1] Novo Cliente");
            System.out.println("[2] Novo Vendedor");
            System.out.println("[3] Nova Loja");
            System.out.println("[4] Mostrar Cliente");
            System.out.println("[5] Mostrar Vendedor");
            System.out.println("[6] Mostrar Loja");
            System.out.println("[7] Voltar");

            op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {
                case 1:
                    novoCliente();
                    break;
                case 2:
                    novoVendedor();
                    break;
                case 3:
                    novaLoja();
                    break;
                case 4:
                    cliente.CadastroDoCliente();
                    break;
                case 5:
                    vendedor.cadastroDoVendedor();
                    break;
                case 6:
                    loja.CadastroDaLoja();
                    break;
            }

        } while (op != 7);
    }


    public static void novoCliente() {
        System.out.println("Nome:");
        cliente.setNomeCliente(scanner.nextLine());

        System.out.println("Idade:");
        cliente.setIdadeCliente(scanner.nextInt());
        scanner.nextLine();

        System.out.println("Cidade:");
        cliente.setCidadeCliente(scanner.nextLine());

        System.out.println("Bairro:");
        cliente.setBairroCliente(scanner.nextLine());

        System.out.println("Rua:");
        cliente.setRuaCliente(scanner.nextLine());
    }

    public static void novoVendedor() {
        System.out.println("Nome:");
        vendedor.setNomeVendedor(scanner.nextLine());

        System.out.println("Idade:");
        vendedor.setIdadeVendedor(scanner.nextInt());
        scanner.nextLine();

        System.out.println("Loja:");
        vendedor.setLojaVendedor(scanner.nextLine());

        System.out.println("Cidade:");
        vendedor.setCidadeVendedor(scanner.nextLine());

        System.out.println("Salário:");
        vendedor.setSalarioRecebidoVendedor(scanner.nextDouble());
    }

    public static void novaLoja() {
        System.out.println("Nome fantasia:");
        loja.setNomeFantasia(scanner.nextLine());

        System.out.println("CNPJ:");
        loja.setCnpjLoja(scanner.nextLine());

        System.out.println("Cidade:");
        loja.setCidadeLoja(scanner.nextLine());
    }
}