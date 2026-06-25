import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyPlant {

    static List<Venda> registroVendas = new ArrayList<>();
    static DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        List<Loja> lojas = Gerador.criarLojas();

        System.out.println("=== BEM-VINDO AO GRUPO MY PLANT ===");
        System.out.println("Selecione a unidade que deseja gerenciar:");
        for (int i = 0; i < lojas.size(); i++) {
            System.out.println(i + " - " + lojas.get(i).nomeLoja);
        }
        
        int indiceLoja = scan.nextInt();
        Loja lojaAtual = lojas.get(indiceLoja);

        int escolha;

        do {
            System.out.println("\nMENU LOJA");
            System.out.println("1- Calcular Preço");
            System.out.println("2- Calcular Troco");
            System.out.println("3- Registro de Vendas");
            System.out.println("4- Buscar por Data");
            System.out.println("5- Consultar Vendedores");
            System.out.println("6- Consultar Clientes");
            System.out.println("7- Consultar dados da Loja");
            System.out.println("8- Calcular Média Salarial Vendedores");
            System.out.println("9- Calcular Bonus Vendedores");
            System.out.println("10- Voltar ao Menu de Seleção de Loja");
            System.out.println("0- Encerrar Programa");   

            escolha = scan.nextInt();

            switch (escolha) {
                case 1:
                    System.out.println("Digite a quantidade de itens:");
                    int quant = scan.nextInt();
                    System.out.println("Digite o valor do iten:");
                    double valunid = scan.nextDouble();
                    double precototal = quant * valunid;
                    if (quant > 10) {
                        System.out.println("Preco total R$" + precototal * 0.95);
                    } else {
                        System.out.println("Preco total R$" + precototal);
                    }
                    registroVendas.add(new Venda(quant, valunid));

                    System.out.println("Venda Registrada!");
                    break;

                case 2:
                    System.out.println("Digite o valor recebido:");
                    double valrecebido = scan.nextDouble();
                    System.out.println("Digite o valor total da compra:");
                    double valcompra = scan.nextDouble();
                    double troco = calculoTroco(valrecebido, valcompra);
                    System.out.println("Troco da Compra R$" + troco);
                    break;

                case 3:
                    System.out.println("\n ---  REGISTRO DE VENDAS  ---");
                    if (registroVendas.isEmpty()) {
                        System.out.println("Nenhuma venda registrada.");
                    } else {
                        for (Venda venda : registroVendas) {
                            System.out.println("Data da Venda: "
                                    + venda.getDataVenda().format(formatador)
                                    + ", Quantidade: " + venda.getQuant()
                                    + ", Valor Final: R$ " + venda.getValorFinal());
                        }
                    }
                    break;

                    case 4:
                        System.out.print("Digite a data para busca (dd/MM/yyyy): ");
                    String dataBusca = scan.next();
        
                    try {
                        LocalDate dataBuscaFormatada = LocalDate.parse(dataBusca, formatador);
                        System.out.println("\n -------VENDAS NO PERIODO " + dataBusca + "-------");
                        boolean encontrouVendas = false;
                        double faturamentoDia = 0.0;

                        for (Venda venda : registroVendas) {
                            if (venda.getDataVenda().equals(dataBuscaFormatada)) {
                                System.out.println("Quantidade: " + venda.getQuant()
                                        + ", Preco Unitario: R$ " + venda.getValunid()
                                        + ", Valor Final: R$ " + venda.getValorFinal());
                                faturamentoDia += venda.getValorFinal();
                                encontrouVendas = true;
                            }
                        
                        }
                        if (!encontrouVendas) {
                            System.out.println("Nenhuma venda encontrada neste periodo.");
                        } else {
                            System.err.println("Faturamento total R$ " + faturamentoDia);
                        }
                           
                    } catch (Exception e) {
                        System.out.println("Data invalida! Use o formato dd/MM/yyyy.");
                    }
                        break;

                case 5:
                    System.out.println("\n--- Lista de Vendedores ---");
                    for (Vendedor v : lojaAtual.vendedores) {
                        v.apresentarVendedor();
                    }
                    break;

                case 6: 
                    System.out.println("\n--- Lista de Clientes ---");
                    for (Cliente c : lojaAtual.clientes) {
                        c.apresentarCliente();
                    }
                    break;

                case 7:
                    lojaAtual.apresentarLoja();
                    break;

                case 8:
                    System.out.println("\n--- Média Salarial dos Vendedores ---");  
                    for (Vendedor v : lojaAtual.vendedores) {
                        System.out.println("\nVendedor: " + v.nome);
                        v.calcularMedia();
                    }
                    break;
                    
                case 9:
                    System.out.println("\n--- Bonus dos Vendedores ---");  
                    for (Vendedor v : lojaAtual.vendedores) {
                        System.out.println("\nVendedor: " + v.nome);
                        v.calcularBonus();
                    }
                    break;
                    
                case 10:
                    System.out.println("Voltando ao menu de seleção de loja...\n");
                    main(args); // Chama o método main para reiniciar o processo de seleção de loja
                    return; // Encerra o método atual para evitar execução adicional    
                    

                case 0:
                    System.out.println("Encerrando o programa. Obrigado por usar o Sistema!"); 
                    break;

                default:
                    System.out.println("Opção inválida! Por favor, escolha uma opção entre 1 e 8.");
                    break; 
            }

        } while (escolha != 10 && escolha != 0);

        scan.close();
    }

    public static double calculoTroco(double valorRecebido, double valorCompra) {
        if (valorRecebido < valorCompra) {
            System.out.println("Valor recebido é menor que o valor da compra. Troco não calculado.");
            return 0.0;
        }
        return valorRecebido - valorCompra;
    }

    

}