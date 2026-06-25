package fag;

public class Estadia {
	private int NumeroQuarto;
	private double ValorQuarto;
	
	public int getNumeroQuarto() {
		return NumeroQuarto;
	}

	public void setNumeroQuarto(int numeroQuarto) {
		if (numeroQuarto > 0) {
			NumeroQuarto = numeroQuarto;
		}
	}

	public double getValorQuarto() {
		return ValorQuarto;
	}

	public void setValorQuarto(double valorQuarto) {
		if (valorQuarto > 0.0) {
			ValorQuarto = valorQuarto;
		}
	}
}
