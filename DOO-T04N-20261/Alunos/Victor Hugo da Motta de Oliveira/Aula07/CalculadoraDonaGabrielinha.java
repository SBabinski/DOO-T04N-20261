import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

public class CalculadoraDonaGabrielinha {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // 1. Setup da Matriz
        Loja myPlant = new Loja();
        myPlant.nomeFantasia = "Dona Gabrielinha Plantas";
        myPlant.cnpj = "12.345.678/0001-90";
        
        Endereco endLoja = new Endereco();
        endLoja.rua = "Rua das Flores";
        endLoja.numero = "100";
        endLoja.bairro = "Jardim Botânico";
        endLoja.cidade = "São Paulo";
        endLoja.estado = "SP";
        myPlant.enderecoLoja = endLoja;

        int opcao = 0;

        while (opcao != 9) {
            System.out.println("\n========= MY PLANT - ERP =========");
            System.out.println("[1] - Venda Rápida (Caixa)");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Histórico Financeiro");
            System.out.println("[4] - Buscar Faturamento por Data");
            System.out.println("[5] - Relatório Corporativo (Loja)");
            System.out.println("[6] - Cadastrar Vendedor");
            System.out.println("[7] - Cadastrar Cliente");
            System.out.println("[8] - Criar Novo Pedido (Teste)");
            System.out.println("[9] - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine();
                    
            switch (opcao) {
                case 1:
                    System.out.print("Quantidade de plantas: ");
                    int quantidade = scanner.nextInt();
                    System.out.print("Preço unitário: R$ ");
                    double precoUn = scanner.nextDouble();
                    System.out.print("Data da venda (Ex: 22/05/2026): ");
                    LocalDate dataVenda = LocalDate.parse(scanner.next(), formatter);
                    myPlant.caixa.calculoPrecoTotal(quantidade, precoUn, dataVenda);
                    break;

                case 2:
                    System.out.print("Valor pago pelo cliente: R$ ");
                    double valorPago = scanner.nextDouble();
                    System.out.print("Valor total da compra: R$ ");
                    double valorTotal = scanner.nextDouble();
                    myPlant.caixa.calculoTroco(valorPago, valorTotal);
                    break;
                    
                case 3:
                    myPlant.caixa.exibirHistorico();
                    break;

                case 4:
                    System.out.print("Digite a data (dd/MM/yyyy): ");
                    LocalDate dataBusca = LocalDate.parse(scanner.next(), formatter);
                    myPlant.caixa.buscarVendasPorData(dataBusca);
                    break;

                case 5:
                    myPlant.apresentarse();
                    myPlant.contarVendedores();
                    myPlant.contarClientes();
                    break;

                case 6:
                    if (myPlant.qtdVendedores < 10) {
                        Vendedor v = new Vendedor();
                        System.out.print("Nome do vendedor: ");
                        v.nome = scanner.nextLine();
                        System.out.print("Salário base: R$ ");
                        v.salarioBase = scanner.nextDouble();
                        v.loja = myPlant.nomeFantasia;
                        
                        myPlant.vendedores[myPlant.qtdVendedores] = v;
                        myPlant.qtdVendedores++;
                        System.out.println("Vendedor cadastrado com sucesso.");
                    }
                    break;

                case 7:
                    if (myPlant.qtdClientes < 10) {
                        Cliente c = new Cliente(); 
                        System.out.print("Nome do cliente: ");
                        c.nome = scanner.nextLine();
                        System.out.print("Idade do cliente: ");
                        c.idade = scanner.nextInt();

                        myPlant.clientes[myPlant.qtdClientes] = c;
                        myPlant.qtdClientes++;
                        System.out.println("Cliente cadastrado com sucesso.");
                    }
                    break;

                case 8:
                    if (myPlant.qtdVendedores == 0 || myPlant.qtdClientes == 0) {
                        System.out.println("ERRO: Cadastre ao menos 1 Vendedor e 1 Cliente (Opções 6 e 7) primeiro!");
                        break; 
                    }
                    
                    Pedido p = new Pedido();
                    System.out.print("\nID do Pedido: ");
                    p.id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("\n--- Clientes Disponíveis ---");
                    for(int i=0; i<myPlant.qtdClientes; i++) {
                        System.out.println("[" + i + "] " + myPlant.clientes[i].nome);
                    }
                    System.out.print("Selecione o índice do Cliente: ");
                    p.cliente = myPlant.clientes[scanner.nextInt()];

                    System.out.println("\n--- Vendedores Disponíveis ---");
                    for(int i=0; i<myPlant.qtdVendedores; i++) {
                        System.out.println("[" + i + "] " + myPlant.vendedores[i].nome);
                    }
                    System.out.print("Selecione o índice do Vendedor: ");
                    p.vendedor = myPlant.vendedores[scanner.nextInt()];
                    scanner.nextLine();

                    Item item = new Item();
                    System.out.print("\nNome da Planta: ");
                    item.nome = scanner.nextLine();
                    System.out.print("Tipo: ");
                    item.tipo = scanner.nextLine();
                    System.out.print("Preço: R$ ");
                    item.preco = scanner.nextDouble();
                    
                    p.itens[0] = item;
                    p.qtdItens = 1;

                    p.dataCriacao = new Date();
                    p.dataVencimento = new Date(System.currentTimeMillis() + 172800000);
                    p.gerarDescricaoVenda();
                    ProcessarPedido processador = new ProcessarPedido();
                    processador.processar(p);
                    break;

                case 9:
                    System.out.println("Encerrando o My Plant ERP!");
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }
        scanner.close();
    }
}