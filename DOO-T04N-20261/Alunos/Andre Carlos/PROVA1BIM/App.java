import java.time.LocalDate;
import java.util.Scanner;

public class App {

    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        Hotel hotel = new Hotel();

        Hospede h1 = new Hospede("Mario", "111", "45999701918");
        Hospede h2 = new Hospede("Fabiano", "213", "45999714810");

        Quarto q1 = new Simples(1, 100, false);
        Quarto q2 = new Luxo(5, 200, true);

        Reserva r1 = new Reserva(h1, q1,
                LocalDate.of(2026, 4, 20),
                LocalDate.of(2026, 4, 25));

        Reserva r2 = new Reserva(h2, q2,
                LocalDate.of(2026, 5, 23),
                LocalDate.of(2026, 5, 30));

        r1.realizarCheckout();

        hotel.adicionarReserva(r1);
        hotel.adicionarReserva(r2);


        int opcao;

        do{

            System.out.println("Bem vindo ao Hotel Copas Amarelas");
            System.out.println("[1] - Listar Reservas Ativas");
            System.out.println("[2] - Realizar CheckOut");
            System.out.println("[3] - Sair");
            opcao = sc.nextInt();


            switch (opcao){

                case 1:
                    hotel.listarReservasAtivas();
                    break;

                case 2:
                    System.out.println("Digite o numero da reserva");
                    int i = sc.nextInt();

                    Reserva r = hotel.getReserva(i);

                    if(r != null){
                        r.realizarCheckout();
                        System.out.println("CheckOut realizado");
                    } else{
                        System.out.println("Reserva invalida");


                    }
                    break;

                default:
                    System.out.println("Opcao invalida");
                    break;

            }
        } while (opcao != 3);

        sc.close();







    }
}