import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static ArrayList<Hospede> hospedes = new ArrayList<>();
    static ArrayList<Quarto> quartos = new ArrayList<>();
    static Hotel hotel = new Hotel();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=== SISTEMA DE HOTEL ===");
            System.out.println("1 - Cadastrar hóspede");
            System.out.println("2 - Cadastrar quarto");
            System.out.println("3 - Cadastrar reserva");
            System.out.println("4 - Realizar check-out");
            System.out.println("5 - Listar reservas ativas");
            System.out.println("6 - Demonstração automática");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarHospede(sc);
                    break;
                case 2:
                    cadastrarQuarto(sc);
                    break;
                case 3:
                    cadastrarReserva(sc);
                    break;
                case 4:
                    realizarCheckOut(sc);
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

    public static void cadastrarHospede(Scanner sc) {
        System.out.print("Nome: ");
        String nome = sc.nextLine();

        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        hospedes.add(new Hospede(nome, cpf, telefone));
        System.out.println("Hóspede cadastrado!");
    }

    public static void cadastrarQuarto(Scanner sc) {
        System.out.println("1 - Simples");
        System.out.println("2 - Luxo");
        int tipo = sc.nextInt();

        System.out.print("Número: ");
        int numero = sc.nextInt();

        System.out.print("Valor diária: ");
        double valor = sc.nextDouble();

        if (tipo == 1) {
            System.out.print("Tem ventilador? (true/false): ");
            boolean ventilador = sc.nextBoolean();
            quartos.add(new QuartoSimples(numero, valor, ventilador));
        } else {
            System.out.print("Tem varanda? (true/false): ");
            boolean varanda = sc.nextBoolean();
            quartos.add(new QuartoLuxo(numero, valor, varanda));
        }

        System.out.println("Quarto cadastrado!");
    }

    public static void cadastrarReserva(Scanner sc) {
        if (hospedes.isEmpty() || quartos.isEmpty()) {
            System.out.println("Cadastre hóspedes e quartos primeiro!");
            return;
        }

        System.out.println("Escolha um hóspede:");
        for (int i = 0; i < hospedes.size(); i++) {
            System.out.println(i + " - " + hospedes.get(i).getNome());
        }
        int h = sc.nextInt();

        System.out.println("Escolha um quarto:");
        for (int i = 0; i < quartos.size(); i++) {
            System.out.println(i + " - Quarto " + quartos.get(i).getNumero());
        }
        int q = sc.nextInt();

        System.out.print("Ano check-in: ");
        int anoIn = sc.nextInt();
        System.out.print("Mês: ");
        int mesIn = sc.nextInt();
        System.out.print("Dia: ");
        int diaIn = sc.nextInt();

        System.out.print("Ano check-out: ");
        int anoOut = sc.nextInt();
        System.out.print("Mês: ");
        int mesOut = sc.nextInt();
        System.out.print("Dia: ");
        int diaOut = sc.nextInt();

        Reserva r = new Reserva(
                hospedes.get(h),
                quartos.get(q),
                LocalDate.of(anoIn, mesIn, diaIn),
                LocalDate.of(anoOut, mesOut, diaOut)
        );

        if (hotel.adicionarReserva(r)) {
            System.out.println("Reserva cadastrada!");
        } else {
            System.out.println("Limite de reservas atingido!");
        }
    }

    public static void realizarCheckOut(Scanner sc) {
        System.out.println("Digite o índice da reserva:");
        int i = sc.nextInt();
        hotel.realizarCheckOut(i);
        System.out.println("Check-out realizado!");
    }

    public static void demonstracao() {
        System.out.println("\n=== DEMONSTRAÇÃO ===");

        Hospede h1 = new Hospede("João", "11112365445", "9999-1111");
        Hospede h2 = new Hospede("Maria", "2221232134", "9999-2222");

        QuartoSimples qs = new QuartoSimples(1, 100, true);
        QuartoLuxo ql = new QuartoLuxo(2, 250, true);

        Reserva r1 = new Reserva(h1, qs,
                LocalDate.of(2025, 5, 1),
                LocalDate.of(2025, 5, 5));

        Reserva r2 = new Reserva(h2, ql,
                LocalDate.of(2025, 6, 1),
                LocalDate.of(2025, 6, 3));

        r1.realizarCheckOut();

        hotel.adicionarReserva(r1);
        hotel.adicionarReserva(r2);

        hotel.listarReservasAtivas();
    }
}