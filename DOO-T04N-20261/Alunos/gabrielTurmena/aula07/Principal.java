package classesAtividade;
import java.util.Scanner;
import java.util.Date;
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
	
	Loja loja = new Loja("Venda da Dona Gabriela", "Gabriela Comercio LTDA", "12.345.678/0001-99", "Cascavel", "Centro", "Rua das Flores, 100");
	loja.vendedores = new Vendedor[10];
	loja.clientes = new Cliente[10];
	Item produto1 = new Item(1, "florzinha ameixa", 25.0, "planta pequena");
	Item produto2 = new Item(2, "pé de mamão", 150.0, "arvore");
	Item produto3 = new Item(2, "pé de uva", 450.0, "arvore");
	ProcessaPedido processador = new ProcessaPedido();
	int totalVendedores = 0;
	int totalClientes = 0;
    Pedido pedido = new Pedido(
            1,
            new Date(),
            null,
            null,
            "Cliente João",
            "Vendedor Maria",
            "Loja Central"
        );
    pedido.adicionarItem(produto1);
    pedido.adicionarItem(produto2);
    pedido.adicionarItem(produto3);
    processador.processar(pedido);
	Scanner sc = new Scanner(System.in);
	int resposta = 0;
	while(resposta != 10) {
		System.out.println(" \nSeja bem vindo a Venda da Dona Gabriela!");
		
		System.out.println("\nPARA:");
		
		System.out.println("\nCALCULAR PREÇO TOTAL - 1");
		
		System.out.println("\nCALCULAR TROCO DE VENDA - 2");
		
	    System.out.println("\nHISTÓRICO DE VENDAS - 3 ");
	    
	    System.out.println("\nCADASTRAR VENDEDOR - 4 ");
	    
	    System.out.println("\nCADASTRAR CLIENTE - 5 ");
	    
	    System.out.println("\nINFORMAÇÕES DA LOJA - 6 ");
	    
	    System.out.println("\nGERAR DESCRIÇÃO DE VENDA - 7 ");
	    
	    System.out.println("\nLISTAR PRODUTOS DO SISTEMA - 8 ");
	    
	    System.out.println("\nCRIAR UM PEDIDO - 9 ");
	    
	    System.out.println("\nSair - 10 ");
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
			System.out.println("\nDigite 1 para filtrar por Mês e 2 para filtrar por dia, qualquer outra coisa entrega todos!\n");
			int escolha = sc.nextInt();
			sc.nextLine();
			if(escolha == 1) {
				System.out.println("\nDigite a Data dia/mês/ano");
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
			System.out.println("Digite o nome do Vendedor");
			String nomeV = sc.nextLine();
			
			System.out.println("Digite a idade do Vendedor");
			int idadeV = sc.nextInt();
			sc.nextLine();
			
			System.out.println("Digite a cidade do Vendedor");
			String cidadeV = sc.nextLine();
			
			System.out.println("Digite o bairro do Vendedor");
			String bairroV = sc.nextLine();
			
			System.out.println("Digite a rua do Vendedor");
			String ruaV = sc.nextLine();
			
			System.out.println("Digite o salário base do Vendedor");
			double salarioV = sc.nextDouble();
			sc.nextLine();
			
			Vendedor vendedor = new Vendedor(nomeV, idadeV, loja.nomeFantasia, cidadeV, bairroV, ruaV, salarioV);
			loja.vendedores[totalVendedores] = vendedor;
			totalVendedores++;
			
			System.out.println("Vendedor cadastrado!");
			vendedor.apresentarse();
			System.out.println("Bonus: " + vendedor.calcularBonus());
			System.out.println("Media de salarios: " + vendedor.calcularMedia());
			break;
		case 5:
			System.out.println("Digite o nome do Cliente");
			String nomeC = sc.nextLine();
			
			System.out.println("Digite a idade do Cliente");
			int idadeC = sc.nextInt();
			sc.nextLine();
			
			System.out.println("Digite a cidade do Cliente");
			String cidadeC = sc.nextLine();
			
			System.out.println("Digite o bairro do Cliente");
			String bairroC = sc.nextLine();
			
			System.out.println("Digite a rua do Cliente");
			String ruaC = sc.nextLine();
			
			Cliente cliente = new Cliente(nomeC, idadeC, cidadeC, bairroC, ruaC);
			loja.clientes[totalClientes] = cliente;
			totalClientes++;
			
			System.out.println("Cliente cadastrado!");
			cliente.apresentarse();
			break;
		case 6:
			loja.apresentarse();
			System.out.println("Total de Vendedores: " + totalVendedores);
			System.out.println("Total de Clientes: " + totalClientes);
			
			for(int v=0; v<totalVendedores; v++) {
				loja.vendedores[v].apresentarse();
			}
			for(int c=0; c<totalClientes; c++) {
				loja.clientes[c].apresentarse();
			}
			break;
			
		case 7:
	        System.out.println("DESCRIÇÃO DE VENDA:\n");
	        pedido.gerarDescricaoVenda();
	        break;
		case 8:
			System.out.println("Itens da Loja: \n");
			Item[] produtos = {produto1, produto2, produto3};
			for (Item p : produtos) {
				System.out.print("\n");
				p.gerarDescricao();
			}
			break;

		case 9:
		    System.out.println("CADASTRAR PEDIDO!");

		    System.out.print("Nome do cliente: ");
		    String clientePedido = sc.nextLine();

		    System.out.print("Nome do vendedor: ");
		    String vendedorPedido = sc.nextLine();

		    System.out.print("Nome da loja: ");
		    String lojaPedido = sc.nextLine();

		    pedido = new Pedido(
		        1,
		        new Date(),
		        null,
		        null,
		        clientePedido,
		        vendedorPedido,
		        lojaPedido
		    );

		    int opcItem;

		    do {
		        System.out.println("\nEscolha um item para adicionar:");
		        System.out.println("1 - Florzinha ameixa");
		        System.out.println("2 - Pé de mamão");
		        System.out.println("3 - Pé de uva");
		        System.out.println("0 - Finalizar pedido");

		        opcItem = sc.nextInt();
		        sc.nextLine();

		        switch (opcItem) {
		            case 1:
		                pedido.adicionarItem(produto1);
		                break;
		            case 2:
		                pedido.adicionarItem(produto2);
		                break;
		            case 3:
		                pedido.adicionarItem(produto3);
		                break;
		            case 0:
		                break;
		            default:
		                System.out.println("Opção inválida");
		        }

		    } while (opcItem != 0);

		    processador.processar(pedido);

		    System.out.println("Pedido criado com sucesso!");
		    break;
				
		case 10:
			System.out.println("Até mais!");
			break;
			
		default:
			System.out.println("OPÇÃO INVÁLIDA"); 
    		break;
			}
		}
	sc.close();
	}
}