import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static Hotel hotel = new Hotel();
    static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    static Hospede[] hospedes = new Hospede[50];
    static int totalHospedes = 0;
    static Quarto[] quartos = new Quarto[50];
    static int totalQuartos = 0;

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            opcao = lerInt("Opção: ");
            switch (opcao) {
                case 1 -> cadastrarHospede();
                case 2 -> cadastrarQuarto();
                case 3 -> cadastrarReserva();
                case 4 -> realizarCheckOut();
                case 5 -> hotel.listarReservasSemCheckOut();
                case 6 -> demonstracao();
                case 0 -> System.out.println("Encerrando o sistema. Até logo!");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    static void exibirMenu() {
        System.out.println("\n");
        System.out.println("    SISTEMA DE RESERVAS HOTEL   ");
        System.out.println("================================");
        System.out.println(" 1. Cadastrar hóspede           ");
        System.out.println(" 2. Cadastrar quarto            ");
        System.out.println(" 3. Cadastrar reserva           ");
        System.out.println(" 4. Realizar check-out          ");
        System.out.println(" 5. Listar reservas ativas      ");
        System.out.println(" 6. Demonstração                ");
        System.out.println(" 0. Sair                        ");
    System.out.println    ("                                ");
    }

    
    static void cadastrarHospede() {
        System.out.println("\n--- Cadastrar Hóspede ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        hospedes[totalHospedes++] = new Hospede(nome, cpf, telefone);
        System.out.println("Hóspede cadastrado com sucesso!");
    }

    // ───────── Cadastro de quarto ─────────
    static void cadastrarQuarto() {
        System.out.println("\n--- Cadastrar Quarto ---");
        System.out.println("Tipo: 1 - Simples | 2 - Luxo");
        int tipo = lerInt("Escolha: ");
        int numero = lerInt("Número do quarto: ");
        double diaria = lerDouble("Valor da diária (R$): ");

        if (tipo == 1) {
           System.out.print("Tem ventilador? (s/n): ");
            boolean vent = scanner.nextLine().trim().equalsIgnoreCase("s");
            quartos[totalQuartos++] = new QuartoSimples(numero, diaria, vent);
        } else if (tipo == 2) {
            System.out.print("Tem varanda? (s/n): ");
            boolean var = scanner.nextLine().trim().equalsIgnoreCase("s");
            quartos[totalQuartos++] = new QuartoLuxo(numero, diaria, var);
        } else {
            System.out.println("Tipo inválido.");
            return;
        }
        System.out.println("Quarto cadastrado com sucesso!");
    }

    
    static void cadastrarReserva() {
        System.out.println("\n--- Cadastrar Reserva ---");

        if (totalHospedes == 0 || totalQuartos == 0) {
            System.out.println("Cadastre ao menos um hóspede e um quarto antes.");
            return;
        }

        System.out.println("Hóspedes disponíveis:");
        for (int i = 0; i < totalHospedes; i++)
            System.out.println("[" + i + "] " + hospedes[i]);

        int ih = lerInt("Selecione o hóspede (índice): ");
        if (ih < 0 || ih >= totalHospedes) { System.out.println("Índice inválido."); return; }

        System.out.println("Quartos disponíveis:");
        for (int i = 0; i < totalQuartos; i++) {
            System.out.print("[" + i + "] ");
            quartos[i].exibirInformacoes();
        }

        int iq = lerInt("Selecione o quarto (índice): ");
        if (iq < 0 || iq >= totalQuartos) { System.out.println("Índice inválido."); return; }

        LocalDate checkIn  = lerData("Data de check-in  (dd/MM/yyyy): ");
        LocalDate checkOut = lerData("Data de check-out (dd/MM/yyyy): ");

        if (!checkOut.isAfter(checkIn)) {
            System.out.println("Data de check-out deve ser posterior ao check-in.");
            return;
        }

        Reserva r = new Reserva(hospedes[ih], quartos[iq], checkIn, checkOut);
        hotel.adicionarReserva(r);
    }

    
    static void realizarCheckOut() {
        System.out.println("\n--- Realizar Check-out ---");
        Reserva[] reservas = hotel.getReservas();
        int total = hotel.getTotalReservas();
        boolean alguma = false;

        for (int i = 0; i < total; i++) {
            if (!reservas[i].isCheckOutRealizado()) {
                System.out.println("[" + i + "] " + reservas[i].getHospede().getNome()
                        + " | Quarto " + reservas[i].getQuarto().getNumero());
                alguma = true;
            }
        }

        if (!alguma) { System.out.println("Nenhuma reserva ativa."); return; }

        int idx = lerInt("Índice da reserva para check-out: ");
        if (idx < 0 || idx >= total || reservas[idx].isCheckOutRealizado()) {
            System.out.println("Índice inválido ou check-out já realizado.");
            return;
        }
        reservas[idx].realizarCheckOut();
        System.out.println("Check-out realizado!");
        reservas[idx].exibirDados();
    }


    static void demonstracao() {
        System.out.println("\n======= DEMONSTRAÇÃO =======");

        
        Hospede h1 = new Hospede("Ana Souza",   "111.222.333-44", "(45) 99100-0001");
        Hospede h2 = new Hospede("Carlos Lima",  "555.666.777-88", "(45) 99200-0002");

        
        QuartoSimples qs = new QuartoSimples(101, 150.00, true);
        QuartoLuxo    ql = new QuartoLuxo   (201, 350.00, true);

        
        LocalDate ci1 = LocalDate.of(2025, 6, 10);
        LocalDate co1 = LocalDate.of(2025, 6, 15);  // 5 diárias — check-out realizado
        Reserva r1 = new Reserva(h1, qs, ci1, co1);
        r1.realizarCheckOut();

        LocalDate ci2 = LocalDate.of(2025, 7,  1);
        LocalDate co2 = LocalDate.of(2025, 7,  5);  // 4 diárias — ativa
        Reserva r2 = new Reserva(h2, ql, ci2, co2);

        
        Hotel hotelDemo = new Hotel();
        hotelDemo.adicionarReserva(r1);
        hotelDemo.adicionarReserva(r2);

        System.out.println("\nDetalhes de todas as reservas criadas:");
        r1.exibirDados();
        r2.exibirDados();

        System.out.println("\nReservas ativas (sem check-out):");
        hotelDemo.listarReservasSemCheckOut();

        System.out.println("======= FIM DA DEMONSTRAÇÃO =======");
    }

    static int lerInt(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                int v = Integer.parseInt(scanner.nextLine().trim());
                return v;
            } catch (NumberFormatException e) {
                System.out.println("Digite um número inteiro válido.");
            }
        }
    }

    static double lerDouble(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Digite um valor numérico válido.");
            }
        }
    }

    static LocalDate lerData(String msg) {
        while (true) {
            System.out.print(msg);
            try {
                return LocalDate.parse(scanner.nextLine().trim(), fmt);
            } catch (DateTimeParseException e) {
                System.out.println("Formato inválido. Use dd/MM/yyyy.");
            }
        }
    }
}
