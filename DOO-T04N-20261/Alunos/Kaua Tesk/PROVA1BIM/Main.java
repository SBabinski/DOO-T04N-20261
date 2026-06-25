import java.util.Scanner; 

public class Main {

    static Scanner scanner = new Scanner(System.in);


    static Hotel hotel = new Hotel(); 
    static Hospede[] hospedes = new Hospede[10];
    static int totalHospedes = 0;
    static Quarto[] quartos = new Quarto[10];
    static int totalQuartos = 0;

    public static void main(String[] args) {
        int opcao; 

        do {
            exibirMenu(); 
            opcao = scanner.nextInt();
            scanner.nextLine(); 
         
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
                    realizarCheckOut();
                    break;
                case 5:
                    hotel.listarReservasSemCheckOut();
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

        scanner.close(); 
    }

    static void exibirMenu() {
        System.out.println("\n=====================================");
        System.out.println(" SISTEMA HOTEL TESK - MENU PRINCIPAL ");
        System.out.println("=====================================");
        System.out.println("1 - Cadastrar Hóspede");
        System.out.println("2 - Cadastrar Quarto");
        System.out.println("3 - Cadastrar Reserva");
        System.out.println("4 - Realizar Check-out");
        System.out.println("5 - Listar Reservas sem Check-out");
        System.out.println("6 - Demonstração");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    
    static void cadastrarHospede() {
        System.out.println("\n--- Cadastrar Hóspede ---");

        if (totalHospedes >= 10) {
            System.out.println("Limite de hóspedes atingido!");
            return; 
        }

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();

        Hospede novoHospede = new Hospede(nome, cpf, telefone);

        hospedes[totalHospedes] = novoHospede;
        totalHospedes++;

        System.out.println("Hóspede cadastrado com sucesso!");
    }

    static void cadastrarQuarto() {
        System.out.println("\n--- Cadastrar Quarto ---");

        if (totalQuartos >= 10) {
            System.out.println("Limite de quartos atingido!");
            return;
        }

        System.out.print("Número do quarto: ");
        int numero = scanner.nextInt();

        System.out.print("Valor da diária: R$ ");
        double valorDiaria = scanner.nextDouble();
        scanner.nextLine(); 

        System.out.println("Tipo: 1 - Simples | 2 - Luxo");
        System.out.print("Escolha: ");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        if (tipo == 1) {
    
            System.out.print("Tem ventilador? (s/n): ");
            String resp = scanner.nextLine();

            boolean temVentilador = resp.equalsIgnoreCase("s");

            quartos[totalQuartos] = new QuartoSimples(numero, valorDiaria, temVentilador);

        } else if (tipo == 2) {
         
            System.out.print("Tem varanda? (s/n): ");
            String resp = scanner.nextLine();
            boolean temVaranda = resp.equalsIgnoreCase("s");

            quartos[totalQuartos] = new QuartoLuxo(numero, valorDiaria, temVaranda);

        } else {
            System.out.println("Tipo inválido!");
            return;
        }

        totalQuartos++;
        System.out.println("Quarto cadastrado com sucesso!");
    }

    static void cadastrarReserva() {
        System.out.println("\n--- Cadastrar Reserva ---");

        if (totalHospedes == 0) {
            System.out.println("Nenhum hóspede cadastrado. Cadastre um hóspede primeiro.");
            return;
        }
        if (totalQuartos == 0) {
            System.out.println("Nenhum quarto cadastrado. Cadastre um quarto primeiro.");
            return;
        }

        System.out.println("Hóspedes disponíveis:");
        for (int i = 0; i < totalHospedes; i++) {
           
            System.out.println((i + 1) + " - " + hospedes[i].getNome());
        }
        System.out.print("Escolha o número do hóspede: ");
        int idxHospede = scanner.nextInt() - 1; 
        scanner.nextLine();

        System.out.println("Quartos disponíveis:");
        for (int i = 0; i < totalQuartos; i++) {
            System.out.println((i + 1) + " - Quarto " + quartos[i].getNumero()
                    + " (" + quartos[i].getClass().getSimpleName() + ")");
        }
        System.out.print("Escolha o número do quarto: ");
        int idxQuarto = scanner.nextInt() - 1;
        scanner.nextLine();

        System.out.print("Data de check-in (ex: 22/07/2026): ");
        String checkIn = scanner.nextLine();

        System.out.print("Data de check-out (ex: 28/07/2026): ");
        String checkOut = scanner.nextLine();

        System.out.print("Quantidade de diárias: ");
        int diarias = scanner.nextInt();
        scanner.nextLine();

        Reserva novaReserva = new Reserva(hospedes[idxHospede], quartos[idxQuarto], checkIn, checkOut, diarias);

        hotel.adicionarReserva(novaReserva);
    }

    static void realizarCheckOut() {
        System.out.println("\n--- Realizar Check-Out ---");

        int total = hotel.getTotalReservas();

        if (total == 0) {
            System.out.println("Nenhuma reserva cadastrada.");
            return;
        }

        Reserva[] reservas = hotel.getReservas();

        System.out.println("Reservas ativas:");
        boolean temAtiva = false;

        for (int i = 0; i < total; i++) {
            if (!reservas[i].isCheckOutRealizado()) {
                System.out.println((i + 1) + " - " + reservas[i].getHospede().getNome()
                        + " | Quarto " + reservas[i].getQuarto().getNumero());
                temAtiva = true;
            }
        }

        if (!temAtiva) {
            System.out.println("Nenhuma reserva ativa para realizar check-out.");
            return;
        }

        System.out.print("Digite o número da reserva para fazer check-out: ");
        int escolha = scanner.nextInt() - 1; 
        scanner.nextLine();

        if (escolha >= 0 && escolha < total && !reservas[escolha].isCheckOutRealizado()) {
            reservas[escolha].realizarCheckOut(); 
            System.out.println("Valor cobrado:");
            reservas[escolha].exibirDados(); 
        } else {
            System.out.println("Opção inválida ou check-out já realizado.");
        }
    }

    static void demonstracao() {
        System.out.println("\n===== DEMONSTRAÇÃO DO SISTEMA =====\n");

        Hospede hospede1 = new Hospede("Ana Lima Ferreira", "123.456.789-00", "(45) 99999-1111");
        Hospede hospede2 = new Hospede("Carlos Souza", "333.555.321-00", "(45) 98888-2222");

        System.out.println("Hóspedes criados:");
        hospede1.exibirDados(); 
        System.out.println();
        hospede2.exibirDados();
        System.out.println();

        QuartoSimples quartoSimples = new QuartoSimples(101, 150.00, true);

        QuartoLuxo quartoLuxo = new QuartoLuxo(301, 350.00, true);

        System.out.println("Quartos criados:");
        quartoSimples.exibirInformacoes(); 
        System.out.println();
        quartoLuxo.exibirInformacoes();   
        System.out.println();

        
        Reserva reserva1 = new Reserva(hospede1, quartoSimples, "01/06/202", "05/06/2026", 4);
        reserva1.realizarCheckOut(); 

        Reserva reserva2 = new Reserva(hospede2, quartoLuxo, "10/07/2026", "15/07/2026", 5);
        
        Hotel hotelDemo = new Hotel(); 
        hotelDemo.adicionarReserva(reserva1);
        hotelDemo.adicionarReserva(reserva2);

        System.out.println("\n--- Exibindo dados de todas as reservas criadas ---");
        reserva1.exibirDados();
        System.out.println();
        reserva2.exibirDados();

        System.out.println("\n--- Listando reservas ativas (sem check-out) ---");
        hotelDemo.listarReservasSemCheckOut();
    }
}
