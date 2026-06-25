package Alunos.Melissa_Ghellere.aula04;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EstoquePlantas {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        // Arrays para armazenar os dados (Exemplo com 100 posições)
        String[] nomes = new String[100];
        double[] precos = new double[100];
        int totalItens = 0;
        int opcao = 0;

        while (opcao != 4) {
            System.out.println("\n--- My Plant - Gestão de Estoque ---");
            System.out.println("[1] Cadastrar Item");
            System.out.println("[2] Listar Itens");
            System.out.println("[3] Buscar Item por Nome");
            System.out.println("[4] Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine(); // Limpar o buffer

            if (opcao == 1) {
                System.out.print("Nome da planta: ");
                nomes[totalItens] = sc.nextLine();
                System.out.print("Preço unitário: ");
                precos[totalItens] = sc.nextDouble();
                totalItens++;
                System.out.println("Item cadastrado com sucesso!");

            } else if (opcao == 2) {
                System.out.println("\n--- Relatório de Estoque - " + LocalDate.now().format(formato) + " ---");
                for (int i = 0; i < totalItens; i++) {
                    System.out.println(nomes[i] + " - R$ " + precos[i]);
                }

            } else if (opcao == 3) {
                System.out.print("Digite o nome para buscar: ");
                String busca = sc.nextLine();
                boolean encontrado = false;

                for (int i = 0; i < totalItens; i++) {
                    if (nomes[i].equalsIgnoreCase(busca)) {
                        System.out.println("Item encontrado! Preço: R$ " + precos[i]);
                        encontrado = true;
                        break;
                    }
                }
                if (!encontrado) {
                    System.out.println("Item não localizado no estoque.");
                }
            }
        }
        sc.close();
    }
}
