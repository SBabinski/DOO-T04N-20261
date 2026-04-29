package fag.objetos;

import java.time.LocalDate;
import java.util.List;

public class PopularLista {
	public static void popular(List<Hospede> hospedes, List<Quarto> quartos, Reserva[] reservas) {
		Hospede hospede1 = new Hospede("Ana Polla", "111.111.111-11", "45999990001");
		Hospede hospede2 = new Hospede("Maria Silva", "222.222.222-22", "45999990002");
		hospedes.add(hospede1);
		hospedes.add(hospede2);

		Quarto quarto1 = new QuartoSimples(101, 120.0, true);
		Quarto quarto2 = new QuartoSimples(102, 110.0, false);
		Quarto quarto3 = new QuartoLuxo(201, 250.0, true);
		Quarto quarto4 = new QuartoLuxo(202, 230.0, false);
		quartos.add(quarto1);
		quartos.add(quarto2);
		quartos.add(quarto3);
		quartos.add(quarto4);

		reservas[0] = new Reserva(1, hospede1, quarto1, LocalDate.of(2026, 4, 28), LocalDate.of(2026, 4, 29),
				true);
		reservas[1] = new Reserva(2, hospede2, quarto3, LocalDate.of(2026, 4, 28), LocalDate.of(2026, 5, 1),
				false);
	}
}
