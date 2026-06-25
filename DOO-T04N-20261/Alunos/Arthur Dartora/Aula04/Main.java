package fag;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		ArrayList<String> vendas = new ArrayList<>();
		
		HashMap<LocalDate, Integer> vendasPorData = new HashMap<>();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		int opcao = 0;
		
		while(opcao != 5) {
			System.out.println("Calculadora dona Gabrielinha");
			System.out.println("1 Calcular preço total");
			System.out.println("2 Calcular troco");
			System.out.println("3 Ver registro de vendas");
			System.out.println("4 Buscar quantidade de vendas por data");
			System.out.println("5 Sair");
			System.out.println("Escolha uma opcao");
			
			opcao = scan.nextInt();
			
			switch (opcao) {
			
			case 1: {
				
				System.out.println("Digite a quantidade de plantas: ");
				int qtd = scan.nextInt();
				
				System.out.println("Digite o preco da planta: ");
				double preco = scan.nextDouble();
				
				double total = qtd * preco;
				double desconto = 0;
				
				if(qtd > 10) {
					desconto = total * 0.05;
					total = total - desconto;
				}
				
				System.out.println("Total: R$ " + total);
				System.out.println("Desconto: R$ " + desconto);
				
				String registro = "Qtd: " + qtd + " | Preco: " + preco + " | Desconto: " + desconto + " | Total: " + total;
				vendas.add(registro);
				
				LocalDate hoje = LocalDate.now();
				vendasPorData.put(hoje, vendasPorData.getOrDefault(hoje, 0) + 1);
				
				break;
			}
			
			case 2: {
				
				System.out.println("Digite o valor pago: ");
				double pago = scan.nextDouble();
				
				System.out.println("Digite o valor total: ");
				double compra = scan.nextDouble();
				
				double troco = pago - compra;
				
				System.out.println("Troco R$ " + troco);
				break;
			}
			
			case 3: {
				
				System.out.println("Registro de vendas:");
				
				if(vendas.isEmpty()) {
					System.out.println("Nenhuma venda registrada.");
				}else {
					for(String venda : vendas) {
						System.out.println(venda);
					}
				}
				
				break;
			}
			
			case 4: {
				
				scan.nextLine(); 
				
				System.out.println("Digite a data (dd/MM/yyyy): ");
				String dataStr = scan.nextLine();
				
				try {
					LocalDate data = LocalDate.parse(dataStr, formatter);
					
					int quantidade = vendasPorData.getOrDefault(data, 0);
					
					System.out.println("Quantidade de vendas no dia " 
						+ data.format(formatter) + ": " + quantidade);
					
				} catch (Exception e) {
					System.out.println("Data inválida!");
				}
				
				break;
			}
			
			case 5: 
				System.out.println("Saindo...");
				break;
				
			default:
				System.out.println("Opção invalida");
			}
			
			System.out.println();
		}
		scan.close();
	}

}