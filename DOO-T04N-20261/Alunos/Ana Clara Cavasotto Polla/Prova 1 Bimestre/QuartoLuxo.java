package fag.objetos;

public class QuartoLuxo extends Quarto {
	private boolean varanda;

	public QuartoLuxo(int numeroQuarto, double valorDiaria, boolean varanda) {
		super(numeroQuarto, valorDiaria);
		this.setVaranda(varanda);
	}

	@Override
	public void exibirInformacoes() {
		String descricaoVaranda;
		if (this.varanda) {
			descricaoVaranda = "Sim";
		} else {
			descricaoVaranda = "Nao";
		}

		System.out.printf("Quarto Luxo | N\u00famero: %d | Valor da di\u00e1ria: %.2f | Possui varanda: %s%n",
				this.getNumeroQuarto(), this.getValorDiaria(), descricaoVaranda);
	}

	//getters:
	public boolean isVaranda() {
		return varanda;
	}

	//setters:
	public void setVaranda(boolean varanda) {
		this.varanda = varanda;
	}
}
