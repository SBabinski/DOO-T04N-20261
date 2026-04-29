package fag;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Locale;
import java.util.ArrayList;
import fag.objetos.VendaPlanta;

public class Principal {
	private static final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
	static ArrayList<VendaPlanta> vendas = new ArrayList<>();
	static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public static void main(String[] args) {
		menu();
	}

	private static void menu() {
		boolean sair = false;

		while (sair == false) {
			System.out.println("Bem vindo a calculadora capaz de realizar as seguintes operações");

			System.out.println("[1] - Visualizar as vendas");
			System.out.println("[2] - Calcular Venda");
			System.out.println("[3] - Sair");
			final int opcao = scanner.nextInt();

			if (opcao == 3) {
				sair = true;
			}

			validarOpcaoEscolhida(opcao);
		}
	}

	private static void validarOpcaoEscolhida(int opcao) {
		switch (opcao) {
			case 1:
				mostraVendas();
				break;
			case 2:
				cadastrarVenda();
				break;
			case 3:
				System.out.println("Volte sempre!");
				break;
			default:
				System.out.println("Opção inválida!");
				break;
		}
	}

	private static void cadastrarVenda() {
		int percentualDesconto = 5;
		boolean temDesconto = false;
		float desconto = 0;
		float troco = 0;

		System.out.println("Informe a quantidade de planta: ");
		final int quantidadePlanta = scanner.nextInt();

		System.out.println("Informe o valor unitário da planta: ");
		final float valorPlantaUnitario = scanner.nextFloat();

		float resultadoCalculoPrecoTotal = calculoPrecoTotal(quantidadePlanta, valorPlantaUnitario);

		if (quantidadePlanta > 10) {
			desconto = resultadoCalculoPrecoTotal / percentualDesconto;
			temDesconto = true;
		}

		resultadoCalculoPrecoTotal -= desconto;

		System.out.printf("O valor total é de: %.2f\n\n", resultadoCalculoPrecoTotal);

		System.out.println("Precisa de troco? (1 -> Sim) (2 -> Não)");
		final int opcao = scanner.nextInt();

		if (opcao == 1) {
			System.out.println("Informe o valor recebido pelo cliente: ");
			final float valorRecebidoCliente = scanner.nextFloat();

			System.out.printf("O valor da compra é: ", resultadoCalculoPrecoTotal);

			final float resultadoTroco = calculoTroco(valorRecebidoCliente, resultadoCalculoPrecoTotal);

			if (resultadoTroco > 0) {
				System.out.printf("O valor do troco é de: %.2f\n\n", resultadoTroco);
				troco = resultadoTroco;
			} else if (resultadoTroco == 0) {
				System.out.println("Não é necessário dar troco de volta!");
			} else {
				System.out.println("Valor Insuficiente da parte do cliente.");
				System.out.printf("Valor faltante: %.2f\n\n", Math.abs(resultadoTroco));
			}
		}

		System.out.println(
				"Você quer salvar a venda com a data de hoje ou selecionar uma data específica para essa venda? (1 - Pega automaticamente a data de hoje, 2 - Seleciona manualmente uma data)");
		final int opcaoData = scanner.nextInt();
		scanner.nextLine();

		LocalDate dataRegistro = LocalDate.now();
		if (opcaoData == 2) {
			String dataEntrada = null;

			while (dataEntrada == null) {
				try {
					System.out.println("Informe a data (dd/MM/yyyy)");
					dataEntrada = scanner.nextLine();

					dataRegistro = LocalDate.parse(dataEntrada, formatter);
				} catch (Exception error) {
					System.out.println(error);
				}
			}
		}

		vendas.add(new VendaPlanta(quantidadePlanta, resultadoCalculoPrecoTotal, desconto, temDesconto,
				troco, dataRegistro));
	}

	private static void mostraVendas() {
		if (vendas.size() > 0) {
			System.out.println(
					"Você quer mostrar todas as vendas ou filtrar por dia, mês e ano? (1 - Mostrar todas as vendas, 2 - Filtrar por dia, mês e ano)");
			final int opcao = scanner.nextInt();

			if (opcao == 1) {
				for (VendaPlanta venda : vendas) {
					venda.mostrarRegistroVendas();
					System.out.println();
				}
			}

			scanner.nextLine();
			if (opcao == 2) {
				LocalDate dataInicio = null;
				LocalDate dataFinal = null;

				while (dataInicio == null || dataFinal == null) {
					try {
						System.out.println("Informe uma data início no formato (dd/MM/yyyy)");
						final String dataInicioEntrada = scanner.nextLine();

						System.out.println("Informe uma data final no formato (dd/MM/yyyy)");
						final String dataFinalEntrada = scanner.nextLine();

						dataInicio = LocalDate.parse(dataInicioEntrada, formatter);
						dataFinal = LocalDate.parse(dataFinalEntrada, formatter);
						boolean nenhumaVenda = false;

						for (VendaPlanta venda : vendas) {
							if (!venda.getDataRegistro().isBefore(dataInicio)
									&& !venda.getDataRegistro().isAfter(dataFinal)) {
								venda.mostrarRegistroVendas();
								System.out.println();

								nenhumaVenda = true;
							}
						}

						if(!nenhumaVenda) {
							System.out.println("Nenhuma venda entre as datas informadas.");
						}
					} catch (Exception error) {
						System.out.println("Data inválida, tente novamente.");
					}
				}
			}
		} else {
			System.out.println("Nenhuma venda feita ainda!");
		}
	}

	private static float calculoPrecoTotal(int quantidadePlanta, float precoPlanta) {
		float total = 0;

		total = precoPlanta * quantidadePlanta;

		return total;
	}

	private static float calculoTroco(float valorCliente, float valorTotalCompra) {
		float total = 0;

		total = valorCliente - valorTotalCompra;

		return total;
	}

}
