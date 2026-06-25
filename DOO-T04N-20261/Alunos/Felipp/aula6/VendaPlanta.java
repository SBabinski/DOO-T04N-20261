package fag.objetos;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VendaPlanta {
	private LocalDate dataRegistro;
	private int quantidadePlantaVendida;
	private float valorVenda;
	private float descontoEspecial;
	private float troco;
	private boolean temDesconto;
	private int percentualDesconto = 5;

	public VendaPlanta(int quantidadePlantaVendida, float valorVenda, float descontoEspecial, boolean temDesconto, float troco, LocalDate dataRegistro) {
		setQuantidadePlantaVendida(quantidadePlantaVendida);
		setValorVenda(valorVenda);
		setDescontoEspecial(descontoEspecial);
		setTemDesconto(temDesconto);
		setTroco(troco);
		setDataRegistro(dataRegistro);
	}

	public LocalDate getDataRegistro() {
		return dataRegistro;
	}

	public void setDataRegistro(LocalDate dataRegistro) {
		this.dataRegistro = dataRegistro;
	}

	public float getTroco() {
		return troco;
	}

	public void setTroco(float troco) {
		if (troco > 0) {
			this.troco = troco;
		}
	}

	public boolean isTemDesconto() {
		return temDesconto;
	}

	public void setTemDesconto(boolean temDesconto) {
		this.temDesconto = temDesconto;
	}

	public int getQuantidadePlantaVendida() {
		return quantidadePlantaVendida;
	}

	public void setQuantidadePlantaVendida(int quantidadePlantaVendida) {
		if (quantidadePlantaVendida > 0) {
			this.quantidadePlantaVendida = quantidadePlantaVendida;
		}
	}

	public float getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(float valorVenda) {
		if (valorVenda > 0) {
			this.valorVenda = valorVenda;
		}
	}

	public float getDescontoEspecial() {
		return descontoEspecial;
	}

	public void setDescontoEspecial(float descontoEspecial) {
		if (descontoEspecial > 0) {
			this.descontoEspecial = descontoEspecial;
		}
	}

	public void mostrarRegistroVendas() {
		String infoDesconto;
		String infoTroco;

		if (temDesconto) {
			infoDesconto = String.format(
					"Desconto aplicado: %d%% -> R$ %.2f",
					percentualDesconto,
					descontoEspecial);
		} else {
			infoDesconto = "Sem desconto aplicado";
		}

		if (troco > 0) {
			infoTroco = String.format("Troco: R$ %.2f", troco);
		} else {
			infoTroco = "Sem troco";
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		System.out.printf(
				"Venda realizada | Valor: R$ %.2f | Quantidade: %d | %s | %s | %s\n",
				valorVenda,
				quantidadePlantaVendida,
				infoDesconto,
				infoTroco,
				dataRegistro.format(formatter)
			);
	}
}
