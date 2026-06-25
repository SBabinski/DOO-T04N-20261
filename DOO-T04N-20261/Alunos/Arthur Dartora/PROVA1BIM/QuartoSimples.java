
public class QuartoSimples extends Quarto {
	boolean ventilador;
	
	public QuartoSimples(int num, double valorDia, boolean ventilador) {
		super(num, valorDia);
		this.ventilador = ventilador;
	}
	
	@Override
	public void exibirQ() {
		System.out.println("Tipo: Quarto Simples");
		super.exibirQ();
		if(ventilador) {
			System.out.println("Ventilador = sim");
		} else {
			System.out.println("Ventilador = não");
		}
	}
}
