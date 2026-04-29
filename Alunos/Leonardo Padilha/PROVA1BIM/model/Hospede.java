import service.HotelService;
import model.Hospede;
import model.Quarto;
import model.QuartoSimples;
import model.QuartoLuxo;
import model.Reserva;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HotelService hotelService = new HotelService();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        List<Hospede> hospedes = new ArrayList<>();
        List<Quarto> quartos = new ArrayList<>();

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
                    hospedes.add(hospede);

                    System.out.println("Hospede cadastrado com sucesso!");
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
                    }

                    if (quarto != null) {
                        quartos.add(quarto);
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

                    Hospede hospedeReserva = null;
                    for (Hospede hospede : hospedes) {
                        if (hospede.getNome().equalsIgnoreCase(nomeReserva)) {
                            hospedeReserva = hospede;
                            break;
                        }
                    }

                    Quarto quartoReserva = null;
                    for (Quarto quarto : quartos) {
                        if (quarto.getNumero() == numeroQuarto) {
                            quartoReserva = quarto;
                            break;
                        }
                    }

                    if (hospedeReserva == null || quartoReserva == null) {
                        System.out.println("Hóspede ou quarto não encontrado!");
                        break;
                    }

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

                    Quarto quarto1 = new QuartoSimples(1, 100.0, true);
                    Quarto quarto2 = new QuartoLuxo(2, 200.0, true);

                    quartos.add(quarto1);
                    quartos.add(quarto2);
                    hospedes.add(demo1);
                    hospedes.add(demo2);

                    Reserva r1 = new Reserva(demo1, quarto1, LocalDate.now(), LocalDate.now().plusDays(1));
                    Reserva r2 = new Reserva(demo2, quarto2, LocalDate.now(), LocalDate.now().plusDays(2));

                    hotelService.addReserva(r1);
                    hotelService.addReserva(r2);

                    System.out.println("Demonstraçao criada!");
                    hotelService.listarReservasAtivas();
                    break;

                case 0:
                    executandoMenu = false;
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção invalida!");
            }
        }

        sc.close();
    }
}