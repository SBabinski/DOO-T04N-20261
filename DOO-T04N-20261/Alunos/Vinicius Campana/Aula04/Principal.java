package doo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Principal {

	static Scanner scanner = new Scanner(System.in);
	static ArrayList<String> historicoVendas = new ArrayList<>();
	static Map<LocalDate, Integer> vendasPorData = new HashMap<>();
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public static void main(String[] args) {

		int menu = 0;
		while (menu != 7) {

			System.out.println("\n1 - Calcular Preço Total");
			System.out.println("2 - Calcular Troco");
			System.out.println("3 - Ver registros de venda");
			System.out.println("4 - Registrar venda por data");
			System.out.println("5 - Consultar vendas por dia");
			System.out.println("6 - Consultar vendas por mês");
			System.out.println("7 - Sair");

			menu = scanner.nextInt();

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
				registrarVendaPorData();
				break;

			case 5:
				consultarVendaPorData();
				break;

			case 6:
				consultarVendasPorMes();
				break;

			case 7:
				System.out.println("fim");
				break;

			default:
				System.out.println("Opção inválida\n");
			}
		}
	}

	public static void precoTotal() {
		System.out.println("informe a quantidade da planta ");
		float quantidade = scanner.nextFloat();

		System.out.println("informe o valor da planta");
		float precoUnitario = scanner.nextFloat();

		float precoTotal = quantidade * precoUnitario;
		float desconto = 0;

		if (quantidade > 10) {
			desconto = precoTotal * 0.05f;
			precoTotal = precoTotal - desconto;

			System.out.println("\nrecebeu 5% de desconto ao comprar acima de dez unidades");
		}

		System.out.println("\no valor total da compra ficou: " + precoTotal);

		LocalDate dataAtual = LocalDate.now();

		String dataFormatada = dataAtual.format(formatter);

		String venda = "Data: " + dataFormatada + 
				" | Quantidade: " + quantidade + 
				" | Preço unitário: " + precoUnitario+ 
				" | Desconto: " + desconto + 
				" | Total pago: " + precoTotal;

		historicoVendas.add(venda);

		vendasPorData.put(dataAtual, vendasPorData.getOrDefault(dataAtual, 0) + 1);
	}

	public static void troco() {
		System.out.println("informe o valor entregue pelo cliente");
		float valorDoCliente = scanner.nextFloat();

		System.out.println("informe o valor total da compra");
		float valorCompra = scanner.nextFloat();

		float troco = valorDoCliente - valorCompra;

		System.out.println("\nValor do troco: " + troco);

	}

	public static void registrarVendaPorData() {

		System.out.println("Digite a data (dd/MM/yyyy): ");
		String dataTexto = scanner.next();

		LocalDate data = LocalDate.parse(dataTexto, formatter);

		System.out.println("Digite a quantidade de vendas: ");
		int quantidade = scanner.nextInt();

		vendasPorData.put(data, vendasPorData.getOrDefault(data, 0) + quantidade);

		System.out.println("Venda registrada com sucesso!");
	}

	public static void exibirHistorico() {

		if (historicoVendas.isEmpty()) {
			System.out.println("Sem vendas registradas ainda.");
		} else {

			System.out.println("\n      HISTÓRICO DE VENDAS     ");

			for (String venda : historicoVendas) {
				System.out.println(venda);
			}
		}
	}

	public static void consultarVendaPorData() {

		System.out.println("Digite a data (dd/MM/yyyy): ");
		String dataTexto = scanner.next();

		LocalDate data = LocalDate.parse(dataTexto, formatter);

		int total = vendasPorData.getOrDefault(data, 0);

		System.out.println("Total de vendas nesse dia: " + total);
	}

	public static void consultarVendasPorMes() {

		System.out.println("Digite o mês (1 a 12): ");
		int mes = scanner.nextInt();

		System.out.println("Digite o ano (ex: 2026): ");
		int ano = scanner.nextInt();

		int total = 0;

		for (LocalDate data : vendasPorData.keySet()) {

			if (data.getMonthValue() == mes && data.getYear() == ano) {

				total += vendasPorData.get(data);
			}
		}
		System.out.println("Total de vendas no mês: " + total);
	}

}
