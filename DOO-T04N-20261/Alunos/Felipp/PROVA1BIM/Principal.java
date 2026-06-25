package prova;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import prova.objetos.Checkin;
import prova.objetos.Hospede;
import prova.objetos.Luxo;
import prova.objetos.Simples;

public class Principal {

    private static final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    static ArrayList<Hospede> hospedes = new ArrayList<>();
    static ArrayList<Simples> quartosSimples = new ArrayList<>();
    static ArrayList<Luxo> quartosLuxo = new ArrayList<>();

    static final int MAX_RESERVAS = 10;
    static ArrayList<Checkin> checkins = new ArrayList<>(MAX_RESERVAS);

    public static void main(String[] args) {
        populaDados();
        menu();
    }

    private static void menu() {
        boolean sair = false;

        while (sair == false) {
            System.out.println("Bem vindo ao Sistema de Gerenciamento de Reservas de um Hotel!");

            System.out.println("[1] - Registrar Hóspedes");
            System.out.println("[2] - Listar Hóspedes");
            System.out.println("[3] - Registrar Quartos");
            System.out.println("[4] - Listar Quartos");
            System.out.println("[5] - Realizar Nova Reserva");
            System.out.println("[6] - Realizar Check-out");
            System.out.println("[7] - Listar todas as reservas com checkout não realizado");
            System.out.println("[8] - Sair");
            final int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 8) {
                sair = true;
            }

            validarOpcaoEscolhida(opcao);
        }
    }

    private static void validarOpcaoEscolhida(int opcao) {
        switch (opcao) {
            case 1:
                registrarHospede();
                break;
            case 2:
                listarHospedes();
                break;
            case 3:
                registrarQuarto();
                break;
            case 4:
                listarQuartos();
                break;
            case 5:
                realizarReserva();
                break;
            case 6:
                realizarCheckout();
                break;
            case 7:
                listarReservasSemCheckout();
                break;
            case 8:
                System.out.println("Volte sempre!");
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    private static void registrarHospede() {
        System.out.println("Iniciando registro de hospede...\n");

        System.out.println("Digite o nome do hóspede: ");
        String nome = scanner.nextLine();

        System.out.println("Digite o CPF do hóspede: ");
        String cpf = scanner.nextLine();

        System.out.println("Digite o telefone do hóspede: ");
        String telefone = scanner.nextLine();

        hospedes.add(new Hospede(nome, cpf, telefone));
        System.out.println("Hóspede registrado com sucesso!");
    }

    private static void registrarQuarto() {
        System.out.println("Iniciando registro de quarto...\n");

        System.out.println("Digite o número do quarto: ");
        final int numero = scanner.nextInt();

        System.out.println("Informe o valor da diária: ");
        final double valorDiaria = scanner.nextDouble();

        System.out.println("Digite o tipo do quarto (1 - Simples, 2 - Luxo): ");
        final int tipo = scanner.nextInt();

        if (tipo == 1) {
            System.out.println("Esse quarto simples, possui ventilador? (1 - Sim, 2 - Não): ");
            final int possuiVentilador = scanner.nextInt();

            quartosSimples.add(new Simples(numero, valorDiaria, possuiVentilador == 1));
            System.out.println("Quarto simples registrado com sucesso!");
        } else if (tipo == 2) {
            System.out.println("Esse quarto de luxo, possui varanda? (1 - Sim, 2 - Não): ");
            final int possuiVaranda = scanner.nextInt();

            quartosLuxo.add(new Luxo(numero, valorDiaria, possuiVaranda == 1));
            System.out.println("Quarto luxo registrado com sucesso!");
        } else {
            System.out.println("Tipo de quarto inválido!");
            registrarQuarto();
            return;
        }

        System.out.println("Quarto registrado com sucesso!");
    }

    private static void realizarReserva() {
        if(hospedes.isEmpty() || (quartosSimples.isEmpty() && quartosLuxo.isEmpty())) {
            System.out.println("Não é possível realizar a reserva, pois não há hóspedes ou quartos registrados.");
            return;
        }

        if(checkins.size() >= MAX_RESERVAS) {
            System.out.println("Não é possível realizar a reserva, pois o número máximo de reservas foi atingido.");
            return;
        }
        
        System.out.println("Iniciando reserva...");

        System.out.println("Escolha o hóspede para a reserva: ");
        listarHospedes();
        final int indiceHospede = scanner.nextInt() - 1;
        if (indiceHospede < 0 || indiceHospede >= hospedes.size()) {
            System.out.println("Hóspede inválido!");
            realizarReserva();
            return;
        }
        final Hospede hospede = hospedes.get(indiceHospede);
        scanner.nextLine();

        System.out.println("Digite a data de check-in (dd/MM/yyyy hh:mm): ");
        String dataCheckin = scanner.nextLine();
        LocalDateTime dataCheckinFormatado = LocalDateTime.parse(dataCheckin, formatter);

        System.out.println("Digite a data de check-out (dd/MM/yyyy hh:mm): ");
        String dataCheckout = scanner.nextLine();
        LocalDateTime dataCheckoutFormatado = LocalDateTime.parse(dataCheckout, formatter);

        System.out.println("Qual a quantidade de diárias para a reserva? ");
        int quantidadeDiarias = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Escolha o quarto para a reserva: ");
        listarQuartos();
        System.out.println("Qual tipo de quarto deseja reservar? (1 - Simples, 2 - Luxo): ");
        final int tipoQuarto = scanner.nextInt();
        scanner.nextLine();
        if (tipoQuarto == 1) {
            System.out.println("Escolha o quarto simples para a reserva: ");
            final int indiceQuartoSimples = scanner.nextInt() - 1;

            final Simples quartoSimples = quartosSimples.get(indiceQuartoSimples);
            checkins.add(new Checkin(hospede, quartoSimples, dataCheckinFormatado, dataCheckoutFormatado, quantidadeDiarias));
        } else if (tipoQuarto == 2) {
            System.out.println("Escolha o quarto de luxo para a reserva: ");
            final int indiceQuartoLuxo = scanner.nextInt() - 1;

            final Luxo quartoLuxo = quartosLuxo.get(indiceQuartoLuxo);
            checkins.add(new Checkin(hospede, quartoLuxo, dataCheckinFormatado, dataCheckoutFormatado, quantidadeDiarias));
        } else {
            System.out.println("Tipo de quarto inválido!");
            realizarReserva();
            return;
        }

        System.out.println("Reserva realizada com sucesso!");
    }

    private static void realizarCheckout() {
        if(checkins.isEmpty()) {
            System.out.println("Nenhuma reserva registrada.");
            return;
        }

        System.out.println("Iniciando check-out...");

        listarReservasSemCheckout();
        System.out.println("Escolha a reserva para realizar o check-out: ");
        final int indiceReserva = scanner.nextInt() - 1;
        if (indiceReserva < 0 || indiceReserva >= checkins.size()) {
            System.out.println("Reserva inválida!");
            realizarCheckout();
            return;
        }

        Checkin checkin = checkins.get(indiceReserva);
        if(checkin.getSituacaoCheckout()) {
            System.out.println("Essa reserva já foi finalizada!");
            realizarCheckout();
            return;
        }

        checkin.realizarCheckout();
        System.out.println("Check-out realizado com sucesso!");
    }

    private static void listarReservasSemCheckout() {
        System.out.println("\nListando reservas sem checkout...");

        int contadorCheckins = 0;
        for (Checkin checkin : checkins) {
            if (!checkin.getSituacaoCheckout()) {
                contadorCheckins++;
                System.out.println("---- RESERVA SEM CHECKIN " + contadorCheckins + " ----");
                checkin.listarCheckin();
            }
        }

        System.out.println("");
    }

    private static void listarQuartos() {
        System.out.println("\n\nListando quartos...");

        System.out.println("---- QUARTOS SIMPLES");
        if(quartosSimples.isEmpty()) {
            System.out.println("Nenhum quarto simples registrado.");
        }
        for (Simples quarto : quartosSimples) {
            quarto.listarQuarto();
        }

        System.out.println("---- QUARTOS LUXO:");
        if(quartosLuxo.isEmpty()) {
            System.out.println("Nenhum quarto luxo registrado.");
        }
        for (Luxo quarto : quartosLuxo) {
            quarto.listarQuarto();
        }

        System.out.println("");
    }

    private static void listarHospedes() {
        System.out.println("\nListando hóspedes...");

        int contadorHospede = 0;
        for (Hospede hospede : hospedes) {
            contadorHospede++;
            System.out.printf("%d. ", contadorHospede);
            hospede.listarHospede();
        }
    }

    private static void populaDados() {
        System.out.println("Populando dados...");

        hospedes.add(new Hospede("João Silva", "123.456.789-00", "(11) 98765-4321"));
        hospedes.add(new Hospede("Maria Oliveira", "987.654.321-00", "(21) 91234-5678"));

        quartosSimples.add(new Simples(101, 150.0, true));
        quartosSimples.add(new Simples(102, 120.0, false));

        quartosLuxo.add(new Luxo(201, 300.0, true));
        quartosLuxo.add(new Luxo(202, 250.0, false));

        checkins.add(new Checkin(hospedes.get(0), quartosSimples.get(0), LocalDateTime.now(), LocalDateTime.now().plusDays(3), 3));
        checkins.add(new Checkin(hospedes.get(1), quartosLuxo.get(0), LocalDateTime.now(), LocalDateTime.now().plusDays(2), 2));

        checkins.get(0).realizarCheckout();
    }
}
