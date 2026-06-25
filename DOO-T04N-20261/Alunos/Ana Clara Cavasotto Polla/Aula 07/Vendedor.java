package fag.objetos;

import java.util.List;

public class Vendedor extends Funcionario {

	public Vendedor(String nome, int idade, Endereco endereco, Loja loja, double salarioBase,
			List<Double> salarioRecebido) {
		super(nome, idade, endereco, loja, salarioBase, salarioRecebido);
	}

	// metodos

	@Override
	public void apresentar() {
		System.out.printf("Vendedor - Nome: %s | Idade: %d | Loja: %s%n",
				getNome(), getIdade(), getLoja().getNomeFantasia());
	}

	@Override
	public double calcularBonus() {
		return getSalarioBase() * 0.2;
	}
}
