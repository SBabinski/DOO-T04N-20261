package fag;

import fag.objetos.Vendas;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaLojaGabrielinha {

	static List<Vendas> registroDeVendas = new ArrayList<>();
	static Scanner scan = new Scanner(System.in);
	static double precoTotal = 0;

	public static void main(String[] args) {
		mostrarMenu();
	}

	private static void mostrarMenu() {
		int escolha = 0;
		do {
			System.out.println("\n---------Menu---------");
			System.out.println("1 - NOVA COMPRA - Calcular Preço Total");
			System.out.println("2 - NOVA COMPRA - Calcular Troco");
			System.out.println("3 - Registro de Vendas Totais");
			System.out.println("4 - Registro de Vendas - Consulta por Data");
			System.out.println("0 - Sair");
			escolha = scan.nextInt();
			scan.nextLine();
			validarEscolha(escolha);
		} while (escolha != 0);
	}

	private static void validarEscolha(int escolha) {
		switch (escolha) {

		case 4:
			menuConsultaPorData();
			break;
		case 3:
			listarRegistroVendas();
			break;
		case 2:
			calcularTroco(precoTotal);
			break;
		case 1:
			precoTotal = registrarVenda();
			break;
		case 0:
			System.out.println("Sistema Encerrado!");
			break;
		default:
			System.out.println("Digite uma opção válida!");
			break;
		}
	}

	public static void menuConsultaPorData() {
		int opcao;

		do {
			System.out.println("\n--- Consulta por Data ---");
			System.out.println("1 - Buscar por dia e mês");
			System.out.println("2 - Buscar por mês");
			System.out.println("0 - Voltar");

			opcao = scan.nextInt();

			switch (opcao) {
			case 1:
				buscarPorDiaMes();
				break;
			case 2:
				buscarPorMes();
				break;
			case 0:
				break;
			default:
				System.out.println("Opção inválida!");
			}

		} while (opcao != 0);
	}

	public static List<Vendas> filtrarData(int dia, int mes) {
		List<Vendas> filtradas = new ArrayList<>();

		for (Vendas vendasFiltradas : registroDeVendas) {

			boolean filtrarMes = vendasFiltradas.getData().getMonthValue() == mes;
			boolean filtrarDia = vendasFiltradas.getData().getDayOfMonth() == dia;

			if (filtrarMes && (dia == 0 || filtrarDia)) {
				filtradas.add(vendasFiltradas);
			}
		}
		return filtradas;
	}

	public static void buscarPorDiaMes() {

		System.out.print("Digite o dia: ");
		int dia = scan.nextInt();

		while (dia < 1 || dia > 31) {
			System.out.println("Dia inválido! Digite entre 1 e 31:");
			dia = scan.nextInt();
		}

		System.out.print("Digite o mês: ");
		int mes = scan.nextInt();

		while (mes < 1 || mes > 12) {
			System.out.println("Mês inválido! Digite entre 1 e 12:");
			mes = scan.nextInt();
		}

		List<Vendas> vendas = filtrarData(dia, mes);

		if (vendas.isEmpty()) {
			System.out.println("Nenhuma venda encontrada!");
		} else {
			System.out.printf("\n------ Vendas para %02d/%02d - Total: %d ------\n", dia, mes, vendas.size());
			int contador = 0;

			for (Vendas vendasdiarias : vendas) {
				contador++;
				System.out.printf("Venda %d - ", contador);
				vendasdiarias.mostrarDetalhes();
			}
		}
	}

	public static void buscarPorMes() {

		System.out.print("Digite o mês: ");
		int mes = scan.nextInt();

		while (mes < 1 || mes > 12) {
			System.out.println("Mês inválido! Digite entre 1 e 12:");
			mes = scan.nextInt();
		}

		List<Vendas> vendas = filtrarData(0, mes);

		if (vendas.isEmpty()) {
			System.out.println("Nenhuma venda encontrada!");
		} else {
			System.out.printf("\n------ Vendas do mês %02d - Total: %d ------\n", mes, vendas.size());

			int contador = 0;

			for (Vendas vendasmensais : vendas) {
				contador++;
				System.out.printf("Venda %d - ", contador);
				vendasmensais.mostrarDetalhes();
			}
		}
	}

	public static double registrarVenda() {
		Vendas venda = criarVenda();
		registroDeVendas.add(venda);

		System.out.println("\n------ Detalhes da Venda: ------");
		venda.mostrarDetalhes();

		return venda.getValorFinal();
	}

	public static Vendas criarVenda() {
		System.out.println("------ Nova Venda ------");

		System.out.print("Preço: R$ ");
		double preco = scan.nextDouble();
		while (preco <= 0) {
			System.out.println("Preço inválido!");
			preco = scan.nextDouble();
		}

		System.out.print("Quantidade: ");
		int quantidade = scan.nextInt();
		while (quantidade <= 0) {
			System.out.println("Quantidade inválida!");
			quantidade = scan.nextInt();
		}

		return new Vendas(quantidade, preco);
	}

	public static void listarRegistroVendas() {
		if (registroDeVendas.isEmpty()) {
			System.out.println("Nenhuma venda registrada!");
			return;
		}

		System.out.println("\n------- Registro de vendas: ------ ");
		for (int i = 0; i < registroDeVendas.size(); i++) {
			System.out.printf("\nVenda %d - ", i + 1);
			registroDeVendas.get(i).mostrarDetalhes();
		}
		System.out.println("\n");
	}

	public static void calcularTroco(double precoTotal) {
		if (precoTotal <= 0) {
			System.out.println("Calcule o preço total primeiro!");
			return;
		}

		System.out.println("------ Calculadora de Troco ------");
		System.out.printf("Valor Total: R$ %.2f\n", precoTotal);

		System.out.print("Valor Pago: ");
		double pagamento = scan.nextDouble();

		while (pagamento < precoTotal) {
			double falta = precoTotal - pagamento;
			System.out.printf("Valor insuficiente! Faltam: R$ %.2f\n", falta);

			System.out.print("Digite novamente o valor pago: ");
			pagamento = scan.nextDouble();
		}

		double troco = pagamento - precoTotal;
		System.out.printf("Valor a ser devolvido: R$ %.2f\n", troco);
	}

}
