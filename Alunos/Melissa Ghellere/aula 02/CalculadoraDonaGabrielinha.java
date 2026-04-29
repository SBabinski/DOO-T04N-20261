package Alunos.Melissa_Ghellere.aula02;

import java.util.Scanner;

public class CalculadoraDonaGabrielinha {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao = 0;

        while (opcao != 3) {
            System.out.println("\n--- Calculadora da Dona Gabrielinha ---");
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Calcular Troco");
            System.out.println("[3] - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();

            if (opcao == 1) {
                // Cálculo de Preço Total
                System.out.print("Digite a quantidade da planta: ");
                int quantidade = sc.nextInt();
                System.out.print("Digite o preço unitário: R$ ");
                double preco = sc.nextDouble();
                
                double total = quantidade * preco;
                System.out.println("O preço total da venda é: R$ " + total);

            } else if (opcao == 2) {
                // Cálculo de Troco
                System.out.print("Digite o valor recebido pelo cliente: R$ ");
                double valorRecebido = sc.nextDouble();
                System.out.print("Digite o valor total da compra: R$ ");
                double valorTotal = sc.nextDouble();
                
                double troco = valorRecebido - valorTotal;
                System.out.println("O troco a ser dado é: R$ " + troco);
            }
        }

        System.out.println("Sistema encerrado. Tenha um ótimo dia!");
        sc.close();
    }
}
