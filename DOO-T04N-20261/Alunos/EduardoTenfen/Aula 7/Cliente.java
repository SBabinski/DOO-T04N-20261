package Aula2;

public class Cliente {

	private String nome;
	private int idade;
	private String cpf;
	private Endereco endereco;

	public Cliente(String nome, int idade, String cpf, Endereco endereco) {
		this.nome = nome;
		this.idade = idade;
		this.cpf = cpf;
		this.endereco = endereco;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public void apresentarse() {
		System.out.println("--- Dados do Cliente ---");
		System.out.println("Nome: " + getNome()); //
		System.out.println("Idade: " + getIdade());
		System.out.println("CPF: " + getCpf());
		System.out.println("Endereço: " + getEndereco());
		System.out.println();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {

		this.nome = nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}