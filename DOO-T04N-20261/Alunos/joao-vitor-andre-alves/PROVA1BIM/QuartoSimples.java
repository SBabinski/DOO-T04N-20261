package objetos;

public class QuartoSimples extends Quarto {
	
	private boolean temVentilador;
	
	public QuartoSimples(int numero, double valorDaDiaria, boolean temVentilador) {
		super(numero, valorDaDiaria);
		this.temVentilador = temVentilador;
	}

	public boolean isTemVentilador() {
		return temVentilador;
	}

	public void setTemVentilador(boolean temVentilador) {
		this.temVentilador = temVentilador;
	}

	@Override
	public void exibirInformacoes() {
		System.out.println("Quarto Simples:");
		super.exibirInformacoes();
		System.out.println("Tem ventilador: " + temVentilador);
	}
	
	// Ambos possuem número do quarto e valor da diária. O quarto simples possui a informação de ter ou não ventilador
}
