package prov1bim.java;

public class Quarto_Simples extends Quarto {
	private boolean ventilador;

	public Quarto_Simples(int num, double val_diaria, boolean ventilador) {
		super(num, val_diaria);
		this.ventilador = ventilador;
	}

	public void exibirInfo() {
		super.exibirInfo();
		System.out.println("Tipo: Simples");

		if (ventilador) {
			System.out.println("Ventilador : Sim");
		} else {
			System.out.println("Veltilador: Não");
		}
	}
}
