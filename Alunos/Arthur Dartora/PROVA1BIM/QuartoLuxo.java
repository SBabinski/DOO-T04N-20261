
public class QuartoLuxo extends Quarto {

	boolean varanda;
	
	public QuartoLuxo(int num, double valorDia, boolean varanda) {
		super(num, valorDia);
		this.varanda = varanda;
	}
	
	@Override
	public void exibirQ() {
		System.out.println("Tipo: Quarto Luxo");
		super.exibirQ();
		if(varanda) {
			System.out.println("Varanda = sim");
		} else {
			System.out.println("Varanda = não");
		}
	}
}


