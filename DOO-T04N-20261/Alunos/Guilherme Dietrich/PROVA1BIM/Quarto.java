package objetos;

public class Quarto {
	int numeroQuarto;
	double diaria;
	
	public Quarto(int numeroQuarto,double diaria) {
		this.numeroQuarto = numeroQuarto;
		this.diaria = diaria;
	}
	
	public void exibirInformacao() {
		System.out.println("Numero do quarto: " + numeroQuarto);
		System.out.println("Valor diária: " + diaria);
	}
	
}
