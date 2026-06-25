package classesAtividade;
import java.util.Scanner;
import java.util.stream.Collectors;

import classesAtividade.CalculadoraVendas.ResultadoVenda;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
public class Principal {
public static void main(String[] args) {
	CalculadoraVendas calc = new CalculadoraVendas();
	List<CalculadoraVendas.ResultadoVenda> lista = new ArrayList<>();
	
	Scanner sc = new Scanner(System.in);
	int resposta = 0;
	while(resposta!= 4) {
		System.out.println(" \nSeja bem vindo a Venda da Dona Gabriela!");
		
		System.out.println("\nPARA:");
		
		System.out.println("\nCALCULAR PREÇO TOTAL - 1");
		
		System.out.println("\nCALCULAR TROCO DE VENDA - 2");
		
	    System.out.println("\nHISTÓRICO DE VENDAS - 3 ");
	    
	    System.out.println("Sair - 4 ");
	    resposta = sc.nextInt();
	    sc.nextLine();
	switch (resposta) {
		case 1:
			
			System.out.println("Digite o número de unidades vendidas");
			
			int unidades = sc.nextInt();
			
			sc.nextLine();
			
			System.out.println("Digite o valor das unidades");
			
			double valor = sc.nextDouble();
			
			sc.nextLine();
			LocalDate data = LocalDate.now();
			
			
			CalculadoraVendas.ResultadoVenda resultado =
		    calc.calcularVendasResposta(unidades, valor, data);
			lista.add(resultado);
			
			System.out.println("Venda realizada no valor de:" + resultado.resposta );
			break;
		case 2:
			System.out.println("Digite o valor pago");
			
			double ValorPago = sc.nextDouble();
			
			sc.nextLine();
			
			System.out.println("Digite o valor do Produto");
			
			double ValorProduto = sc.nextDouble();
			
			sc.nextLine();
			
			new Troco(ValorPago, ValorProduto);
			break;
		case 3:
			int i;
			System.out.println("\nDigite 1 para filtrar por mês e 2 para filtrar por dia qualquer outra coisa entrega todos!\n");
			int escolha = sc.nextInt();
			sc.nextLine();
			if(escolha == 1) {
				System.out.println("\nDigite a Data dia-mês-ano");
				String entrada = sc.nextLine();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				LocalDate dataFiltro = LocalDate.parse(entrada, formatter);
				List<ResultadoVenda> pedidosFiltrados = lista.stream()
					    .filter(p -> p.getData().equals(dataFiltro))
					    .collect(Collectors.toList());
				for(int k=0; k<pedidosFiltrados.size(); k++) {
					System.out.println("Linha:"+ k);
					System.out.println(pedidosFiltrados.get(k));
					
				}
				break;
			}
			
			if(escolha== 2) {
				
				System.out.println("\nDigite o Mês! EX: (01 - 12)");
				int filtro = sc.nextInt();
				sc.nextLine();
				List<ResultadoVenda> pedidosFiltrados = lista.stream()
					    .filter(p -> p.getData().getMonthValue() == filtro)
					    .collect(Collectors.toList());
				for(int j=0; j<pedidosFiltrados.size(); j++) {
					System.out.println("Linha:"+ j);
					System.out.println(pedidosFiltrados.get(j));
				}
				break;
			}else {
				for(i=0; i<lista.size(); i++) {
					System.out.println("Linha:"+ i);
					System.out.println(lista.get(i));
			}
			break;
			}

		case 4:
			System.out.println("Até mais!");
			break;
		default:
			System.out.println("Opção inválida!"); 
    		break;

			}
		}
	sc.close();
	}
}
