
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		ArrayList<String> vendas = new ArrayList<>();
		HashMap<LocalDate, Integer> vendasPorData = new HashMap<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		Loja loja = new Loja("My Plant", "My Plant LTDA", "82.647.884/0001-35",
				"Cascavel", "Centro", "Av Brasil");

		Vendedor vendedor = new Vendedor("Joao", 30, loja,
				"Cascavel", "Cidade Verde", "Rua Angelim", 2000);

		Cliente cliente = new Cliente("Maria", 92,
				"Cascavel", "Canadá", "Rua Marechal");

		loja.vendedores[0] = vendedor;
		loja.clientes[0] = cliente;

		int opcao = 0;
		
		while(opcao != 9) {
			System.out.println("Sistema dona Gabrielinha");
			System.out.println("1 Calcular preco total");
			System.out.println("2 Calcular troco");
			System.out.println("3 Ver registro de vendas");
			System.out.println("4 Buscar vendas por data");
			System.out.println("5 Mostrar loja");
			System.out.println("6 Mostrar vendedor");
			System.out.println("7 Mostrar clientes");
			System.out.println("8 Contar dados loja");
			System.out.println("9 Sair");

			opcao = scan.nextInt();
			
			switch (opcao) {
			
			case 1: {
				System.out.println("Digite quantidade: ");
				int qtd = scan.nextInt();
				
				System.out.println("Digite preco: ");
				double preco = scan.nextDouble();
				
				double total = qtd * preco;
				double desconto = 0;
				
				if(qtd > 10) {
					desconto = total * 0.05;
					total -= desconto;
				}
				
				System.out.println("Total: " + total);
				System.out.println("Desconto: " + desconto);
				
				vendas.add("Qtd: " + qtd + " Total: " + total);

				LocalDate hoje = LocalDate.now();
				vendasPorData.put(hoje, vendasPorData.getOrDefault(hoje, 0) + 1);
				
				break;
			}
			
			case 2: {
				System.out.println("Valor pago: ");
				double pago = scan.nextDouble();
				
				System.out.println("Valor total: ");
				double compra = scan.nextDouble();
				
				System.out.println("Troco: " + (pago - compra));
				break;
			}
			
			case 3: {
				for(String venda : vendas) {
					System.out.println(venda);
				}
				break;
			}
			
			case 4: {
				scan.nextLine();
				System.out.println("Digite data:");
				String dataStr = scan.nextLine();
				
				try {
					LocalDate data = LocalDate.parse(dataStr, formatter);
					System.out.println("Quantidade: " + vendasPorData.getOrDefault(data, 0));
				} catch(Exception e) {
					System.out.println("Data invalida");
				}
				break;
			}

			case 5:
				loja.apresentarse();
				break;

			case 6:
				vendedor.apresentarse();
				System.out.println("Media: " + vendedor.calcularMedia());
				System.out.println("Bonus: " + vendedor.calcularBonus());
				break;

			case 7:
				for(Cliente cli : loja.clientes) {
					if(cli != null) cli.apresentarse();
				}
				break;

			case 8:
				loja.contarClientes();
				loja.contarVendedores();
				break;

			case 9:
				System.out.println("Saindo...");
				break;
			}
		}
		
		scan.close();
	}
}

class Vendedor {

	String nome;
	int idade;
	Loja loja;
	String cidade;
	String bairro;
	String rua;
	double salarioBase;
	double[] salarioRecebido;

	public Vendedor(String nome, int idade, Loja loja,
			String cidade, String bairro, String rua, double salarioBase) {

		this.nome = nome;
		this.idade = idade;
		this.loja = loja;
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
		this.salarioBase = salarioBase;

		salarioRecebido = new double[3];
		salarioRecebido[0] = 1500;
		salarioRecebido[1] = 1600;
		salarioRecebido[2] = 1700;
	}

	public void apresentarse() {
		System.out.println(nome + " " + idade + " " + loja.nomeFantasia);
	}

	public double calcularMedia() {
		double soma = 0;
		for(double s : salarioRecebido) soma += s;
		return soma / salarioRecebido.length;
	}

	public double calcularBonus() {
		return salarioBase * 0.2;
	}
}

class Cliente {

	String nome;
	int idade;
	String cidade;
	String bairro;
	String rua;

	public Cliente(String nome, int idade, String cidade, String bairro, String rua) {
		this.nome = nome;
		this.idade = idade;
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
	}

	public void apresentarse() {
		System.out.println(nome + " " + idade);
	}
}

class Loja {

	String nomeFantasia;
	String razaoSocial;
	String cnpj;
	String cidade;
	String bairro;
	String rua;

	Vendedor[] vendedores = new Vendedor[10];
	Cliente[] clientes = new Cliente[10];

	public Loja(String nomeFantasia, String razaoSocial, String cnpj,
			String cidade, String bairro, String rua) {

		this.nomeFantasia = nomeFantasia;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
	}

	public void contarClientes() {
		int total = 0;
		for(Cliente cli : clientes) if(cli != null) total++;
		System.out.println("Clientes: " + total);
	}

	public void contarVendedores() {
		int total = 0;
		for(Vendedor venda : vendedores) if(venda != null) total++;
		System.out.println("Vendedores: " + total);
	}

	public void apresentarse() {
		System.out.println(nomeFantasia + " " + cnpj);
		System.out.println(cidade + " " + bairro + " " + rua);
	}
}