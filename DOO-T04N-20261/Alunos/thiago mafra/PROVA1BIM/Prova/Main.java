package Prova;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Hotel hotel = new Hotel();

        ArrayList<Hospede> hospedes = new ArrayList<>();
        ArrayList<Quarto> quartos = new ArrayList<>();

       
        Hospede h1 = new Hospede("João", "111", "9999");
        Hospede h2 = new Hospede("Maria", "222", "8888");

        Quarto q1 = new QuartoSimples(101, 100, true);
        Quarto q2 = new QuartoLuxo(202, 300, true);

        Reserva r1 = new Reserva(h1, q1, LocalDate.of(2026, 4, 1), LocalDate.of(2026, 4, 5));
        Reserva r2 = new Reserva(h2, q2, LocalDate.of(2026, 4, 10), LocalDate.of(2026, 4, 15));

        r1.realizarCheckout(); 
        hotel.adicionarReserva(r1);
        hotel.adicionarReserva(r2); 

        hotel.listarReservasAtivas();

       
        int op;
        do {
            System.out.println("\n=================================");
            System.out.println("        SISTEMA DE HOTEL");
            System.out.println("=================================");
            System.out.println("1 - Cadastrar Hóspede");
            System.out.println("2 - Cadastrar Quarto");
            System.out.println("3 - Cadastrar Reserva");
            System.out.println("4 - Realizar Check-out");
            System.out.println("5 - Listar Reservas Ativas");
            System.out.println("0 - Sair");
            System.out.println("=================================");
            System.out.print("Escolha uma opção: ");

            op = sc.nextInt();
            sc.nextLine();

            System.out.println("=================================\n");

            switch (op) {

                case 1:
                    System.out.println(">>> CADASTRO DE HÓSPEDE <<<");
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();
                    System.out.print("Telefone: ");
                    String tel = sc.nextLine();

                    hospedes.add(new Hospede(nome, cpf, tel));
                    System.out.println("✔ Hóspede cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.println(">>> CADASTRO DE QUARTO <<<");
                    System.out.println("1 - Simples");
                    System.out.println("2 - Luxo");
                    System.out.print("Tipo: ");
                    int tipo = sc.nextInt();

                    System.out.print("Número do quarto: ");
                    int num = sc.nextInt();

                    System.out.print("Valor da diária: ");
                    double val = sc.nextDouble();

                    if (tipo == 1) {
                        System.out.print("Possui ventilador? (true/false): ");
                        boolean vent = sc.nextBoolean();
                        quartos.add(new QuartoSimples(num, val, vent));
                    } else {
                        System.out.print("Possui varanda? (true/false): ");
                        boolean var = sc.nextBoolean();
                        quartos.add(new QuartoLuxo(num, val, var));
                    }

                    System.out.println("✔ Quarto cadastrado com sucesso!");
                    break;

                case 3:
                    System.out.println(">>> NOVA RESERVA <<<");

                    if (hospedes.isEmpty() || quartos.isEmpty()) {
                        System.out.println(" Cadastre pelo menos um hóspede e um quarto antes!");
                        break;
                    }

                    System.out.println("\nHóspedes disponíveis:");
                    for (int i = 0; i < hospedes.size(); i++) {
                        System.out.println(i + " - " + hospedes.get(i).getNome());
                    }
                    System.out.print("Escolha: ");
                    int ih = sc.nextInt();

                    System.out.println("\nQuartos disponíveis:");
                    for (int i = 0; i < quartos.size(); i++) {
                        System.out.println(i + " - Quarto " + i);
                    }
                    System.out.print("Escolha: ");
                    int iq = sc.nextInt();

                    System.out.println("\nData Check-in:");
                    System.out.print("Ano: ");
                    int ai = sc.nextInt();
                    System.out.print("Mês: ");
                    int mi = sc.nextInt();
                    System.out.print("Dia: ");
                    int di = sc.nextInt();

                    System.out.println("\nData Check-out:");
                    System.out.print("Ano: ");
                    int ao = sc.nextInt();
                    System.out.print("Mês: ");
                    int mo = sc.nextInt();
                    System.out.print("Dia: ");
                    int doo = sc.nextInt();

                    Reserva r = new Reserva(
                            hospedes.get(ih),
                            quartos.get(iq),
                            LocalDate.of(ai, mi, di),
                            LocalDate.of(ao, mo, doo)
                    );

                    if (hotel.adicionarReserva(r)) {
                        System.out.println(" Reserva cadastrada com sucesso!");
                    } else {
                        System.out.println(" Limite de reservas atingido!");
                    }
                    break;

                case 4:
                    System.out.println(">>> CHECK-OUT <<<");

                    // MOSTRA TODAS AS RESERVAS
                    hotel.listarTodasReservas();

                    System.out.print("Escolha o índice da reserva: ");
                    int i = sc.nextInt();

                    Reserva res = hotel.getReserva(i);

                    if (res != null) {
                        if (res.isCheckoutRealizado()) {
                            System.out.println(" Esta reserva já foi finalizada!");
                        } else {
                            res.realizarCheckout();
                            System.out.println(" Check-out realizado com sucesso!");
                        }
                    } else {
                        System.out.println(" Índice inválido!");
                    }
                    break;

                case 5:
                    hotel.listarReservasAtivas();
                    break;

                case 0:
                    System.out.println("Encerrando sistema...");
                    break;

                default:
                    System.out.println(" Opção inválida!");
            }

        } while (op != 0);

        sc.close();
    }
}