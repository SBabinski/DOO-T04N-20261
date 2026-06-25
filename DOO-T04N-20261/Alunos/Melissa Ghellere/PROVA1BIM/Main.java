package Alunos.Melissa_Ghellere.PROVA1BIM;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Hotel hotel = new Hotel();
        Hospede[] hospedes = new Hospede[10];
        Quarto[] quartos = new Quarto[10];
        int contH = 0, contQ = 0;

        int op = 0;
        while (op != 7) {
            System.out.println("\n1.Cadastrar Hóspede 2.Cadastrar Quarto 3.Reservar 4.Check-out 5.Listar 7.Sair");
            op = sc.nextInt(); sc.nextLine();

            switch (op) {
                case 1:
                    System.out.print("Nome: "); String n = sc.nextLine();
                    hospedes[contH++] = new Hospede(n, "000", "000");
                    break;
                case 2:
                    System.out.print("1.Simples 2.Luxo: "); int t = sc.nextInt();
                    System.out.print("Número: "); int num = sc.nextInt();
                    System.out.print("Valor Diária: "); double v = sc.nextDouble();
                    if (t == 1) quartos[contQ++] = new QuartoSimples(num, v, true);
                    else quartos[contQ++] = new QuartoLuxo(num, v, true);
                    break;
                case 3:
                    System.out.print("ID Hóspede (0 a "+(contH-1)+"): "); int ih = sc.nextInt();
                    System.out.print("ID Quarto (0 a "+(contQ-1)+"): "); int iq = sc.nextInt();
                    hotel.adicionarReserva(new Reserva(hospedes[ih], quartos[iq], LocalDate.now(), LocalDate.now().plusDays(2)));
                    break;
                case 4:
                    System.out.print("ID Reserva: ");
                    hotel.getReservas()[sc.nextInt()].realizarCheckout();
                    break;
                case 5: hotel.listarReservasAtivas(); break;
            }
        }
    }
}
