import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SistemaHotel {
    private List<Hospede> hospedes = new ArrayList<>();
    private List<Quarto> quartos = new ArrayList<>();
    private List<Reserva> reservas = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private final int MAX_RESERVAS = 10;

    public static void main(String[] args) {
        SistemaHotel sistema = new SistemaHotel();
        sistema.menu();
    }

    public void menu() {
        int opcao;
        do {
            System.out.println("\n=== Sistema de Gerenciamento de Reservas de Hotel ===");
            System.out.println("[1] Cadastrar Hóspede");
            System.out.println("[2] Cadastrar Quarto Simples");
            System.out.println("[3] Cadastrar Quarto Luxo");
            System.out.println("[4] Cadastrar Reserva");
            System.out.println("[5] Realizar Check-out");
            System.out.println("[6] Listar Reservas sem Check-out");
            System.out.println("[7] Demonstraçao");
            System.out.println("[8] Sair");
            System.out.print("Escolha uma opçao: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    registrarHospede();
                    break;
                case 2:
                    registrarQuartoSimples();
                    break;
                case 3:
                    registrarQuartoLuxo();
                    break;
                case 4:
                    fazerReserva();
                    break;
                case 5:
                    realizarCheckOut();
                    break;
                case 6:
                    listarReservasSemCheckOut();
                    break;
                case 7:
                    demonstracao();
                    break;
                case 8:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 8);
    }

    private void registrarHospede() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        hospedes.add(new Hospede(nome, cpf, telefone));
        System.out.println("Hóspede registrado com sucesso!");
    }

    private void registrarQuartoSimples() {
        System.out.print("Número do quarto: ");
        int numero = scanner.nextInt();
        System.out.print("Valor da diária: ");
        double valor = scanner.nextDouble();
        System.out.print("Tem ventilador? (true/false): ");
        boolean ventilador = scanner.nextBoolean();
        quartos.add(new QuartoSimples(numero, valor, ventilador));
        System.out.println("Quarto simples registrado com sucesso!");
    }

    private void registrarQuartoLuxo() {
        System.out.print("Número do quarto: ");
        int numero = scanner.nextInt();
        System.out.print("Valor da diária: ");
        double valor = scanner.nextDouble();
        System.out.print("Tem varanda? (true/false): ");
        boolean varanda = scanner.nextBoolean();
        quartos.add(new QuartoLuxo(numero, valor, varanda));
        System.out.println("Quarto luxo registrado com sucesso!");
    }

    private void fazerReserva() {
        if (reservas.size() >= MAX_RESERVAS) {
            System.out.println("Limite de reservas atingido!");
            return;
        }
        if (hospedes.isEmpty() || quartos.isEmpty()) {
            System.out.println("Não há hóspedes ou quartos registrados!");
            return;
        }
        System.out.println("Hóspedes disponíveis:");
        for (int i = 0; i < hospedes.size(); i++) {
            System.out.println((i + 1) + ". " + hospedes.get(i).getNome());
        }
        System.out.print("Escolha o hóspede (número): ");
        int hospedeIndex = scanner.nextInt() - 1;

        System.out.println("Quartos disponíveis:");
        for (int i = 0; i < quartos.size(); i++) {
            System.out.print((i + 1) + ". ");
            quartos.get(i).exibirInformacoes();
        }
        System.out.print("Escolha o quarto (número): ");
        int quartoIndex = scanner.nextInt() - 1;

        System.out.print("Data de Check-in (dia mês ano): ");
        int diaIn = scanner.nextInt();
        int mesIn = scanner.nextInt();
        int anoIn = scanner.nextInt();
        LocalDate checkIn = LocalDate.of(anoIn, mesIn, diaIn);

        System.out.print("Data de Check-out (dia mês ano): ");
        int diaOut = scanner.nextInt();
        int mesOut = scanner.nextInt();
        int anoOut = scanner.nextInt();
        LocalDate checkOut = LocalDate.of(anoOut, mesOut, diaOut);

        if (checkOut.isBefore(checkIn) || checkOut.isEqual(checkIn)) {
            System.out.println("Data de check-out deve ser posterior ao check-in!");
            return;
        }

        if (hospedeIndex >= 0 && hospedeIndex < hospedes.size() && quartoIndex >= 0 && quartoIndex < quartos.size()) {
            reservas.add(new Reserva(hospedes.get(hospedeIndex), quartos.get(quartoIndex), checkIn, checkOut));
            System.out.println("Reserva cadastrada com sucesso!");
        } else {
            System.out.println("Seleção inválida!");
        }
    }

    private void realizarCheckOut() {
        List<Reserva> semCheckOut = reservas.stream().filter(r -> !r.isCheckOutRealizado()).collect(Collectors.toList());
        if (semCheckOut.isEmpty()) {
            System.out.println("Não há reservas sem check-out.");
            return;
        }
        System.out.println("Reservas sem check-out:");
        for (int i = 0; i < semCheckOut.size(); i++) {
            System.out.println((i + 1) + ". " + semCheckOut.get(i));
        }
        System.out.print("Escolha a reserva para check-out (número): ");
        int index = scanner.nextInt() - 1;
        if (index >= 0 && index < semCheckOut.size()) {
            semCheckOut.get(index).setCheckOutRealizado(true);
            System.out.println("Check-out realizado com sucesso!");
        } else {
            System.out.println("Seleção inválida!");
        }
    }

    private void listarReservasSemCheckOut() {
        List<Reserva> semCheckOut = reservas.stream().filter(r -> !r.isCheckOutRealizado()).collect(Collectors.toList());
        if (semCheckOut.isEmpty()) {
            System.out.println("Não há reservas sem check-out.");
        } else {
            System.out.println("Reservas sem check-out:");
            for (Reserva r : semCheckOut) {
                System.out.println(r);
            }
        }
    }

    private void demonstracao() {
        hospedes.add(new Hospede("Wesley", "12345678901", "11987654321"));
        hospedes.add(new Hospede("Martins", "09876543210", "11876543210"));


        quartos.add(new QuartoSimples(101, 100.0, true));

        quartos.add(new QuartoLuxo(201, 200.0, true));

        LocalDate checkIn1 = LocalDate.of(2026, 4, 28);
        LocalDate checkOut1 = LocalDate.of(2026, 4, 30);
        reservas.add(new Reserva(hospedes.get(0), quartos.get(0), checkIn1, checkOut1));

        LocalDate checkIn2 = LocalDate.of(2026, 5, 1);
        LocalDate checkOut2 = LocalDate.of(2026, 5, 3);
        reservas.add(new Reserva(hospedes.get(1), quartos.get(1), checkIn2, checkOut2));
reservas.get(0).setCheckOutRealizado(true);

        System.out.println("Demonstraçao executada com sucesso!");
        System.out.println("Hóspedes, quartos e reservas foram criados.");
    }
}