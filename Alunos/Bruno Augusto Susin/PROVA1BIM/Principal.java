import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {

        Hotel hotel = new Hotel();

        Hospede h1 = new Hospede("Bruno", "123", "99999999999");
        Hospede h2 = new Hospede("Amanda", "987", "12345678910");

        Quarto q1 = new Simples(564, 150, true);
        Quarto q2 = new Luxo(344, 350, true);

        Reserva r1 = new Reserva(
                h1, q1,
                LocalDate.of(2026, 4, 26),
                LocalDate.of(2026, 4, 28)
        );

        Reserva r2 = new Reserva(
                h2, q2,
                LocalDate.of(2026, 4, 27),
                LocalDate.of(2026, 4, 30)
        );

        r1.realizarCheckout();
        hotel.adicionarReserva(r1);
        hotel.adicionarReserva(r2);

        System.out.println("\n=== DEMONSTRAÇÃO ===");
        hotel.listarReservasAtivas();

        Scanner scanner = new Scanner(System.in);


        ArrayList<Hospede> hospedes = new ArrayList<>();
        ArrayList<Quarto> quartos = new ArrayList<>();

        int opcao;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Cadastrar hóspede");
            System.out.println("2 - Cadastrar quarto");
            System.out.println("3 - Cadastrar reserva");
            System.out.println("4 - Realizar checkout");
            System.out.println("5 - Listar reservas ativas");
            System.out.println("0 - Sair");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {

                case 1:
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();

                    System.out.print("Telefone: ");
                    String telefone = scanner.nextLine();

                    hospedes.add(new Hospede(nome, cpf, telefone));
                    System.out.println("Hóspede cadastrado!");
                    break;

                case 2:
                    System.out.println("1 - Simples | 2 - Luxo");
                    int tipo = scanner.nextInt();

                    System.out.print("Número do quarto: ");
                    int numero = scanner.nextInt();

                    System.out.print("Valor da diária: ");
                    double valor = scanner.nextDouble();

                    if (tipo == 1) {
                        System.out.print("Tem ventilador? (1-Sim / 2-Não): ");
                        int op = scanner.nextInt();
                        boolean ventilador = (op == 1);

                        quartos.add(new Simples(numero, valor, ventilador));
                    } else {
                        System.out.print("Tem varanda? (1-Sim / 2-Não): ");
                        int op = scanner.nextInt();
                        boolean varanda = (op == 1);

                        quartos.add(new Luxo(numero, valor, varanda));
                    }

                    System.out.println("Quarto cadastrado!");
                    break;

                case 3:
                    if (hospedes.isEmpty() || quartos.isEmpty()) {
                        System.out.println("Cadastre hóspede e quarto primeiro!");
                        break;
                    }

                    System.out.println("Escolha o hóspede:");
                    for (int i = 0; i < hospedes.size(); i++) {
                        System.out.println(i + " - " + hospedes.get(i).getNome());
                    }
                    int iHospede = scanner.nextInt();

                    System.out.println("Escolha o quarto:");
                    for (int i = 0; i < quartos.size(); i++) {
                        System.out.println(i + " - Quarto " + quartos.get(i).getNumero());
                    }
                    int iQuarto = scanner.nextInt();

                    System.out.print("Dia check-in: ");
                    int d1 = scanner.nextInt();
                    System.out.print("Mês check-in: ");
                    int m1 = scanner.nextInt();
                    System.out.print("Ano check-in: ");
                    int a1 = scanner.nextInt();

                    System.out.print("Dia check-out: ");
                    int d2 = scanner.nextInt();
                    System.out.print("Mês check-out: ");
                    int m2 = scanner.nextInt();
                    System.out.print("Ano check-out: ");
                    int a2 = scanner.nextInt();

                    Reserva r = new Reserva(
                            hospedes.get(iHospede),
                            quartos.get(iQuarto),
                            LocalDate.of(a1, m1, d1),
                            LocalDate.of(a2, m2, d2)
                    );

                    if (!hotel.adicionarReserva(r)) {
                        System.out.println("Limite de reservas atingido!");
                    } else {
                        System.out.println("Reserva cadastrada!");
                    }
                    break;

                case 4:
                    System.out.println("Reservas:");
                    for (int i = 0; i < hotel.getReservas().size(); i++) {
                        System.out.println(i + " - Reserva");
                    }

                    int iRes = scanner.nextInt();
                    hotel.getReservas().get(iRes).realizarCheckout();
                    System.out.println("Checkout realizado!");
                    break;

                case 5:
                    System.out.println("\nReservas ativas:");
                    hotel.listarReservasAtivas();
                    break;
            }

        } while (opcao != 0);

        scanner.close();
    }
}