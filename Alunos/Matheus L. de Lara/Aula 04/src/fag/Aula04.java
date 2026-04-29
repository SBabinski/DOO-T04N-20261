package fag;

import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Aula04 {
    static int qntPlanta = 0;
    static double valorUnPlanta = 0;
    static double totalComDesconto = 0;
    static double valorDescontoAplicado = 0;
    
    // Agora o ArrayList armazena Objetos do tipo Venda
    static ArrayList<Venda> registroVendas = new ArrayList<>();
    static Scanner scan = new Scanner(System.in);
    
    // Formatador para exibir e ler datas no padrão brasileiro
    static DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Classe de apoio para organizar os dados da venda
    static class Venda {
        LocalDate data;
        int quantidade;
        double valorTotal;

        Venda(LocalDate data, int quantidade, double valorTotal) {
            this.data = data;
            this.quantidade = quantidade;
            this.valorTotal = valorTotal;
        }
    }

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
            System.out.println("\n--- MENU LOJA ---");
            System.out.println("[1] - Calcular Preço Total");
            System.out.println("[2] - Pagamento e Troco");
            System.out.println("[3] - Ver Registro Geral");
            System.out.println("[4] - Nova Compra");
            System.out.println("[5] - Buscar Vendas por Data"); // Nova funcionalidade
            System.out.println("[0] - Sair");
            System.out.print("Escolha: ");
            escolhaUser = scan.nextInt();
            
            validaEscolha(escolhaUser);
        } while (escolhaUser != 0);
    }

    private static void validaEscolha(int escolhaUser) {
        switch (escolhaUser) {
            case 1: calculoPrecoTotal(); break;
            case 2:
                if (totalComDesconto <= 0) {
                    System.out.println("\n# ERRO # Calcule o valor antes de pagar!");
                } else {
                    calculaTroco();
                }
                break;
            case 3: exibirRegistro(); break;
            case 4:
                compras();
                totalComDesconto = 0;
                break;
            case 5:
                buscarPorData();
                break;
            case 0:
                System.out.println("Encerrando...");
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private static void calculoPrecoTotal() {
        double totalBruto = qntPlanta * valorUnPlanta;
        valorDescontoAplicado = 0;

        if (qntPlanta > 10) {
            valorDescontoAplicado = totalBruto * 0.05;
            totalComDesconto = totalBruto - valorDescontoAplicado;
        } else {
            totalComDesconto = totalBruto;
        }

        System.out.printf("Valor Total a Pagar: R$ %.2f\n", totalComDesconto);
    }

    private static void calculaTroco() {
        double pagamento = 0;
        while (true) {
            System.out.printf("Total: R$ %.2f | Recebido: ", totalComDesconto);
            pagamento = scan.nextDouble();
            if (pagamento >= totalComDesconto) break;
            System.out.println("Valor Insuficiente!");
        }

        double troco = pagamento - totalComDesconto;
        System.out.printf("Troco: R$ %.2f\n", troco);

        // Criando o objeto venda com a data atual do sistema
        Venda novaVenda = new Venda(LocalDate.now(), qntPlanta, totalComDesconto);
        registroVendas.add(novaVenda);
        
        System.out.println("Venda registrada com sucesso!");
        totalComDesconto = 0;
    }

    private static void exibirRegistro() {
        System.out.println("\nHISTÓRICO COMPLETO");
        if (registroVendas.isEmpty()) {
            System.out.println("Vazio.");
        } else {
            for (Venda v : registroVendas) {
                // Usando o formatador para a data ficar bonita
                System.out.println("Data: " + v.data.format(formatador) + " | Qtd: " + v.quantidade + " | Valor: R$ " + v.valorTotal);
            }
        }
    }

    // NOVA FUNCIONALIDADE REQUERIDA
    private static void buscarPorData() {
        System.out.println("\nDigite a data para busca (Ex: 23/03/2026):");
        String dataInput = scan.next();
        
        try {
            // Converte a String do usuário para LocalDate
            LocalDate dataBusca = LocalDate.parse(dataInput, formatador);
            int totalPlantasDia = 0;
            double faturamentoDia = 0;
            boolean encontrou = false;

            for (Venda v : registroVendas) {
                if (v.data.equals(dataBusca)) {
                    totalPlantasDia += v.quantidade;
                    faturamentoDia += v.valorTotal;
                    encontrou = true;
                }
            }

            if (encontrou) {
                System.out.println("--- RESULTADO DO DIA " + dataInput + " ---");
                System.out.println("Total de plantas vendidas: " + totalPlantasDia);
                System.out.printf("Faturamento total: R$ %.2f\n", faturamentoDia);
            } else {
                System.out.println("Nenhuma venda encontrada para esta data.");
            }
        } catch (Exception e) {
            System.out.println("Formato de data inválido! Use dd/MM/yyyy.");
        }
    }
}