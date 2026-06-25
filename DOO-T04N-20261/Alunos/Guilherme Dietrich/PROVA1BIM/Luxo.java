package objetos;

public class Luxo extends Quarto {
	boolean temVaranda;
	
	
	public Luxo(int numeroQuarto, double diaria, boolean temVaranda) {
		super(numeroQuarto, diaria);
		this.temVaranda = temVaranda;
	}
	
	
	public void exibirInformacao() {
		System.out.println("Tipo do quarto: Simples");
		System.out.println("Numero do quarto: " + numeroQuarto);
		System.out.println("Valor diária: " + diaria);
		System.out.println("O quarto tem varanda?: " + (temVaranda? "Sim" : "Não"));
	}
}
