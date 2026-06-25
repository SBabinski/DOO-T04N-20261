package fag;

public class QuartoLuxo  extends Estadia {
	private boolean PossuiVaranda;
	
	public QuartoLuxo(int numero, double valor, boolean varanda) {
		setNumeroQuarto(numero);
		setValorQuarto(valor);
		setPossuiVentilador(varanda);
	}

	public void Apresentar() {
		String possuiVaranda = PossuiVaranda? "sim" : "não";
		System.out.println("Quarto de Luxo de numero " + getNumeroQuarto() + " diaria de " + getValorQuarto() + " Possui Varanda: " + possuiVaranda);
	}
	
	public boolean isPossuiVentilador() {
		return PossuiVaranda;
	}

	public void setPossuiVentilador(boolean possuiVaranda) {
		PossuiVaranda = possuiVaranda;
	}
}
