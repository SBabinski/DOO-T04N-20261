package prov1bim.java;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Hotel sistema = new Hotel();
		int op;

		do {
			System.out.println("\n===== MENU =====");
			System.out.println("1 - Cadastrar Hóspede");
			System.out.println("2 - Cadastrar Quarto");
			System.out.println("3 - Realizar Check-out");
			System.out.println("4 - Listar Reservas Ativas");
			System.out.println("5 - Demonstração");
			System.out.println("0 - Sair");

			op = sc.nextInt();
			sc.nextLine();

			switch (op) {

			case 1:
				System.out.print("Nome: ");
				String nome = sc.nextLine();
				System.out.print("CPF: ");
				String cpf = sc.nextLine();
				System.out.print("Telefone: ");
				String tel = sc.nextLine();
				sistema.addHospede(new Hospedes(nome, cpf, tel));
				System.out.println("Hóspede cadastrado!");
				break;
				
			case 2:
				System.out.print("Número do quarto: ");
				int num = sc.nextInt();
				System.out.print("Valor diária: ");
				double valor = sc.nextDouble();
				System.out.print("Tipo (1-Simples / 2-Luxo): ");
				int tipo = sc.nextInt();
				if (tipo == 1) {
					System.out.print("Tem ventilador? (true/false): ");
					boolean vent = sc.nextBoolean();
					sistema.addQuarto(new Quarto_Simples(num, valor, vent));
				} else {
					System.out.print("Tem varanda? (true/false): ");
					boolean var = sc.nextBoolean();
					sistema.addQuarto(new Quarto_Luxo(num, valor, var));
				}

				System.out.println("Quarto cadastrado!");
				break;

			// =========================
			case 3:
				System.out.print("Índice da reserva: ");
				int iR = sc.nextInt();

				sistema.fazerCheckout(iR);
				System.out.println("Check-out realizado!");
				break;

			// =========================
			case 4:
				System.out.println("\n=== RESERVAS ATIVAS ===");
				sistema.listarAtivas();
				break;

			// =========================
			case 5:
				demonstracao(sistema);
				break;
			}

		} while (op != 0);

		sc.close();
	}

	public static void demonstracao(Hotel sistema) {

		System.out.println("\n=== DEMONSTRAÇÃO ===");

		Hospedes h1 = new Hospedes("João", "111", "9999");
		Hospedes h2 = new Hospedes("Maria", "222", "8888");

		sistema.addHospede(h1);
		sistema.addHospede(h2);

		Quarto q1 = new Quarto_Simples(101, 100, true);
		Quarto q2 = new Quarto_Luxo(202, 200, true);

		sistema.addQuarto(q1);
		sistema.addQuarto(q2);

		Regis_reservas r1 = new Regis_reservas(h1, q1, LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 5));

		Regis_reservas r2 = new Regis_reservas(h2, q2, LocalDate.of(2025, 6, 10), LocalDate.of(2025, 6, 15));

		r1.realizarcheckout();

		sistema.addReserva(r1);
		sistema.addReserva(r2);

		System.out.println("\nReservas ativas:");
		sistema.listarAtivas();
	}
}
