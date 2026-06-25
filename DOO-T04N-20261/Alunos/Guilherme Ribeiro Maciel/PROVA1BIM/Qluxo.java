package prova;

public class Qluxo extends Quartos{
	boolean varanda;

	public Qluxo() {
		
	}
	
	public Qluxo(int num, float diaria, boolean varanda) {
		setNum(num);
		setDiaria(diaria);
		setVaranda(varanda);
	}
	
	public boolean isVaranda() {
		return varanda;
	}

	public void setVaranda(boolean varanda) {
		this.varanda = varanda;
	}
	
	public String apresentarQuarto() {
		return num + "|| " + diaria + "|| " + varanda;
	}
}
