import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Hotel hotel = new Hotel();

        Hospede[] hospedes = new Hospede[10];
        Quarto[] quartos = new Quarto[10];

        int qtdHospedes = 0;
        int qtdQuartos = 0;

        while (true) {
            System.out.println("\n1 - Cadastrar Hospede");
            System.out.println("2 - Cadastrar Quarto");
            System.out.println("3 - Fazer Reserva");
            System.out.println("4 - Listar Reservas Ativas");
            System.out.println("5 - Demonstracao");
            System.out.println("0 - Sair");

            int op = sc.nextInt();

            if (op == 1) {
                sc.nextLine();

                System.out.print("Nome: ");
                String nome = sc.nextLine();

                System.out.print("CPF: ");
                String cpf = sc.nextLine();

                System.out.print("Telefone: ");
                String tel = sc.nextLine();

                hospedes[qtdHospedes++] = new Hospede(nome, cpf, tel);
            }

            else if (op == 2) {
                System.out.println("1 - Simples | 2 - Luxo");
                int tipo = sc.nextInt();

                System.out.print("Numero: ");
                int numero = sc.nextInt();

                System.out.print("Valor diaria: ");
                double valor = sc.nextDouble();

                if (tipo == 1) {
                    System.out.print("Tem ventilador? (true/false): ");
                    boolean vent = sc.nextBoolean();
                    quartos[qtdQuartos++] = new QuartoSimples(numero, valor, vent);
                } else {
                    System.out.print("Tem varanda? (true/false): ");
                    boolean var = sc.nextBoolean();
                    quartos[qtdQuartos++] = new QuartoLuxo(numero, valor, var);
                }
            }

            else if (op == 3) {
                System.out.print("Indice do hospede: ");
                int iH = sc.nextInt();

                System.out.print("Indice do quarto: ");
                int iQ = sc.nextInt();

                sc.nextLine();

                System.out.print("Check-in: ");
                String in = sc.nextLine();

                System.out.print("Check-out: ");
                String out = sc.nextLine();

                System.out.print("Dias: ");
                int dias = sc.nextInt();

                Reserva r = new Reserva(hospedes[iH], quartos[iQ], in, out, dias, false);
                hotel.adicionarReserva(r);
            }

            else if (op == 4) {
                hotel.listarReservasAtivas();
            }

            else if (op == 5) {
            
                Hospede h1 = new Hospede("Isaac", "111.111.111-11", "9999");
                Hospede h2 = new Hospede("Samuel", "222.222.222-22", "8888");

                QuartoSimples qs = new QuartoSimples(101, 100, true);
                QuartoLuxo ql = new QuartoLuxo(202, 300, true);

                Reserva r1 = new Reserva(h1, qs, "01/01/2026", "05/01/2026", 4, true);
                Reserva r2 = new Reserva(h2, ql, "10/01/2026", "15/01/2026", 5, false);

                hotel.adicionarReserva(r1);
                hotel.adicionarReserva(r2);

                hotel.listarReservasAtivas();
            }

            else {
                break;
            }
        }

        sc.close();
    }
}