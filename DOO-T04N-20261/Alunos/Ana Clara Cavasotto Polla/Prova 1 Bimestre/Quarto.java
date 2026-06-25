package fag.objetos;

public abstract class Quarto {
	private int numeroQuarto;
	private double valorDiaria;

	public Quarto(int numeroQuarto, double valorDiaria) {
		this.setNumeroQuarto(numeroQuarto);
		this.setValorDiaria(valorDiaria);
	}

	public abstract void exibirInformacoes();

	//getters:
	public int getNumeroQuarto() {
		return numeroQuarto;
	}

	public double getValorDiaria() {
		return valorDiaria;
	}

	public boolean dadosValidos() {
		if (this.numeroQuarto <= 0) {
			return false;
		}
		if (this.valorDiaria <= 0) {
			return false;
		}
		return true;
	}

	//setters:
	public boolean setNumeroQuarto(int numeroQuarto) {
		if (numeroQuarto <= 0) {
			System.out.println("O numero do quarto deve ser maior que zero.");
			return false;
		}
		this.numeroQuarto = numeroQuarto;
		return true;
	}

	public boolean setValorDiaria(double valorDiaria) {
		if (valorDiaria <= 0) {
			System.out.println("O valor da diaria deve ser maior que zero.");
			return false;
		}
		this.valorDiaria = valorDiaria;
		return true;
	}
}
