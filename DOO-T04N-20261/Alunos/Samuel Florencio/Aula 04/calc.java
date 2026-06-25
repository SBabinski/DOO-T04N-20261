import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
public class calc {
	static Scanner Calculadora = new Scanner (System.in);
	static ArrayList<Vendas> historico = new ArrayList<>();
	static DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public static void main(String[] args) {
		int escolha = 0;
		do {
			System.out.println("Seja Bem Vinda Gabriela! \nAbaixo está sua calculadora, escolha uma das opções.");
			System.out.println("Digite [1] para Calcular o Preço Total.\n");
			System.out.println("Digite [2] para Calcular o Troco.\n");
			System.out.println("Digite [3] para ver o Histórico de Vendas.\n");
			System.out.println("Digite [4] para buscar as vendas por DIA");
			System.out.println("Digite [5] para buscar as vendas por MÊS");
			System.out.println("Digite [0] para Sair.\n");
			escolha = Calculadora.nextInt();
			Calculadora.nextLine();
			opção(escolha);
		}while(escolha!=0);
	}
	public static void opção(int escolha) {
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
}
class Vendas{
	int quantidade;
	double valores;
	double desconto;
	double total;
	LocalDate data;
}
