import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Hotel {
    static Scanner scan = new Scanner(System.in);
    static List<Quarto> quartos = new ArrayList<>();
    static List<User> users = new ArrayList<>();
    static controleReserva controle = new controleReserva();

    public static void main(String[] args) {
        demonstracao();
        mostrarMenu();
    }

    public static void mostrarMenu() {
        int opc;
        do {
            System.out.println("\n--- Menu do Hotel ---");
            System.out.println("Escolha uma opção: ");
            System.out.println("1 - Cadastrar Hóspede");
            System.out.println("2 - Cadastrar Quarto");
            System.out.println("3 - Realizar Reserva");
            System.out.println("4 - Realizar Check-out");
            System.out.println("5 - Listar Reservas Ativas");
            System.out.println("6 - Demonstração");
            System.out.println("0 - Sair");

            opc = lerInt();
            switch (opc) {
                case 1: cadastrarUser(); break;
                case 2: cadastrarQuarto(); break;
                case 3: realizarReserva(); break;
                case 4: realizarCheckOut(); break;
                case 5: listarReservas(); break;
                case 6: demonstracao(); break;
                case 0: System.out.println("Sistema encerrado! Obrigado."); break;
                default: System.out.println("Opção inválida!"); break;
            }
        } while (opc != 0);
    }

    private static void cadastrarUser() {
        System.out.print("Nome: ");
        String nome = scan.nextLine().trim();
        System.out.print("Telefone: ");
        String telefone = scan.nextLine().trim();
        System.out.print("CPF: ");
        String cpf = scan.nextLine().trim();
        users.add(new User(nome, telefone, cpf));
        System.out.println("Hóspede cadastrado com sucesso!");
    }

    private static void cadastrarQuarto() {
        System.out.print("Número do quarto: ");
        int numero = lerInt();
        System.out.print("Valor da diária: R$ ");
        double valorDiaria = lerDouble();
        System.out.println("Tipo do quarto:");
        System.out.println("1 - Simples");
        System.out.println("2 - Luxo");
        System.out.print("Escolha: ");
        int tipo = lerInt();

        switch (tipo) {
            case 1:
                System.out.print("Tem ventilador? (S/N): ");
                boolean ventilador = scan.nextLine().trim().toUpperCase().equals("S");
                quartos.add(new QuartoSimples(numero, valorDiaria, ventilador));
                break;
            case 2:
                System.out.print("Tem varanda? (S/N): ");
                boolean varanda = scan.nextLine().trim().toUpperCase().equals("S");
                quartos.add(new QuartoLuxo(numero, valorDiaria, varanda));
                break;
            default:
                System.out.println("Tipo inválido!");
                return;
        }
        System.out.println("Quarto cadastrado com sucesso!");
    }

    private static void realizarReserva() {
        if (users.isEmpty()) {
            System.out.println("Nenhum hóspede cadastrado!");
            return;
        }
        if (quartos.isEmpty()) {
            System.out.println("Nenhum quarto cadastrado!");
            return;
        }

        System.out.println("Hóspedes disponíveis:");
        for (int i = 0; i < users.size(); i++) {
            System.out.println((i + 1) + " - " + users.get(i).getNome());
        }
        System.out.print("Escolha o hóspede (número): ");
        int indiceHospede = lerInt() - 1;
        if (indiceHospede < 0 || indiceHospede >= users.size()) {
            System.out.println("Hóspede inválido!");
            return;
        }

        System.out.println("Quartos disponíveis:");
        for (int i = 0; i < quartos.size(); i++) {
            System.out.print((i + 1) + " - ");
            quartos.get(i).mostrarResumo();
        }
        System.out.print("Escolha o quarto (número): ");
        int indiceQuarto = lerInt() - 1;
        if (indiceQuarto < 0 || indiceQuarto >= quartos.size()) {
            System.out.println("Quarto inválido!");
            return;
        }

        System.out.print("Data de check-in (AAAA-MM-DD): ");
        LocalDate checkIn = LocalDate.parse(scan.nextLine().trim());
        System.out.print("Data de check-out (AAAA-MM-DD): ");
        LocalDate checkOut = LocalDate.parse(scan.nextLine().trim());

        Reserva nova = new Reserva(users.get(indiceHospede), quartos.get(indiceQuarto), checkIn, checkOut);
        if (controle.adicionarReserva(nova)) {
            System.out.println("Reserva realizada com sucesso!");
        }
    }

    private static void realizarCheckOut() {
        controle.listarReservasAtivas();
        System.out.print("Digite o índice da reserva para check-out: ");
        int indice = lerInt();
        controle.realizarCheckOut(indice);
    }

    private static void listarReservas() {
        controle.listarReservasAtivas();
    }

    private static void demonstracao() {
        System.out.println("Demonstração do Sistema");

        users.add(new User("João Silva", "11 99999-9999", "123.456.789-00"));
        users.add(new User("Maria Santos", "11 88888-8888", "987.654.321-00"));

        quartos.add(new QuartoSimples(101, 100.0, true));
        quartos.add(new QuartoLuxo(201, 200.0, true));

        Reserva reserva1 = new Reserva(users.get(0), quartos.get(0), LocalDate.of(2026, 4, 28), LocalDate.of(2026, 4, 30));
        Reserva reserva2 = new Reserva(users.get(1), quartos.get(1), LocalDate.of(2026, 5, 1), LocalDate.of(2026, 5, 3));
        reserva2.realizarSaida();

        controle.adicionarReserva(reserva1);
        controle.adicionarReserva(reserva2);

        System.out.println("Demonstração criada! Listando reservas ativas:");
        controle.listarReservasAtivas();
    }

    private static int lerInt() {
        while (true) {
            try {
                String input = scan.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Digite um número válido: ");
            }
        }
    }

    private static double lerDouble() {
        while (true) {
            try {
                String input = scan.nextLine().trim();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.print("Digite um valor válido: ");
            }
        }
    }
}