package Aula2;

import java.util.Scanner;

public class Principal {

	static Scanner scan = new Scanner(System.in);
	static double Total;

	public static void main(String[] args) {
		Menu();

	}

	private static void Menu() {
		System.out.println("Escolha uma opçao abaixo");
		System.out.println("1 - Preço Total");
		System.out.println("2 - Troco");
		int escolha = scan.nextInt();

		switch (escolha) {
		case 1:
			calculoPT();
			System.out.println();
			Menu();
			break;
		case 2:
			Troco();
			System.out.println();
			Menu();
			break;
		case 3:
			System.out.println("Saindo da calculadora...");
			return;

		default:
			System.out.println("Opcao inválida");
			break;
		}
	}

	public static void Troco() {
		if (Total == 0) {
			System.out.println("Voce ainda não definiu sua compra.");
			return;
		}
		System.out.println("Valor recebido do cliente:");
		double vCliente = scan.nextDouble();
		if (vCliente < Total) {
			System.out.println("Falta um pouco de dinheiro!!");
		}
		else {
			System.out.println("Seu Troco é " + (vCliente - Total));
		}
	}

	public static void calculoPT() {
		System.out.println("Quantas unidades voce deseja ?");
		double Unid = scan.nextDouble();

		if (Unid <= 0) {
			System.out.println("Numero 0 ou negativo não válido");
			return;
		}
		System.out.println("Valor da Unidade da planta:");
		double Valor = scan.nextDouble();

		if (Valor <= 0) {
			System.out.println("Numero 0 ou negativo não válido");
			return;
		}
		Total = Unid * Valor;

		System.out.println("VALOR TOTAL: " + Total);
	}

}
