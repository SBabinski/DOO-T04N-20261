import java.util.Scanner;

public class Hotel {
    private Reserva[] reservas = new Reserva[10];
    private int contador = 0;

    private Hospede[] hospedes = new Hospede[10];
    private int contHospedes = 0;

    private Quarto[] quartos = new Quarto[10];
    private int contQuartos = 0;

    public void cadastrarHospede(Scanner sc) {
        if (contHospedes >= 10) {
            System.out.println("Limite de hóspedes atingido!");
            return;
        }

        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("CPF: ");
        String cpf = sc.nextLine();
        System.out.print("Telefone: ");
        String tel = sc.nextLine();

        hospedes[contHospedes++] = new Hospede(nome, cpf, tel);
    }

    public void cadastrarQuarto(Scanner sc) {
        if (contQuartos >= 10) {
            System.out.println("Limite de quartos atingido!");
            return;
        }

        System.out.print("1-Simples / 2-Luxo: ");
        int tipo = sc.nextInt();

        System.out.print("Número: ");
        int num = sc.nextInt();
        System.out.print("Valor diária: ");
        double valor = sc.nextDouble();

        if (tipo == 1) {
            System.out.print("Tem ventilador (true/false): ");
            boolean vent = sc.nextBoolean();
            quartos[contQuartos++] = new QuartoSimples(num, valor, vent);
        } else {
            System.out.print("Tem varanda (true/false): ");
            boolean var = sc.nextBoolean();
            quartos[contQuartos++] = new QuartoLuxo(num, valor, var);
        }
    }

    public void cadastrarReserva(Scanner sc) {
        if (contador >= 10) {
            System.out.println("Limite de reservas atingido!");
            return;
        }

        if (contHospedes == 0 || contQuartos == 0) {
            System.out.println("Cadastre hóspede e quarto primeiro!");
            return;
        }

        System.out.println("Escolha hóspede:");
        for (int i = 0; i < contHospedes; i++) {
            System.out.println(i + " - " + hospedes[i].getNome());
        }
        int h = sc.nextInt();

        System.out.println("Escolha quarto:");
        for (int i = 0; i < contQuartos; i++) {
            System.out.println(i + " - Quarto " + quartos[i].numero);
        }
        int q = sc.nextInt();

        sc.nextLine();
        System.out.print("Check-in: ");
        String ci = sc.nextLine();
        System.out.print("Check-out: ");
        String co = sc.nextLine();

        System.out.print("Quantidade de dias: ");
        int dias = sc.nextInt();

        reservas[contador++] = new Reserva(hospedes[h], quartos[q], ci, co, dias);
    }

    public boolean adicionarReserva(Reserva r) {
        if (contador < 10) {
            reservas[contador++] = r;
            return true;
        }
        return false;
    }

    public void listarSemCheckout() {
        System.out.println("\n--- RESERVAS ATIVAS ---");
        for (int i = 0; i < contador; i++) {
            if (!reservas[i].isCheckoutRealizado()) {
                System.out.print("ID " + i + " ");
                reservas[i].exibirReserva();
            }
        }
    }

    public void realizarCheckout(int i) {
        if (i >= 0 && i < contador) {
            if (reservas[i].isCheckoutRealizado()) {
                System.out.println("Já finalizada!");
            } else {
                reservas[i].realizarCheckout();
                System.out.println("Check-out realizado!");
            }
        } else {
            System.out.println("ID inválido!");
        }
    }
}