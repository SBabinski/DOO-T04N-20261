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
			cadastroVendedor(minhaLoja);
			break;
		case 6:
			verLoja();
			minhaLoja.apresentarse();
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
		// 1. Criando as Lojas
		Loja l1 = new Loja("Loja do Centro", "Centro LTDA", "123", "Joinville", "Centro", "Rua das Palmeiras");
		Loja l2 = new Loja("Loja do Shopping", "Shopping S.A", "456", "Joinville", "Bom Retiro", "Rua do Shopping");
		Loja l3 = new Loja("Loja de Bairro", "Bairro Comercio", "789", "Joinville", "Itaum", "Rua Principal");

		Cliente c1 = new Cliente("João Silva", 30, "111.222.333-44", "Joinville", "Centro", "Rua das Palmeiras");
		Cliente c2 = new Cliente("Maria Oliveira", 25, "555.666.777-88", "Joinville", "Bom Retiro", "Rua do Shopping");
		Cliente c3 = new Cliente("Carlos Souza", 40, "999.000.111-22", "Joinville", "Itaum", "Rua Principal");

		l1.getListaCliente().add(c1);
		l1.getListaCliente().add(c2);
		l1.getListaCliente().add(c3);

		double[] salarios = { 2500.0, 2800.0, 3100.0 };
		Vendedor v1 = new Vendedor("Gabriel", 25, l1, "Joinville", "Centro", "Rua 1", 2000.0, salarios);
		l1.getListaVendedores().add(v1);
		vendedor.add(v1);

		listaLojas.add(l1);
		listaLojas.add(l2);
		listaLojas.add(l3);

		minhaLoja = l1; // Define a Loja 1 como padrão para o sistema
		System.out.println("Sistema inicializado com Lojas, Vendedores e Clientes prontos!");
	}

	public static void cadCliente() {
		System.out.println("\n--- Cadastro de Cliente ---");
		scan.nextLine();

		System.out.print("Nome completo do Cliente: ");
		String nomeCli = scan.nextLine();

		System.out.print("Idade: ");
		int idadeCli = scan.nextInt();
		scan.nextLine();

		System.out.print("CPF: ");
		String cpfCli = scan.nextLine();

		System.out.print("Cidade: ");
		String cidadeCli = scan.nextLine();

		System.out.print("Bairro: ");
		String bairroCli = scan.nextLine();

		System.out.print("Rua: ");
		String ruaCli = scan.nextLine();

		Cliente novoCliente = new Cliente(nomeCli, idadeCli, cpfCli, cidadeCli, bairroCli, ruaCli);

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
		scan.nextLine();
		System.out.println("--- Cadastro da Loja ---");
		System.out.println();
		System.out.println();

		String nome = cadNomeLoja();
		String razaoSocial = cadRazSoc();
		String cnpj = cadCnpj();
		String cidade = cadCidade();
		String bairro = cadBairro();
		String rua = cadRuaLoja();

		minhaLoja = new Loja(nome, razaoSocial, cnpj, cidade, bairro, rua);
		System.out.println("Loja" + nome + "Cadastrada!");

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
			System.out.println("CNPJ:");
			rua = scan.nextLine();
			if (rua.isEmpty()) {
				System.out.println("O campo está vazio.");
			}
		} while (rua.isEmpty());
		scan.nextLine();
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
		scan.nextLine();
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

	public static void cadastroVendedor(Loja loja) {

		String nome = cadNome();
		int idade = cadIdade();
		String cidade = cadCidade();
		String bairro = cadBairro();
		String rua = cadRua();
		double salarioBase = cadSalBase();
		double[] salarioRecebido = cadSalRec();

		Vendedor v1 = new Vendedor(nome, idade, loja, cidade, bairro, rua, salarioBase, salarioRecebido);
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
			System.out.println("Bairro:");
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