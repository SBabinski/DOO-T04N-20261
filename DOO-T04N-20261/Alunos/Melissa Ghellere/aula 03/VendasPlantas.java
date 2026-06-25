package Alunos.Melissa_Ghellere.aula03;

import java.util.Scanner;

public class VendasPlantas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Vetores para armazenar as quantidades e os preços das vendas
        int[] quantidades = new int[100];
        double[] precos = new double[100];
        int totalVendas = 0;
        int opcao = 0;

        while (opcao != 3) {
            System.out.println("\n--- My Plant - Registro de Vendas ---");
            System.out.println("[1] Registrar Venda");
            System.out.println("[2] Listar Vendas do Dia");
            System.out.println("[3] Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();

            if (opcao == 1) {
                System.out.print("Quantidade vendida: ");
                quantidades[totalVendas] = sc.nextInt();
                System.out.print("Preço unitário: ");
                precos[totalVendas] = sc.nextDouble();
                totalVendas++;
                System.out.println("Venda registrada com sucesso!");

            } else if (opcao == 2) {
                System.out.println("\n--- Relatório de Vendas ---");
                double faturamentoTotal = 0;
                
                for (int i = 0; i < totalVendas; i++) {
                    double valorVenda = quantidades[i] * precos[i];
                    faturamentoTotal += valorVenda;
                    System.out.println("Venda " + (i + 1) + ": " + quantidades[i] + " unid. x R$ " + precos[i] + " = R$ " + valorVenda);
                }
                System.out.println("---------------------------");
                System.out.println("Faturamento Total: R$ " + faturamentoTotal);
            }
        }

        System.out.println("Sistema encerrado.");
        sc.close();
    }
}
