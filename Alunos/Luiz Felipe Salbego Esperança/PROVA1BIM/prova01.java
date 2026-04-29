import java.util.*;

public class prova01 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        ArrayList<Hospede> hospedes = new ArrayList<>();
        ArrayList<Quarto> quartos = new ArrayList<>();

        Hospede h1 = new Hospede("Joao", "123", "9999");
        Hospede h2 = new Hospede("Maria", "456", "8888");

        hospedes.add(h1);
        hospedes.add(h2);

        Quarto q1 = new QuartoSimples(1, 100, true);
        Quarto q2 = new QuartoLuxo(2, 300, true);

        quartos.add(q1);
        quartos.add(q2);

        Calendar c = Calendar.getInstance();

        c.set(2026, Calendar.APRIL, 1);
        Date in1 = c.getTime();
        c.set(2026, Calendar.APRIL, 5);
        Date out1 = c.getTime();

        c.set(2026, Calendar.APRIL, 10);
        Date in2 = c.getTime();
        c.set(2026, Calendar.APRIL, 15);
        Date out2 = c.getTime();

        Reserva r1 = new Reserva(h1, q1, in1, out1);
        Reserva r2 = new Reserva(h2, q2, in2, out2);

        hotel.addReserva(r1);
        hotel.addReserva(r2);

        int op;
        do {
            System.out.println("\n1-Cadastrar hospede");
            System.out.println("2-Cadastrar quarto");
            System.out.println("3-Cadastrar reserva");
            System.out.println("4-Realizar checkout");
            System.out.println("5-Listar reservas ativas");
            System.out.println("0-Sair");

            op = sc.nextInt();

            switch (op) {
                case 1:
                    sc.nextLine();
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("CPF: ");
                    String cpf = sc.nextLine();
                    System.out.print("Telefone: ");
                    String tel = sc.nextLine();
                    hospedes.add(new Hospede(nome, cpf, tel));
                    break;

                case 2:
                    System.out.print("1-Simples 2-Luxo: ");
                    int tipo = sc.nextInt();
                    System.out.print("Numero: ");
                    int num = sc.nextInt();
                    System.out.print("Diaria: ");
                    double val = sc.nextDouble();

                    if (tipo == 1) {
                        System.out.print("Ventilador (sim/nao): ");
                        String resp = sc.next();
                        boolean v = resp.equalsIgnoreCase("sim");
                        quartos.add(new QuartoSimples(num, val, v));
                    } else {
                        System.out.print("Varanda (sim/nao): ");
                        String resp = sc.next();
                        boolean v = resp.equalsIgnoreCase("sim");
                        quartos.add(new QuartoLuxo(num, val, v));
                    }
                    break;

                case 3:
                    if (hospedes.isEmpty() || quartos.isEmpty()) {
                        System.out.println("Cadastre hospedes e quartos primeiro.");
                        break;
                    }

                    System.out.println("Escolha hospede:");
                    for (int i = 0; i < hospedes.size(); i++)
                        System.out.println(i + " - " + hospedes.get(i));

                    int h = sc.nextInt();

                    System.out.println("Escolha quarto:");
                    for (int i = 0; i < quartos.size(); i++)
                        System.out.println(i + " - " + quartos.get(i).getInfo());

                    int q = sc.nextInt();

                    Calendar c2 = Calendar.getInstance();
                    c2.set(2026, Calendar.APRIL, 1);
                    Date in = c2.getTime();
                    c2.set(2026, Calendar.APRIL, 5);
                    Date out = c2.getTime();

                    hotel.addReserva(new Reserva(hospedes.get(h), quartos.get(q), in, out));
                    break;

                case 4:
                    if (!hotel.temReservasAtivas()) {
                        System.out.println("Nenhuma reserva ativa para checkout.");
                        break;
                    }

                    hotel.listarAtivasComId();
                    System.out.print("Escolha ID: ");
                    int id = sc.nextInt();
                    hotel.realizarCheckout(id);
                    break;

                case 5:
                    hotel.listarAtivas();
                    break;
            }

        } while (op != 0);
    }
}

class Hospede {
    String nome, cpf, telefone;

    public Hospede(String nome, String cpf, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public String toString() {
        return nome + " | CPF: " + cpf + " | Tel: " + telefone;
    }
}

abstract class Quarto {
    int numero;
    double valorDiaria;

    public Quarto(int numero, double valorDiaria) {
        this.numero = numero;
        this.valorDiaria = valorDiaria;
    }

    public abstract String getInfo();
}

class QuartoSimples extends Quarto {
    boolean ventilador;

    public QuartoSimples(int numero, double valorDiaria, boolean ventilador) {
        super(numero, valorDiaria);
        this.ventilador = ventilador;
    }

    public String getInfo() {
        return "Simples | N " + numero + " | R$" + valorDiaria + " | Ventilador: " + (ventilador ? "Sim" : "Nao");
    }
}

class QuartoLuxo extends Quarto {
    boolean varanda;

    public QuartoLuxo(int numero, double valorDiaria, boolean varanda) {
        super(numero, valorDiaria);
        this.varanda = varanda;
    }

    public String getInfo() {
        return "Luxo | N " + numero + " | R$" + valorDiaria + " | Varanda: " + (varanda ? "Sim" : "Nao");
    }
}

class Reserva {
    Hospede hospede;
    Quarto quarto;
    Date checkIn, checkOut;
    boolean checkout;

    public Reserva(Hospede h, Quarto q, Date in, Date out) {
        hospede = h;
        quarto = q;
        checkIn = in;
        checkOut = out;
        checkout = false;
    }

    public long diarias() {
        return (checkOut.getTime() - checkIn.getTime()) / (1000 * 60 * 60 * 24);
    }

    public double total() {
        return diarias() * quarto.valorDiaria;
    }

    public void exibir() {
        System.out.println(hospede);
        System.out.println(quarto.getInfo());
        System.out.println("Total: R$" + total());
        System.out.println("------------------");
    }
}

class Hotel {
    ArrayList<Reserva> reservas = new ArrayList<>();
    final int LIMITE = 10;

    public boolean addReserva(Reserva r) {
        if (reservas.size() < LIMITE) {
            reservas.add(r);
            return true;
        }
        return false;
    }

    public boolean temReservasAtivas() {
        for (Reserva r : reservas)
            if (!r.checkout) return true;
        return false;
    }

    public void listarAtivas() {
        for (Reserva r : reservas)
            if (!r.checkout) r.exibir();
    }

    public void listarAtivasComId() {
        for (int i = 0; i < reservas.size(); i++) {
            if (!reservas.get(i).checkout) {
                System.out.println("ID: " + i);
                reservas.get(i).exibir();
            }
        }
    }

    public void realizarCheckout(int i) {
        if (i >= 0 && i < reservas.size()) {
            if (!reservas.get(i).checkout) {
                reservas.get(i).checkout = true;
                System.out.println("Checkout realizado.");
            } else {
                System.out.println("Reserva ja finalizada.");
            }
        } else {
            System.out.println("ID invalido.");
        }
    }
}