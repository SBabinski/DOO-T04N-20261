import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    static Scanner sc     = new Scanner(System.in);
    static Hotel   hotel  = new Hotel();
    static Hospede hospedeCadastrado = null;
    static Quarto  quartoCadastrado  = null;

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n===== MENU HOTEL =====");
            System.out.println("1 - Cadastrar hóspede");
            System.out.println("2 - Cadastrar quarto");
            System.out.println("3 - Cadastrar reserva");
            System.out.println("4 - Realizar check-out");
            System.out.println("5 - Listar reservas ativas");
            System.out.println("6 - Demonstração");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt(); sc.nextLine();

            switch (opcao) {
                case 1 -> cadastrarHospede();
                case 2 -> cadastrarQuarto();
                case 3 -> cadastrarReserva();
                case 4 -> realizarCheckOut();
                case 5 -> hotel.listarSemCheckOut();
                case 6 -> demonstracao();
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    static void cadastrarHospede() {
        System.out.print("Nome: ");     String nome     = sc.nextLine();
        System.out.print("CPF: ");      String cpf      = sc.nextLine();
        System.out.print("Telefone: "); String telefone = sc.nextLine();
        hospedeCadastrado = new Hospede(nome, cpf, telefone);
        System.out.println("Hóspede cadastrado!");
    }

    static void cadastrarQuarto() {
        System.out.print("Tipo (1-Simples / 2-Luxo): ");
        int tipo = sc.nextInt(); sc.nextLine();
        System.out.print("Número do quarto: "); int num  = sc.nextInt(); sc.nextLine();
        System.out.print("Valor da diária: ");  double val = sc.nextDouble(); sc.nextLine();

        if (tipo == 1) {
            System.out.print("Tem ventilador? (1-Sim / 2-Não): ");
            boolean vent = sc.nextInt() == 1; sc.nextLine();
            quartoCadastrado = new QuartoSimples(num, val, vent);
        } else {
            System.out.print("Tem varanda? (1-Sim / 2-Não): ");
            boolean var = sc.nextInt() == 1; sc.nextLine();
            quartoCadastrado = new QuartoLuxo(num, val, var);
        }
        System.out.println("Quarto cadastrado!");
    }

    static void cadastrarReserva() {
        if (hospedeCadastrado == null || quartoCadastrado == null) {
            System.out.println("Cadastre um hóspede e um quarto primeiro.");
            return;
        }
        System.out.print("Check-in  (AAAA-MM-DD): "); LocalDate ci = LocalDate.parse(sc.nextLine());
        System.out.print("Check-out (AAAA-MM-DD): "); LocalDate co = LocalDate.parse(sc.nextLine());
        Reserva r = new Reserva(hospedeCadastrado, quartoCadastrado, ci, co);
        hotel.adicionarReserva(r);
    }

    static void realizarCheckOut() {
        hotel.listarAtivaPorIndice();
        System.out.print("Índice da reserva (0 a " + (hotel.getTotal() - 1) + "): ");
        int idx = sc.nextInt(); sc.nextLine();
        Reserva r = hotel.getReserva(idx);
        if (r != null) {
            if (r.isCheckOutRealizado()) {
                System.out.println("Este quarto já realizou o check-out.");
            } else {
                r.realizarCheckOut();
            }
        } else {
            System.out.println("Índice inválido.");
        }
    }

    static void demonstracao() {
        System.out.println("\n=== DEMONSTRAÇÃO ===");

        Hospede h1 = new Hospede("Carlos Mendes", "111.222.333-44", "(17) 99999-0001");
        Hospede h2 = new Hospede("Fernanda Costa", "555.666.777-88", "(17) 98888-0002");

        QuartoSimples qs = new QuartoSimples(101, 150.0, true);
        QuartoLuxo    ql = new QuartoLuxo   (201, 350.0, true);

        Reserva r1 = new Reserva(h1, qs, LocalDate.of(2025, 3, 10), LocalDate.of(2025, 3, 13));
        Reserva r2 = new Reserva(h2, ql, LocalDate.of(2025, 3, 15), LocalDate.of(2025, 3, 20));

        hotel.adicionarReserva(r1);
        hotel.adicionarReserva(r2);

        r1.realizarCheckOut(); 


        hotel.listarSemCheckOut(); 
    }
}
