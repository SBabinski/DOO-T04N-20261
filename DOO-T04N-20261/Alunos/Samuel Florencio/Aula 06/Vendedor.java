import java.util.ArrayList;

public class Vendedor {
	String nome;
	int idade;
	String loja;
	String cidade; 
	String bairro;
	String rua;
	double salBase;
	ArrayList<Double> salrec = new ArrayList<>();
	public void apresentar() {
		System.out.println("Vendedor: " +nome);
		System.out.println("Idade: "+ idade);
		System.out.println("Cidade: "+cidade+",  Bairro: "+bairro+",  Rua: "+rua+".");
		System.out.println("Loja: " +loja+".\n");
	}
	public double calcularMedia() {
		double soma = 0;
		for (double s : salrec) {
			soma += s;
		}
		return soma/salrec.size();
	}
	public double calcularBonus () {
		return salBase * 0.2;
	} 
	public double atualizarSalario (boolean bonus) {
		double salFinal = salBase-salBase*0.14;
		if (bonus) {
			salFinal+=calcularBonus()
;		}
		salrec.add(salFinal);
		return salFinal;
	}
}
