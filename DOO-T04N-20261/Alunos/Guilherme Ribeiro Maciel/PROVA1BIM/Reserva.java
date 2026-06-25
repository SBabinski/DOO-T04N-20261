package prova;
import java.time.LocalDate;

public class Reserva {
	int hospede;
	LocalDate checkin;
	LocalDate checkout;
	boolean situacaoCout;
	int quarto;
	
	public Reserva() {
		
	}
	
	public Reserva(int hospede, int quarto, LocalDate checkin, LocalDate checkout, boolean situacaoCout) {
		setHospede(hospede);
		setQuarto(quarto);
		setCheckin(checkin);
		setCheckout(checkout);
		setSituacaoCout(situacaoCout);
	}
	
	public int getQuarto() {
		return quarto;
	}

	public void setQuarto(int quarto) {
		this.quarto = quarto;
	}

	public int getHospede() {
		return hospede;
	}
	
	public void setHospede(int hospede) {
		this.hospede = hospede;
	}
	
	public LocalDate getCheckin() {
		return checkin;
	}
	
	public void setCheckin(LocalDate checkin) {
		this.checkin = checkin;
	}
	
	public LocalDate getCheckout() {
		return checkout;
	}
	
	public void setCheckout(LocalDate checkout) {
		this.checkout = checkout;
	}
	
	public boolean isSituacaoCout() {
		return situacaoCout;
	}
	
	public void setSituacaoCout(boolean situacaoCout) {
		this.situacaoCout = situacaoCout;
	}
	
	public String apresentarReserva() {
		return checkin + "|| " + hospede + "|| " + quarto + "|| " + checkout + "|| " + situacaoCout;
	}
}
