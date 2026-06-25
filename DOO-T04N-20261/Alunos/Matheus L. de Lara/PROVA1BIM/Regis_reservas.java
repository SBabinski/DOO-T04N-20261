package prov1bim.java;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Regis_reservas {
	private Hospedes Hospedes;
	private Quarto Quarto;
	private LocalDate checkin;
	private LocalDate checkoutData;
	private boolean checkout;

	public Regis_reservas(Hospedes Hospedes, Quarto Quarto, LocalDate checkin, LocalDate checkoutData) {
		this.Hospedes = Hospedes;
		this.Quarto = Quarto;
		this.checkin = checkin;
		this.checkoutData = checkoutData;
		this.checkout = false;
	}

	public double calcularTotal() {
		long dias = ChronoUnit.DAYS.between(checkin, checkoutData);
		return dias * Quarto.getval_diaria();
	}

	public void vizuReserva() {
		System.out.println("  ---  Reservas  ---  ");
		System.out.println("Hospede:" + Hospedes.getnome());
		System.out.println("quarto:" + Quarto.getnum());
		System.out.println("Check - In" + checkin);
		System.out.println("total R$" + calcularTotal());
		if (checkout) {
			System.out.println("Status: Realizado");
		} else {
			System.out.println("Status: Pendente");
		}
	}
	public boolean ischeckout() {
		return checkout;
	}
	public void realizarcheckout() {
		this.checkout = true;
	}
}
