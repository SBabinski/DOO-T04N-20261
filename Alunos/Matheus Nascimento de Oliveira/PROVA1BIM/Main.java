import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Hotel hotel = new Hotel();

        Hospede[] hospedes = new Hospede[10];
        int qtdHospedes = 0;

        Quarto[] quartos = new Quarto[10];
        int qtdQuartos = 0;

        int opcao = -1;

        while (opcao != 0) {
            System.out.println();
            System.out.println("1 - Cadastrar hóspede");
            System.out.println("2 - Cadastrar quarto");
            System.out.println("3 - Cadastrar reserva");
            System.out.println("4 - Realizar check-out");
            System.out.println("5 - Listar reservas sem check-out");
            System.out.println("6 - Demonstração");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            if (opcao == 1) {
                if (qtdHospedes < hospedes.length) {
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = sc.nextLine();
                    hospedes[qtdHospedes++] = new Hospede(nome, cpf, telefone);
                    System.out.println("Hóspede cadastrado com sucesso.");
                } else {
                    System.out.println("Limite de hóspedes alcançado.");
                }
            } else if (opcao == 2) {
                if (qtdQuartos < quartos.length) {
                    System.out.println("1 - Quarto simples");
                    System.out.println("2 - Quarto luxo");
                    System.out.print("Tipo de quarto: ");
                    int tipo = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Número do quarto: ");
                    int numero = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Valor da diária: ");
                    double valor = sc.nextDouble();
                    sc.nextLine();

                    if (tipo == 1) {
                        System.out.print("Tem ventilador? (s/n): ");
                        String resp = sc.nextLine();
                        boolean ventilador = resp.equalsIgnoreCase("s");
                        quartos[qtdQuartos++] = new QuartoSimples(numero, valor, ventilador);
                    } else {
                        System.out.print("Tem varanda? (s/n): ");
                        String resp = sc.nextLine();
                        boolean varanda = resp.equalsIgnoreCase("s");
                        quartos[qtdQuartos++] = new QuartoLuxo(numero, valor, varanda);
                    }
                    System.out.println("Quarto cadastrado com sucesso.");
                } else {
                    System.out.println("Limite de quartos alcançado.");
                }
            } else if (opcao == 3) {
                if (qtdHospedes == 0) {
                    System.out.println("Nenhum hóspede cadastrado.");
                } else if (qtdQuartos == 0) {
                    System.out.println("Nenhum quarto cadastrado.");
                } else if (hotel.getQtd() >= 10) {
                    System.out.println("Não é possível cadastrar mais reservas.");
                } else {
                    System.out.println("Hóspedes disponíveis:");
                    for (int i = 0; i < qtdHospedes; i++) {
                        System.out.println(i + " - " + hospedes[i].getNome());
                    }
                    System.out.print("Escolha o hóspede: ");
                    int ih = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Quartos disponíveis:");
                    for (int i = 0; i < qtdQuartos; i++) {
                        System.out.println(i + " - Quarto " + quartos[i].getNumero() + " (R$ " + quartos[i].getValorDiaria() + ")");
                    }
                    System.out.print("Escolha o quarto: ");
                    int iq = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Data do check-in: ");
                    String entrada = sc.nextLine();
                    System.out.print("Data do check-out: ");
                    String saida = sc.nextLine();
                    System.out.print("Quantidade de diárias: ");
                    int diarias = sc.nextInt();
                    sc.nextLine();

                    Reserva reserva = new Reserva(hospedes[ih], quartos[iq], entrada, saida, diarias);
                    hotel.adicionar(reserva);
                }
            } else if (opcao == 4) {
                if (hotel.getQtd() == 0) {
                    System.out.println("Nenhuma reserva cadastrada.");
                } else {
                    for (int i = 0; i < hotel.getQtd(); i++) {
                        System.out.println("Reserva " + i + ":");
                        hotel.getReserva(i).mostrar();
                    }
                    System.out.print("Escolha a reserva para check-out: ");
                    int idx = sc.nextInt();
                    sc.nextLine();
                    Reserva reserva = hotel.getReserva(idx);
                    if (reserva != null) {
                        reserva.realizarCheckout();
                        System.out.println("Check-out realizado com sucesso.");
                    } else {
                        System.out.println("Reserva inválida.");
                    }
                }
            } else if (opcao == 5) {
                hotel.listarAtivas();
            } else if (opcao == 6) {
                Hospede h1 = new Hospede("João Silva", "12345678900", "(11) 99999-0000");
                Hospede h2 = new Hospede("Mariana Costa", "98765432100", "(21) 98888-1111");

                Quarto q1 = new QuartoSimples(101, 180.0, true);
                Quarto q2 = new QuartoLuxo(202, 320.0, true);

                Reserva r1 = new Reserva(h1, q1, "01/04/2026", "03/04/2026", 2);
                Reserva r2 = new Reserva(h2, q2, "05/04/2026", "08/04/2026", 3);

                r1.realizarCheckout();

                hotel.adicionar(r1);
                hotel.adicionar(r2);

                hotel.listarAtivas();
            } else if (opcao != 0) {
                System.out.println("Opção inválida.");
            }
        }

        sc.close();
    }
}
