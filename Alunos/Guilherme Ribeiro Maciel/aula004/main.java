package calculadora;

import java.util.Scanner;
import calculadora.Venda;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.*;

public class main {

	static Scanner scan = new Scanner(System.in);
	static List<Venda> Venda = new ArrayList<>();
	
	public static void main(String[] args) {
		Menu();
	}
	
	public static void Menu() {
		int opt = 0;
		do {
			System.out.println("Entre com a opção desejada");
			System.out.println("0-Sair");
			System.out.println("1-Calcular Preço Total");
			System.out.println("2-Calcular Troco");
			System.out.println("3-Listagem de Vendas");
			System.out.println("4-Relatorio por Dia/Mes");
			opt = scan.nextInt();
			scan.nextLine();
			switch(opt) {
			case 0:
				break;
			case 1:
				CalcularPreco();
				break;
			case 2:
				CalcularTroco();
				break;
			case 3:
				Listagem();
				break;
			case 4:
				RelatarioDiaMes();
				break;
			default:
				System.out.println("Opção Invalida, porfavor tente novamente");
			}
		} while(opt!=0);
	}
	
	private static void RelatarioDiaMes() {
		DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println("Entre com a data desejada para o relatorio");
		String dataEntrada = scan.nextLine();
		LocalDate data = LocalDate.parse(dataEntrada, formatoEntrada);
		String dataJava = data.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate dataData = LocalDate.parse(dataJava);
		System.out.println("Data da Venda||Vl Uni.||QTD||Vl Total");
		int cont=0;
		for (int i=0; i < Venda.size(); i++) {
			if(dataData.isEqual(Venda.get(i).getData())) {
				System.out.println(i+1 + " - " + Venda.get(i).resumo());
				cont++;
			}
		}
		System.out.println("Vendas Totais do Dia: " + cont);
	}

	private static void Listagem() {
		System.out.println("Data da Venda||Vl Uni.||QTD||Vl Total");
		for (int i=0; i < Venda.size(); i++) {
			System.out.println(i+1 + " - " + Venda.get(i).resumo());
		}
	}

	public static void CalcularPreco() {
		Venda venda = new Venda();
		venda.setData(LocalDate.now());
		System.out.println("Entre com o valor da Planta:");
		venda.setValorUni(scan.nextFloat());
		scan.nextLine();
		System.out.println("Entre com a quantidade de Plantas:");
		venda.setQtd(scan.nextInt());
		scan.nextLine();
		Venda.add(venda);
		System.out.println("O Valor Total da Compra de Plantas é: " + venda.vlTotal());		
	}
	
	public static void CalcularTroco() {
		 System.out.println("Entre com o Valor Recebido:");
		 float vlr = scan.nextFloat();
		 System.out.println("Entre com o Preço da Compra de Plantas");
		 float vlt = scan.nextFloat();
		 float vltr = vlr - vlt;
		 System.out.println("O Valor do Troco é: " + vltr);
	}
	
}
