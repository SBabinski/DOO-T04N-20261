
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static Hotel hotel = new Hotel();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = lerInteiro(scanner);

            switch (opcao) {
                case 1:
                    cadastrarHospede(scanner);
                    break;
                case 2:
                    cadastrarQuarto(scanner);
                    break;
                case 3:
                    cadastrarReserva(scanner);
                    break;
                case 4:
                    realizarCheckout(scanner);
                    break;
                case 5:
                    listarReservasAtivas();
                    break;
                case 6:
                    demonstracao();
                    break;
                case 7:
                    System.out.println("Encerrando o sistema. Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 7);

        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\n=== Sistema de Gerenciamento de Reservas do Hotel ===");
        System.out.println("[1] - Cadastrar hóspede");
        System.out.println("[2] - Cadastrar quarto");
        System.out.println("[3] - Cadastrar reserva");
        System.out.println("[4] - Realizar check-out");
        System.out.println("[5] - Listar reservas sem check-out");
        System.out.println("[6] - Demonstração");
        System.out.println("[7] - Sair");
    }

    private static void cadastrarHospede(Scanner scanner) {
        System.out.print("Nome do hóspede: ");
        String nome = scanner.nextLine();
        System.out.print("CPF do hóspede: ");
        String cpf = scanner.nextLine();
        System.out.print("Telefone do hóspede: ");
        String telefone = scanner.nextLine();

        Hospede hospede = new Hospede(nome, cpf, telefone);
        hotel.adicionarHospede(hospede);
        System.out.println("Hóspede cadastrado com sucesso!");
    }

    private static void cadastrarQuarto(Scanner scanner) {
        System.out.println("[1] - Quarto Simples");
        System.out.println("[2] - Quarto Luxo");
        System.out.print("Escolha o tipo de quarto: ");
        int tipo = lerInteiro(scanner);

        System.out.print("Número do quarto: ");
        int numero = lerInteiro(scanner);
        System.out.print("Valor da diária: ");
        double diaria = lerDouble(scanner);

        switch (tipo) {
            case 1:
                System.out.print("Possui ventilador? (s/n): ");
                boolean ventilador = lerSimOuNao(scanner);
                hotel.adicionarQuarto(new QuartoSimples(numero, diaria, ventilador));
                break;
            case 2:
                System.out.print("Possui varanda? (s/n): ");
                boolean varanda = lerSimOuNao(scanner);
                hotel.adicionarQuarto(new QuartoLuxo(numero, diaria, varanda));
                break;
            default:
                System.out.println("Tipo de quarto inválido.");
                return;
        }

        System.out.println("Quarto cadastrado com sucesso!");
    }

    private static void cadastrarReserva(Scanner scanner) {
        if (hotel.getHospedes().isEmpty()) {
            System.out.println("Não há hóspedes cadastrados. Cadastre um hóspede primeiro.");
            return;
        }

        if (hotel.getQuartos().isEmpty()) {
            System.out.println("Não há quartos cadastrados. Cadastre um quarto primeiro.");
            return;
        }

        System.out.println("Selecione o hóspede:");
        List<Hospede> hospedes = hotel.getHospedes();
        for (int i = 0; i < hospedes.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + hospedes.get(i));
        }
        System.out.print("Opção: ");
        int indiceHospede = lerInteiro(scanner) - 1;
        if (indiceHospede < 0 || indiceHospede >= hospedes.size()) {
            System.out.println("Hóspede inválido.");
            return;
        }

        System.out.println("Selecione o quarto:");
        List<Quarto> quartos = hotel.getQuartos();
        for (int i = 0; i < quartos.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + quartos.get(i));
        }
        System.out.print("Opção: ");
        int indiceQuarto = lerInteiro(scanner) - 1;
        if (indiceQuarto < 0 || indiceQuarto >= quartos.size()) {
            System.out.println("Quarto inválido.");
            return;
        }

        LocalDate checkIn = lerData(scanner, "Data do check-in (dd/MM/yyyy): ");
        LocalDate checkOut = lerData(scanner, "Data do check-out (dd/MM/yyyy): ");

        try {
            Reserva reserva = new Reserva(hospedes.get(indiceHospede), quartos.get(indiceQuarto), checkIn, checkOut);
            if (hotel.adicionarReserva(reserva)) {
                System.out.println("Reserva cadastrada com sucesso!");
            } else {
                System.out.println("Não foi possível cadastrar a reserva: limite de 10 reservas atingido.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro ao cadastrar reserva: " + e.getMessage());
        }
    }

    private static void realizarCheckout(Scanner scanner) {
        List<Reserva> reservasAtivas = hotel.listarReservasAtivas();
        if (reservasAtivas.isEmpty()) {
            System.out.println("Não há reservas ativas para realizar check-out.");
            return;
        }

        System.out.println("Selecione a reserva para check-out:");
        for (int i = 0; i < reservasAtivas.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + reservasAtivas.get(i).getResumo());
        }
        System.out.print("Opção: ");
        int escolha = lerInteiro(scanner) - 1;
        if (escolha < 0 || escolha >= reservasAtivas.size()) {
            System.out.println("Reserva inválida.");
            return;
        }

        reservasAtivas.get(escolha).realizarCheckout();
        System.out.println("Check-out realizado com sucesso.");
    }

    private static void listarReservasAtivas() {
        List<Reserva> reservasAtivas = hotel.listarReservasAtivas();
        if (reservasAtivas.isEmpty()) {
            System.out.println("Não há reservas sem check-out.");
            return;
        }

        System.out.println("\n=== Reservas sem check-out ===");
        for (Reserva reserva : reservasAtivas) {
            System.out.println(reserva);
            System.out.println("---------------------------");
        }
    }

    private static void demonstracao() {
        Hospede hospede1 = new Hospede("Ana Silva", "123.456.789-00", "(11) 99999-0001");
        Hospede hospede2 = new Hospede("Carlos Souza", "987.654.321-00", "(11) 98888-0002");

        QuartoSimples quartoSimples = new QuartoSimples(101, 150.00, true);
        QuartoLuxo quartoLuxo = new QuartoLuxo(201, 320.00, true);

        hotel.adicionarHospede(hospede1);
        hotel.adicionarHospede(hospede2);
        hotel.adicionarQuarto(quartoSimples);
        hotel.adicionarQuarto(quartoLuxo);

        Reserva reserva1 = new Reserva(hospede1, quartoSimples, LocalDate.of(2026, 5, 10), LocalDate.of(2026, 5, 13));
        reserva1.realizarCheckout();
        Reserva reserva2 = new Reserva(hospede2, quartoLuxo, LocalDate.of(2026, 5, 14), LocalDate.of(2026, 5, 18));

        if (!hotel.adicionarReserva(reserva1)) {
            System.out.println("Não foi possível adicionar a reserva de demonstração 1.");
        }
        if (!hotel.adicionarReserva(reserva2)) {
            System.out.println("Não foi possível adicionar a reserva de demonstração 2.");
        }

        System.out.println("Demonstração concluída. Reservas ativas:");
        listarReservasAtivas();
    }

    private static int lerInteiro(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.print("Digite um número válido: ");
        }
        int valor = scanner.nextInt();
        scanner.nextLine();
        return valor;
    }

    private static double lerDouble(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            scanner.nextLine();
            System.out.print("Digite um valor numérico válido: ");
        }
        double valor = scanner.nextDouble();
        scanner.nextLine();
        return valor;
    }

    private static boolean lerSimOuNao(Scanner scanner) {
        String resposta = scanner.nextLine().trim().toLowerCase();
        while (!resposta.equals("s") && !resposta.equals("n")) {
            System.out.print("Digite 's' para sim ou 'n' para não: ");
            resposta = scanner.nextLine().trim().toLowerCase();
        }
        return resposta.equals("s");
    }

    private static LocalDate lerData(Scanner scanner, String mensagem) {
        System.out.print(mensagem);
        while (true) {
            try {
                String texto = scanner.nextLine();
                return LocalDate.parse(texto, FORMATO_DATA);
            } catch (DateTimeParseException e) {
                System.out.print("Data inválida. Digite no formato dd/MM/yyyy: ");
            }
        }
    }
}
