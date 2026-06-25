package prov1bim.java;

public class Hotel {

	private Hospedes[] hospedes = new Hospedes[10];
	private Quarto[] quartos = new Quarto[10];
	private Regis_reservas[] reservas = new Regis_reservas[10];

	private int h = 0, q = 0, r = 0;

	public void addHospede(Hospedes hospede) {
		hospedes[h++] = hospede;
	}

	public void addQuarto(Quarto quarto) {
		quartos[q++] = quarto;
	}

	public void addReserva(Regis_reservas reserva) {
		if (r >= 10) {
			System.out.println("Limite de reservas atingido!");
			return;
		}
		reservas[r++] = reserva;
	}

	public void listarAtivas() {
		for (int i = 0; i < r; i++) {
			if (!reservas[i].ischeckout()) {
				reservas[i].vizuReserva();
			}
		}
	}

	public void fazerCheckout(int check) {
		if (check < r) {
			reservas[check].realizarcheckout();
		}
	}

	public Hospedes getHospede(int i) {
		return hospedes[i];
	}

	public Quarto getQuarto(int i) {
		return quartos[i];
	}
}
