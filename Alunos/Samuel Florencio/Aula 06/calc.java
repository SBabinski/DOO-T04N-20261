import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
public class calc {
	static Scanner Calculadora = new Scanner (System.in);
	static ArrayList<Vendas> historico = new ArrayList<>();
	static DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	static Loja loja = new Loja();
	public static void main(String[] args) {
		//Criando Loja. 
		loja.nome = "My Plant";
		loja.razaoSocial = "My Plant LTDA";
		loja.cnpj = "27.432.630/0001-54";
		loja.cidade = "Cascavel - PR";
		loja.bairro = "Parque Verde";
		loja.rua = "Salgueiro";
		//Criando Vendedor.
		Vendedor vendedor1 = new Vendedor();
		vendedor1.nome = "Samuel";
		vendedor1.idade = 20;
		vendedor1.loja = loja.nome;
		vendedor1.cidade = loja.cidade;
		vendedor1.bairro = loja.bairro;
		vendedor1.rua = loja.rua;
		vendedor1.salBase = 2400;
		vendedor1.salrec.add(2400.0);
		vendedor1.salrec.add(2400.0);
		vendedor1.salrec.add(2400.0);
		//Criando Cliente
		Cliente cliente1 = new Cliente();
		cliente1.nome = "Cleyton";
		cliente1.idade = 37;
		cliente1.cidade = loja.cidade;
		cliente1.bairro = loja.bairro;
		cliente1.rua = loja.rua;
		
		loja.vendedores.add(vendedor1);
		loja.clientes.add(cliente1);
		
		int escolha = 0;
		do {
			System.out.println("Seja Bem Vinda Gabriela! \nAbaixo está sua calculadora, escolha uma das opções.");
			System.out.println("Digite [1] para Calcular o Preço Total.\n");
			System.out.println("Digite [2] para Calcular o Troco.\n");
			System.out.println("Digite [3] para ver o Histórico de Vendas.\n");
			System.out.println("Digite [4] para buscar as vendas por DIA\n");
			System.out.println("Digite [5] para buscar as vendas por MÊS\n");
			System.out.println("Digite [6] para ver a quantidade de clientes e vendedores da loja.\n");
			System.out.println("Digite [7] para ver as informações da loja.\n");
			System.out.println("Digite [8] para ver as informações dos clientes\n");
			System.out.println("Digite [9] para ver as informações dos vendedores\n");
			System.out.println("Digite [10] para cadastrar um novo cliente\n");
			System.out.println("Digite [11] para cadastrar um novo vendedor\n");
			System.out.println("Digite [0] para Sair.\n");
			escolha = Calculadora.nextInt();
			Calculadora.nextLine();
			opcao(escolha);
		}while(escolha!=0);
	}
	public static void opcao(int escolha) {
		switch(escolha) {
		case 1:
			calculartotal();
			break;
		case 2:
		    calculartroco();
			break;
		case 3:
			mostrarhistorico();
			break;
		case 4:
			porDia();
			break;
		case 5:
			porMes();
			break;
		case 6: 
			System.out.println("** Quantidade na loja **");
			loja.contarCliente();
			loja.contarVendedor();
			break;
		case 7:
			System.out.println("** Informações da LOJA **");
			loja.apresentar();
			break;
		case 8:
			loja.listarCliente();
			System.out.println("Digite o nome do cliente");
			String nomeCliente = Calculadora.nextLine();
			loja.infoCliente(nomeCliente);
			break;
		case 9:
			loja.listarVendedor();
			System.out.println("Digite o nome do vendedor");
			String nomeVendedor = Calculadora.nextLine();
			loja.infoVendedor(nomeVendedor);
			break;
		case 10:
			cadastroCliente();
			break;
		case 11:
			cadastroVendedor();
			break;
		case 0:
			System.out.println("Sistema encerrado! \nObrigado por utilizar o sistema, até a próxima!");
			break;
		default:
			System.out.println("Informe uma opção válida");
			break;
		}
	}
	public static void calculartotal(){
		double descontar;
		System.out.println("Digite a quantidade total de plantas que serão vendidas.");
		int planta = Calculadora.nextInt();
		System.out.println("Digite o valor unitário da planta");
		double valor = Calculadora.nextDouble();
		if (planta >= 10) {
			descontar = valor * planta * 0.05;
		}
	    else {			
	    	descontar = 0;
	    }
		double totalpago = planta * valor - descontar;
		System.out.printf("O preço total da venda será de: R$ %.2f\n", totalpago);
		Vendas venda = new Vendas();
		venda.quantidade = planta;
		venda.desconto = descontar;
		venda.valores = valor;
	    venda.total = totalpago;
	    venda.data = LocalDate.now();
		historico.add(venda);
}
	public static void calculartroco() {
		System.out.println("Informe o valor total entregado pelo cliente.");
		double valorcliente = Calculadora.nextDouble();
		System.out.println("Informe o valor total da compra");
		double valortotal = Calculadora.nextDouble();
		double troco = valorcliente - valortotal;
		if (troco>0) {
			System.out.printf("O cliente tem o valor de: R$ %.2f para ser devolvido.\n",troco);
		}
		else if (troco==0) {
			System.out.println("O cliente pagou o valor total e não tem troco para receber.");
		}
		else {
			System.out.println("O cliente não pagou o valor total da compra.");
		}
	}
	public static void mostrarhistorico() {
		System.out.println("Histórico de Vendas:\n");
		if (historico.isEmpty()) {
			System.out.println("Nenhuma venda registrada.");
		}
		else {
			for (Vendas v : historico) {
				System.out.println("Data: " + v.data.format(formato));
				System.out.println("Quantidade: " + v.quantidade);
				System.out.println("Valor: "+ v.valores);
				System.out.println("Desconto: " + v.desconto);
				System.out.println("Total: " + v.total);
				System.out.println("------------------------");
			}
		}
	}
	public static void porDia() {
		System.out.println("Digite a data (dia/mês/ano)");
		String dia = Calculadora.nextLine();
		LocalDate data = LocalDate.parse(dia, formato);
		int total = 0;
		for (Vendas v : historico) {
			if (v.data.equals(data)) {
				total += v.quantidade;
			}
		}
		System.out.println("Total no dia é: "+ total);
	}
	public static void porMes() {
		System.out.println("Digite o mês (Mes/Ano): ");
		String entrada = Calculadora.nextLine();
		String [] partes = entrada.split("/");
		int mes = Integer.parseInt(partes[0]);
		int ano = Integer.parseInt(partes[1]);
		int total = 0;
		for (Vendas v : historico){
			if (v.data.getMonthValue() == mes && v.data.getYear() == ano){
				total += v.quantidade;
			}
		}
		System.out.println("Total no mês: "+ total);
	}
	public static void cadastroCliente() {
		Cliente novoCLiente = new Cliente();
		System.out.println("Digite o nome do cliente: ");
		novoCLiente.nome = Calculadora.nextLine();
		System.out.println("Digite a idade do cliente: ");
		novoCLiente.idade = Calculadora.nextInt();
		Calculadora.nextLine();
		System.out.println("Digite a cidade do cliente: ");
		novoCLiente.cidade = Calculadora.nextLine();
		System.out.println("Digite o bairro do cliente: ");
		novoCLiente.bairro = Calculadora.nextLine();
		System.out.println("Digite a rua do Cliente: ");
		novoCLiente.rua = Calculadora.nextLine();
		
		loja.addCliente(novoCLiente);
		System.out.println("Cliente cadastrado com sucesso!\n");
	}
	public static void cadastroVendedor() {
		Vendedor novoVendedor = new Vendedor ();
		System.out.println("Digite o nome do vendedor: ");
		novoVendedor.nome = Calculadora.nextLine();
		System.out.println("Digite a idade do vendedor: ");
		novoVendedor.idade = Calculadora.nextInt();
		Calculadora.nextLine();
		System.out.println("Digite o salario base do vendedor: ");
		novoVendedor.salBase = Calculadora.nextDouble();
		Calculadora.nextLine();
		novoVendedor.loja = loja.nome;
		novoVendedor.cidade = loja.cidade;
		novoVendedor.bairro = loja.bairro;
		novoVendedor.rua = loja.rua;
		
		System.out.println("Deseja pagar um bonus para ele? (sim ou não): ");
		String resposta = Calculadora.nextLine();
		boolean bonus = resposta.equalsIgnoreCase("sim");
		novoVendedor.atualizarSalario(bonus);
		
		loja.addVendedor(novoVendedor);
		System.out.println("Vendedor cadastrado com sucesso!");
		}
}
class Vendas{
	int quantidade;
	double valores;
	double desconto;
	double total;
	LocalDate data;
}