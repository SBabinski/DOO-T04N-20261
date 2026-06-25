package fag;

import java.time.LocalDate;
import java.time.Period;

public class Reserva {
	private Hospede Reservante;
	private Estadia Quarto;
	private LocalDate DataCheckIn; 
	private LocalDate DataCheckOut; 
	boolean CheckOutStatus; 
	
	public Reserva (Hospede reservante, Estadia quarto, LocalDate checkoutDate) {
		setReservante(reservante);
		setQuarto(quarto);
		setDataCheckOut(checkoutDate);
		DataCheckIn = (LocalDate.now());
		CheckOutStatus = false;
	}
	
	public boolean getCheckOutStatus() {
		return CheckOutStatus;
	}
	
	public LocalDate getDataCheckIn() {
		return DataCheckIn;
	}

	public  Hospede getReservante() {
		return Reservante;
	}

	public void setReservante(Hospede reservante) {
		Reservante = reservante;
	}

	public Estadia getQuarto() {
		return Quarto;
	}

	public void setQuarto(Estadia quarto) {
		Quarto = quarto;
	}

	public LocalDate getDataCheckOut() {
		return DataCheckOut;
	}

	public void setDataCheckOut(LocalDate dataCheckIn) {
		DataCheckOut = dataCheckIn;
	}
	
	public String DadosReserva() {
		Period periodo = Period.between(DataCheckIn, DataCheckOut);
		int dias = periodo.getDays();
		double valorQuarto = Quarto.getValorQuarto();
		double valorTotal = dias * valorQuarto;
		
		return "Reserva do quarto " + Quarto.getNumeroQuarto() + " pelo pessoa " + Reservante.getNome() + " com um valor total de " + valorTotal;
	}
}
