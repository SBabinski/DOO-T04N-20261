package fag.objetos;

import fag.objetos.Endereco;
import fag.objetos.Loja;

public class Cliente {

	private String nome;
	private int idade;
	private Endereco endereço;

	public Cliente() {
	}

	public Cliente(String nome, int idade, Endereco endereço) {
		this.nome = nome;
		this.idade = idade;
		this.endereço = endereço;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		if (nome != null && !nome.isBlank()) {
			this.nome = nome;
		}
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		if (idade >= 0) {
			this.idade = idade;
		}
	}

	public Endereco getEndereco() {
		return endereço;
	}

	public void setEndereco(Endereco endereço) {
		if (endereço != null) {
			this.endereço = endereço;
		}
	}

	public void apresentarSe() {
		System.out.printf("Cliente: | Nome: %s | Idade: %d anos\n", nome, idade);
	}
}
