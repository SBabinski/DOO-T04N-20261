package Aula2;

public class Vendedor extends Funcionario {

	public Vendedor(String nome, int idade, Loja loja, Endereco endereco, double salarioBase,
			double[] salarioRecebido) {
		super(nome, idade, endereco, loja, salarioBase, salarioRecebido);
	}

	public double calcularBonus() {
		return getSalarioBase() * 0.2;
	}

}
