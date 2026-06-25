package prova01;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        
        List<Hospede> hospedesCadastrados = new ArrayList<>();
        List<Quarto> quartosCadastrados = new ArrayList<>();

        
        // 1. PRÉ-DEFINIÇÃO DE DADOS
        hospedesCadastrados.add(new Hospede("Eduardo Tenfen", "111.111.111-11", "45 99911-1111"));
        hospedesCadastrados.add(new Hospede("Kurama", "999.999.999-99", "45 99999-9999"));
        hospedesCadastrados.add(new Hospede("Meliodas", "777.777.777-77", "45 99977-7777"));

        quartosCadastrados.add(new QuartoSimples(101, 120.0, true));  // Com ventilador
        quartosCadastrados.add(new QuartoSimples(102, 110.0, false)); // Sem ventilador
        quartosCadastrados.add(new QuartoLuxo(201, 300.0, true));     // Com varanda
        quartosCadastrados.add(new QuartoLuxo(202, 350.0, true));     // Com varanda

        
        // Reserva 1: Ativa (Ana no Quarto 101)
        Reserva res1 = new Reserva(hospedesCadastrados.get(0), quartosCadastrados.get(0), 
                                   LocalDate.now(), LocalDate.now().plusDays(3));
        hotel.adicionarReserva(res1);

        // Reserva 2: Com Check-out realizado (Bruno no Quarto 201)
        Reserva res2 = new Reserva(hospedesCadastrados.get(1), quartosCadastrados.get(2), 
                                   LocalDate.of(2023, 10, 1), LocalDate.of(2023, 10, 5));
        res2.realizarCheckOut(); // Não aparecerá na lista de "ativas"
        hotel.adicionarReserva(res2);

        // Reserva 3: Ativa (Carla no Quarto 202)
        Reserva res3 = new Reserva(hospedesCadastrados.get(2), quartosCadastrados.get(3), 
                                   LocalDate.now().plusDays(1), LocalDate.now().plusDays(5));
        hotel.adicionarReserva(res3);

        System.out.println(" SISTEMA INICIALIZADO COM DADOS PRÉ-DEFINIDOS PARA TESTE ");
        

        int opcao = 0;
        while (opcao != 7) {
            System.out.println("\n===== SISTEMA DE HOTEL - MENU PRINCIPAL =====");
            System.out.println("1. Cadastrar Novo Hóspede");
            System.out.println("2. Cadastrar Novo Quarto");
            System.out.println("3. Criar Nova Reserva");
            System.out.println("4. Realizar Check-out (Finalizar)");
            System.out.println("5. Listar Reservas Ativas (Sem check-out)");
            System.out.println("6. Exibir Todos os Hóspedes e Quartos");
            System.out.println("7. Sair");
            System.out.print("Escolha: ");
            
            opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    System.out.print("Nome: "); String nome = scanner.nextLine();
                    System.out.print("CPF: "); String cpf = scanner.nextLine();
                    System.out.print("Tel: "); String tel = scanner.nextLine();
                    hospedesCadastrados.add(new Hospede(nome, cpf, tel));
                    System.out.println("Hóspede cadastrado!");
                    break;

                case 2:
                    System.out.print("Tipo (1-Simples, 2-Luxo): "); int tipo = scanner.nextInt();
                    System.out.print("Número: "); int num = scanner.nextInt();
                    System.out.print("Valor Diária: "); double val = scanner.nextDouble();
                    if (tipo == 1) {
                        System.out.print("Ventilador? (1-Sim, 2-Não): ");
                        quartosCadastrados.add(new QuartoSimples(num, val, scanner.nextInt() == 1));
                    } else {
                        System.out.print("Varanda? (1-Sim, 2-Não): ");
                        quartosCadastrados.add(new QuartoLuxo(num, val, scanner.nextInt() == 1));
                    }
                    System.out.println("Quarto cadastrado!");
                    break;

                case 3:
                    System.out.println("\n--- Selecione o Hóspede ---");
                    for (int i = 0; i < hospedesCadastrados.size(); i++) 
                        System.out.println(i + " - " + hospedesCadastrados.get(i).getNome());
                    int idH = scanner.nextInt();

                    System.out.println("\n--- Selecione o Quarto ---");
                    for (int i = 0; i < quartosCadastrados.size(); i++) {
                        System.out.print(i + " - ");
                        quartosCadastrados.get(i).exibirInformacoes();
                    }
                    int idQ = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Check-in (dd/MM/yyyy): ");
                    LocalDate cin = LocalDate.parse(scanner.nextLine(), fmt);
                    System.out.print("Check-out (dd/MM/yyyy): ");
                    LocalDate cout = LocalDate.parse(scanner.nextLine(), fmt);

                    if (hotel.adicionarReserva(new Reserva(hospedesCadastrados.get(idH), 
                                                           quartosCadastrados.get(idQ), cin, cout))) {
                        System.out.println("Reserva concluída!");
                    }
                    break;

                case 4:
                    hotel.listarReservasAtivas();
                    System.out.print("\nID da Reserva para Check-out: ");
                    hotel.realizarCheckOut(scanner.nextInt());
                    break;

                case 5:
                    System.out.println("\n--- RESERVAS ATIVAS NO SISTEMA ---");
                    hotel.listarReservasAtivas();
                    break;

                case 6:
                    System.out.println("\n--- HÓSPEDES NO SISTEMA ---");
                    hospedesCadastrados.forEach(System.out::println);
                    System.out.println("\n--- QUARTOS NO SISTEMA ---");
                    quartosCadastrados.forEach(Quarto::exibirInformacoes);
                    break;

                case 7:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
        }
        scanner.close();
    }
}