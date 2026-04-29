package objetos;

import java.time.LocalDate;

public class Reserva {
	
	private Hospede hospede;
	private Quarto quarto;
	private LocalDate dataCheckIn;
	private LocalDate dataCheckOut;
	private int quantidadeDeDiarias;
	private boolean checkoutRealizado;
	
	public Reserva(Hospede hospede, Quarto quarto, LocalDate dataCheckIn, LocalDate dataCheckOut, int quantidadeDeDiarias, boolean checkoutRealizado) {
		this.hospede = hospede;
		this.quarto = quarto;
		this.dataCheckIn = dataCheckIn;
		this.dataCheckOut = dataCheckOut;
		this.quantidadeDeDiarias = quantidadeDeDiarias;
		this.checkoutRealizado = checkoutRealizado;
	}

	public Hospede getHospede() {
		return hospede;
	}

	public void setHospede(Hospede hospede) {
		this.hospede = hospede;
	}

	public Quarto getQuarto() {
		return quarto;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}

	public LocalDate getDataCheckIn() {
		return dataCheckIn;
	}

	public void setDataCheckIn(LocalDate dataCheckIn) {
		this.dataCheckIn = dataCheckIn;
	}

	public LocalDate getDataCheckOut() {
		return dataCheckOut;
	}

	public void setDataCheckOut(LocalDate dataCheckOut) {
		this.dataCheckOut = dataCheckOut;
	}

	public int getQuantidadeDeDiarias() {
		return quantidadeDeDiarias;
	}

	public void setQuantidadeDeDiarias(int quantidadeDeDiarias) {
		this.quantidadeDeDiarias = quantidadeDeDiarias;
	}

	public boolean isCheckoutRealizado() {
		return checkoutRealizado;
	}
	
	public void setCheckoutRealizado(boolean checkoutRealizado) {
		this.checkoutRealizado = checkoutRealizado;
	}
	
	public double calcularValorTotal() {
		return quantidadeDeDiarias * quarto.getValorDaDiaria();
	}
	
	public void realizarCheckOut() {
		this.checkoutRealizado = true;
	}
	
	public void exibirInformacoes() {
		System.out.println("Dados do cliente:");
		hospede.exibirInformacoes();
		
		System.out.println("Dados do quarto:");
		quarto.exibirInformacoes();
		
		System.out.println("Data de check-in: " + dataCheckIn);
		System.out.println("Data de check-out: " + dataCheckOut);
		System.out.println("Quantidade de diarias: " + quantidadeDeDiarias);
		System.out.println("Checkout realizado: " + checkoutRealizado);
		System.out.println("Valor total: 0 + " + calcularValorTotal());
		
	}
	
	// Para registrar uma reserva, o sistema deve associar um hóspede a um quarto, além de armazenar a data do check-in, a data do check-out e a situação do check-out (realizado ou não).
	// Deve ser possível visualizar os dados de uma reserva, incluindo o valor total a ser pago (quantidade de diárias × valor da diária do quarto).
}
