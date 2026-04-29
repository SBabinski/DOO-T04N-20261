import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Hotel hotel = new Hotel();

        int op;

        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1 - Cadastrar hóspede");
            System.out.println("2 - Cadastrar quarto");
            System.out.println("3 - Cadastrar reserva");
            System.out.println("4 - Realizar check-out");
            System.out.println("5 - Listar reservas ativas");
            System.out.println("6 - Demonstração");
            System.out.println("0 - Sair");
            System.out.print("Opção: ");
            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    hotel.cadastrarHospede(sc);
                    break;
                case 2:
                    hotel.cadastrarQuarto(sc);
                    break;
                case 3:
                    hotel.cadastrarReserva(sc);
                    break;
                case 4:
                    hotel.listarSemCheckout();
                    System.out.print("Digite o ID da reserva: ");
                    int id = sc.nextInt();
                    hotel.realizarCheckout(id);
                    break;
                case 5:
                    hotel.listarSemCheckout();
                    break;
                case 6:
                    executarDemonstracao(hotel);
                    break;
            }

        } while (op != 0);

        sc.close();
    }

    public static void executarDemonstracao(Hotel hotel) {
        System.out.println("\n--- DEMONSTRAÇÃO ---");

        Hospede h1 = new Hospede("Leticia", "123", "9999");
        Hospede h2 = new Hospede("Matheus", "456", "8888");

        Quarto q1 = new QuartoSimples(101, 100, true);
        Quarto q2 = new QuartoLuxo(201, 250, true);

        Reserva r1 = new Reserva(h1, q1, "01/05", "04/05", 3);
        Reserva r2 = new Reserva(h2, q2, "10/05", "12/05", 2);

        r1.realizarCheckout();

        hotel.adicionarReserva(r1);
        hotel.adicionarReserva(r2);

        hotel.listarSemCheckout();
    }
}