package calculadora;

import java.util.Scanner;
import calculadora.Venda;
import calculadora.Loja;
import calculadora.Vendedor;
import calculadora.Cliente;
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
	
	public static void main(String[] args) {
		popularLoja();
		popularVendedor();
		popularCliente();
		Menu();
	}
	
	public static void popularLoja() {
		Loja matriz = new Loja("My Plant Matriz", "My Plant Inc", "99.999.999/0001-32", "Cascavel", "Centro", "Carlos Gomes");
		Loja filial1 = new Loja("My Plant toledo", "My Plant Me", "99.999.999/0002-89", "Toledo", "Centro", "Parigot de Souza");
		Loja.add(matriz);
		Loja.add(filial1);
	}
	
	public static void popularVendedor() {
		Vendedor v1 = new Vendedor("Laura", 21, "Cascavel", "Parque Verde", "Sadi Antonio zortea", 0);
		Vendedor v2 = new Vendedor("teste1", 23, "toledo", "centro", "parigot", 1);
		Vendedor v3 = new Vendedor("rogerio", 35, "Cascavel", "Coqueiral", "santa chiquinha", 0);
		Vendedor v4 = new Vendedor("Leonardo", 29, "toledo", "goookke", "presidente kennedi", 1);
		Vendedor v5 = new Vendedor("clara", 18, "Cascavel", "murombi", "av brasil", 0);
		Vendedor v6 = new Vendedor("ana", 30, "toledo", "murombi", "av brasil", 1);
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
	
	public static void Menu() {
		int opt = 0;
		do {
			System.out.println("Entre com a opção desejada");
			System.out.println("0-Sair");
			System.out.println("1-Calcular Preço Total");
			System.out.println("2-Calcular Troco");
			System.out.println("3-Listagem de Vendas");
			System.out.println("4-Relatorio por Dia/Mes");
			System.out.println("5-Menu RH");
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
				RelatarioDiaMes();
				break;
			case 5:
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
			System.out.println("3-Cadastrar Cliente");
			System.out.println("4-Listar Lojas");
			System.out.println("5-Listar Vendedores por Loja");
			System.out.println("6-Listar Clientes por Loja");
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
				cadastrarCliente();
				break;
			case 4:
				listarLojas();
				break;
			case 5:
				vendedorLoja();
				break;
			case 6:
				clienteLoja();
				break;
			default:
				System.out.println("Opção Invalida, porfavor tente novamente");
			}
		} while(opt!=0);
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
		cliente.setLoja(loja);
		Cliente.add(cliente);
		Loja.get(loja).setClientes(cliente.getNome());
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
		vendedor.setLoja(loja);
		Vendedor.add(vendedor);
		Loja.get(loja).setVendedores(vendedor.getNome());
		System.out.println("Vendedor Cadastrado com Sucesso!");
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
		DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println("Entre com a data desejada para o relatorio");
		String dataEntrada = scan.nextLine();
		LocalDate data = LocalDate.parse(dataEntrada, formatoEntrada);
		String dataJava = data.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate dataData = LocalDate.parse(dataJava);
		System.out.println("Data da Venda||Vl Uni.||QTD||Vl Total");
		int cont=0;
		for (int i=0; i < Venda.size(); i++) {
			if(dataData.isEqual(Venda.get(i).getData())) {
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
	
	public static void CalcularTroco() {
		 System.out.println("Entre com o Valor Recebido:");
		 float vlr = scan.nextFloat();
		 System.out.println("Entre com o Preço da Compra de Plantas");
		 float vlt = scan.nextFloat();
		 float vltr = vlr - vlt;
		 System.out.println("O Valor do Troco é: " + vltr);
	}
	
}
