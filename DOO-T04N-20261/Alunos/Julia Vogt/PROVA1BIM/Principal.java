package hotel;

import java.time.LocalDate;
import java.util.Scanner;

public class Principal {

    public static void main (String[] args) {

        Scanner sc = new Scanner(System.in);
        Hotel hotel = new Hotel();

        Hospede hospede = null;

        int opcao;

        do {
            System.out.println("\nHOTEL");
            System.out.println("1 - Cadastrar hóspede");
            System.out.println("2 - Cadastrar quarto simples");
            System.out.println("3 - Cadastrar quarto luxo");
            System.out.println("4 - Fazer reserva");
            System.out.println("5 - Fazer check-out");
            System.out.println("6 - Listar reservas ativas");
            System.out.println("7 - Demonstração");
            System.out.println("0 - Sair");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1:

                    System.out.print("Nome: ");
                    String nome = sc.nextLine();

                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();

                    System.out.print("Telefone: ");
                    String telefone = sc.nextLine();

                    hospede = new Hospede(nome, cpf, telefone);
                    System.out.println("Hóspede cadastrado.");
                    break;

                case 2:

                    System.out.print("Número: ");
                    int numero1 = sc.nextInt();

                    System.out.print("Diária: ");
                    double diaria1 = sc.nextDouble();

                    System.out.print("Tem ventilador (true/false): ");
                    boolean vent = sc.nextBoolean();

                    hotel.adicionarQuarto(
                            new QuartoSimples(numero1, diaria1, vent)
                    );
                    break;

                case 3:

                    System.out.print("Número: ");
                    int numero2 = sc.nextInt();

                    System.out.print("Diária: ");
                    double diaria2 = sc.nextDouble();

                    System.out.print("Tem varanda (true/false): ");
                    boolean var = sc.nextBoolean();

                    hotel.adicionarQuarto(new QuartoLuxo(numero2, diaria2, var));
                    break;

                case 4:

                    if (hospede == null || hotel.totalQuartos == 0) {
                        System.out.println("Cadastre hóspede e quarto antes.");
                    } else {

                        hotel.listarQuartos();

                        System.out.print("Escolha o quarto: ");
                        int escolha = sc.nextInt();

                        Quarto quarto = hotel.quartos[escolha - 1];

                        System.out.print("Ano entrada: ");
                        int ano1 = sc.nextInt();

                        System.out.print("Mês entrada: ");
                        int mes1 = sc.nextInt();

                        System.out.print("Dia entrada: ");
                        int dia1 = sc.nextInt();

                        System.out.print("Ano saída: ");
                        int ano2 = sc.nextInt();

                        System.out.print("Mês saída: ");
                        int mes2 = sc.nextInt();

                        System.out.print("Dia saída: ");
                        int dia2 = sc.nextInt();

                        LocalDate entrada = LocalDate.of(ano1, mes1, dia1);

                        LocalDate saida = LocalDate.of(ano2, mes2, dia2);

                        Reserva reserva = new Reserva(hospede, quarto, entrada, saida);

                        hotel.adicionarReserva(reserva);
                    }

                    break;

                case 5:

                    hotel.listarTodas();

                    System.out.print("Número da reserva: ");
                    int indice = sc.nextInt();

                    hotel.fazerCheckOut(indice - 1);
                    break;

                case 6:

                    hotel.listarSemCheckOut();
                    break;

                case 7:

                    demo(hotel);
                    break;

                case 0:

                    System.out.println("Sistema encerrado.");
                    break;

                default:

                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        sc.close();
    }

    public static void demo(Hotel hotel) {

        Hospede hospede1 = new Hospede("Julia", "111", "222");

        Hospede hospede2 = new Hospede("Joao", "222", "111");

        Quarto quarto1 = new QuartoSimples(101, 120, true);

        Quarto quarto2 = new QuartoLuxo(102, 160, true);

        hotel.adicionarQuarto(quarto1);
        hotel.adicionarQuarto(quarto2);

        Reserva reserva1 = new Reserva(hospede1,quarto1, LocalDate.of(2026, 4, 10),LocalDate.of(2026, 4, 12));

        Reserva reserva2 = new Reserva(hospede2,quarto2, LocalDate.of(2026, 4, 20),LocalDate.of(2026, 4, 25));

        reserva1.fazerCheckOut();

        hotel.adicionarReserva(reserva1);
        hotel.adicionarReserva(reserva2);

        hotel.listarSemCheckOut();
    }
}