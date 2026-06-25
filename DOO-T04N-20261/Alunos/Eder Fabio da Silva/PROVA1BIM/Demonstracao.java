
import java.time.LocalDate;

public class Demonstracao {

    public static void executar(Hotel hotel) {
        System.out.println("\n=== DEMONSTRAÇÃO ===");
        System.out.println("Criando hóspedes, quartos e reservas de exemplo...\n");
        
        Hospede h1 = new Hospede("Ana Silva", "111.111.111-11", "(11)99999-1111");
        Hospede h2 = new Hospede("Bruno Souza", "222.222.222-22", "(11)98888-2222");

        Quartos quartoSimples = new QuartoSimples(101, 120.0, true);
        Quartos quartoLuxo = new QuartoLuxo(202, 280.0, true);

        Reserva r1 = new Reserva(h1, quartoSimples, LocalDate.of(2026, 4, 10), LocalDate.of(2026, 4, 12));
        Reserva r2 = new Reserva(h2, quartoLuxo, LocalDate.of(2026, 4, 20), LocalDate.of(2026, 4, 25));

        r1.realizarCheckout(); // finalizada
        // r2 permanece ativa

        hotel.adicionarReserva(r1);
        hotel.adicionarReserva(r2);

        hotel.listarReservasAtivas();
    }
}
