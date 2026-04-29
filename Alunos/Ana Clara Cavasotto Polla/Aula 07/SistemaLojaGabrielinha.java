package fag;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import fag.objetos.Cliente;
import fag.objetos.Endereco;
import fag.objetos.Funcionario;
import fag.objetos.Gerente;
import fag.objetos.Item;
import fag.objetos.Loja;
import fag.objetos.Pedido;
import fag.objetos.PopularListas;
import fag.objetos.ProcessarPedido;
import fag.objetos.Vendedor;

public class SistemaLojaGabrielinha {

	private static final Scanner scan = new Scanner(System.in);
	private static final SimpleDateFormat FORMATADOR_DATA = new SimpleDateFormat("dd/MM/yyyy");

	private static List<Loja> lojas = new ArrayList<>();
	private static List<Cliente> clientes = new ArrayList<>();
	private static List<Vendedor> vendedores = new ArrayList<>();
	private static List<Gerente> gerentes = new ArrayList<>();
	private static List<Item> itens = new ArrayList<>();
	private static List<Pedido> pedidos = new ArrayList<>();

	public static void main(String[] args) {
		carregarDadosIniciais();
		menuPrincipal();
	}

	private static void carregarDadosIniciais() {
		PopularListas popularListas = new PopularListas();
		lojas = popularListas.criarLojas();
		clientes = popularListas.criarClientes(lojas);
		vendedores = popularListas.criarVendedores(lojas);
		gerentes = popularListas.criarGerentes(lojas);
		itens = popularListas.criarItens();
		pedidos = popularListas.criarPedidos(clientes, vendedores, itens);
	}

	private static void menuPrincipal() {
		int opcao;

		do {
			System.out.println("\n------- MENU PRINCIPAL ------");
			System.out.println("1 - Novo Pedido");
			System.out.println("2 - Registro de vendas");
			System.out.println("3 - Cadastrar Dados");
			System.out.println("4 - Consultar Dados");
			System.out.println("0 - Sair");
			System.out.println("-----------------------------");
			System.out.print("Escolha uma opção: ");

			opcao = lerInteiro();

			validarEscolha(opcao);
		} while (opcao != 0);
	}

	private static void validarEscolha(int opcao) {
		switch (opcao) {
		case 1:
			novoPedido();
			break;
		case 2:
			menuRegistroVendas();
			break;
		case 3:
			menuCadastrar();
			break;
		case 4:
			menuConsultarDados();
			break;
		case 0:
			System.out.println("Sistema encerrado.");
			break;
		default:
			System.out.println("Opcao invalida.");
			break;
		}
	}

	// registros de vendas

	private static void menuRegistroVendas() {
		int opcao;

		do {
			System.out.println("\n------ REGISTRO DE VENDAS ------");
			System.out.println("1 - Vendas totais");
			System.out.println("2 - Filtrar vendas");
			System.out.println("0 - Voltar");
			System.out.print("Escolha uma opcao: ");

			opcao = lerInteiro();

			switch (opcao) {
			case 1:
				listarPedidos(pedidos);
				break;
			case 2:
				menuFiltrarVendas();
				break;
			case 0:
				break;
			default:
				System.out.println("Opcao invalida.");
				break;
			}
		} while (opcao != 0);
	}

	private static void menuFiltrarVendas() {
		int opcao;

		do {
			System.out.println("\n------ FILTRAR VENDAS ------");
			System.out.println("1 - Por cliente");
			System.out.println("2 - Por vendedor");
			System.out.println("3 - Por loja");
			System.out.println("4 - Por dia e mes");
			System.out.println("5 - Apenas por mes");
			System.out.println("0 - Voltar");
			System.out.print("Escolha uma opcao: ");

			opcao = lerInteiro();

			switch (opcao) {
			case 1:
				filtrarPedidosPorCliente();
				break;
			case 2:
				filtrarPedidosPorVendedor();
				break;
			case 3:
				filtrarPedidosPorLoja();
				break;
			case 4:
				filtrarPedidosPorDiaEMes();
				break;
			case 5:
				filtrarPedidosPorMes();
				break;
			case 0:
				break;
			default:
				System.out.println("Opcao invalida.");
				break;
			}
		} while (opcao != 0);
	}

	
	// cadastros

	private static void novoPedido() {
		if (lojas.isEmpty() || clientes.isEmpty() || vendedores.isEmpty() || itens.isEmpty()) {
			System.out.println("Cadastre ao menos uma loja, um cliente, um vendedor e um item antes de criar pedidos.");
			return;
		}

		System.out.println("\n------ NOVO PEDIDO ------");
		int id = pedidos.size() + 1;
		Loja loja = escolherLoja();
		Cliente cliente = escolherCliente();
		Vendedor vendedor = escolherVendedorPorLoja(loja);
		Item itemSelecionado = escolherItem();
		System.out.print("Quantidade: ");
		int quantidade = lerInteiro();

		while (quantidade <= 0) {
			System.out.print("Quantidade invalida. Informe um valor maior que zero: ");
			quantidade = lerInteiro();
		}

		List<Item> itensPedido = criarItensPedido(itemSelecionado, quantidade);
		if (itensPedido.isEmpty()) {
			System.out.println("Pedido cancelado. Nenhum item foi selecionado.");
			return;
		}

		System.out.print("Data de vencimento da reserva (dd/MM/yyyy): ");
		Date dataVencimentoReserva = lerData();

		while (dataVencimentoReserva.before(new Date())) {
			System.out.print("A data de vencimento da reserva nao pode ser menor que a data atual. Digite novamente: ");
			dataVencimentoReserva = lerData();
		}

		Pedido pedido = new Pedido(id, new Date(), null, dataVencimentoReserva, cliente, vendedor, loja, itensPedido);
		ProcessarPedido processarPedido = new ProcessarPedido();
		processarPedido.processar(pedido);

		pedidos.add(pedido);

		System.out.println("Pedido criado com sucesso.");
		pedido.gerarDescricaoVenda();
		
		System.out.printf("\nValor pago pelo cliente: \n");
		
		double valorPago = lerDouble();

		while (valorPago < pedido.calcularValorFinal()) {
			System.out.printf("Valor insuficiente. Faltam R$ %.2f%n", pedido.calcularValorFinal() - valorPago);
			System.out.print("Informe novamente o valor pago: ");
			valorPago = lerDouble();
		}

		System.out.printf("\n Troco: R$ %.2f%n ", valorPago - pedido.calcularValorFinal());
	}
	
	private static void menuCadastrar() {
		int opcao;

		do {
			System.out.println("\n------ REALIZAR CADASTRO ------");
			System.out.println("1 - Cadastrar cliente");
			System.out.println("2 - Cadastrar gerente");
			System.out.println("3 - Cadastrar vendedor");
			System.out.println("4 - Cadastrar item");
			System.out.println("5 - Cadastrar loja");
			System.out.println("0 - Voltar");
			System.out.print("Escolha uma opcao: ");

			opcao = lerInteiro();

			switch (opcao) {
			case 1:
				cadastrarCliente();
				break;
			case 2:
				cadastrarGerente();
				break;
			case 3:
				cadastrarVendedor();
				break;
			case 4:
				cadastrarItem();
				break;
			case 5:
				cadastrarLoja();
				break;
			case 0:
				break;
			default:
				System.out.println("Opcao invalida.");
				break;
			}
		} while (opcao != 0);
	}

	private static void cadastrarLoja() {
		System.out.println("\n------ CADASTRO DE LOJA ------");
		System.out.print("Nome fantasia: ");
		String nomeFantasia = scan.nextLine();
		System.out.print("Razao social: ");
		String razaoSocial = scan.nextLine();
		System.out.print("CNPJ: ");
		String cnpj = scan.nextLine();
		Endereco endereco = lerEndereco();

		Loja loja = new Loja(nomeFantasia, razaoSocial, cnpj, endereco);
		lojas.add(loja);

		System.out.println("Loja cadastrada com sucesso.");
	}

	private static void cadastrarCliente() {
		if (lojas.isEmpty()) {
			System.out.println("Cadastre uma loja antes de cadastrar clientes.");
			return;
		}

		System.out.println("\n------ CADASTRO DE CLIENTE ------");
		System.out.print("Nome: ");
		String nome = scan.nextLine();
		System.out.print("Idade: ");
		int idade = lerInteiro();
		Endereco endereco = lerEndereco();
		Loja loja = escolherLoja();

		Cliente cliente = new Cliente(nome, idade, endereco, loja);
		clientes.add(cliente);
		loja.adicionarCliente(cliente);

		System.out.println("Cliente cadastrado com sucesso.");
	}

	private static void cadastrarVendedor() {
		if (lojas.isEmpty()) {
			System.out.println("Cadastre uma loja antes de cadastrar vendedores.");
			return;
		}

		System.out.println("\n------ CADASTRO DE VENDEDOR ------");
		System.out.print("Nome: ");
		String nome = scan.nextLine();
		System.out.print("Idade: ");
		int idade = lerInteiro();
		Endereco endereco = lerEndereco();
		Loja loja = escolherLoja();
		System.out.print("Salario base: ");
		double salarioBase = lerDouble();
		List<Double> salariosRecebidos = lerSalarios();

		Vendedor vendedor = new Vendedor(nome, idade, endereco, loja, salarioBase, salariosRecebidos);
		vendedores.add(vendedor);
		loja.adicionarVendedor(vendedor);

		System.out.println("Vendedor cadastrado com sucesso.");
	}

	private static void cadastrarGerente() {
		if (lojas.isEmpty()) {
			System.out.println("Cadastre uma loja antes de cadastrar gerentes.");
			return;
		}

		System.out.println("\n------ CADASTRO DE GERENTE ------");
		System.out.print("Nome: ");
		String nome = scan.nextLine();
		System.out.print("Idade: ");
		int idade = lerInteiro();
		Endereco endereco = lerEndereco();
		Loja loja = escolherLoja();
		System.out.print("Salario base: ");
		double salarioBase = lerDouble();
		List<Double> salariosRecebidos = lerSalarios();

		Gerente gerente = new Gerente(nome, idade, endereco, loja, salarioBase, salariosRecebidos);
		gerentes.add(gerente);

		System.out.println("Gerente cadastrado com sucesso.");
	}

	private static void cadastrarItem() {
		System.out.println("\n------ CADASTRO DE ITEM ------");
		int id = itens.size() + 1;
		System.out.print("Nome: ");
		String nome = scan.nextLine();
		System.out.print("Tipo: ");
		String tipo = scan.nextLine();
		System.out.print("Valor: ");
		double valor = lerDouble();

		Item item = new Item(id, nome, tipo, valor);
		itens.add(item);

		System.out.println("Item cadastrado com sucesso.");
	}

	// consulta de dados
	
	private static void menuConsultarDados() {
		int opcao;

		do {
			System.out.println("\n------ CONSULTAR DADOS ------");
			System.out.println("1 - Itens");
			System.out.println("2 - Clientes");
			System.out.println("3 - Funcionarios");
			System.out.println("4 - Lojas");
			System.out.println("0 - Voltar");
			System.out.print("Escolha uma opcao: ");

			opcao = lerInteiro();

			switch (opcao) {
			case 1:
				mostrarItens();
				break;
			case 2:
				mostrarClientes();
				break;
			case 3:
				mostrarFuncionarios();
				break;
			case 4:
				mostrarLojas();
				break;
			case 0:
				break;
			default:
				System.out.println("Opcao invalida.");
				break;
			}
		} while (opcao != 0);
	}

	private static void mostrarItens() {
		if (itens.isEmpty()) {
			System.out.println("Nenhum item cadastrado.");
			return;
		}

		System.out.println("\n------ ITENS ------");
		for (Item item : itens) {
			item.gerarDescricao();
		}
	}

	private static void mostrarClientes() {
		if (clientes.isEmpty()) {
			System.out.println("Nenhum cliente cadastrado.");
			return;
		}

		System.out.println("\n------ CLIENTES ------");
		for (Cliente cliente : clientes) {
			cliente.apresentar();
			System.out.println("Loja: " + cliente.getLoja().getNomeFantasia());
			System.out.print("Endereco: ");
			cliente.getEndereco().apresentarLogradouro();
		}
	}

	private static void mostrarFuncionarios() {
		if (vendedores.isEmpty() && gerentes.isEmpty()) {
			System.out.println("Nenhum funcionario cadastrado.");
			return;
		}

		System.out.println("\n------ VENDEDORES ------");
		for (Vendedor vendedor : vendedores) {
			mostrarFuncionario(vendedor);
		}

		System.out.println("\n------ GERENTES ------");
		for (Gerente gerente : gerentes) {
			mostrarFuncionario(gerente);
		}
	}

	private static void mostrarFuncionario(Funcionario funcionario) {
		funcionario.apresentar();
		System.out.printf("Media salarial: R$ %.2f%n", funcionario.calcularMedia());
		System.out.printf("Bonus: R$ %.2f%n", funcionario.calcularBonus());
		System.out.print("Endereco: ");
		funcionario.getEndereco().apresentarLogradouro();
	}

	private static void mostrarLojas() {
		if (lojas.isEmpty()) {
			System.out.println("Nenhuma loja cadastrada.");
			return;
		}

		System.out.println("\n------ LOJAS ------");
		for (Loja loja : lojas) {
			loja.apresentar();
			System.out.println("Quantidade de clientes: " + loja.contarClientes());
			System.out.println("Quantidade de vendedores: " + loja.contarVendedores());
		}
	}

	// registros de vendas

	private static void listarPedidos(List<Pedido> listaPedidos) {
		if (listaPedidos.isEmpty()) {
			System.out.println("Nenhuma venda registrada.");
			return;
		}

		System.out.println("\n------ PEDIDOS ------");
		for (Pedido pedido : listaPedidos) {
			pedido.gerarDescricaoVenda();
		}
	}

	private static void mostrarResumoVendasPeriodo(List<Pedido> pedidosFiltrados, String titulo) {
		System.out.println("\n------ " + titulo + " ------");
		System.out.println("Numero total de vendas neste periodo: " + pedidosFiltrados.size());

		if (pedidosFiltrados.isEmpty()) {
			System.out.println("Nenhuma venda encontrada.");
			return;
		}

		System.out.println("Vendas encontradas:");
		listarPedidos(pedidosFiltrados);
	}

	private static void filtrarPedidosPorCliente() {
		Cliente cliente = escolherCliente();
		List<Pedido> filtrados = new ArrayList<>();

		for (Pedido pedido : pedidos) {
			if (pedido.getCliente().equals(cliente)) {
				filtrados.add(pedido);
			}
		}

		listarPedidos(filtrados);
	}

	private static void filtrarPedidosPorVendedor() {
		Vendedor vendedor = escolherVendedor();
		List<Pedido> filtrados = new ArrayList<>();

		for (Pedido pedido : pedidos) {
			if (pedido.getVendedor().equals(vendedor)) {
				filtrados.add(pedido);
			}
		}

		listarPedidos(filtrados);
	}

	private static void filtrarPedidosPorLoja() {
		Loja loja = escolherLoja();
		List<Pedido> filtrados = new ArrayList<>();

		for (Pedido pedido : pedidos) {
			if (pedido.getLoja().equals(loja)) {
				filtrados.add(pedido);
			}
		}

		listarPedidos(filtrados);
	}

	private static void filtrarPedidosPorDiaEMes() {
		System.out.print("Informe a data de criacao (dd/MM/yyyy): ");
		Date data = lerData();
		List<Pedido> filtrados = new ArrayList<>();
		Calendar calendarioInformado = Calendar.getInstance();
		calendarioInformado.setTime(data);

		for (Pedido pedido : pedidos) {
			Calendar calendarioPedido = Calendar.getInstance();
			calendarioPedido.setTime(pedido.getDataCriacao());

			boolean mesmoDia = calendarioPedido.get(Calendar.DAY_OF_MONTH) == calendarioInformado.get(Calendar.DAY_OF_MONTH);
			boolean mesmoMes = calendarioPedido.get(Calendar.MONTH) == calendarioInformado.get(Calendar.MONTH);

			if (mesmoDia && mesmoMes) {
				filtrados.add(pedido);
			}
		}

		mostrarResumoVendasPeriodo(filtrados, "VENDAS POR DIA E MES");
	}

	private static void filtrarPedidosPorMes() {
		System.out.print("Informe o mes desejado (1 a 12): ");
		int mes = lerInteiro();

		while (mes < 1 || mes > 12) {
			System.out.print("Mes invalido. Informe um valor entre 1 e 12: ");
			mes = lerInteiro();
		}

		List<Pedido> filtrados = new ArrayList<>();

		for (Pedido pedido : pedidos) {
			Calendar calendarioPedido = Calendar.getInstance();
			calendarioPedido.setTime(pedido.getDataCriacao());

			if (calendarioPedido.get(Calendar.MONTH) + 1 == mes) {
				filtrados.add(pedido);
			}
		}

		mostrarResumoVendasPeriodo(filtrados, "VENDAS POR MES");
	}

	private static Loja escolherLoja() {
		System.out.println("\nSelecione a loja:");
		for (int i = 0; i < lojas.size(); i++) {
			System.out.println((i + 1) + " - " + lojas.get(i).getNomeFantasia());
		}

		int indice = lerIndiceValido(lojas.size());
		return lojas.get(indice);
	}

	private static Cliente escolherCliente() {
		System.out.println("\nSelecione o cliente:");
		for (int i = 0; i < clientes.size(); i++) {
			System.out.println((i + 1) + " - " + clientes.get(i).getNome());
		}

		int indice = lerIndiceValido(clientes.size());
		return clientes.get(indice);
	}

	private static Vendedor escolherVendedor() {
		System.out.println("\nSelecione o vendedor:");
		for (int i = 0; i < vendedores.size(); i++) {
			System.out.println((i + 1) + " - " + vendedores.get(i).getNome());
		}

		int indice = lerIndiceValido(vendedores.size());
		return vendedores.get(indice);
	}

	private static Vendedor escolherVendedorPorLoja(Loja loja) {
		List<Vendedor> vendedoresDaLoja = new ArrayList<>();

		for (Vendedor vendedor : vendedores) {
			if (vendedor.getLoja().equals(loja)) {
				vendedoresDaLoja.add(vendedor);
			}
		}

		if (vendedoresDaLoja.isEmpty()) {
			System.out.println("A loja escolhida nao possui vendedor cadastrado.");
			return escolherVendedor();
		}

		System.out.println("\nSelecione o vendedor:");
		for (int i = 0; i < vendedoresDaLoja.size(); i++) {
			System.out.println((i + 1) + " - " + vendedoresDaLoja.get(i).getNome());
		}

		int indice = lerIndiceValido(vendedoresDaLoja.size());
		return vendedoresDaLoja.get(indice);
	}

	private static Item escolherItem() {
		System.out.println("\nSelecione um item:");
		for (int i = 0; i < itens.size(); i++) {
			System.out.print((i + 1) + " - ");
			itens.get(i).gerarDescricao();
		}

		int indice = lerIndiceValido(itens.size());
		return itens.get(indice);
	}

	private static List<Item> criarItensPedido(Item item, int quantidade) {
		List<Item> itensPedido = new ArrayList<>();

		for (int i = 0; i < quantidade; i++) {
			itensPedido.add(item);
		}

		return itensPedido;
	}

	private static List<Double> lerSalarios() {
		List<Double> salarios = new ArrayList<>();

		for (int i = 1; i <= 3; i++) {
			System.out.print("Informe o salario recebido " + i + ": ");
			salarios.add(lerDouble());
		}

		return salarios;
	}

	private static Endereco lerEndereco() {
		System.out.print("Estado: ");
		String estado = scan.nextLine();
		System.out.print("Cidade: ");
		String cidade = scan.nextLine();
		System.out.print("Bairro: ");
		String bairro = scan.nextLine();
		System.out.print("Numero: ");
		String numero = scan.nextLine();
		System.out.print("Complemento: ");
		String complemento = scan.nextLine();

		return new Endereco(estado, cidade, bairro, numero, complemento);
	}

	private static int lerInteiro() {
		while (!scan.hasNextInt()) {
			System.out.print("Digite um numero inteiro valido: ");
			scan.nextLine();
		}

		int valor = scan.nextInt();
		scan.nextLine();
		return valor;
	}

	private static double lerDouble() {
		while (!scan.hasNextDouble()) {
			System.out.print("Digite um numero valido: ");
			scan.nextLine();
		}

		double valor = scan.nextDouble();
		scan.nextLine();
		return valor;
	}

	private static int lerIndiceValido(int tamanhoLista) {
		int opcao = lerInteiro();

		while (opcao < 1 || opcao > tamanhoLista) {
			System.out.print("Escolha uma opcao valida: ");
			opcao = lerInteiro();
		}

		return opcao - 1;
	}

	private static Date lerData() {
		while (true) {
			String texto = scan.nextLine();
			try {
				return FORMATADOR_DATA.parse(texto);
			} catch (ParseException e) {
				System.out.print("Data invalida. Digite novamente no formato dd/MM/yyyy: ");
			}
		}
	}
}
