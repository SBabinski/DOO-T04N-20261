package Aula07;

import java.util.*;

public class Main {
	public static void main(String []args) {
		Scanner scan = new Scanner(System.in);
		
		Endereco end1 = new Endereco("PR", "Cascavel", "Parque São Paulo", "Rua Carlos Gormes", "7120", "Casa");
		Endereco end2 = new Endereco("SP", "São Paulo", "Tatuape", "Rua das Araras", "510", "Ap 23");
		Endereco end3 = new Endereco("PR", "Cascavel", "Centro", "Av. Brasil", "202", "Ap 14");
		
		Endereco endLoja = new Endereco("PR", "Cascavel", "Centro", "Av. Brasil", "100", "Loja");
		Loja loja = new Loja("My Plant", "My Plant LTDA", "75.659.573/0001-20", endLoja);
		
		Cliente cliente = new Cliente("Josebaldo", 65, end1);
		Vendedor vendedor = new Vendedor("José", 30, end2, loja, 2000);
		Gerente gerente = new Gerente("Aderbal", 33, end3, loja, 5000);

		loja.clientes[0] = cliente;
		loja.vendedores[0] = vendedor;
		
		int opcao = 0;
		
		while (opcao != 9) {
			System.out.println("\n--- MENU ---");
			System.out.println("1 - Loja");
			System.out.println("2 - Cliente");
			System.out.println("3 - Vendedor");
			System.out.println("4 - Gerente");
			System.out.println("5 - Dados loja");
			System.out.println("6 - Criar pedido");
			System.out.println("7 - Registrar vendas do dia");
			System.out.println("8 - Buscar vendas por mes e dia");
			System.out.println("9 - Sair");
		
			opcao = scan.nextInt();
			
			switch (opcao) {
			case 1:
				loja.apresentarSe();
				break;
			case 2:
				cliente.apresentarSe();
				break;
			case 3:
				vendedor.apresentarSe();
				System.out.println("Media: " + vendedor.calcularMedia());
				System.out.println("Bonus: " + vendedor.calcularBonus());
				break;
			case 4:
				gerente.apresentarSe();
				System.out.println("Media " + gerente.calcularMedia());
				System.out.println("Bonus: " + gerente.calcularBonus());
				break;
			case 5:
				loja.contaClientes();
				loja.contarVendedor();
				break;
			case 6:
				ProcessaPedido proc = new ProcessaPedido();
				Pedido pedido = proc.processar(1, cliente, vendedor, loja);
				
				pedido.itens.add(new Item(1, "Cachepô", "decor", 34));
				pedido.itens.add(new Item(2, "Substarto", "fertil", 20));
				pedido.itens.add(new Item(3, "Macieira P", "frutifera", 40));
				
				pedido.gerarDescricaoVenda();
				proc.testarPag(pedido);
				break;
			case 7:
				System.out.print("Dia: ");
				int dia = scan.nextInt();
				System.out.print("Mes: ");
				int mes = scan.nextInt();
				System.out.print("Ano: ");
				int ano = scan.nextInt();
				System.out.print("Quantidade de vendas: ");
				int qtd = scan.nextInt();
				loja.registrarVenda(dia, mes, ano, qtd);
				break;
			case 8:
				System.out.print("Mes: ");
				int mesBusca = scan.nextInt();
				System.out.print("Dia: ");
				int diaBusca = scan.nextInt();
				loja.buscarVendas(mesBusca, diaBusca);
				break;
			case 9:
				System.out.println("Saindo");
				break;
			}
		}
		scan.close();
	}
}