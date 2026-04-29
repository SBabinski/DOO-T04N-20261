import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	static Scanner scan = new Scanner(System.in);

	static ArrayList<Venda> vendas = new ArrayList<>();
	static ArrayList<Cliente> clientes = new ArrayList<>();
	static ArrayList<Vendedor> vendedores = new ArrayList<>();
	static ArrayList<Loja> lojas = new ArrayList<>();
	static ArrayList<Pedido> pedidos = new ArrayList<>();

	public static void main(String[] args) {
		chamarMenu();
	}

	public static void chamarMenu() {
		int escolhaUsuarioMenu = 0;

		do {
			System.out.println("\nSeja bem vindo(a) ao MyPlant");
			System.out.println("Escolha a função que quer executar:");
			System.out.println("[1] - Calcular Preço Total");
			System.out.println("[2] - Calcular Troco");
			System.out.println("[3] - Registrar Venda (Data de agora)");
			System.out.println("[4] - Registrar Venda em determinado dia");
			System.out.println("[5] - Buscar venda por dia");
			System.out.println("[6] - Buscar venda por mês");
			System.out.println("[7] - Módulo de RH");
			System.out.println("[8] - Criar Pedido");
			System.out.println("[0] - Sair");

			escolhaUsuarioMenu = scan.nextInt();
			validarEscolhaMenu(escolhaUsuarioMenu);

		} while (escolhaUsuarioMenu != 0);
	}

	public static void validarEscolhaMenu(int escolhaUsuarioMenu) {
		switch (escolhaUsuarioMenu) {
			case 1:
				System.out.println("Qual o valor da planta?");
				float valorProduto = scan.nextFloat();

				System.out.println("Quantas unidades dessa planta você deseja comprar?");
				int qtdProduto = scan.nextInt();

				calcularValorTotal(valorProduto, qtdProduto);
				break;

			case 2:
				System.out.println("Qual o valor total da compra?");
				float valorTotal = scan.nextFloat();

				System.out.println("Qual o valor recebido pelo cliente?");
				float valorRecebidoCliente = scan.nextFloat();

				calcularTroco(valorTotal, valorRecebidoCliente);
				break;

			case 3:
				System.out.println("Qual o valor da planta?");
				float valorPlanta = scan.nextFloat();

				System.out.println("Quantas unidades dessa planta você deseja comprar?");
				int qtd = scan.nextInt();

				registrarVenda(valorPlanta, qtd, LocalDate.now());
				break;

			case 4:
				System.out.println("Qual o valor da planta?");
				float valorPlantaData = scan.nextFloat();

				System.out.println("Quantas unidades dessa planta você deseja comprar?");
				int qtdData = scan.nextInt();
				scan.nextLine();

				System.out.println("Informe a data da venda (dd/MM/yyyy):");
				String dataTexto = scan.nextLine();

				LocalDate dataInformada = LocalDate.parse(dataTexto, formatter);
				registrarVenda(valorPlantaData, qtdData, dataInformada);
				break;

			case 5:
				buscarVendaPorDia();
				break;

			case 6:
				buscarVendaPorMes();
				break;

			case 7:
				chamarModuloRH();
				break;

			case 8:
				criarPedido();
				break;

			case 0:
				System.out.println("Encerrando a execução do MyPlant. Obrigado por comprar conosco!");
				break;

			default:
				System.out.println("Opção inválida.");
				break;
		}
	}

	public static void calcularValorTotal(float valorProduto, int qtdProduto) {
		float desconto = 0;
		float discountPercent = 1;

		if (qtdProduto > 10) {
			System.out
					.println("Seu pedido ultrapassou a marca de 10 unidades, você acaba de receber um desconto de 5%!");
			desconto = 0.05f;
			discountPercent = 1 - desconto;
		}

		float valorSemDesconto = qtdProduto * valorProduto;
		float valorComDesconto = qtdProduto * (valorProduto * discountPercent);
		float valorDesconto = valorSemDesconto - valorComDesconto;

		System.out.println("O valor total da sua compra foi: " + valorComDesconto);
		System.out.println("Valor sem desconto: " + valorSemDesconto);
		System.out.println("Valor do desconto: " + valorDesconto);
	}

	public static void calcularTroco(float valorTotal, float valorRecebidoCliente) {
		String statusTroco = "";
		float valorTroco = valorRecebidoCliente - valorTotal;

		if (valorTroco < 0) {
			statusTroco = "Valor insuficiente.";
		} else if (valorTroco == 0) {
			statusTroco = "Você não tem troco a receber.";
		} else {
			statusTroco = "Troco a pagar: " + valorTroco;
		}

		System.out.println(statusTroco);
	}

	public static Venda registrarVenda(float valorProduto, int qtdProduto, LocalDate dataVenda) {
		Venda venda = new Venda();

		float valorTotal = qtdProduto * valorProduto;
		float desconto = 0;
		float valorComDesconto = valorTotal;

		if (qtdProduto > 10) {
			desconto = valorTotal * 0.05f;
			valorComDesconto = valorTotal - desconto;
		}

		venda.setQuantidade(qtdProduto);
		venda.setValorUnitario(valorProduto);
		venda.setValorTotal(valorTotal);
		venda.setDesconto(desconto);
		venda.setValorComDesconto(valorComDesconto);
		venda.setDataVenda(dataVenda);

		vendas.add(venda);
		listarVendas(vendas);

		return venda;
	}

	public static void listarVendas(ArrayList<Venda> vendas) {
		float somaTotal = 0;

		for (int i = 0; i < vendas.size(); i++) {
			Venda venda = vendas.get(i);

			System.out.println("Venda " + (i + 1));
			System.out.println("Data: " + venda.getDataVenda().format(formatter));
			System.out.println("Valor unitário: " + venda.getValorUnitario());
			System.out.println("Valor total: " + venda.getValorTotal());
			System.out.println("Desconto: " + venda.getDesconto());
			System.out.println("Valor com desconto: " + venda.getValorComDesconto());

			somaTotal = somaTotal + venda.getValorComDesconto();
		}

		System.out.println("Vendas: " + vendas.size());
		System.out.println("Valor total: " + somaTotal);
	}

	public static void buscarVendaPorDia() {
		scan.nextLine();
		System.out.println("Informe a data para busca (dd/MM/yyyy):");
		String dataTexto = scan.nextLine();

		LocalDate dataBusca = LocalDate.parse(dataTexto, formatter);
		int quantidadeVendas = 0;
		float totalVendido = 0;

		for (Venda venda : vendas) {
			if (venda.getDataVenda().equals(dataBusca)) {
				quantidadeVendas++;
				totalVendido += venda.getValorComDesconto();
			}
		}

		System.out.println("Data pesquisada: " + dataBusca.format(formatter));
		System.out.println("Quantidade de vendas: " + quantidadeVendas);
		System.out.println("Total vendido no dia: " + totalVendido);
	}

	public static void buscarVendaPorMes() {
		scan.nextLine();

		System.out.println("Informe o ano em que quer buscar (Apenas o número [xxxx]):");
		int ano = scan.nextInt();

		System.out.println("Informe o mês que quer buscar (Apenas o número [xx]): ");
		int mes = scan.nextInt();

		int quantidadeDeVendas = 0;
		float totalVendido = 0;

		for (Venda venda : vendas) {
			if (venda.getDataVenda().getMonthValue() == mes && venda.getDataVenda().getYear() == ano) {
				quantidadeDeVendas++;
				totalVendido += venda.getValorComDesconto();
			}
		}

		System.out.printf("Data pesquisada: %02d/%d%n", mes, ano);
		System.out.println("Total de vendas feita nesse período: " + quantidadeDeVendas);
		System.out.println("Total vendido nesse período: " + totalVendido);
	}

	public static void chamarModuloRH() {
		int escolhaUsuarioModuloRH = 0;

		do {
			System.out.println("\nSeja bem vindo(a) ao Módulo de RH do MyPlant");
			System.out.println("Escolha a função que quer executar:");
			System.out.println("[1] - Cadastrar Cliente");
			System.out.println("[2] - Cadastrar Vendedor");
			System.out.println("[3] - Cadastrar Loja");
			System.out.println("[4] - Listar Clientes");
			System.out.println("[5] - Listar Vendedores");
			System.out.println("[6] - Listar Lojas");
			System.out.println("[0] - Voltar");

			escolhaUsuarioModuloRH = scan.nextInt();
			validarEscolhaModuloRH(escolhaUsuarioModuloRH);

		} while (escolhaUsuarioModuloRH != 0);
	}

	public static void validarEscolhaModuloRH(int escolhaUsuarioModuloRH) {
		switch (escolhaUsuarioModuloRH) {
			case 1:
				cadastrarCliente();
				break;

			case 2:
				cadastrarVendedor();
				break;

			case 3:
				cadastrarLoja();
				break;

			case 4:
				listarClientes();
				break;

			case 5:
				listarVendedores();
				break;

			case 6:
				listarLojas();
				break;

			case 0:
				System.out.println("Voltando ao menu...");
				break;

			default:
				System.out.println("Opção inválida.");
				break;
		}
	}

	public static void listarClientes() {
		System.out.println("Listando Clientes...");

		if (clientes.size() == 0) {
			System.out.println("Nenhum cliente cadastrado.");
			return;
		}

		for (int i = 0; i < clientes.size(); i++) {
			clientes.get(i).listarCliente();
		}
	}

	public static void listarVendedores() {
		System.out.println("Listando Vendedores...");

		if (vendedores.size() == 0) {
			System.out.println("Nenhum vendedor cadastrado.");
			return;
		}

		for (int i = 0; i < vendedores.size(); i++) {
			vendedores.get(i).listarVendedor();
		}
	}

	public static void listarLojas() {
		System.out.println("Listando Lojas...");

		if (lojas.size() == 0) {
			System.out.println("Nenhuma loja cadastrada.");
			return;
		}

		for (int i = 0; i < lojas.size(); i++) {
			lojas.get(i).listarLoja();
		}
	}

	public static void cadastrarCliente() {
		scan.nextLine();

		Cliente cliente = new Cliente();

		System.out.println("Digite as informacoes do cliente para efetuar o cadastro.");

		System.out.println("Nome:");
		cliente.setNome(scan.nextLine());

		System.out.println("Idade:");
		cliente.setIdade(Integer.parseInt(scan.nextLine()));

		System.out.println("Bairro:");
		cliente.setBairro(scan.nextLine());

		System.out.println("Rua:");
		cliente.setRua(scan.nextLine());

		clientes.add(cliente);

		System.out.println("Cliente cadastrado com sucesso!");
	}

	public static void cadastrarVendedor() {
		scan.nextLine();

		Vendedor vendedor = new Vendedor();

		System.out.println("Digite as informacoes do vendedor para efetuar o cadastro.");

		System.out.println("Nome:");
		vendedor.setNome(scan.nextLine());

		System.out.println("Idade:");
		vendedor.setIdade(Integer.parseInt(scan.nextLine()));

		System.out.println("Loja:");
		vendedor.setLoja(scan.nextLine());

		System.out.println("Cidade:");
		vendedor.setCidade(scan.nextLine());

		System.out.println("Bairro:");
		vendedor.setBairro(scan.nextLine());

		System.out.println("Rua:");
		vendedor.setRua(scan.nextLine());

		System.out.println("Salario base:");
		vendedor.setSalarioBase(Float.parseFloat(scan.nextLine()));

		System.out.println("Quantos salarios recebidos deseja cadastrar?");
		int qtdSalarios = Integer.parseInt(scan.nextLine());

		float[] salarios = new float[qtdSalarios];

		for (int i = 0; i < qtdSalarios; i++) {
			System.out.println("Digite o salario recebido " + (i + 1) + ":");
			salarios[i] = Float.parseFloat(scan.nextLine());
		}

		vendedor.setSalarioRecebido(salarios);

		vendedores.add(vendedor);

		System.out.println("Vendedor cadastrado com sucesso!");
	}

	public static void cadastrarLoja() {
		scan.nextLine();

		Loja loja = new Loja();

		System.out.println("Digite as informacoes da loja para efetuar o cadastro.");

		System.out.println("Nome:");
		loja.setNome(scan.nextLine());

		System.out.println("Nome fantasia:");
		loja.setNomeFantasia(scan.nextLine());

		System.out.println("Razao social:");
		loja.setRazaoSocial(scan.nextLine());

		System.out.println("CNPJ:");
		loja.setCnpj(scan.nextLine());

		System.out.println("Cidade:");
		loja.setCidade(scan.nextLine());

		System.out.println("Bairro:");
		loja.setBairro(scan.nextLine());

		System.out.println("Rua:");
		loja.setRua(scan.nextLine());

		// inicialmente sem clientes e vendedores cadastrados dentro da loja, necessario
		// rodar o sistema e inserir pra ter os resultados
		loja.setClientes(new Cliente[0]);
		loja.setVendedores(new Vendedor[0]);

		lojas.add(loja);

		System.out.println("Loja cadastrada com sucesso!");
	}

	public static void criarPedido() {
		if (clientes.isEmpty() || vendedores.isEmpty() || lojas.isEmpty()) {
			System.out.println(
					"É necessário ter pelo menos um cliente, vendedor e loja cadastrados para criar um pedido.");
			return;
		}

		ProcessaPedido processaPedido = new ProcessaPedido();

		// Dados fake
		int id = pedidos.size() + 1;
		LocalDate dataCriacao = LocalDate.now();
		LocalDate dataVencimentoReserva = LocalDate.now().plusDays(7);

		Cliente cliente = clientes.get(0); // Primeiro cliente
		Vendedor vendedor = vendedores.get(0); // Primeiro vendedor
		Loja loja = lojas.get(0); // Primeira loja

		ArrayList<Item> itens = new ArrayList<>();
		Item item1 = new Item();
		item1.setId(1);
		item1.setNome("Planta Fake 1");
		item1.setTipo("Planta");
		item1.setValor(50.0f);
		itens.add(item1);

		Item item2 = new Item();
		item2.setId(2);
		item2.setNome("Planta Fake 2");
		item2.setTipo("Planta");
		item2.setValor(30.0f);
		itens.add(item2);

		Pedido pedido = processaPedido.processar(id, dataCriacao, dataVencimentoReserva, cliente, vendedor, loja,
				itens);
		pedidos.add(pedido);

		System.out.println("Pedido criado com sucesso!");
		pedido.gerarDescricaoVenda();
	}

}
