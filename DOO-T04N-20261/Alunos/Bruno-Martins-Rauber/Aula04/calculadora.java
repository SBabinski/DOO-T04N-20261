package metodos;

import java.util.Scanner;
import java.time.LocalDate; // Para pegar a data do seu PC
import java.time.format.DateTimeFormatter; // Para formatar a data (dd/MM/yyyy)

public class calculadora {

	public static void main(String[] args) {
		Scanner Compra = new Scanner(System.in);
		int Op = 0;
		double resultado = 0;
		int Quantidade = 0;
		double Desconto = 5.0 / 100.0;
		double pago = 0;
		double total = 0;

		
		// IMPORTANTE!!!!!!!!!!!!!!!!!!!!! Matriz de String: [Linha] representa o dia, [0] é a Data, [1] é o Valor
		String[][] bancoDeDados = new String[100][2]; 
		int linhasPreenchidas = 0; // Conta quantas linhas ja usamos 
		DateTimeFormatter formatoBr = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		// ---------------------------------

		while (Op != 3) {
			System.out.println("\n--- MENU DE VENDAS ---");
			System.out.println("1- Calcular Preço total");
			System.out.println("2- Calcular Troco e Salvar Venda");
			System.out.println("4- Consultar Vendas por Data"); 
			System.out.println("3- Sair");
			System.out.print("Escolha: ");

			Op = Compra.nextInt();

			if (Op == 1) {
				System.out.println("Quantidade de plantas");
				Quantidade = Compra.nextInt();

				System.out.println("Preço unidade: ");
				double preco = Compra.nextDouble();

				resultado = Quantidade * preco;
				System.out.println("Total da venda: R$ " + resultado);

			} else if (Op == 2) {
				
				if (Quantidade >= 10) {
					total = resultado - (resultado * Desconto);
					System.out.println("Valor com desconto: R$ " + total);
				} else {
					total = resultado;
					System.out.println("Valor total: R$ " + total);
				}

				System.out.print("Valor pago: ");
				pago = Compra.nextDouble();

				while (pago < total) {
					System.out.println("Valor insuficiente! O total é R$ " + total);
					System.out.print("Por favor, informe um valor valido: ");
					pago = Compra.nextDouble();
				}

				double troco = pago - total;
				System.out.println("Troco a devolver: R$ " + troco);

				// usa a data ddo pc 
				String hoje = LocalDate.now().format(formatoBr);
				boolean diaEncontrado = false;

			
				for (int i = 0; i < linhasPreenchidas; i++) {
					if (bancoDeDados[i][0].equals(hoje)) {
						
						
						double valorAntigo = Double.parseDouble(bancoDeDados[i][1]);
						bancoDeDados[i][1] = String.valueOf(valorAntigo + total);
						diaEncontrado = true;
						break;
					}
				}

				
				if (!diaEncontrado && linhasPreenchidas < 100) {
					bancoDeDados[linhasPreenchidas][0] = hoje;
					bancoDeDados[linhasPreenchidas][1] = String.valueOf(total);
					linhasPreenchidas++;
				}
				System.out.println("Venda registrada hoje (" + hoje + ")");
				// ----------------------------------------------

			} else if (Op == 4) {
				// --- CONSULTA POR DATA ---
				System.out.print("Digite a data para busca (dd/mm/aaaa): ");
				String busca = Compra.next();
				boolean achou = false;

				for (int i = 0; i < linhasPreenchidas; i++) {
					if (bancoDeDados[i][0].equals(busca)) {
						System.out.println("Total de vendas em " + bancoDeDados[i][0] + ": R$ " + bancoDeDados[i][1]);
						achou = true;
						break;
					}
				}

				if (!achou) {
					System.out.println("Nenhuma venda encontrada para esta data.");
				}
				// --------------------------

			} else if (Op == 3) {
				System.out.println("Sistema encerrado. Boas vendas!");
			} else {
				System.out.println("Opção inválida!");
			}
		}
		Compra.close();
	}
}