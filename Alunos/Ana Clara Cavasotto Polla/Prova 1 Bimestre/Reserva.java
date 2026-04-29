package fag.objetos;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva {
	private int idReserva;
	private Hospede hospede;
	private Quarto quarto;
	private LocalDate dataCheckIn;
	private LocalDate dataCheckOut;
	private boolean checkOutRealizado;
	private double valorTotal;

	public Reserva(int idReserva, Hospede hospede, Quarto quarto, LocalDate dataCheckIn, LocalDate dataCheckOut,
			boolean checkOutRealizado) {
		this.setIdReserva(idReserva);
		this.setHospede(hospede);
		this.setQuarto(quarto);
		this.setDataCheckIn(dataCheckIn);
		this.setDataCheckOut(dataCheckOut);
		this.setCheckOutRealizado(checkOutRealizado);
		if (dadosValidos()) {
			this.atualizarValorTotal();
		}
	}

	private void atualizarValorTotal() {
		long quantidadeDiarias = ChronoUnit.DAYS.between(this.dataCheckIn, this.dataCheckOut);
		if (quantidadeDiarias <= 0) {
			quantidadeDiarias = 1;
		}
		this.valorTotal = quantidadeDiarias * this.quarto.getValorDiaria();
	}

	public void realizarCheckOut() {
		if (this.checkOutRealizado) {
			System.out.println("O check-out desta reserva ja foi realizado.");
			return;
		}
		this.checkOutRealizado = true;
	}

	public void exibirInformacoes() {
		String descricaoCheckOut;
		String tipoQuarto;
		String descricaoAdicional;

		if (this.checkOutRealizado) {
			descricaoCheckOut = "Realizado";
		} else {
			descricaoCheckOut = "Pendente";
		}

		if (this.quarto instanceof QuartoSimples) {
			tipoQuarto = "Quarto Simples";
			QuartoSimples quartoSimples = (QuartoSimples) this.quarto;
			if (quartoSimples.isVentilador()) {
				descricaoAdicional = "Sim";
			} else {
				descricaoAdicional = "Nao";
			}
		} else {
			tipoQuarto = "Quarto Luxo";
			QuartoLuxo quartoLuxo = (QuartoLuxo) this.quarto;
			if (quartoLuxo.isVaranda()) {
				descricaoAdicional = "Sim";
			} else {
				descricaoAdicional = "Nao";
			}
		}

		System.out.printf(
				"Reserva: %d | Hospede: %s | CPF: %s | Telefone: %s | Quarto: %s | N\u00famero: %d | Valor da di\u00e1ria: %.2f | Adicional: %s | Check-in: %s | Check-out: %s | Check-out realizado: %s | Valor total: %.2f%n",
				this.idReserva, this.hospede.getNome(), this.hospede.getCpf(), this.hospede.getTelefone(), tipoQuarto,
				this.quarto.getNumeroQuarto(), this.quarto.getValorDiaria(), descricaoAdicional, this.dataCheckIn,
				this.dataCheckOut, descricaoCheckOut, this.valorTotal);
	}

	private boolean validarDatas(LocalDate dataCheckIn, LocalDate dataCheckOut) {
		if (dataCheckIn == null || dataCheckOut == null) {
			System.out.println("As datas de check-in e check-out devem ser informadas.");
			return false;
		}
		if (dataCheckIn.isAfter(dataCheckOut)) {
			System.out.println("A data de check-in nao pode ser maior que a data de check-out.");
			return false;
		}
		if (dataCheckIn.isEqual(dataCheckOut)) {
			System.out.println("A data de check-out nao pode ser no mesmo dia que a data de check-in.");
			return false;
		}
		if (dataCheckIn.isBefore(LocalDate.now())) {
			System.out.println("A data de check-in nao pode ser anterior a data atual.");
			return false;
		}
		return true;
	}

	//getters:
	public int getIdReserva() {
		return idReserva;
	}

	public Hospede getHospede() {
		return hospede;
	}

	public Quarto getQuarto() {
		return quarto;
	}

	public LocalDate getDataCheckIn() {
		return dataCheckIn;
	}

	public LocalDate getDataCheckOut() {
		return dataCheckOut;
	}

	public boolean isCheckOutRealizado() {
		return checkOutRealizado;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public boolean dadosValidos() {
		if (this.idReserva <= 0) {
			return false;
		}
		if (this.hospede == null) {
			return false;
		}
		if (this.quarto == null) {
			return false;
		}
		if (this.dataCheckIn == null || this.dataCheckOut == null) {
			return false;
		}
		return true;
	}

	//setters:
	public boolean setIdReserva(int idReserva) {
		if (idReserva <= 0) {
			System.out.println("O ID da reserva deve ser maior que zero.");
			return false;
		}
		this.idReserva = idReserva;
		return true;
	}

	public boolean setHospede(Hospede hospede) {
		if (hospede == null) {
			System.out.println("O hospede da reserva deve ser informado.");
			return false;
		}
		this.hospede = hospede;
		return true;
	}

	public boolean setQuarto(Quarto quarto) {
		if (quarto == null) {
			System.out.println("O quarto da reserva deve ser informado.");
			return false;
		}
		this.quarto = quarto;
		return true;
	}

	public boolean setDataCheckIn(LocalDate dataCheckIn) {
		if (dataCheckIn == null) {
			System.out.println("A data de check-in deve ser informada.");
			return false;
		}
		if (dataCheckIn.isBefore(LocalDate.now())) {
			System.out.println("A data de check-in nao pode ser anterior a data atual.");
			return false;
		}
		if (this.dataCheckOut != null && !validarDatas(dataCheckIn, this.dataCheckOut)) {
			return false;
		}
		this.dataCheckIn = dataCheckIn;
		if (this.quarto != null && this.dataCheckOut != null) {
			atualizarValorTotal();
		}
		return true;
	}

	public boolean setDataCheckOut(LocalDate dataCheckOut) {
		if (dataCheckOut == null) {
			System.out.println("A data de check-out deve ser informada.");
			return false;
		}
		if (this.dataCheckIn != null && !validarDatas(this.dataCheckIn, dataCheckOut)) {
			return false;
		}
		this.dataCheckOut = dataCheckOut;
		if (this.quarto != null && this.dataCheckIn != null) {
			atualizarValorTotal();
		}
		return true;
	}

	public void setCheckOutRealizado(boolean checkOutRealizado) {
		this.checkOutRealizado = checkOutRealizado;
	}
}
