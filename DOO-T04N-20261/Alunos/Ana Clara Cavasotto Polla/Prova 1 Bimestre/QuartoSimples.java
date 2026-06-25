package fag.objetos;

public class QuartoSimples extends Quarto {
	private boolean ventilador;

	public QuartoSimples(int numeroQuarto, double valorDiaria, boolean ventilador) {
		super(numeroQuarto, valorDiaria);
		this.setVentilador(ventilador);
	}

	@Override
	public void exibirInformacoes() {
		String descricaoVentilador;
		if (this.ventilador) {
			descricaoVentilador = "Sim";
		} else {
			descricaoVentilador = "Nao";
		}

		System.out.printf("Quarto Simples | Numero: %d | Valor da diaria: %.2f | Possui ventilador: %s%n",
				this.getNumeroQuarto(), this.getValorDiaria(), descricaoVentilador);
	}

	//getters:
	public boolean isVentilador() {
		return ventilador;
	}

	//setters:
	public void setVentilador(boolean ventilador) {
		this.ventilador = ventilador;
	}
}
