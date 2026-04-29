package prov1bim.java;

public class Quarto_Luxo extends Quarto{
	private boolean Varanda;

	public Quarto_Luxo(int num, double val_diaria, boolean Varanda) {
		super(num, val_diaria);
		this.Varanda= Varanda;
	}

	public void exibirInfo() {
		super.exibirInfo();
		System.out.println("Tipo: Luxo");
		if (Varanda) {
			System.out.println("Varanda : Sim");
		} else {
			System.out.println("V: Não");
		}
	}	
}
