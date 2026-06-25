package fag;

import java.util.Scanner;
import java.util.ArrayList;

public class aula03 {
    static int qntPlanta = 0;
    static double valorUnPlanta = 0;
    static double totalComDesconto = 0;
    static double valorDescontoAplicado = 0;
    
    static ArrayList<String> registroVendas = new ArrayList<>();
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        compras();
        visuMenu();
    }

    static void compras() {
        System.out.println("\nValor da unidade (planta):");
        valorUnPlanta = scan.nextDouble();
        System.out.println("Quantas plantas deseja levar?");
        qntPlanta = scan.nextInt();
    }

    private static void visuMenu() {
        int escolhaUser = 0;
        do {
            System.out.println("[1] - Calcular Preço Total (com descontos)");
            System.out.println("[2] - Pagamento e Troco");
            System.out.println("[3] - Ver Registro de Vendas");
            System.out.println("[4] - Nova Compra");
            System.out.println("[0] - Sair");
            System.out.print("Escolha: ");
            escolhaUser = scan.nextInt();
            
            validaEscolha(escolhaUser);
        } while (escolhaUser != 0);
    }

    private static void validaEscolha(int escolhaUser) {
        switch (escolhaUser) {
            case 1:
                calculoPrecoTotal();
                break;
            case 2:
                if (totalComDesconto <= 0) {
                    System.out.println("\n# ERRO # Calcule o valor antes de pagar!");
                } else {
                    calculaTroco();
                }
                break;
            case 3:
                exibirRegistro();
                break;
            case 4:
                compras();
                totalComDesconto = 0;
                break;
            case 0:
                System.out.println("Encerrando... Obrigado por utilizar o sistema!");
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private static void calculoPrecoTotal() {
        double totalBruto = qntPlanta * valorUnPlanta;
        valorDescontoAplicado = 0;

        if (qntPlanta > 10) {
            valorDescontoAplicado = totalBruto * 0.05; // 5% de desconto
            totalComDesconto = totalBruto - valorDescontoAplicado;
            System.out.println("DESCONTO APLICADO (Mais de 10 plantas): 5%");
        } else {
            totalComDesconto = totalBruto;
        }

        if (totalComDesconto <= 0) {
            System.out.println("Quantidade ou valor inválido.");
            compras();
        } else {
            System.out.printf("Valor Total Bruto: R$ %.2f\n", totalBruto);
            System.out.printf("Desconto: R$ %.2f\n", valorDescontoAplicado);
            System.out.printf("Valor Total a Pagar: R$ %.2f\n", totalComDesconto);
        }
    }

    private static void calculaTroco() {
        double pagamento = 0;
        while (true) {
            System.out.printf("Total a pagar: R$ %.2f | Valor recebido: ", totalComDesconto);
            pagamento = scan.nextDouble();

            if (pagamento >= totalComDesconto) {
                break;
            }
            System.out.println("Valor Insuficiente! Tente novamente.");
        }

        double troco = pagamento - totalComDesconto;
        System.out.printf("Troco: R$ %.2f\n", troco);

        // Salva a venda no registro após o pagamento
        String dadosVenda = "Qtd: " + qntPlanta + " | Total: R$ " + totalComDesconto + " | Desc: R$ " + valorDescontoAplicado;
        registroVendas.add(dadosVenda);
        
        System.out.println("Venda registrada com sucesso!");
        totalComDesconto = 0; // Limpa o carrinho após finalizar
    }

    private static void exibirRegistro() {
        System.out.println("\n--- HISTÓRICO DE VENDAS ---");
        if (registroVendas.isEmpty()) {
            System.out.println("Nenhuma venda realizada ainda.");
        } else {
            for (String venda : registroVendas) {
                System.out.println(venda);
            }
        }
    }
}
