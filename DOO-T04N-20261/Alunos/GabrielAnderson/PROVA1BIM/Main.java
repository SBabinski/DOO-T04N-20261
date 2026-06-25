import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static Hotel hotel = new Hotel();
    static List<Hospede> hospedes = new ArrayList<>();
    static List<Quarto> quartos = new ArrayList<>();
    static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            opcao = lerInteiro("Escolha uma opção: ");
            switch (opcao) {
                case 1 -> cadastrarHospede();
                case 2 -> cadastrarQuarto();
                case 3 -> cadastrarReserva();
                case 4 -> realizarCheckout();
                case 5 -> hotel.listarReservasAtivas();
                case 6 -> demonstracao();
                case 0 -> System.out.println("Encerrando o sistema. Até logo!");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    static void exibirMenu() {
        System.out.println("\n");
        System.out.println("    SISTEMA DE RESERVAS - HOTEL       ");
        System.out.println("");
        System.out.println("  1. Cadastrar Hóspede                ");
        System.out.println("  2. Cadastrar Quarto                 ");
        System.out.println("  3. Cadastrar Reserva                ");
        System.out.println("  4. Realizar Check-out               ");
        System.out.println("  5. Listar Reservas Ativas           ");
        System.out.println("  6. Demonstração                     ");
        System.out.println("  0. Sair                             ");
        System.out.println("");
    }

    static void cadastrarHospede() {
        System.out.println("\n--- Cadastro de Hóspede ---");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();
        hospedes.add(new Hospede(nome, cpf, telefone));
        System.out.println("Hóspede cadastrado com sucesso!");
    }

    static void cadastrarQuarto() {
        System.out.println("\n--- Cadastro de Quarto ---");
        System.out.println("Tipo: 1 - Simples | 2 - Luxo");
        int tipo = lerInteiro("Tipo: ");
        int numero = lerInteiro("Número do quarto: ");
        double diaria = lerDouble("Valor da diária: R$ ");

        if (tipo == 1) {
            int v = lerInteiro("Tem ventilador? (1-Sim / 2-Não): ");
            quartos.add(new QuartoSimples(numero, diaria, v == 1));
            System.out.println("Quarto Simples cadastrado com sucesso!");
        } else if (tipo == 2) {
            int v = lerInteiro("Tem varanda? (1-Sim / 2-Não): ");
            quartos.add(new QuartoLuxo(numero, diaria, v == 1));
            System.out.println("Quarto Luxo cadastrado com sucesso!");
        } else {
            System.out.println("Tipo inválido.");
        }
    }

    static void cadastrarReserva() {
        System.out.println("\n--- Cadastro de Reserva ---");

        if (hospedes.isEmpty()) {
            System.out.println("Nenhum hóspede cadastrado. Cadastre um hóspede primeiro.");
            return;
        }
        if (quartos.isEmpty()) {
            System.out.println("Nenhum quarto cadastrado. Cadastre um quarto primeiro.");
            return;
        }

        System.out.println("Hóspedes disponíveis:");
        for (int i = 0; i < hospedes.size(); i++)
            System.out.println((i + 1) + ". " + hospedes.get(i).getNome());

        int ih = lerInteiro("Escolha o hóspede: ") - 1;
        if (ih < 0 || ih >= hospedes.size()) { System.out.println("Índice inválido."); return; }

        System.out.println("Quartos disponíveis:");
        for (int i = 0; i < quartos.size(); i++)
            System.out.println((i + 1) + ". " + quartos.get(i));

        int iq = lerInteiro("Escolha o quarto: ") - 1;
        if (iq < 0 || iq >= quartos.size()) { System.out.println("Índice inválido."); return; }

        LocalDate checkin  = lerData("Data de check-in  (dd/MM/yyyy): ");
        LocalDate checkout = lerData("Data de check-out (dd/MM/yyyy): ");

        if (!checkout.isAfter(checkin)) {
            System.out.println("A data de check-out deve ser após o check-in.");
            return;
        }

        hotel.adicionarReserva(new Reserva(hospedes.get(ih), quartos.get(iq), checkin, checkout));
    }

    static void realizarCheckout() {
        System.out.println("\n--- Realizar Check-out ---");
        Reserva[] reservas = hotel.getReservas();
        int total = hotel.getTotalReservas();

        List<Integer> ativas = new ArrayList<>();
        for (int i = 0; i < total; i++)
            if (!reservas[i].isCheckoutRealizado()) ativas.add(i);

        if (ativas.isEmpty()) { System.out.println("Nenhuma reserva ativa."); return; }

        System.out.println("Reservas ativas:");
        for (int i = 0; i < ativas.size(); i++) {
            Reserva r = reservas[ativas.get(i)];
            System.out.println((i + 1) + ". " + r.getHospede().getNome() +
                               " | Quarto " + r.getQuarto().getNumero() +
                               " | Check-in: " + r.getCheckin());
        }

        int escolha = lerInteiro("Escolha a reserva para check-out: ") - 1;
        if (escolha < 0 || escolha >= ativas.size()) { System.out.println("Opção inválida."); return; }

        Reserva reserva = reservas[ativas.get(escolha)];
        reserva.realizarCheckout();
        System.out.println("\nCheck-out realizado com sucesso!");
        reserva.exibirDados();
    }

    static void demonstracao() {
        System.out.println("\n========== DEMONSTRAÇÃO ==========");

        Hospede h1 = new Hospede("Ana Souza",    "123.456.789-00", "(45) 99999-1111");
        Hospede h2 = new Hospede("Carlos Lima",  "987.654.321-00", "(45) 98888-2222");
        hospedes.add(h1);
        hospedes.add(h2);
        System.out.println("Hóspedes criados:");
        System.out.println(h1);
        System.out.println(h2);

        QuartoSimples qs = new QuartoSimples(101, 150.00, true);
        QuartoLuxo    ql = new QuartoLuxo   (201, 350.00, true);
        quartos.add(qs);
        quartos.add(ql);
        System.out.println("\nQuartos criados:");
        qs.exibirInformacoes();
        ql.exibirInformacoes();

        Reserva r1 = new Reserva(h1, qs, LocalDate.of(2025, 6, 1), LocalDate.of(2025, 6, 5));
        hotel.adicionarReserva(r1);
        r1.realizarCheckout();
        System.out.println("\nReserva 1 (check-out realizado):");
        r1.exibirDados();

        Reserva r2 = new Reserva(h2, ql, LocalDate.of(2025, 6, 10), LocalDate.of(2025, 6, 15));
        hotel.adicionarReserva(r2);
        System.out.println("Reserva 2 (ativa):");
        r2.exibirDados();

        hotel.listarReservasAtivas();
    }

    static int lerInteiro(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
            }
        }
    }

    static double lerDouble(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return Double.parseDouble(sc.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um valor numérico.");
            }
        }
    }

    static LocalDate lerData(String msg) {
        while (true) {
            try {
                System.out.print(msg);
                return LocalDate.parse(sc.nextLine().trim(), fmt);
            } catch (DateTimeParseException e) {
                System.out.println("Formato inválido. Use dd/MM/yyyy.");
            }
        }
    }
}