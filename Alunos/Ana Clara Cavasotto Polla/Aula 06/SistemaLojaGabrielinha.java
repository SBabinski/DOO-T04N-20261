package fag;

import fag.objetos.Vendas;
import fag.objetos.Cliente;
import fag.objetos.Endereco;
import fag.objetos.Loja;
import fag.objetos.Vendedor;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaLojaGabrielinha {

	static List<Vendas> registroDeVendas = new ArrayList<>();
	static List<Vendedor> vendedores = new ArrayList<>();
	static List<Cliente> clientes = new ArrayList<>();
	static List<Loja> lojas = new ArrayList<>();

	static Scanner scan = new Scanner(System.in);
	static double precoTotal = 0;

	static Loja loja1;

	public static void main(String[] args) {
		popularListas();
		mostrarMenu();
	}

	// MENU PRINCIPAL

	private static void mostrarMenu() {
		int escolha = 0;
		do {
			System.out.println("\n--------- MENU PRINCIPAL ---------");
			System.out.println("1 - NOVA COMPRA - Calcular Preço Total");
			System.out.println("2 - NOVA COMPRA - Calcular Troco");
			System.out.println("3 - Registro de Vendas Totais");
			System.out.println("4 - Registro de Vendas - Consulta / Filtros");
			System.out.println("5 - Listar Lojas, Vendedores e Clientes");
			System.out.println("6 - Cadastros");
			System.out.println("0 - Sair");
			System.out.print("Escolha uma opção: ");

			escolha = scan.nextInt();
			scan.nextLine();

			validarEscolha(escolha);

		} while (escolha != 0);
	}

	private static void validarEscolha(int escolha) {
		switch (escolha) {

		case 6:
			menuCadastros();
			break;

		case 5:
			menuListagens();
			break;

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

	// MENU CADASTROS

	public static void menuCadastros() {
		int opcao;

		do {
			System.out.println("\n--------- MENU DE CADASTROS ---------");
			System.out.println("1 - Cadastrar Loja");
			System.out.println("2 - Cadastrar Vendedor");
			System.out.println("3 - Cadastrar Cliente");
			System.out.println("0 - Voltar");
			System.out.print("Escolha uma opção: ");

			opcao = scan.nextInt();
			scan.nextLine();

			switch (opcao) {
			case 1:
				cadastrarLoja();
				break;
			case 2:
				cadastrarVendedor();
				break;
			case 3:
				cadastrarCliente();
				break;
			case 0:
				break;
			default:
				System.out.println("Opção inválida!");
			}

		} while (opcao != 0);
	}

	// CADASTROS

	public static void cadastrarLoja() {
		System.out.println("\n------ CADASTRO DE LOJA ------");

		System.out.print("Nome Fantasia: ");
		String nomeFantasia = scan.nextLine();

		System.out.print("Razão Social: ");
		String razaoSocial = scan.nextLine();

		System.out.print("CNPJ: ");
		String cnpj = scan.nextLine();

		System.out.print("Cidade: ");
		String cidade = scan.nextLine();

		System.out.print("Bairro: ");
		String bairro = scan.nextLine();

		System.out.print("Rua: ");
		String rua = scan.nextLine();

		Endereco enderecoLoja = new Endereco(cidade, bairro, rua);

		Loja novaLoja = new Loja(nomeFantasia, razaoSocial, cnpj, enderecoLoja, null, null);

		lojas.add(novaLoja);

		System.out.println("Loja cadastrada com sucesso!");
	}

	public static void cadastrarVendedor() {
		if (lojas.isEmpty()) {
			System.out.println("Cadastre uma loja antes de cadastrar vendedores!");
			return;
		}

		System.out.println("\n------ CADASTRO DE VENDEDOR ------");

		System.out.print("Nome: ");
		String nome = scan.nextLine();

		System.out.print("Idade: ");
		int idade = scan.nextInt();

		System.out.print("Salário Base: ");
		double salarioBase = scan.nextDouble();
		scan.nextLine();

		System.out.print("Cidade: ");
		String cidade = scan.nextLine();

		System.out.print("Bairro: ");
		String bairro = scan.nextLine();

		System.out.print("Rua: ");
		String rua = scan.nextLine();

		Endereco enderecoVendedor = new Endereco(cidade, bairro, rua);

		Loja lojaEscolhida = escolherLoja();

		double[] salariosRecebidos = { salarioBase, salarioBase, salarioBase };

		Vendedor novoVendedor = new Vendedor(nome, idade, salarioBase, salariosRecebidos, lojaEscolhida,
				enderecoVendedor);

		vendedores.add(novoVendedor);

		atualizarVendedoresDaLoja(lojaEscolhida);

		System.out.println("Vendedor cadastrado com sucesso!");
	}

	public static void cadastrarCliente() {
		cadastrarNovoCliente();
	}

	public static Cliente cadastrarNovoCliente() {
		System.out.println("\n------ CADASTRO DE CLIENTE ------");

		System.out.print("Nome: ");
		String nome = scan.nextLine();

		System.out.print("Idade: ");
		int idade = scan.nextInt();
		scan.nextLine();

		System.out.print("Cidade: ");
		String cidade = scan.nextLine();

		System.out.print("Bairro: ");
		String bairro = scan.nextLine();

		System.out.print("Rua: ");
		String rua = scan.nextLine();

		Endereco endereco = new Endereco(cidade, bairro, rua);
		Cliente novoCliente = new Cliente(nome, idade, endereco);

		clientes.add(novoCliente);

		for (Loja loja : lojas) {
			atualizarClientesDaLoja(loja);
		}

		System.out.println("Cliente " + novoCliente.getNome() + " cadastrado com sucesso!");

		return novoCliente;
	}

	// LISTAGENS

	public static void menuListagens() {
		int opcao;

		do {
			System.out.println("\n--- LISTAGENS ---");
			System.out.println("1 - Listar Lojas");
			System.out.println("2 - Listar Vendedores");
			System.out.println("3 - Listar Clientes");
			System.out.println("0 - Voltar");
			System.out.print("Escolha uma opção: ");

			opcao = scan.nextInt();
			scan.nextLine();

			switch (opcao) {
			case 1:
				mostrarLojas();
				break;
			case 2:
				mostrarVendedores();
				break;
			case 3:
				mostrarClientes();
				break;
			case 0:
				break;
			default:
				System.out.println("Opção inválida!");
			}

		} while (opcao != 0);
	}

	public static void mostrarVendedores() {
		if (vendedores.isEmpty()) {
			System.out.println("Nenhum vendedor cadastrado!");
			return;
		}

		System.out.println("\n------ VENDEDORES ------");
		for (int i = 0; i < vendedores.size(); i++) {
			System.out.println("Vendedor " + (i + 1) + ":");
			vendedores.get(i).apresentarSe();
			vendedores.get(i).getEndereco().imprimirEndereco();
			System.out.printf("Média salarial: R$ %.2f%n", vendedores.get(i).calcularMedia());
			System.out.printf("Bônus: R$ %.2f%n%n", vendedores.get(i).calcularBonus());
		}
	}

	public static void mostrarClientes() {
		if (clientes.isEmpty()) {
			System.out.println("Nenhum cliente cadastrado!");
			return;
		}

		System.out.println("\n------ CLIENTES ------");
		for (int i = 0; i < clientes.size(); i++) {
			System.out.println("Cliente " + (i + 1) + ":");
			clientes.get(i).apresentarSe();
			clientes.get(i).getEndereco().imprimirEndereco();
			System.out.println();
		}
	}

	public static void mostrarLojas() {
		if (lojas.isEmpty()) {
			System.out.println("Nenhuma loja cadastrada!");
			return;
		}

		System.out.println("\n------ LOJAS ------");
		for (int i = 0; i < lojas.size(); i++) {
			System.out.println("Loja " + (i + 1) + ":");
			lojas.get(i).apresentarSe();
			System.out.println();
		}
	}

	// CONSULTA / FILTROS

	public static void menuConsultaPorData() {
		int opcao;

		do {
			System.out.println("\n--- CONSULTA / FILTROS DE VENDAS ---");
			System.out.println("1 - Buscar por dia e mês");
			System.out.println("2 - Buscar por mês");
			System.out.println("3 - Buscar por loja");
			System.out.println("4 - Buscar por vendedor");
			System.out.println("0 - Voltar");
			System.out.print("Escolha uma opção: ");

			opcao = scan.nextInt();
			scan.nextLine();

			switch (opcao) {
			case 1:
				buscarPorDiaMes();
				break;
			case 2:
				buscarPorMes();
				break;
			case 3:
				buscarPorLoja();
				break;
			case 4:
				buscarPorVendedor();
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
		scan.nextLine();

		while (mes < 1 || mes > 12) {
			System.out.println("Mês inválido! Digite entre 1 e 12:");
			mes = scan.nextInt();
		}

		List<Vendas> vendas = filtrarData(dia, mes);
		mostrarVendasFiltradas(vendas, String.format("Vendas para %02d/%02d", dia, mes));
	}

	public static void buscarPorMes() {
		System.out.print("Digite o mês: ");
		int mes = scan.nextInt();
		scan.nextLine();

		while (mes < 1 || mes > 12) {
			System.out.println("Mês inválido! Digite entre 1 e 12:");
			mes = scan.nextInt();
		}

		List<Vendas> vendas = filtrarData(0, mes);
		mostrarVendasFiltradas(vendas, String.format("Vendas do mês %02d", mes));
	}

	public static void buscarPorLoja() {
		Loja lojaEscolhida = escolherLoja();
		List<Vendas> filtradas = new ArrayList<>();

		for (Vendas venda : registroDeVendas) {
			if (venda.getLoja() == lojaEscolhida) {
				filtradas.add(venda);
			}
		}

		mostrarVendasFiltradas(filtradas, "Vendas da loja " + lojaEscolhida.getNomeFantasia());
	}

	public static void buscarPorVendedor() {
		Vendedor vendedorEscolhido = escolherVendedor();
		List<Vendas> filtradas = new ArrayList<>();

		for (Vendas venda : registroDeVendas) {
			if (venda.getVendedor() == vendedorEscolhido) {
				filtradas.add(venda);
			}
		}

		mostrarVendasFiltradas(filtradas, "Vendas do vendedor " + vendedorEscolhido.getNome());
	}

	public static void mostrarVendasFiltradas(List<Vendas> vendas, String titulo) {
		if (vendas.isEmpty()) {
			System.out.println("Nenhuma venda encontrada!");
			return;
		}

		System.out.println("\n------ " + titulo + " ------");
		for (int i = 0; i < vendas.size(); i++) {
			System.out.printf("Venda %d - ", i + 1);
			vendas.get(i).mostrarDetalhes();
		}
	}

	// VENDAS

	public static double registrarVenda() {
		Vendas venda = criarVenda();
		registroDeVendas.add(venda);

		System.out.println("\n------ DETALHES DA VENDA ------");
		venda.mostrarDetalhes();

		return venda.getValorFinal();
	}

	public static Vendas criarVenda() {
		System.out.println("------ NOVA VENDA ------");

		Loja lojaEscolhida = escolherLoja();
		Vendedor vendedorEscolhido = escolherVendedor();
		Cliente clienteEscolhido = escolherOuCadastrarCliente();

		System.out.print("Preço: R$ ");
		double preco = scan.nextDouble();
		while (preco <= 0) {
			System.out.println("Preço inválido! Digite um valor maior que 0.");
			System.out.print("Preço: R$ ");
			preco = scan.nextDouble();
		}

		System.out.print("Quantidade: ");
		int quantidade = scan.nextInt();
		while (quantidade <= 0) {
			System.out.println("Quantidade inválida! Digite um valor maior que 0.");
			System.out.print("Quantidade: ");
			quantidade = scan.nextInt();
		}
		scan.nextLine();

		return new Vendas(quantidade, preco, clienteEscolhido, vendedorEscolhido, lojaEscolhida);
	}

	// ESCOLHAS

	public static Loja escolherLoja() {
		if (lojas.isEmpty()) {
			System.out.println("Nenhuma loja cadastrada!");
			return null;
		}

		System.out.println("\nSelecione a loja:");
		for (int i = 0; i < lojas.size(); i++) {
			System.out.printf("%d - %s%n", i + 1, lojas.get(i).getNomeFantasia());
		}

		int opcao = scan.nextInt();
		while (opcao < 1 || opcao > lojas.size()) {
			System.out.println("Loja inválida! Escolha novamente:");
			opcao = scan.nextInt();
		}
		scan.nextLine();

		return lojas.get(opcao - 1);
	}

	public static Vendedor escolherVendedor() {
		if (vendedores.isEmpty()) {
			System.out.println("Nenhum vendedor cadastrado!");
			return null;
		}

		System.out.println("\nSelecione o vendedor:");
		for (int i = 0; i < vendedores.size(); i++) {
			System.out.printf("%d - %s%n", i + 1, vendedores.get(i).getNome());
		}

		int opcao = scan.nextInt();
		while (opcao < 1 || opcao > vendedores.size()) {
			System.out.println("Vendedor inválido! Escolha novamente:");
			opcao = scan.nextInt();
		}
		scan.nextLine();

		return vendedores.get(opcao - 1);
	}

	public static Cliente escolherOuCadastrarCliente() {
		System.out.println("\nCliente já cadastrado?");
		System.out.println("1 - Sim");
		System.out.println("2 - Não, cadastrar novo cliente");
		System.out.print("Escolha: ");

		int opcao = scan.nextInt();
		scan.nextLine();

		if (opcao == 1) {
			return escolherCliente();
		} else {
			return cadastrarNovoCliente();
		}
	}

	public static Cliente escolherCliente() {
		if (clientes.isEmpty()) {
			System.out.println("Nenhum cliente cadastrado. Será necessário cadastrar um novo.");
			return cadastrarNovoCliente();
		}

		System.out.println("\nSelecione o cliente:");
		for (int i = 0; i < clientes.size(); i++) {
			System.out.printf("%d - %s%n", i + 1, clientes.get(i).getNome());
		}

		int opcao = scan.nextInt();
		while (opcao < 1 || opcao > clientes.size()) {
			System.out.println("Cliente inválido! Escolha novamente:");
			opcao = scan.nextInt();
		}
		scan.nextLine();

		return clientes.get(opcao - 1);
	}

	// REGISTRO DE VENDAS

	public static void listarRegistroVendas() {
		if (registroDeVendas.isEmpty()) {
			System.out.println("Nenhuma venda registrada!");
			return;
		}

		System.out.println("\n------- REGISTRO DE VENDAS TOTAIS ------");
		for (int i = 0; i < registroDeVendas.size(); i++) {
			System.out.printf("Venda %d - ", i + 1);
			registroDeVendas.get(i).mostrarDetalhes();
		}
	}

	// TROCO

	public static void calcularTroco(double precoTotal) {
		if (precoTotal <= 0) {
			System.out.println("Calcule o preço total primeiro!");
			return;
		}

		System.out.println("------ CALCULADORA DE TROCO ------");
		System.out.printf("Valor Total: R$ %.2f%n", precoTotal);

		System.out.print("Valor Pago: ");
		double pagamento = scan.nextDouble();

		while (pagamento < precoTotal) {
			double falta = precoTotal - pagamento;
			System.out.printf("Valor insuficiente! Faltam: R$ %.2f%n", falta);

			System.out.print("Digite novamente o valor pago: ");
			pagamento = scan.nextDouble();
		}

		double troco = pagamento - precoTotal;
		System.out.printf("Valor a ser devolvido: R$ %.2f%n", troco);
	}

	// ATUALIZAR LISTAS

	public static void atualizarVendedoresDaLoja(Loja loja) {
		List<Vendedor> vendedoresDaLoja = new ArrayList<>();

		for (Vendedor vendedor : vendedores) {
			if (vendedor.getLoja() == loja) {
				vendedoresDaLoja.add(vendedor);
			}
		}

		loja.setVendedores(vendedoresDaLoja.toArray(new Vendedor[0]));
	}

	public static void atualizarClientesDaLoja(Loja loja) {
		loja.setClientes(clientes.toArray(new Cliente[0]));
	}

	// POPULAR LISTAS

	public static void popularListas() {

		// LOJA 1
		Endereco enderecoLoja1 = new Endereco("Cascavel", "Centro", "Rua A");

		loja1 = new Loja("My Plant", "My Plant LTDA", "12.345.678/0001-99", enderecoLoja1, null, null);

		lojas.add(loja1);

		// VENDEDORES
		Endereco enderecoVendedor1 = new Endereco("Cascavel", "Neva", "Rua B");
		Endereco enderecoVendedor2 = new Endereco("Cascavel", "Country", "Rua C");

		double[] salariosVendedor1 = { 1800.0, 1900.0, 2000.0 };
		double[] salariosVendedor2 = { 2200.0, 2100.0, 2300.0 };

		Vendedor vendedor1 = new Vendedor("Ana", 22, 2000.0, salariosVendedor1, loja1, enderecoVendedor1);
		Vendedor vendedor2 = new Vendedor("Carlos", 28, 2200.0, salariosVendedor2, loja1, enderecoVendedor2);

		vendedores.add(vendedor1);
		vendedores.add(vendedor2);

		// CLIENTES
		Endereco enderecoCliente1 = new Endereco("Cascavel", "Centro", "Rua D");
		Endereco enderecoCliente2 = new Endereco("Cascavel", "Pacaembu", "Rua E");

		Cliente cliente1 = new Cliente("Mariana", 30, enderecoCliente1);
		Cliente cliente2 = new Cliente("João", 25, enderecoCliente2);

		clientes.add(cliente1);
		clientes.add(cliente2);

		// Adiciona vendedores e clientes à loja 1
		loja1.setVendedores(vendedores.toArray(new Vendedor[0]));
		loja1.setClientes(clientes.toArray(new Cliente[0]));
	}
}
