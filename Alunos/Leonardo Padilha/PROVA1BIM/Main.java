import model.Hospede;
import model.Quarto;
import model.QuartoSimples;
import model.QuartoLuxo;
import model.Reserva;
import service.HotelService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HotelService hotelService = new HotelService();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        boolean executandoMenu = true;

        while (executandoMenu) {
            System.out.println("\n===== MENU RAFAIN HOTEL =====");
            System.out.println("1- Cadastrar Hospede");
            System.out.println("2 - Cadastrar Quarto");
            System.out.println("3 - Cadastrar Reserva");
            System.out.println("4 - Realizar Check-out");
            System.out.println("5 - Listar Reservas Ativas");
            System.out.println("6 - Demonstração");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {

                case 1:
                    System.out.print("Nome do hospede: ");
                    String nome = sc.nextLine();
                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = sc.nextLine();

                    Hospede hospede = new Hospede(nome, cpf, telefone);
                    System.out.println("hospede cadastrado com sucesso!");
                    break;

                case 2:
                    System.out.print("Número do quarto: ");
                    int numero = sc.nextInt();
                    System.out.print("Valor da diária: ");
                    double valor = sc.nextDouble();
                    System.out.print("Tipo (1-Simples, 2-Luxo): ");
                    int tipo = sc.nextInt();
                    sc.nextLine();

                    Quarto quarto = null;

                    if (tipo == 1) {
                        System.out.print("Ventilador? (true/false): ");
                        boolean ventilador = sc.nextBoolean();
                        quarto = new QuartoSimples(numero, valor, ventilador);

                    } else if (tipo == 2) {
                        System.out.print("Varanda? (true/false): ");
                        boolean varanda = sc.nextBoolean();
                        quarto = new QuartoLuxo(numero, valor, varanda);

                    } else {
                        System.out.println("Tipo inválido.");
                    }

                    if (quarto != null) {
                        System.out.println("Quarto cadastrado com sucesso!");
                    }
                    break;

                case 3:
                    System.out.print("Nome do hóspede: ");
                    String nomeReserva = sc.nextLine();

                    System.out.print("Número do quarto: ");
                    int numeroQuarto = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Check-in (dd/MM/yyyy): ");
                    LocalDate checkIn = LocalDate.parse(sc.nextLine(), formatter);

                    System.out.print("Check-out (dd/MM/yyyy): ");
                    LocalDate checkOut = LocalDate.parse(sc.nextLine(), formatter);

                    Hospede hospedeReserva = new Hospede(nomeReserva, "", "");
                    Quarto quartoReserva = new QuartoSimples(numeroQuarto, 100.0, true);

                    Reserva reserva = new Reserva(hospedeReserva, quartoReserva, checkIn, checkOut);
                    hotelService.addReserva(reserva);

                    System.out.println("Reserva cadastrada com sucesso!");
                    break;

                case 4:
                    hotelService.listarReservasAtivas();
                    System.out.print("Índice da reserva: ");
                    int indice = sc.nextInt();
                    hotelService.realizarCheckOut(indice);
                    break;

                case 5:
                    hotelService.listarReservasAtivas();
                    break;

                case 6:
                    Hospede demo1 = new Hospede("Leonardo", "70089550650", "45998184776");
                    Hospede demo2 = new Hospede("Padilha", "00504603663", "45998184777");

                    Quarto q1 = new QuartoSimples(1, 100.0, true);
                    Quarto q2 = new QuartoLuxo(2, 200.0, true);

                    Reserva r1 = new Reserva(demo1, q1, LocalDate.now(), LocalDate.now().plusDays(1));
                    Reserva r2 = new Reserva(demo2, q2, LocalDate.now(), LocalDate.now().plusDays(2));

                    hotelService.addReserva(r1);
                    hotelService.addReserva(r2);

                    System.out.println("Demonstração criada!");
                    hotelService.listarReservasAtivas();
                    break;

                case 0:
                    executandoMenu = false;
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        }

        sc.close();
    }
}