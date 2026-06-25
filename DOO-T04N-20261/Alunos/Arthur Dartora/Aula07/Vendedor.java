package Aula07;

public class Vendedor extends Pessoa {
	
	
	Loja loja;
	double salarioBase;
	double[] salarioRecebido;
	
	 public Vendedor(String nome, int idade, Endereco endereco, Loja loja, double salarioBase) {
	        super(nome, idade, endereco);
	        this.loja = loja;
	        this.salarioBase = salarioBase;

	        salarioRecebido = new double[]{1500, 1600, 1700};
	}
	
	@Override
	public void apresentarSe() {
		System.out.println(nome + " " + idade + " " + loja.nomeFantasia);
	}
	public double calcularMedia() {
		double soma = 0;
		for(double sal:salarioRecebido) soma += sal;
		return soma / salarioRecebido.length;
	}
	public double calcularBonus() {
		return salarioBase * 0.2;
	}
	
}
