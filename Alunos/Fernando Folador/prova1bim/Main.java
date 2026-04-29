import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Hotel hotel = new Hotel();

        Hospede[] hospedes = new Hospede[10];
        Quarto[] quartos = new Quarto[10];

        int totalHospedes = 0;
        int totalQuartos = 0;

        int op = -1;

        while (op != 0) {

            System.out.println("1 - Cadastrar Hospede");
            System.out.println("2 - Cadastrar Quarto");
            System.out.println("3 - Cadastrar Reserva");
            System.out.println("4 - Realizar Check-out");
            System.out.println("5 - Listar Reservas Ativas");
            System.out.println("6 - Demonstracao");
            System.out.println("0 - Sair");

            op = sc.nextInt();

            if (op == 1) {
                Hospede h = new Hospede();
                sc.nextLine();
                System.out.println("Nome:");
                h.nome = sc.nextLine();
                System.out.println("CPF:");
                h.cpf = sc.nextLine();
                System.out.println("Telefone:");
                h.telefone = sc.nextLine();

                hospedes[totalHospedes] = h;
                totalHospedes++;
                    System.out.println ("Hospede Cadastrado com Sucesso!");
            }

            if (op == 2) {
                System.out.println("1 - Simples | 2 - Luxo");
                int tipo = sc.nextInt();

                if (tipo == 1) {
                    QuartoSimples qs = new QuartoSimples();
                    System.out.println("Numero:");
                    qs.numero = sc.nextInt();
                    System.out.println("Valor:");
                    qs.valorDiaria = sc.nextDouble();
                    System.out.println("Tem ventilador? (true/false)");
                    qs.ventilador = sc.nextBoolean();

                    quartos[totalQuartos] = qs;
                } else {
                    QuartoLuxo ql = new QuartoLuxo();
                    System.out.println("Numero:");
                    ql.numero = sc.nextInt();
                    System.out.println("Valor:");
                    ql.valorDiaria = sc.nextDouble();
                    System.out.println("Tem varanda? (true/false)");
                    ql.varanda = sc.nextBoolean();

                    quartos[totalQuartos] = ql;
                }

                totalQuartos++;
                    System.out.println ("Quarto Cadastrado com Sucesso!");
            }

            if (op == 3) {
                Reserva r = new Reserva();

                System.out.println("Escolha Hospede:");
                for (int i = 0; i < totalHospedes; i++) {
                    System.out.println((i  + 1) + " - " + hospedes[i].nome);
                }
                int hIndex = sc.nextInt() - 1;
                r.hospede = hospedes[hIndex];

                System.out.println("Escolha Quarto:");
                for (int i = 0; i < totalQuartos; i++) {
                    System.out.println((i + 1) + " - Quarto " + quartos[i].numero);
                }
                int qIndex = sc.nextInt() - 1;
                r.quarto = quartos[qIndex];

                r.checkin = new Date();

                System.out.println("Quantidade de dias:");
                int dias = sc.nextInt();
                r.checkout = new Date(System.currentTimeMillis() + dias * 86400000L);

                r.finalizada = false;

                hotel.adicionarReserva(r);
                    System.out.println ("Reserva Cadastrada com Sucesso!");
            }

            if (op == 4) {
                System.out.println("Escolha uma reserva para finalizar:");
                for (int i = 0; i < hotel.total; i++) {
                    System.out.println((i + 1) + " - " + hotel.reservas[i].hospede.nome);
                }
                int rIndex = sc.nextInt() - 1;
                hotel.reservas[rIndex].finalizada = true;
                    System.out.println ("Check-out Realizado com Sucesso!");
            }

            if (op == 5) {
                hotel.listarAtivas();
            }

            if (op == 6) {

                Hospede h1 = new Hospede();
                h1.nome = "Fernando Folador";
                h1.cpf = "123.456.789.01";
                h1.telefone = "(55) 4591234-5678";

                Hospede h2 = new Hospede();
                h2.nome = "João Vitor";
                h2.cpf = "987.765.432.10";
                h2.telefone = "(55) 4599876-5432";

                QuartoSimples qs = new QuartoSimples();
                qs.numero = 1;
                qs.valorDiaria = 350;
                qs.ventilador = true;

                QuartoLuxo ql = new QuartoLuxo();
                ql.numero = 2;
                ql.valorDiaria = 850;
                ql.varanda = true;

                Reserva r1 = new Reserva();
                r1.hospede = h1;
                r1.quarto = qs;
                r1.checkin = new Date();
                r1.checkout = new Date(System.currentTimeMillis() + 2 * 86400000L);
                r1.finalizada = true;

                Reserva r2 = new Reserva();
                r2.hospede = h2;
                r2.quarto = ql;
                r2.checkin = new Date();
                r2.checkout = new Date(System.currentTimeMillis() + 3 * 86400000L);
                r2.finalizada = false;

                hotel.adicionarReserva(r1);
                hotel.adicionarReserva(r2);

                hotel.listarAtivas();
            }
        }
    }
}