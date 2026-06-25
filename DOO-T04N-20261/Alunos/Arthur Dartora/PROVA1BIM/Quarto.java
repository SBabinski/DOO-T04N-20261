
public class Quarto {
	int num;
	double valorDia;
	
	public Quarto(int num, double valorDia) {
		this.num = num;
		this.valorDia = valorDia;
	}
	
	public void exibirQ() {
		System.out.println("Quarto N°: " + num);
		System.out.println("Valor por dia: R$" + valorDia);
	}
}
