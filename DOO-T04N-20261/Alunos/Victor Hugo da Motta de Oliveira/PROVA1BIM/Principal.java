import java.time.LocalDate;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Hotel hotel = new Hotel();
        int opcao = 0;

        while (opcao != 6) {
            System.out.println("\n=== SISTEMA DE RESERVAS ===");
            System.out.println("[1] - Cadastrar Hóspede (WIP)");
            System.out.println("[2] - Cadastrar Quarto (WIP)");
            System.out.println("[3] - Realizar Reserva (WIP)");
            System.out.println("[4] - Realizar Check-out");
            System.out.println("[5] - Listar Reservas Ativas");
            System.out.println("[6] - Sair");
            System.out.println("[7] - Demonstração do Sistema");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 4:
                    System.out.println("Digite o número da reserva: ");
                    int numeroReserva = scanner.nextInt();
                    hotel.realizarCheckOut(numeroReserva);
                    break;
                case 5:
                    hotel.listarAtivas();
                    break;
                case 6:
                    System.out.println("Saindo do sistema...");
                    break;
                case 7:
                    fazerDemonstracao(hotel);
                    break;
            }
        }
        
        scanner.close();
    }

    public static void fazerDemonstracao(Hotel hotel) {
        Hospede h1 = new Hospede("Victor Hugo da Motta de Oliveira", "123.456.789-00", "45991149310");
        Hospede h2 = new Hospede("Gabriel", "987.654.321-00", "45991149311");

        QuartoSimples q1 = new QuartoSimples(777, 150.0, true);
        QuartoLuxo q2 = new QuartoLuxo(888, 300.0, true);

        Reserva r1 = new Reserva(h1, q1, LocalDate.now(), LocalDate.now().plusDays(3));
        Reserva r2 = new Reserva(h2, q2, LocalDate.now(), LocalDate.now().plusDays(5));

        r1.realizarCheckOut();

        hotel.adicionarReserva(r1);
        hotel.adicionarReserva(r2);

        System.out.println("Demonstração carregada!");

        hotel.listarAtivas();
    }
}