package calculadora_dona_gabrielinha;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<Venda> vendas = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        boolean sair = false;

        while (!sair) {

            System.out.println("\n===== MENU DE OPCOES =====\n");
            System.out.println("[1] - Vender\n");
            System.out.println("[2] - Calcular Troco\n");
            System.out.println("[3] - Ver Relatorio\n");
            System.out.println("[4] - Consultar Quantidade de Vendas por Dia\n");
            System.out.println("[5] - Consultar Quantidade de Vendas por Mes\n");
            System.out.println("[6] - Sair\n");
            System.out.println("===== ===== ===== =====\n");
            System.out.println("Escolha uma opcao: ");

            int opcao = scanner.nextInt();

            switch (opcao) {

                case 1:
                	try {
	                    System.out.print("Informe a quantidade de plantas: ");
	                    int quantidade = scanner.nextInt();
	
	                    System.out.print("Informe o preco unitario da planta: ");
	                    float valorUn = scanner.nextFloat();
	                    scanner.nextLine();
	                    
	                    System.out.print("Informe a data da venda (dd/MM/yyyy): ");
	                    String dataTexto = scanner.nextLine();
	                    
	                    LocalDate dataVenda = LocalDate.parse(dataTexto, formatter);
	
	                    Venda venda = new Venda(quantidade, valorUn, dataVenda);
	                    vendas.add(venda);
	
	                    System.out.println("\nVenda registrada com sucesso na data: " + venda.getDataFormatada());
	                    System.out.printf("Preco total da venda: %.2f\n", venda.getTotal());
	                    System.out.printf("Desconto concedido: %.2f\n", venda.getDesconto());

		            } catch (DateTimeParseException e) {
		                System.out.println("\nData invalida! Use o formato dd/MM/yyyy.");
		            }
                	
                    break;

                case 2:

                    System.out.print("Informe o valor recebido: ");
                    float valorRecebido = scanner.nextFloat();

                    System.out.print("Informe o valor total da compra: ");
                    float valorCompra = scanner.nextFloat();

                    float troco = valorRecebido - valorCompra;

                    System.out.printf("Valor do troco devido: %.2f\n", troco);
                    break;

                case 3:

                    int totalPlantas = 0;
                    float totalVendas = 0;
                    float totalDescontos = 0;

                    for (Venda v : vendas) {
                        totalPlantas += v.getQuantidade();
                        totalVendas += v.getTotal();
                        totalDescontos += v.getDesconto();
                    }

                    System.out.println("\n===== RELATORIO =====");
                    System.out.println("Quantidade de vendas: " + vendas.size());
                    System.out.println("Total de plantas: " + totalPlantas);
                    System.out.printf("Total vendido: %.2f\n", totalVendas);
                    System.out.printf("Total descontos: %.2f\n", totalDescontos);

                    break;

                case 4:
                	 try {
                		 scanner.nextLine();
                         System.out.print("Qual a data para consulta, use (dd/MM/yyyy):");
                         String dataConsultaString = scanner.nextLine();

                         LocalDate dataConsulta = LocalDate.parse(dataConsultaString, formatter);

                         int quantidadeVendasDia = 0;

                         for (Venda v : vendas) {
                             if (v.getDataVenda().equals(dataConsulta)) {
                                 quantidadeVendasDia++;
                             }
                         }

                         System.out.println("Quantidade total de vendas em " 
                                 + dataConsulta.format(formatter) + ": " + quantidadeVendasDia);

 		            } catch (DateTimeParseException e) {
		                System.out.println("Data invalida! Use o formato dd/MM/yyyy.");
		            }
                	 
                     break;
                     
                case 5:
                    System.out.print("Informe o mes em numeral (01 - 12): ");
                    int mes = scanner.nextInt();

                    System.out.print("Informe o ano: ");
                    int ano = scanner.nextInt();
                    scanner.nextLine();

                    int quantidadeVendasMes = 0;

                    for (Venda v : vendas) {
                        if (v.getDataVenda().getMonthValue() == mes && v.getDataVenda().getYear() == ano) {
                            quantidadeVendasMes++;
                        }
                    }

                    System.out.println("Quantidade total de vendas no mes " + mes + "/" + ano + ": " + quantidadeVendasMes);
                    break;
                    
                case 6:
                    System.out.println("Voce escolheu sair do menu!");
                    sair = true;
                    break;

                default:
                    System.out.println("Opcao invalida!");
            }
        }

        scanner.close();
    }
}