package calculadora;

import java.util.Scanner;
public class calc {
	static Scanner Calculadora = new Scanner (System.in);

	public static void main(String[] args) {
		int escolha = 0;
		do {
			System.out.println("Seja Bem Vinda Gabriela! \nAbaixo está sua calculadora, escolha uma das opções.");
			System.out.println("Digite [1] para Calcular o Preço Total");
			System.out.println("Digite [2] para Calcular o Troco");
			System.out.println("Digite [0] para Sair");
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
		case 0:
			System.out.println("Sistema encerrado! \nObrigado por utilizar o sistema, até a próxima!");
			break;
		default:
			System.out.println("Informe uma opção válida");
			break;
		}
	}
	public static void calculartotal(){
		System.out.println("Digite a quantidade total de plantas que serão vendidas.");
		int planta = Calculadora.nextInt();
		System.out.println("Digite o valor unitário da planta");
		double valor = Calculadora.nextDouble();
		double total = planta * valor;
		System.out.printf("O preço total da venda será de: R$ %.2f\n", total);		
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

}
