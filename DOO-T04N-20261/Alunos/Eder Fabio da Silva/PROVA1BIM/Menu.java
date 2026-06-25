
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    // atributos.
    private final Scanner sc = new Scanner(System.in);
    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    // listas para armazenar os dados.
    private final ArrayList<Hospede> hospedes = new ArrayList<>();
    private final ArrayList<Quartos> quartos = new ArrayList<>();
    private final Hotel hotel = new Hotel();
    // método para exibir o menu e processar as opções.
    public void executar() {
        int opcao;
        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Cadastrar hóspede");
            System.out.println("2. Cadastrar quarto");
            System.out.println("3. Cadastrar reserva");
            System.out.println("4. Realizar check-out");
            System.out.println("5. Listar reservas sem check-out");
            System.out.println("6. Demonstraçao");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = Integer.parseInt(sc.nextLine());

            switch (opcao) {
                case 1 ->
                    cadastrarHospede();
                case 2 ->
                    cadastrarQuarto();
                case 3 ->
                    cadastrarReserva();
                case 4 ->
                    realizarCheckout();
                case 5 ->
                    hotel.listarReservasAtivas();
                case 6 ->
                    Demonstracao.executar(hotel);
                case 0 ->
                    System.out.println("Encerrado.");
                default ->
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        sc.close();
    }
    // métodos para cada opção do menu.
    private void cadastrarHospede() {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Telefone: ");
        String telefone = sc.nextLine();

        hospedes.add(new Hospede(nome, cpf, telefone));
        System.out.println("Hóspede cadastrado.");
    }
    // método para cadastrar um quarto, solicitando os dados necessários.
    private void cadastrarQuarto() {
        System.out.print("Tipo (1-Simples / 2-Luxo): ");
        int tipo = Integer.parseInt(sc.nextLine());

        System.out.print("Número do quarto: ");
        int numero = Integer.parseInt(sc.nextLine());

        System.out.print("Valor da diária: ");
        double preco = Double.parseDouble(sc.nextLine());

        if (tipo == 1) {
            System.out.print("Tem ventilador? (true/false): ");
            boolean ventilador = Boolean.parseBoolean(sc.nextLine());
            quartos.add(new QuartoSimples(numero, preco, ventilador));
            System.out.println("Quarto simples cadastrado.");
        } else if (tipo == 2) {
            System.out.print("Tem varanda? (true/false): ");
            boolean varanda = Boolean.parseBoolean(sc.nextLine());
            quartos.add(new QuartoLuxo(numero, preco, varanda));
            System.out.println("Quarto luxo cadastrado.");
        } else {
            System.out.println("Tipo inválido.");
        }
    }
    // método para cadastrar uma reserva, solicitando os dados necessários e associando o hóspede e o quarto.
    private void cadastrarReserva() {
        if (hospedes.isEmpty() || quartos.isEmpty()) {
            System.out.println("Cadastre ao menos 1 hóspede e 1 quarto antes.");
            return;
        }

        System.out.println("Hóspedes:");
        for (int i = 0; i < hospedes.size(); i++) {
            System.out.println((i+1) + " - " + hospedes.get(i).getNome());
        }
        System.out.print("Escolha o índice do hóspede: ");
        int idxHospede = Integer.parseInt(sc.nextLine()) - 1;

        System.out.println("Quartos:");
        for (int i = 0; i < quartos.size(); i++) {
            System.out.print((i+1) + " - ");
            quartos.get(i).exibirDetalhes();
        }
        System.out.print("Escolha o índice do quarto: ");
        int idxQuarto = Integer.parseInt(sc.nextLine()) - 1;

        System.out.print("Check-in (dd/MM/yyyy): ");
        LocalDate checkIn = LocalDate.parse(sc.nextLine(), fmt);

        System.out.print("Check-out (dd/MM/yyyy): ");
        LocalDate checkOut = LocalDate.parse(sc.nextLine(), fmt);

        Reserva reserva = new Reserva(hospedes.get(idxHospede), quartos.get(idxQuarto), checkIn, checkOut);
        boolean adicionou = hotel.adicionarReserva(reserva);
        if (adicionou) {
            //System.out.println("Reserva cadastrada com sucesso.");
            reserva.exibirDados();
        } 
    }
    // método para realizar o check-out de uma reserva, solicitando o índice da reserva.
    private void realizarCheckout() {
        hotel.listarTodasReservas();
        System.out.print("Informe o índice da reserva para check-out: ");
        int indice = Integer.parseInt(sc.nextLine());
        hotel.realizarCheckout(indice);
    }
}
