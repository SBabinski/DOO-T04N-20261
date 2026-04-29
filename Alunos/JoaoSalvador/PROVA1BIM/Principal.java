package hotel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Hotel hotel = new Hotel();

        ArrayList<Hospede> hospedes = new ArrayList<>();
        ArrayList<Quarto> quartos = new ArrayList<>();

        boolean sair = false;

        while (!sair) {

            System.out.println("\n===== MENU HOTEL =====\n");
            System.out.println("[1] - Cadastrar hospede");
            System.out.println("[2] - Cadastrar quarto");
            System.out.println("[3] - Cadastrar reserva");
            System.out.println("[4] - Realizar check-out");
            System.out.println("[5] - Listar reservas ativas");
            System.out.println("[6] - Demonstracao");
            System.out.println("[7] - Sair");
            System.out.print("\nEscolha: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                // ── Cadastrar hóspede ─────────────────────────────
                case 1:
                    System.out.println("\n===== CADASTRO DE HOSPEDES =====\n");

                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();

                    System.out.print("Telefone: ");
                    String telefone = scanner.nextLine();

                    Hospede hospede = new Hospede(nome, cpf, telefone);
                    hospedes.add(hospede);

                    System.out.println("Hospede cadastrado!");
                    break;

                // ── Cadastrar quarto ──────────────────────────────
                case 2:
                    System.out.println("\n===== CADASTRO DE QUARTOS =====\n");

                    System.out.println("[1] Simples \n [2] Luxo");
                    int tipo = scanner.nextInt();

                    System.out.print("Numero do quarto: ");
                    int numero = scanner.nextInt();

                    System.out.print("Valor da diaria: ");
                    double valor = scanner.nextDouble();

                    if (tipo == 1) {
                        System.out.print("Tem ventilador? (true/false): ");
                        boolean ventilador = scanner.nextBoolean();
                        quartos.add(new QuartoSimples(numero, valor, ventilador));
                    } else {
                        System.out.print("Tem varanda? (true/false): ");
                        boolean varanda = scanner.nextBoolean();
                        quartos.add(new QuartoLuxo(numero, valor, varanda));
                    }

                    System.out.println("Quarto cadastrado!");
                    break;

                // ── Cadastrar reserva ─────────────────────────────
                case 3:

                    if (hospedes.isEmpty() || quartos.isEmpty()) {
                        System.out.println("Cadastre hospedes e quartos primeiro!");
                        break;
                    }

                    System.out.println("\n===== CADASTRO DE RESERVAS =====\n");

                    System.out.println("Escolha o hospede:");
                    for (int i = 0; i < hospedes.size(); i++) {
                        System.out.println(i + " - " + hospedes.get(i).getNome());
                    }
                    int h = scanner.nextInt();

                    System.out.println("Escolha o quarto:");
                    for (int i = 0; i < quartos.size(); i++) {
                        System.out.println(i + " - Quarto #" + quartos.get(i).getNumero());
                    }
                    int q = scanner.nextInt();

                    System.out.print("Coloque o check-in (AAAA-MM-DD): ");
                    LocalDate checkIn = LocalDate.parse(scanner.next());

                    System.out.print("Coloque o check-out (AAAA-MM-DD): ");
                    LocalDate checkOut = LocalDate.parse(scanner.next());

                    Reserva reserva = new Reserva(hospedes.get(h), quartos.get(q), checkIn, checkOut);

                    hotel.adicionarReserva(reserva);
                    break;

                // ── Realizar checkout ─────────────────────────────
                case 4:
                    System.out.println("\n===== REALIZACAO DE CHECKOUT =====\n");

                    System.out.println("Reservas sem checkout realizado:");
                    ArrayList<Reserva> lista = Hotel.getReserva();

                    for (int i = 0; i < lista.size(); i++) {
                        System.out.println(i + " - " + lista.get(i).calcularTotal());
                    }

                    System.out.print("Escolha a reserva para fazer o checkout: ");
                    int r = scanner.nextInt();

                    lista.get(r).realizarCheckout();

                    System.out.println("Check-out realizado!");
                    break;

                // ── Listar reservas ativas ────────────────────────
                case 5:
                    hotel.listarReservasAtivas();
                    break;

                // ── Demonstração ──────────────────────────────────
                case 6:

                    System.out.println("\n===== DEMONSTRACAO =====\n");

                    Hospede h1 = new Hospede("Joao", "111", "9999");
                    Hospede h2 = new Hospede("Maria", "222", "8888");

                    Quarto q1 = new QuartoSimples(101, 100, true);
                    Quarto q2 = new QuartoLuxo(202, 300, true);

                    Reserva r1 = new Reserva(h1, q1,
                            LocalDate.of(2026, 4, 20),
                            LocalDate.of(2026, 4, 25));

                    r1.realizarCheckout();

                    Reserva r2 = new Reserva(h2, q2,
                            LocalDate.of(2026, 4, 26),
                            LocalDate.of(2026, 4, 30));

                    hotel.adicionarReserva(r1);
                    hotel.adicionarReserva(r2);

                    hotel.listarReservasAtivas();
                    break;

                // ── Sair ──────────────────────────────────────────
                case 7:
                    sair = true;
                    break;

                default:
                    System.out.println("Opcao invalida!");
            }
        }

        scanner.close();
    }
}