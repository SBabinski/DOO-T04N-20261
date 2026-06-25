import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Hotel hotel = new Hotel();

    static Hospede[] hospedes = new Hospede[50];
    static int totalHospedes = 0;

    static Quarto[] quartos = new Quarto[50];
    static int totalQuartos = 0;

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n╔══════════════════════════════════════╗");
            System.out.println("║     SISTEMA DE RESERVAS DO HOTEL     ║");
            System.out.println("╠══════════════════════════════════════╣");
            System.out.println("║ 1 - Cadastrar Hóspede                ║");
            System.out.println("║ 2 - Cadastrar Quarto                 ║");
            System.out.println("║ 3 - Cadastrar Reserva                ║");
            System.out.println("║ 4 - Realizar Check-out               ║");
            System.out.println("║ 5 - Listar Reservas sem Check-out    ║");
            System.out.println("║ 6 - Demonstração                     ║");
            System.out.println("║ 0 - Sair                             ║");
            System.out.println("╚══════════════════════════════════════╝");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1:
                    cadastrarHospede();
                    break;
                case 2:
                    cadastrarQuarto();
                    break;
                case 3:
                    cadastrarReserva();
                    break;
                case 4:
                    realizarCheckout();
                    break;
                case 5:
                    hotel.listarReservasAtivas();
                    break;
                case 6:
                    demonstracao();
                    break;
                case 0:
                    System.out.println("Encerrando o sistema. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 0);
    }

    static void cadastrarHospede() {
        System.out.println("\n--- Cadastro de Hóspede ---");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        for (int i = 0; i < totalHospedes; i++) {
            if (hospedes[i].getCpf().equals(cpf)) {
                System.out.println("CPF já cadastrado. Operação cancelada.");
                return;
            }
        }

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        hospedes[totalHospedes] = new Hospede(nome, cpf, telefone);
        totalHospedes++;
        System.out.println("Hóspede cadastrado com sucesso!");
    }

    static void cadastrarQuarto() {
        System.out.println("\n--- Cadastro de Quarto ---");
        System.out.println("Tipo: 1 - Simples | 2 - Luxo");
        System.out.print("Escolha: ");
        int tipo;
        try {
            tipo = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Tipo inválido.");
            return;
        }

        System.out.print("Número do quarto: ");
        int numero;
        try {
            numero = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Número inválido.");
            return;
        }

        System.out.print("Valor da diária (R$): ");
        double valor;
        try {
            valor = Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
        } catch (NumberFormatException e) {
            System.out.println("Valor inválido.");
            return;
        }

        if (tipo == 1) {
            System.out.print("Tem ventilador? (1-Sim / 2-Não): ");
            int v;
            try {
                v = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                v = 2;
            }
            quartos[totalQuartos] = new QuartoSimples(numero, valor, v == 1);
        } else if (tipo == 2) {
            System.out.print("Tem varanda? (1-Sim / 2-Não): ");
            int v;
            try {
                v = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                v = 2;
            }
            quartos[totalQuartos] = new QuartoLuxo(numero, valor, v == 1);
        } else {
            System.out.println("Tipo inválido.");
            return;
        }
        totalQuartos++;
        System.out.println("Quarto cadastrado com sucesso!");
    }

    static void cadastrarReserva() {
        if (totalHospedes == 0 || totalQuartos == 0) {
            System.out.println("Cadastre ao menos um hóspede e um quarto antes de criar uma reserva.");
            return;
        }

        System.out.println("\n--- Cadastro de Reserva ---");
        System.out.println("Hóspedes disponíveis:");
        for (int i = 0; i < totalHospedes; i++) {
            System.out.println("[" + (i + 1) + "] " + hospedes[i].getNome() + " - CPF: " + hospedes[i].getCpf());
        }
        System.out.print("Selecione o hóspede: ");
        int idxHos;
        try {
            idxHos = Integer.parseInt(scanner.nextLine().trim()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Seleção inválida. Operação cancelada.");
            return;
        }

        if (idxHos < 0 || idxHos >= totalHospedes) {
            System.out.println("Hóspede inválido. Operação cancelada.");
            return;
        }

        System.out.println("Quartos disponíveis:");
        for (int i = 0; i < totalQuartos; i++) {
            System.out.println("[" + (i + 1) + "] " + quartos[i].toString());
        }
        System.out.print("Selecione o quarto: ");
        int idxQua;
        try {
            idxQua = Integer.parseInt(scanner.nextLine().trim()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Seleção inválida. Operação cancelada.");
            return;
        }

        if (idxQua < 0 || idxQua >= totalQuartos) {
            System.out.println("Quarto inválido. Operação cancelada.");
            return;
        }

        LocalDate checkin;
        LocalDate checkout;
        try {
            System.out.print("Data de check-in (AAAA-MM-DD): ");
            checkin = LocalDate.parse(scanner.nextLine());
            System.out.print("Data de check-out (AAAA-MM-DD): ");
            checkout = LocalDate.parse(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Data inválida! Use o formato AAAA-MM-DD (ex: 2024-01-15). Operação cancelada.");
            return;
        }

        if (!checkout.isAfter(checkin)) {
            System.out.println("A data de check-out deve ser posterior ao check-in. Operação cancelada.");
            return;
        }

        Reserva reserva = new Reserva(hospedes[idxHos], quartos[idxQua], checkin, checkout);
        hotel.adicionarReserva(reserva);
    }

    static void realizarCheckout() {
        hotel.listarTodasReservas();
        if (hotel.getTotalReservas() == 0) return;

        System.out.print("Número da reserva para check-out: ");
        int num;
        try {
            num = Integer.parseInt(scanner.nextLine().trim()) - 1;
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida.");
            return;
        }

        Reserva r = hotel.getReserva(num);
        if (r == null) {
            System.out.println("Reserva não encontrada.");
        } else {
            r.realizarCheckout();
        }
    }

    static void demonstracao() {
        System.out.println("\n========== DEMONSTRAÇÃO ==========");

        Hotel hotelDemo = new Hotel();

        Hospede hospede1 = new Hospede("Carlos Silva", "123.456.789-00", "(44) 99801-1234");
        Hospede hospede2 = new Hospede("Ana Pereira", "987.654.321-00", "(44) 99812-5678");

        System.out.println("Hóspedes criados:");
        System.out.println(hospede1);
        System.out.println(hospede2);

        QuartoSimples simples = new QuartoSimples(101, 150.00, true);
        QuartoLuxo luxo = new QuartoLuxo(201, 350.00, true);

        System.out.println("\nQuartos criados:");
        simples.exibirInformacoes();
        System.out.println();
        luxo.exibirInformacoes();

        Reserva reserva1 = new Reserva(hospede1, simples,
                LocalDate.of(2024, 5, 1), LocalDate.of(2024, 5, 4));

        Reserva reserva2 = new Reserva(hospede2, luxo,
                LocalDate.of(2024, 6, 10), LocalDate.of(2024, 6, 15));

        hotelDemo.adicionarReserva(reserva1);
        hotelDemo.adicionarReserva(reserva2);

        reserva1.realizarCheckout();

        System.out.println("\nDados da Reserva 1 (Check-out realizado):");
        reserva1.exibirDados();

        System.out.println("Dados da Reserva 2 (Ativa):");
        reserva2.exibirDados();

        System.out.println("\n>>> Reservas SEM check-out ao final da demonstração:");
        hotelDemo.listarReservasAtivas();
    }
}
