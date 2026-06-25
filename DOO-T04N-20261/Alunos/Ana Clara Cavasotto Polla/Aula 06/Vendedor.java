package fag.objetos;

import fag.objetos.Endereco;
import fag.objetos.Loja;

public class Vendedor {
	private String nome;
	private int idade;
	private double salarioBase;
	private double[] salarioRecebido;
	private Loja loja;
	private Endereco endereço;

	public Vendedor() {
	}

	public Vendedor(String nome, int idade, double salarioBase, double[] salarioRecebido, Loja loja,
			Endereco endereço) {
		setNome(nome);
		setIdade(idade);
		setSalarioBase(salarioBase);
		this.salarioRecebido = salarioRecebido;
		this.loja = loja;
		this.endereço = endereço;
	}

	// getters:

	public String getNome() {
		return nome;
	}

	private int getIdade() {
		return idade;
	}

	private double getSalarioBase() {
		return salarioBase;
	}

	public double[] getSalarioRecebido() {
		return salarioRecebido;
	}

	public Loja getLoja() {
		return loja;
	}

	public Endereco getEndereco() {
		return endereço;
	}
	// setters:

	public void setNome(String nome) {
		if (nome != null && !nome.isBlank()) {
			this.nome = nome;
		}
	}

	public void setIdade(int idade) {
		if (idade >= 0) {
			this.idade = idade;
		}
	}

	public void setSalarioBase(double salarioBase) {
		if (salarioBase >= 0) {
			this.salarioBase = salarioBase;
		}
	}

	public void setLoja(Loja loja) {
		if (loja != null) {
			this.loja = loja;
		}
	}

	public void setEndereco(Endereco endereço) {
		if (endereço != null) {
			this.endereço = endereço;
		}
	}

	public void setSalarioRecebido(double[] salarioRecebido) {
		if (salarioRecebido != null && salarioRecebido.length >= 3) {
			this.salarioRecebido = salarioRecebido;
		}
	}

	// métodos:

	public void apresentarSe() {
		System.out.printf("Vendedor: | Nome: %s | Idade: %d anos | Loja: %s\n", nome, idade, loja.getNomeFantasia());
	}

	public double calcularMedia() {
		double soma = 0;

		for (int i = 0; i < salarioRecebido.length; i++) {
			soma += salarioRecebido[i];
		}

		return soma / salarioRecebido.length;
	}

	public double calcularBonus() {
		return salarioBase * 0.2;
	}
}
