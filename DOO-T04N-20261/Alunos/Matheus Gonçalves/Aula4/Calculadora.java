package fag;

import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Calculadora {
    static Scanner scan = new Scanner(System.in); 
    
    static ArrayList<Venda> RegistroVendas = new ArrayList<>();

    public static void main(String args[]) {
        Menu();
    }
    
    public static void Menu() {
        System.out.println("-----------------------------------");
        System.out.println("Escolha a Operação");
        System.out.println("[1] - Calcular Preço Total");
        System.out.println("[2] - Calcular Troco");
        System.out.println("[3] - Registro de Vendas");
        System.out.println("[4] - Pesquisar pela Data");
        System.out.println("[5] - Sair");
        int escolha = scan.nextInt();
        
        switch (escolha) {
            case 1:
                PrecoTotal();
            break;
            
            case 2:
                Troco();
            break;
            
            case 3: 
                Registro();
            break;
            
            case 4: 
                PesquisaData();
            break;

            case 5:
                System.out.println("Você saiu do sistema...");
                return;

            default:
                System.out.println("Input invalido");
            break;
        }
        Menu();
    }
    
    public static void PrecoTotal() {
        System.out.println("Qual a quantidade do item ?");
        double qtd = scan.nextDouble();
        if (qtd <= 0) {
            System.out.println("quantidade invalida");
            return;
        }
        
        System.out.println("Qual o valor unitario? ");
        double vlrunt = scan.nextDouble();
        if (vlrunt <= 0) {
            System.out.println("valor invalida");
            return;
        }
        
        double desconto = 0.0;
        double total = qtd * vlrunt;

        if (qtd >= 10) {
            System.out.println("Desconto especial aplicado para mais de 10 itens: 5%");
            desconto = total * 0.05;
            total = total - desconto;
        }
        
        System.out.println("o Total é " + total);
        
        LocalDate hoje = LocalDate.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Venda venda = new Venda(hoje.format(formato), total);
        RegistroVendas.add(venda);
    }
    
    public static void Troco() {
        System.out.println("Qual o valor pago pelo cliente ?");
        double vlrPago = scan.nextDouble();
        if (vlrPago <= 0) {
            System.out.println("valor invalido");
            return;
        }
        
        System.out.println("Qual o total da compra ?");
        double vlrTotal = scan.nextDouble();
        if (vlrTotal <= 0) {
            System.out.println("valor invalido");
            return;
        }
        
        double troco = vlrPago - vlrTotal;

        if (troco < 0) {
            System.out.println("Faltam " + (troco * -1));
        } else {
            System.out.println("o Troco é de R$" + troco);
        }
    }
    
    public static void Registro() {
        for (int i = 0; i < RegistroVendas.size(); i++) {
            Venda v = RegistroVendas.get(i);
            System.out.println("[" + i + "] Data: " + v.getData() + " | Total: " + v.getValor());
        }
    }
    
    public static void PesquisaData() {
        scan.nextLine(); 

        System.out.println("Digite a data (dd/MM/yyyy): ");
        String dataBusca = scan.nextLine();

        int quantidade = 0;
        double soma = 0;

        for (int i = 0; i < RegistroVendas.size(); i++) {
            Venda v = RegistroVendas.get(i);

            if (v.getData().equals(dataBusca)) {
                quantidade++;
                soma += v.getValor();
            }
        }

        System.out.println("Data: " + dataBusca);
        System.out.println("Quantidade de vendas: " + quantidade);
        System.out.println("Total faturado: R$ " + soma);
    }
}