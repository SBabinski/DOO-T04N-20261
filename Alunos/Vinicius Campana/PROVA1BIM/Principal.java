package gerenciarHotel;

import java.time.LocalDate;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Hotel hotel = new Hotel();

        Hospede[] hospedes = new Hospede[10];
        Quarto[] quartos = new Quarto[10];

        int qtdHospedes = 0;
        int qtdQuartos = 0;

        int opcao;

        do {
            System.out.println("\n1 - Cadastrar Hóspede");
            System.out.println("2 - Cadastrar Quarto");
            System.out.println("3 - Cadastrar Reserva");
            System.out.println("4 - Realizar Check-out");
            System.out.println("5 - Listar Reservas Ativas");
            System.out.println("6 - Demonstração");
            System.out.println("0 - Sair");

            opcao = sc.nextInt();

            switch (opcao) {

                case 1:
                    sc.nextLine();
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();
                    System.out.print("Telefone: ");
                    String tel = sc.nextLine();

                    hospedes[qtdHospedes++] = new Hospede(nome, cpf, tel);
                    break;

                case 2:
                    System.out.println("1 - Simples | 2 - Luxo");
                    int tipo = sc.nextInt();

                    System.out.print("Número: ");
                    int numero = sc.nextInt();

                    System.out.print("Valor diária: ");
                    double valor = sc.nextDouble();

                    if (tipo == 1) {
                        System.out.print("Tem ventilador (true/false): ");
                        boolean vent = sc.nextBoolean();
                        quartos[qtdQuartos++] = new QuartoSimples(numero, valor, vent);
                    } else {
                        System.out.print("Tem varanda (true/false): ");
                        boolean var = sc.nextBoolean();
                        quartos[qtdQuartos++] = new QuartoLuxo(numero, valor, var);
                    }
                    break;

                case 3:
                    System.out.print("Quantidade de hospede: ");
                    int iH = sc.nextInt();

                    System.out.print("Quantidade do quarto: ");
                    int iQ = sc.nextInt();

                    System.out.print("Check-in (AAAA-MM-DD): ");
                    LocalDate in = LocalDate.parse(sc.next());

                    System.out.print("Check-out (AAAA-MM-DD): ");
                    LocalDate out = LocalDate.parse(sc.next());

                    Reserva r = new Reserva(hospedes[iH], quartos[iQ], in, out);

                    if (hotel.adicionarReserva(r)) {
                        System.out.println("Reserva cadastrada!");
                    } else {
                        System.out.println("Limite de reservas atingido!");
                    }
                    break;

                case 4:
                    System.out.print("Numero da reserva: ");
                    int idx = sc.nextInt();

                    Reserva reserva = hotel.getReserva(idx);

                    if (reserva != null) {
                        reserva.realizarCheckout();
                        System.out.println("Check-out realizado!");
                    }
                    break;

                case 5:
                    hotel.listarReservasAtivas();
                    break;

                case 6:
                    demonstracao();
                    break;
            }

        } while (opcao != 0);
        sc.close();
    }

    public static void demonstracao() {

        System.out.println("\n=== DEMONSTRACAO ===");

        Hospede h1 = new Hospede("Vinicius", "8888888888", "00000000000");
        Hospede h2 = new Hospede("Carlos", "00000000000", "8888888888");

        Quarto q1 = new QuartoSimples(101, 150.0, true);
        Quarto q2 = new QuartoLuxo(202, 300.0, true);

        Hotel hotel = new Hotel();

        Reserva r1 = new Reserva(h1, q1, LocalDate.of(2026, 1, 1),LocalDate.of(2026, 1, 6));

        Reserva r2 = new Reserva(h2, q2, LocalDate.of(2026, 3, 1),  LocalDate.of(2026, 3, 4));
               
               

        r1.realizarCheckout();

        System.out.println("\nTodas as reservas:");
        r1.exibirReserva();
        r2.exibirReserva();

        System.out.println("\nReservas ATIVAS:");
        hotel.listarReservasAtivas();
    }
}