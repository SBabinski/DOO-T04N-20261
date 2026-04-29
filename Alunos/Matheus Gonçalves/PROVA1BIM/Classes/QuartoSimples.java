package fag;

public class QuartoSimples extends Estadia {
	private boolean PossuiVentilador;
	
	public QuartoSimples(int numero, double valor, boolean ventilador) {
		setNumeroQuarto(numero);   
		setValorQuarto(valor);
		this.PossuiVentilador = ventilador;
	}
	
	public void apresentar() {
		String ventiladorTexto = PossuiVentilador ? "sim" : "não";
		
		System.out.println(
			"Quarto Simples de número " + getNumeroQuarto() +
			" diária de " + getValorQuarto() +
			" Possui ventilador: " + ventiladorTexto
		);
	}

	public boolean isPossuiVentilador() {
		return PossuiVentilador;
	}

	public void setPossuiVentilador(boolean possuiVentilador) {
		this.PossuiVentilador = possuiVentilador;
	}
}