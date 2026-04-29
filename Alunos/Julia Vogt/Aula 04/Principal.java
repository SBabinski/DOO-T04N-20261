package Calculadora;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

    static Scanner scanner = new Scanner(System.in);

    static ArrayList<LocalDate> historicoDatas = new ArrayList<>();

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {

        int menu = 0;

        while (menu != 6) {

            System.out.println("\n1 - Calcular Preço Total");
            System.out.println("2 - Calcular Troco");
            System.out.println("3 - Histórico de Vendas");
            System.out.println("4 - Buscar vendas por DIA");
            System.out.println("5 - Buscar vendas por MÊS");
            System.out.println("6 - Sair");

            menu = scanner.nextInt();
            scanner.nextLine();

            switch (menu) {

                case 1:
                    precoTotal();
                    break;

                case 2:
                    troco();
                    break;

                case 3:
                    exibirHistorico();
                    break;

                case 4:
                    buscarPorDia();
                    break;

                case 5:
                    buscarPorMes();
                    break;

                case 6:
                    System.out.println("Sistema encerrado.");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public static void precoTotal() {

        System.out.println("Informe a quantidade de plantas:");
        int quantidade = scanner.nextInt();

        System.out.println("Informe o valor da planta:");
        float precoUnitario = scanner.nextFloat();

        float precoTotal = quantidade * precoUnitario;
        float desconto = 0;

        if (quantidade > 10) {
            desconto = precoTotal * 0.05f;
            precoTotal -= desconto;

            System.out.println("Você ganhou 5% de desconto!");
        }

        System.out.println("Valor final da compra: " + precoTotal);

        LocalDate hoje = LocalDate.now();
        historicoDatas.add(hoje);

        System.out.println("Venda registrada em: " + hoje.format(formatter));
    }

    public static void troco() {

        System.out.println("Informe o valor entregue pelo cliente:");
        float valorCliente = scanner.nextFloat();

        System.out.println("Informe o valor total da compra:");
        float valorCompra = scanner.nextFloat();

        float troco = valorCliente - valorCompra;

        System.out.println("Valor do troco: " + troco);
    }

    public static void exibirHistorico() {

        if (historicoDatas.isEmpty()) {
            System.out.println("Nenhuma venda registrada ainda.");
        } else {

            System.out.println("\n===== HISTÓRICO DE VENDAS =====");

            for (LocalDate data : historicoDatas) {
                System.out.println(data.format(formatter));
            }
        }
    }

    public static void buscarPorDia() {

        System.out.println("Digite a data (dd/MM/yyyy):");
        String dataInput = scanner.nextLine();

        LocalDate dataBusca = LocalDate.parse(dataInput, formatter);

        int contador = 0;

        for (LocalDate data : historicoDatas) {
            if (data.equals(dataBusca)) {
                contador++;
            }
        }

        System.out.println("Total de vendas no dia: " + contador);
    }

    public static void buscarPorMes() {

        System.out.println("Digite o mês e ano (MM/yyyy):");
        String input = scanner.nextLine();

        String[] partes = input.split("/");

        int mes = Integer.parseInt(partes[0]);
        int ano = Integer.parseInt(partes[1]);

        int contador = 0;

        for (LocalDate data : historicoDatas) {

            if (data.getMonthValue() == mes && data.getYear() == ano) {
                contador++;
            }
        }

        System.out.println("Total de vendas no mês: " + contador);
    }
}