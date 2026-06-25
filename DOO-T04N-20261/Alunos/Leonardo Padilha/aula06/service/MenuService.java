package calculadora.service;

import java.util.Scanner;

public class MenuService {

    Scanner scan = new Scanner(System.in);
    CalculadoraService calculadora = new CalculadoraService();

    public void exibirMenu() {

        int opcao = 0;

        while (opcao != 6) {

            System.out.println("\n===== SISTEMA MY PLANT =====");
            System.out.println("[1] - Registrar Venda");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Listar Vendas");
            System.out.println("[4] - Buscar Vendas por DIA");
            System.out.println("[5] - Buscar Vendas por MÊS");
            System.out.println("[6] - Sair");

            opcao = scan.nextInt();
            scan.nextLine();

            switch (opcao) {

                case 1:
                    calculadora.registrarVenda();
                    break;

                case 2:
                    calculadora.calcularTroco();
                    break;

                case 3:
                    calculadora.listarVendas();
                    break;

                case 4:
                    System.out.println("Digite a data (dd/MM/yyyy): ");
                    String dia = scan.nextLine();
                    calculadora.listarVendasPorData(dia, "DIA");
                    break;

                case 5:
                    System.out.println("Digite o mês (MM/yyyy): ");
                    String mes = scan.nextLine();
                    calculadora.listarVendasPorData(mes, "MES");
                    break;

                case 6:
                    System.out.println("Sistema encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}