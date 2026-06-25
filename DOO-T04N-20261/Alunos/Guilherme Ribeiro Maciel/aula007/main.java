package calculadora;

import java.util.Scanner;
import calculadora.Venda;
import calculadora.Loja;
import calculadora.Vendedor;
import calculadora.Gerente;
import calculadora.Cliente;
import calculadora.Item;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.*;

public class main {

	static Scanner scan = new Scanner(System.in);
	static List<Venda> Venda = new ArrayList<>();
	static List<Cliente> Cliente = new ArrayList<>();
	static List<Loja> Loja = new ArrayList<>();
	static List<Vendedor> Vendedor = new ArrayList<>();
	static List<Gerente> Gerente = new ArrayList<>();
	static List<Item> Item = new ArrayList<>();
	static List<Pedido> Pedido = new ArrayList<>();
	
	public static void main(String[] args) {
		popularLoja();
		popularVendedor();
		popularCliente();
		popularItem();
		Menu();
	}
	
	public static void popularLoja() {
		Loja matriz = new Loja("My Plant Matriz", "My Plant Inc", "99.999.999/0001-32", "Cascavel", "Centro", "Carlos Gomes");
		Loja filial1 = new Loja("My Plant toledo", "My Plant Me", "99.999.999/0002-89", "Toledo", "Centro", "Parigot de Souza");
		Loja.add(matriz);
		Loja.add(filial1);
	}
	
	public static void popularVendedor() {
		Vendedor v1 = new Vendedor("Laura", 21, "Cascavel", "PR", "Parque Verde", "Sadi Antonio zortea", 1181, "", 0);
		Vendedor v2 = new Vendedor("teste1", 23, "toledo", "PR", "centro", "parigot", 1092,  "", 1);
		Vendedor v3 = new Vendedor("rogerio", 35, "Cascavel", "PR", "Coqueiral", "santa chiquinha", 983, "Casa", 0);
		Vendedor v4 = new Vendedor("Leonardo", 29, "toledo", "PR", "goookke", "presidente kennedi", 501, "apartamento 132", 1);
		Vendedor v5 = new Vendedor("clara", 18, "Cascavel", "PR", "murombi", "av brasil", 2398, "", 0);
		Vendedor v6 = new Vendedor("ana", 30, "toledo", "PR", "murombi", "av brasil", 2983, "", 1);
		Vendedor.add(v1);
		Loja.get(0).setVendedores(v1.getNome());
		Vendedor.add(v2);
		Loja.get(1).setVendedores(v2.getNome());
		Vendedor.add(v3);
		Loja.get(0).setVendedores(v3.getNome());
		Vendedor.add(v4);
		Loja.get(1).setVendedores(v4.getNome());
		Vendedor.add(v5);
		Loja.get(1).setVendedores(v5.getNome());
		Vendedor.add(v6);
		Loja.get(0).setVendedores(v6.getNome());
	}
	
	public static void popularCliente() {
		Cliente clie1 = new Cliente("Rogerio", 30, "cascavel", "recife", "parque verde", 0);
		Cliente clie2 = new Cliente("Leandro", 21, "toledo", "rua de toledo", "flores", 1);
		Cliente clie3 = new Cliente("Guizo", 24, "cascavel", "rio grande do sul", "centro", 0);
		Cliente clie4 = new Cliente("teste23", 40, "toledo", "rogerio", "bairro", 1);
		Cliente clie5 = new Cliente("pedro", 54, "cascavel", "av toledo", "universitario", 0);
		Cliente clie6 = new Cliente("Nobody", 35, "toledo", "av cascavel", "zona sul", 1);
		Cliente.add(clie1);
		Loja.get(0).setClientes(clie1.getNome());
		Cliente.add(clie2);
		Loja.get(1).setClientes(clie2.getNome());
		Cliente.add(clie3);
		Loja.get(0).setClientes(clie3.getNome());
		Cliente.add(clie4);
		Loja.get(1).setClientes(clie4.getNome());
		Cliente.add(clie5);
		Loja.get(0).setClientes(clie4.getNome());
		Cliente.add(clie6);
		Loja.get(1).setClientes(clie5.getNome());
	}
	
	public static void popularItem() {
		Item item1 = new Item(1, "Bonsai", "Arvore", 12.5f);
		Item item2 = new Item(2, "Samambaia", "Vegetais", 5.5f);
		Item item3 = new Item(3, "Feijao", "Semente", 10.2f);
		Item item4 = new Item(4, "Pau-Brasil", "Arvore", 25.9f);
		Item item5 = new Item(5, "Crisantemo", "Flor", 15.9f);
		Item.add(item1);
		Item.add(item2);
		Item.add(item3);
		Item.add(item4);
		Item.add(item5);
	}
	
	public static void Menu() {
		int opt = 0;
		do {
			System.out.println("Entre com a opção desejada");
			System.out.println("0-Sair");
			System.out.println("1-Calcular Preço Total");
			System.out.println("2-Calcular Troco");
			System.out.println("3-Listagem de Vendas");
			System.out.println("4-Registrar Pedido");
			System.out.println("5-Relatorio por Dia/Mes");
			System.out.println("6-Menu Gerencial");
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
				RegistrarPedido();
				break;
			case 5:
				RelatarioDiaMes();
				break;
			case 6:
				MenuRH();
				break;
			default:
				System.out.println("Opção Invalida, porfavor tente novamente");
			}
		} while(opt!=0);
	}
	
	private static void MenuRH() {
		int opt = 0;
		do {
			System.out.println("Entre com a opção desejada");
			System.out.println("0-Voltar");
			System.out.println("1-Cadastrar Loja");
			System.out.println("2-Cadastrar Vendedor");
			System.out.println("3-Cadastrar Gerente");
			System.out.println("4-Cadastrar Item");
			System.out.println("5-Cadastrar Cliente");
			System.out.println("6-Listar Lojas");
			System.out.println("7-Listar Vendas por Loja");
			System.out.println("8-Listar Vendedores por Loja");
			System.out.println("9-Listar Gerente por Loja");
			System.out.println("10-Listar Clientes por Loja");
			System.out.println("11-Relatorio de Itens");
			opt = scan.nextInt();
			scan.nextLine();
			switch(opt) {
			case 0:
				break;
			case 1:
				cadastrarLoja();
				break;
			case 2:
				cadastrarVendedor();
				break;
			case 3:
				cadastrarGerente();
				break;
			case 4:
				cadastrarItem();
				break;
			case 5:
				cadastrarCliente();
				break;
			case 6:
				listarLojas();
				break;
			case 7:
				pedidoLoja();
				break;
			case 8:
				vendedorLoja();
				break;
			case 9:
				gerenteLoja();
				break;
			case 10:
				clienteLoja();
				break;
			case 11:
				ListarItens();
				break;
			default:
				System.out.println("Opção Invalida, porfavor tente novamente");
			}
		} while(opt!=0);
	}

	private static void pedidoLoja() {
		System.out.println("Selecione a Loja a ser filtrada (Digite apenas o Numero Identificador da Loja):");
		listarLojas();
		int loja;
		do {
			loja = scan.nextInt();
			scan.nextLine();
			if(loja-1 >= Loja.size()) {
				System.out.println("Opção Invalida, porfavor tente novamente");
			}
		} while(loja-1 >= Loja.size());
		for(int i = 0; i < Pedido.size(); i++) {
			if(Pedido.get(i).getLoja() == loja-1) {
				System.out.println(i+1 + " - " + Pedido.get(i).descricaoVenda());
			}
		}
	}

	private static void cadastrarItem() {
		Item item = new Item();
		item.setId(Item.size());
		System.out.println("Entre com o Nome do Item");
		item.setNome(scan.nextLine());
		System.out.println("Entre com o Tipo do Item");
		item.setTipo(scan.nextLine());
		System.out.println("Entre com o valor do Item");
		item.setValor(scan.nextFloat());
		Item.add(item);
		System.out.println("Item cadastrado com Sucesso!");
	} 

	private static void gerenteLoja() {
		System.out.println("Selecione a Loja a ser filtrada (Digite apenas o Numero Identificador da Loja):");
		listarLojas();
		int loja;
		do {
			loja = scan.nextInt();
			scan.nextLine();
			if(loja-1 >= Loja.size()) {
				System.out.println("Opção Invalida, porfavor tente novamente");
			}
		} while(loja-1 >= Loja.size());
		for(int i = 0; i < Gerente.size(); i++) {
			if(Gerente.get(i).getLoja() == loja-1) {
				System.out.println(i+1 + " - " + Gerente.get(i).apresentarse());
			}
		}
		System.out.println(Loja.get(loja-1).contarVendedores());
	}

	private static void clienteLoja() {
		System.out.println("Selecione a Loja a ser filtrada (Digite apenas o Numero Identificador da Loja):");
		listarLojas();
		int loja;
		do {
			loja = scan.nextInt();
			scan.nextLine();
			if(loja-1 >= Loja.size()) {
				System.out.println("Opção Invalida, porfavor tente novamente");
			}
		} while(loja-1 >= Loja.size());
		for(int i = 0; i < Cliente.size(); i++) {
			if(Cliente.get(i).getLoja() == loja-1) {
				System.out.println(i+1 + " - " + Cliente.get(i).apresentarse());
			}
		}
		System.out.println(Loja.get(loja-1).contarClientes());
	}

	private static void vendedorLoja() {
		System.out.println("Selecione a Loja a ser filtrada (Digite apenas o Numero Identificador da Loja):");
		listarLojas();
		int loja;
		do {
			loja = scan.nextInt();
			scan.nextLine();
			if(loja-1 >= Loja.size()) {
				System.out.println("Opção Invalida, porfavor tente novamente");
			}
		} while(loja-1 >= Loja.size());
		for(int i = 0; i < Vendedor.size(); i++) {
			if(Vendedor.get(i).getLoja() == loja-1) {
				System.out.println(i+1 + " - " + Vendedor.get(i).apresentarse());
			}
		}
		System.out.println(Loja.get(loja-1).contarVendedores());
	}

	private static void listarLojas() {
		for(int i = 0; i < Loja.size(); i++) {
			System.out.println(i+1 + " - " + Loja.get(i).apresentarse());
		}
	}

	private static void cadastrarCliente() {
		Cliente cliente = new Cliente();
		System.out.println("Insira o Nome do Cliente:");
		cliente.setNome(scan.nextLine());
		System.out.println("Insira a Idade do cliente:");
		cliente.setIdade(scan.nextInt());
		scan.nextLine();
		System.out.println("Insira a Cidade do Cliente:");
		cliente.setCidade(scan.nextLine());
		System.out.println("Insira o Bairro do Cliente:");
		cliente.setBairro(scan.nextLine());
		System.out.println("Insira a Rua do Cliente:");
		cliente.setRua(scan.nextLine());
		System.out.println("Selecione a Loja ao qual o Cliente é associado (Digite apenas o Numero Identificador):");
		listarLojas();
		int loja;
		do {
			loja = scan.nextInt();
			scan.nextLine();
			if(loja-1 <= Loja.size()) {
				System.out.println("Opção Invalida, porfavor tente novamente");
			}
		} while(loja-1 >= Loja.size());
		cliente.setLoja(loja-1);
		Cliente.add(cliente);
		Loja.get(loja-1).setClientes(cliente.getNome());
		System.out.println("Cliente Cadastrado com Sucesso!");
	}

	private static void cadastrarVendedor() {
		Vendedor vendedor = new Vendedor();
		System.out.println("Insira o Nome do Vendedor:");
		vendedor.setNome(scan.nextLine());
		System.out.println("Insira a Idade do Vendedor:");
		vendedor.setIdade(scan.nextInt());
		scan.nextLine();
		System.out.println("Insira a Cidade do Vendedor:");
		vendedor.setCidade(scan.nextLine());
		System.out.println("Insira o Bairro do Vendedor:");
		vendedor.setBairro(scan.nextLine());
		System.out.println("Insira a Rua do Vendedor:");
		vendedor.setRua(scan.nextLine());
		System.out.println("Selecione a Loja ao qual o Vendedor é atribuido (Digite apenas o Numero Identificador):");
		listarLojas();
		int loja;
		do {
			loja = scan.nextInt();
			scan.nextLine();
			if(loja-1 <= Loja.size()) {
				System.out.println("Opção Invalida, porfavor tente novamente");
			}
		} while(loja-1 >= Loja.size());
		vendedor.setLoja(loja-1);
		Vendedor.add(vendedor);
		Loja.get(loja-1).setVendedores(vendedor.getNome());
		System.out.println("Vendedor Cadastrado com Sucesso!");
	}
	
	private static void cadastrarGerente() {
		Gerente gerente = new Gerente();
		System.out.println("Insira o Nome do Gerente:");
		gerente.setNome(scan.nextLine());
		System.out.println("Insira a Idade do Gerente:");
		gerente.setIdade(scan.nextInt());
		scan.nextLine();
		System.out.println("Insira a Cidade do Gerente:");
		gerente.setCidade(scan.nextLine());
		System.out.println("Insira o Bairro do Gerente:");
		gerente.setBairro(scan.nextLine());
		System.out.println("Insira a Rua do Gerente:");
		gerente.setRua(scan.nextLine());
		System.out.println("Selecione a Loja ao qual o Gerente é atribuido (Digite apenas o Numero Identificador):");
		listarLojas();
		int loja;
		do {
			loja = scan.nextInt();
			scan.nextLine();
			if(loja-1 <= Loja.size()) {
				System.out.println("Opção Invalida, porfavor tente novamente");
			}
		} while(loja-1 >= Loja.size());
		gerente.setLoja(loja-1);
		Vendedor.add(gerente);
		Loja.get(loja-1).setVendedores(gerente.getNome());
		System.out.println("Gerente Cadastrado com Sucesso!");
	}

	private static void cadastrarLoja() {
		Loja loja = new Loja();
		System.out.println("Insira a Razão Social da Loja:");
		loja.setRazaoSocial(scan.nextLine());
		System.out.println("Insira o Nome Fantasia da Loja:");
		loja.setFantasia(scan.nextLine());
		System.out.println("Insira o CNPJ da Loja:");
		loja.setCnpj(scan.nextLine());
		System.out.println("Insira a Cidade da Loja:");
		loja.setCidade(scan.nextLine());
		System.out.println("Insira o Bairro da Loja:");
		loja.setBairro(scan.nextLine());
		System.out.println("Insira a Rua da Loja:");
		loja.setRua(scan.nextLine());
		Loja.add(loja);
		System.out.println("Loja Cadastrada com Sucesso!");
	}

	private static void RelatarioDiaMes() {
		System.out.println("Entre com a data desejada para o relatorio");
		String dataEntrada = scan.nextLine();
		System.out.println("Data da Venda||Vl Uni.||QTD||Vl Total");
		int cont=0;
		for (int i=0; i < Venda.size(); i++) {
			if(DateEn(dataEntrada).isEqual(Venda.get(i).getData())) {
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
	
	public static void RegistrarPedido() {
		Pedido pedido = new Pedido();
		ProcessaPedido processaPedido = new ProcessaPedido();
		pedido.setDataCriacao(LocalDate.now());
		pedido.setId(Pedido.size()+1);
		System.out.println("Selecione o Atendente:");
		int vendedor = scan.nextInt();
		scan.nextLine();
		pedido.setVendedor(vendedor-1);
		System.out.println(Vendedor.get(vendedor-1).apresentarse());
		pedido.setLoja(Vendedor.get(vendedor-1).getLoja());
		System.out.println("Selecione o Cliente:");
		int clie = scan.nextInt();
		scan.nextLine();
		pedido.setCliente(clie-1);
		System.out.println(Cliente.get(clie-1).apresentarse());
		System.out.println("Entre os produtos do pedido(Entre com 0 para concluir):");
		int it = 0;
		do {
			it = scan.nextInt();
			scan.nextLine();
			if(it>0) 
			{
				pedido.setItens(Item.get(it-1));
				System.out.println(Item.get(it-1).gerarDescricao());
			}
		}while (it!=0);
		do {
			System.out.println("Entre com a Data de Vencimento da Reserva:");
			String data = scan.nextLine();
			pedido.setDataVencimentoReserva(DateEn(data));
			System.out.println("Entre com a Data do Pagamento:");
			data = scan.nextLine();
			pedido.setDataPagamento(DateEn(data));
			processaPedido.processar(pedido);
		}while (processaPedido.cp);
		Pedido.add(pedido);
		System.out.println(pedido.descricaoVenda());
	}
	
	private static void ListarItens() {
		for (int i = 0; i < Item.size(); i++) {
			System.out.println(Item.get(i).gerarDescricao());
		}
	}

	public static void CalcularTroco() {
		 System.out.println("Entre com o Valor Recebido:");
		 float vlr = scan.nextFloat();
		 System.out.println("Entre com o Preço da Compra de Plantas");
		 float vlt = scan.nextFloat();
		 float vltr = vlr - vlt;
		 System.out.println("O Valor do Troco é: " + vltr);
	}
	
	public static LocalDate DateEn(String dataS) {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate data = LocalDate.parse(dataS, formato);
		return data;
	}
	
}