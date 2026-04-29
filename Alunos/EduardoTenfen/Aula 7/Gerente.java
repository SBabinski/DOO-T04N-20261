package Aula2;

public class Gerente extends Funcionario {

	public Gerente(String nome, int idade, Loja loja, Endereco endereco, double salarioBase, double[] salarioRecebido) {
		super(nome, idade, endereco, loja, salarioBase, salarioRecebido);
	}

	public double calcularBonus() {
		return getSalarioBase() * 0.35;

	}

}
