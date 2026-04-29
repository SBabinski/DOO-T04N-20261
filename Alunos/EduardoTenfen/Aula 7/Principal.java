package Aula2;

import java.util.Scanner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Principal {

	static Scanner scan = new Scanner(System.in);
	static ArrayList<Vendas> vendas = new ArrayList<>();
	static ArrayList<Vendedor> vendedor = new ArrayList<>();
	static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	static ArrayList<Cliente> listaCliente = new ArrayList<>();
	static ArrayList<Loja> listaLojas = new ArrayList<>();
	static ArrayList<Endereco> endereco = new ArrayList<>();
	static Loja Loja;
	static Vendedor Vendedor;
	static Loja minhaLoja;
	static int quantidadeTemp;
	static double valorTemp;

	public static void main(String[] args) {
		preDefinirLojaseClientes();
		menu();
	}

	public static void menu() {
		System.out.println("--- MENU SISTEMA ---");
		System.out.println("1 - Preço Total");
		System.out.println("2 - Troco");
		System.out.println("3 - Registrar Venda");
		System.out.println("4 - Consultar Vendas por Data");
		System.out.println("5 - Cadastrar Vendedor");
		System.out.println("6 - Ver Lojas e Vendedores");
		System.out.println("7 - Ver Clientes e Quantidade"); // Atualizado para Atv 3
		System.out.println("8 - Cadastrar Cliente");
		System.out.println("9 - Cadastrar Nova Loja");
		System.out.println("10 - Contar Vendedores da Loja"); // Novo requisito
		System.out.println("11 - Sair");

		System.out.print("Escolha: ");
		int escolha = scan.nextInt();
		scan.nextLine();

		switch (escolha) {
		case 1:
			calculoPT();
			break;
		case 2:
			troco();
			break;
		case 3:
			registroDeVenda();
			break;
		case 4:
			consultarVendasPorData();
			break;
		case 5:
			cadastroVendedor();
			break;
		case 6:
			verLoja();

			break;
		case 7:
			minhaLoja.mostrarClientes();
			minhaLoja.contarClientes();
			break;
		case 8:
			cadCliente();
			break;
		case 9:
			cadLoja();
			break;
		case 10:
			minhaLoja.contarVendedores();
			break;
		case 11:
			System.out.println("Saindo do sistema...");
			return;
		default:
			System.out.println("Opção inválida!!");
		}

		System.out.println();
		menu();
	}

	public static void preDefinirLojaseClientes() {
		// 1. Criando Endereços Diferentes (Estado, Cidade, Bairro, Rua, Numero,
		// Complemento)
		Endereco end1 = new Endereco("SP", "São Paulo", "Centro", "Av. Paulista", 1000, "Térreo");
		Endereco end2 = new Endereco("RJ", "Rio de Janeiro", "Copacabana", "Av. Atlântica", 500, "Loja B");
		Endereco end3 = new Endereco("PR", "Cascavel", "Centro", "Av. Brasil", 3000, "Sala 5");

		// 2. Criando as Lojas com CNPJs e endereços diferentes
		Loja l1 = new Loja("Loja Paulista", "Paulista LTDA", "11.111.111/0001-11", end1);
		Loja l2 = new Loja("Loja Copacabana", "Praia S.A", "22.222.222/0002-22", end2);
		Loja l3 = new Loja("Loja Cascavel", "Oeste Comercio", "33.333.333/0003-33", end3);

		// 3. Criando Clientes (já com idades mais realistas também)
		Cliente c1 = new Cliente("João Silva", 30, "111.222.333-44", end1);
		Cliente c2 = new Cliente("Maria Oliveira", 85, "555.666.777-88", end2);
		Cliente c3 = new Cliente("Carlos Souza", 45, "999.000.111-22", end3);

		// Adicionando os clientes na Loja 1
		l1.getListaCliente().add(c1);
		l1.getListaCliente().add(c2);
		l1.getListaCliente().add(c3);

		// 4. Criando Vendedor para a Loja 1
		double[] salarios = { 2500.0, 2800.0, 3100.0 };
		Vendedor v1 = new Vendedor("Gabriel", 25, l1, end1, 2000.0, salarios);

		l1.getListaVendedores().add(v1);

		// 5. Cadastrando as lojas no sistema
		listaLojas.add(l1);
		listaLojas.add(l2);
		listaLojas.add(l3);

		minhaLoja = l1; // Define a Loja 1 como padrão para o sistema
		System.out.println("Sistema inicializado com Lojas, Vendedores e Clientes prontos!");
	}

	public static void cadCliente() {
		System.out.println("\n--- Cadastro de Cliente ---");
		System.out.println();

		System.out.print("Nome completo do Cliente: ");
		String nome = scan.nextLine();

		int idade = cadIdade();
		String estado = cadEstado();
		System.out.print("CPF: ");
		String cpfCli = scan.nextLine();
		String cidade = cadCidade();
		String bairro = cadBairro();
		String rua = cadRua();
		int numero = cadNumero();
		String complemento = cadComplemento();

		Endereco enderecoCliente = new Endereco(estado, cidade, bairro, rua, numero, complemento);

		Cliente novoCliente = new Cliente(nome, idade, cpfCli, enderecoCliente);

		minhaLoja.getListaCliente().add(novoCliente);
		System.out.println("Cliente cadastrado com sucesso!");
	}

	public static void verLoja() {
		if (listaLojas.isEmpty()) {
			System.out.println("Nenhuma loja cadastrada.");
		} else {
			// Percorre a lista que criamos lá no topo
			for (Loja l : listaLojas) {
				l.apresentarse();

			}
		}
	}

	public static void cadLoja() {

		System.out.println("--- Cadastro da Loja ---");
		System.out.println();
		System.out.println();

		String nome = cadNomeLoja();
		String razaoSocial = cadRazSoc();
		String cnpj = cadCnpj();
		String estado = cadEstado();
		String cidade = cadCidade();
		String bairro = cadBairro();
		String rua = cadRuaLoja();
		int numero = cadNumero();
		String complemento = cadComplemento();

		Endereco enderecoLoja = new Endereco(estado, cidade, bairro, rua, numero, complemento);

		minhaLoja = new Loja(nome, razaoSocial, cnpj, enderecoLoja);
		listaLojas.add(minhaLoja);
		System.out.println("Loja " + nome + " Cadastrada!");

	}

	public static String cadEstado() {
		String estado = "";
		do {
			System.out.println("Estado: ");
			estado = scan.nextLine();
			if (estado.isEmpty()) {
				System.out.println("ERRO: O campo está vazio");
			}
		} while (estado.isEmpty());
		return estado;
	}

	public static int cadNumero() {
		int numero = -1;
		do {
			System.out.print("Número: ");
			try {
				// Lê a entrada como texto (String) e tenta converter para Inteiro
				numero = Integer.parseInt(scan.nextLine());

				if (numero < 0) {
					System.out.println("ERRO: Número inválido (não pode ser negativo).");
				}
			} catch (NumberFormatException e) {
				// Se der erro na conversão (porque tem letras), o código "cai" aqui
				System.out.println("ERRO: Por favor, digite apenas números inteiros!");
				numero = -1; // Mantém a variável negativa para o loop repetir a pergunta
			}
		} while (numero < 0);

		return numero;
	}

	public static String cadComplemento() {
		String complemento = "";
		do {
			System.out.println("Complemento: ");
			complemento = scan.nextLine();
			if (complemento.isEmpty()) {
				System.out.println("ERRO: Campo está vazio.");
			}

		} while (complemento.isEmpty());
		return complemento;
	}

	public static String cadCnpj() {
		String cnpj = "";
		do {
			System.out.println("CNPJ:");
			cnpj = scan.nextLine();
			if (cnpj.isEmpty()) {
				System.out.println("O campo está vazio.");
			}
		} while (cnpj.isEmpty());

		return cnpj;
	}

	static String cadRuaLoja() {
		String rua = "";
		do {
			System.out.println("Rua:");
			rua = scan.nextLine();
			if (rua.isEmpty()) {
				System.out.println("O campo está vazio.");
			}
		} while (rua.isEmpty());

		return rua;
	}

	public static String cadRazSoc() {
		String razaoSocial = "";
		do {
			System.out.println("Razão social:");
			razaoSocial = scan.nextLine();
			if (razaoSocial.isEmpty()) {
				System.out.println("O campo está vazio.");
			}
		} while (razaoSocial.isEmpty());

		return razaoSocial;
	}

	public static String cadNomeLoja() {
		String nome = "";
		do {
			System.out.println("Nome fantasia:");
			nome = scan.nextLine();
			if (nome.isEmpty()) {
				System.out.println("O campo está vazio.");

			}
		} while (nome.isEmpty());

		return nome;
	}

	public static void cadastroVendedor() {

		String nome = cadNome();
		int idade = cadIdade();
		String estado = cadEstado();
		String cidade = cadCidade();
		String bairro = cadBairro();
		String rua = cadRua();
		int numero = cadNumero();
		String complemento = cadComplemento();
		double salarioBase = cadSalBase();
		double[] salarioRecebido = cadSalRec();

		Endereco enderecoVendedor = new Endereco(estado, cidade, bairro, rua, numero, complemento);

		Vendedor v1 = new Vendedor(nome, idade, minhaLoja, enderecoVendedor, salarioBase, salarioRecebido);
		vendedor.add(v1);

	}

	public static double[] cadSalRec() {
		double[] salarioRecebido = { 2500.00, 2750.00, 1850.00 };

		scan.nextLine();
		return salarioRecebido;
	}

	public static double cadSalBase() {

		double salarioBase = -1;
		do {
			System.out.println("Salário Base:");
			salarioBase = scan.nextDouble();
			if (salarioBase < 0) {
				System.out.println("ERRO: Salário base deve ser maior que zero.");
			}

		} while (salarioBase < 0);

		return salarioBase;

	}

	public static String cadRua() {
		String rua = "";
		do {
			System.out.println("Rua:");
			rua = scan.nextLine();
			if (rua.isEmpty()) {
				System.out.println("ERRO: Rua nao pode estar vazio.");
			}
		} while (rua.isEmpty());

		return rua;
	}

	public static String cadBairro() {
		String bairro = "";
		do {
			System.out.println("Bairro:");
			bairro = scan.nextLine();
			if (bairro.isEmpty()) {
				System.out.println("ERRO: Bairro nao pode estar vazio.");
			}
		} while (bairro.isEmpty());

		return bairro;
	}

	public static String cadCidade() {
		String cidade = "";
		do {
			System.out.println("Cidade:");
			cidade = scan.nextLine();
			if (cidade.isEmpty()) {
				System.out.println("ERRO: cidade nao pode estar vazia.");
			}
		} while (cidade.isEmpty());

		return cidade;
	}

	public static String cadNome() {
		String nome = "";

		do {
			System.out.println("Digite o nome do vendedor:");
			nome = scan.nextLine();
			if (nome.trim().isEmpty()) {
				System.out.println("Nome inválido.");
			}
		} while (nome.trim().isEmpty());

		return nome;

	}

	public static int cadIdade() {
		int idade = 0;

		do {
			System.out.println("Idade:");

			// verifica se é um número
			if (!scan.hasNextInt()) {
				System.out.println("Erro: Digite um número inteiro!");
				scan.next();
				continue;
			}

			idade = scan.nextInt();
			scan.nextLine();

			// Verifica as regras de valor (Negativo ou muito jovem)
			if (idade < 14 || idade > 100) {
				System.out.println("Idade inválida para um vendedor (deve ser entre 14 e 100).");
			}

		} while (idade < 14 || idade > 100);

		return idade;
	}

	public static void calculoPT() {
		System.out.println("Quantas unidades voce deseja?");
		quantidadeTemp = scan.nextInt();

		if (quantidadeTemp <= 0) {
			System.out.println("Número inválido!");
			return;
		}

		System.out.println("Valor da unidade:");
		valorTemp = scan.nextDouble();

		if (valorTemp <= 0) {
			System.out.println("Valor inválido!");
			return;
		}

		double valorBruto = quantidadeTemp * valorTemp;
		double desconto = (quantidadeTemp > 10) ? valorBruto * 0.05 : 0;
		double total = valorBruto - desconto;

		System.out.println("VALOR TOTAL: " + total);
	}

	public static void registroDeVenda() {
		if (quantidadeTemp == 0) {
			System.out.println("Faça um cálculo primeiro!");
			return;
		}

		scan.nextLine();
		System.out.println("Data da venda (dd/MM/yyyy):");
		String dataStr = scan.nextLine();

		LocalDate data;

		try {
			data = LocalDate.parse(dataStr, formatter);
		} catch (Exception e) {
			System.out.println("Data inválida!");
			return;
		}

		Vendas venda = new Vendas(quantidadeTemp, valorTemp, data, vendedor.get(0), minhaLoja.getListaCliente().get(0));
		vendas.add(venda);

		System.out.println("Venda registrada com sucesso!");
	}

	public static void consultarVendasPorData() {
		if (vendas.isEmpty()) {
			System.out.println("Nenhuma venda registrada!");
			return;
		}

		scan.nextLine();
		System.out.println("Digite a data (dd/MM/yyyy):");
		String dataStr = scan.nextLine();

		LocalDate data;

		try {
			data = LocalDate.parse(dataStr, formatter);
		} catch (Exception e) {
			System.out.println("Data inválida!");
			return;
		}

		boolean encontrou = false;

		for (Vendas v : vendas) {
			if (v.getData().equals(data)) {
				v.mostrarVenda();
				encontrou = true;
			}
		}

		if (!encontrou) {
			System.out.println("Nenhuma venda encontrada nessa data!");
		}
	}

	public static void troco() {
		if (quantidadeTemp == 0) {
			System.out.println("Faça um cálculo primeiro!");
			return;
		}

		double valorBruto = quantidadeTemp * valorTemp;
		double desconto = (quantidadeTemp > 10) ? valorBruto * 0.05 : 0;
		double total = valorBruto - desconto;

		System.out.println("Valor recebido:");
		double recebido = scan.nextDouble();

		if (recebido < total) {
			System.out.println("Falta dinheiro!");
		} else {
			System.out.println("Troco: " + (recebido - total));
		}
	}
}