package prova;

public class Qsimples extends Quartos{
	boolean ventilador;

	public Qsimples() {
		
	}
	
	public Qsimples(int num, float diaria, boolean ventilador) {
		setNum(num);
		setDiaria(diaria);
		setVentilador(ventilador);
	}
	
	public boolean isVentilador() {
		return ventilador;
	}

	public void setVentilador(boolean ventilador) {
		this.ventilador = ventilador;
	}
	
	public String apresentarQuarto() {
		return num + "|| " + diaria + "|| " + ventilador;
	}
}
