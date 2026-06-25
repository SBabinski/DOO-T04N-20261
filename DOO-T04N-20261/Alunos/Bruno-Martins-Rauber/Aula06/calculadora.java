package metodos;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class calculadora {

	public static void main(String[] args) {
		Scanner Compra = new Scanner(System.in);
		int Op = 0;
		double resultado = 0;
		int Quantidade = 0;
		double Desconto = 5.0 / 100.0;
		double pago = 0;
		double total = 0;

		String[][] bancoDeDados = new String[100][2]; 
		int linhasPreenchidas = 0; 
		DateTimeFormatter formatoBr = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		while (Op != 7) {
			System.out.println("\n--- MENU DE VENDAS ---");
			System.out.println("1- Calcular Preço total");
			System.out.println("2- Calcular Troco e Salvar Venda");
			System.out.println("3- Consultar Vendas por Data"); 
			System.out.println("4- Ver Dados do Vendedor");
			System.out.println("5- Ver Dados da Loja");
			System.out.println("6- Ver Dados do Cliente");
			System.out.println("7- Sair");
			System.out.print("Escolha: ");

			Op = Compra.nextInt();

			if (Op == 1) {
				System.out.println("Quantidade de plantas:");
				Quantidade = Compra.nextInt();
				System.out.println("Preço unidade:");
				double preco = Compra.nextDouble();
				resultado = Quantidade * preco;
				System.out.println("Total da venda: R$ " + resultado);

			} else if (Op == 2) {
				total = (Quantidade >= 10) ? resultado - (resultado * Desconto) : resultado;
				System.out.println("Valor total: R$ " + total);

				System.out.print("Valor pago: ");
				pago = Compra.nextDouble();

				while (pago < total) {
					System.out.println("Valor insuficiente! O total é R$ " + total);
					System.out.print("Informe um valor válido: ");
					pago = Compra.nextDouble();
				}

				System.out.println("Troco a devolver: R$ " + (pago - total));

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
				System.out.println("Venda registrada com sucesso!");

			} else if (Op == 3) {
				System.out.print("Digite a data (dd/mm/aaaa): ");
				String busca = Compra.next();
				boolean achou = false;

				for (int i = 0; i < linhasPreenchidas; i++) {
					if (bancoDeDados[i][0].equals(busca)) {
						System.out.println("Total em " + bancoDeDados[i][0] + ": R$ " + bancoDeDados[i][1]);
						achou = true;
						break;
					}
				}
				if (!achou) System.out.println("Nenhuma venda encontrada.");

			} else if (Op == 4) {
			    vendedor v1 = new vendedor(); 

			    v1.nome = "Bruno Martins"; 
			    v1.idade = 21;
			    v1.loja = "My Plant Centro";
			    v1.salarioBase = 2500.0;

			    System.out.println("--- RELATÓRIO DO FUNCIONÁRIO ---");
			    v1.apresentarse();   
			    
			 
			    System.out.println("Salário Base: R$ " + v1.salarioBase);
			    
			    
			    v1.calcularMedia();  
			    v1.calcularBonus();
			    
			} else if (Op == 5) {
				loja l1 = new loja();
				l1.nomeFantasia = "My Plant";
				l1.cnpj = "12.345.678/0001-00";
				l1.cidade = "Cascavel";
				l1.bairro = "Centro";
				l1.rua = "Av. Brasil";
				l1.vendedores = new vendedor[3]; 
				l1.clientes = new cliente[5];
				l1.apresentarse();
				l1.contarVendedores();
				l1.contarClientes();

			} else if (Op == 6) {
				cliente c1 = new cliente();
				c1.nome = "Dona Gabrielinha";
				c1.idade = 55;
				c1.apresentarse();

			} else if (Op == 7) {
				System.out.println("Sistema encerrado. Boas vendas!");
			} else {
				System.out.println("Opção inválida!");
			}
		}
		Compra.close();
	}
}