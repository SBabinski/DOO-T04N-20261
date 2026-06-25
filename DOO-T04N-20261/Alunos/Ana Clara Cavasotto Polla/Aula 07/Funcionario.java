package fag.objetos;

import java.util.ArrayList;
import java.util.List;

public abstract class Funcionario extends Pessoa {

	private double salarioBase;
	private List<Double> salarioRecebido;

	protected Funcionario(String nome, int idade, Endereco endereco, Loja loja, double salarioBase,
			List<Double> salarioRecebido) {
		super(nome, idade, endereco, loja);
		this.salarioBase = validarSalarioBase(salarioBase);
		this.salarioRecebido = validarSalarios(salarioRecebido);
	}

	// setters

	public void setSalarioBase(double salarioBase) {
		this.salarioBase = validarSalarioBase(salarioBase);
	}

	public void setSalarioRecebido(List<Double> salarioRecebido) {
		this.salarioRecebido = validarSalarios(salarioRecebido);
	}

	// getters

	public double getSalarioBase() {
		return salarioBase;
	}

	public List<Double> getSalarioRecebido() {
		return new ArrayList<>(salarioRecebido);
	}

	// metodos

	public double calcularMedia() {
		double soma = 0;

		for (Double salario : salarioRecebido) {
			soma += salario;
		}

		return soma / salarioRecebido.size();
	}

	public abstract double calcularBonus();

	private List<Double> validarSalarios(List<Double> salarios) {
		if (salarios == null || salarios.size() < 3) {
			System.out.println("O funcionario deve ter no minimo tres salarios recebidos.");
			return new ArrayList<>();
		}

		List<Double> salariosValidados = new ArrayList<>();
		for (Double salario : salarios) {
			if (salario == null || salario < 0) {
				System.out.println("Os salarios recebidos devem ser valores validos e nao negativos.");
				return new ArrayList<>();
			}
			salariosValidados.add(salario);
		}

		return salariosValidados;
	}

	private double validarSalarioBase(double salarioBase) {
		if (salarioBase < 0) {
			System.out.println("O salario base não pode ser negativo.");
			return 0;
		}
		return salarioBase;
	}
}
