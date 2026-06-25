package objetos;

public class QuartoLuxo extends Quarto {
	
	private boolean temVaranda;
	
	public QuartoLuxo(int numero, double valorDaDiaria, boolean temVaranda) {
		super(numero, valorDaDiaria);
		this.temVaranda = temVaranda;
	}

	public boolean isTemVaranda() {
		return temVaranda;
	}

	public void setTemVaranda(boolean temVaranda) {
		this.temVaranda = temVaranda;
	}

	@Override
	public void exibirInformacoes() {
		System.out.println("Quarto Luxo:");
		super.exibirInformacoes();
		System.out.println("Tem varanda: " + temVaranda);
	}
	
	// Ambos possuem número do quarto e valor da diária. O quarto luxo possui a informação de ter ou não varanda.
}
