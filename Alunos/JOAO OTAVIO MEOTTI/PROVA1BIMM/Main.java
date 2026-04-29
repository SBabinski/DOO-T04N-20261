import java.time.LocalDate;
public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Hospede hospede1 = new Hospede("Robert", "111", "9797");
        Hospede hospede2 = new Hospede("Pafuncia", "222", "8182");
        QuartoSimples quarto1 = new QuartoSimples(1, 157, true);
        QuartoLuxo quarto2 = new QuartoLuxo(2, 599, true);
        Reserva reserva1 = new Reserva(hospede1, quarto1,
                LocalDate.of(2026,4,20),
                LocalDate.of(2026,4,25));
        Reserva reserva2 = new Reserva(hospede2, quarto2,
                LocalDate.of(2026,4,27),
                LocalDate.of(2026,5,2));
        reserva1.realizado = true;
        hotel.adicionar(reserva1);
        hotel.adicionar(reserva2);
        hotel.listarAtivas();
    }
}