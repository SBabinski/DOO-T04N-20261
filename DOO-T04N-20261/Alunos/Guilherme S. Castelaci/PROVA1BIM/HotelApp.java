import java.util.Scanner;

public class HotelApp {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        Hotel hotel = new Hotel();

        Hospede h1 = new Hospede("Ana", "111", "9999");
        Hospede h2 = new Hospede("Carlos", "222", "8888");

        hotel.hospedes.add(h1);
        hotel.hospedes.add(h2);

        hotel.quartos.add(new QuartoSimples(101, 100, true));
        hotel.quartos.add(new QuartoLuxo(202, 300, true));

        hotel.adicionarReserva(new Reserva(h1, hotel.quartos.get(0), 1, 4));
        Reserva r2 = new Reserva(h2, hotel.quartos.get(1), 5, 8);
        r2.realizarCheckout();
        hotel.adicionarReserva(r2);

        int op;

        do {
            System.out.println("\n1 Hospede  2 Quarto Simples  3 Quarto Luxo");
            System.out.println("4 Reserva  5 Checkout  6 Listar  0 Sair");

            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1 -> cadastrarHospede(hotel);
                case 2 -> cadastrarSimples(hotel);
                case 3 -> cadastrarLuxo(hotel);
                case 4 -> fazerReserva(hotel);
                case 5 -> checkout(hotel);
                case 6 -> hotel.listarReservas();
            }

        } while (op != 0);
    }

    static void cadastrarHospede(Hotel h) {
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        if (nome.matches(".*\\d.*")) return;

        System.out.print("CPF: ");
        String cpf = sc.nextLine();

        System.out.print("Tel: ");
        String tel = sc.nextLine();

        h.hospedes.add(new Hospede(nome, cpf, tel));
    }

    static void cadastrarSimples(Hotel h) {
        int n = Integer.parseInt(sc.nextLine());
        double v = Double.parseDouble(sc.nextLine());
        boolean vent = Boolean.parseBoolean(sc.nextLine());
        h.quartos.add(new QuartoSimples(n, v, vent));
    }

    static void cadastrarLuxo(Hotel h) {
        int n = Integer.parseInt(sc.nextLine());
        double v = Double.parseDouble(sc.nextLine());
        boolean var = Boolean.parseBoolean(sc.nextLine());
        h.quartos.add(new QuartoLuxo(n, v, var));
    }

    static void fazerReserva(Hotel h) {
        int i = Integer.parseInt(sc.nextLine());
        int q = Integer.parseInt(sc.nextLine());
        int in = Integer.parseInt(sc.nextLine());
        int out = Integer.parseInt(sc.nextLine());

        if (out <= in) return;

        h.adicionarReserva(new Reserva(h.hospedes.get(i), h.quartos.get(q), in, out));
    }

    static void checkout(Hotel h) {
        int i = Integer.parseInt(sc.nextLine());
        h.reservas.get(i).realizarCheckout();
    }
}