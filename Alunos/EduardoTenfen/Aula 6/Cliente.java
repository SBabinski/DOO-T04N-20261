package Aula2;

public class Cliente {

	private String nome;
	private int idade;
	private String cpf;
	private String cidade;
	private String bairro;
	private String rua;

	public Cliente(String nome, int idade, String cpf, String cidade, String bairro, String rua) {
		this.nome = nome;
		this.idade = idade;
		this.cpf = cpf;
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
	}

	public void apresentarseCliente() {
		System.out.println("--- Dados do Cliente ---");
		System.out.println("Nome: " + getNome()); //
		System.out.println("Idade: " + getIdade());
		System.out.println("CPF: " + getCpf());
		System.out.println("Endereço: " + getRua() + ", " + getBairro() + " - " + getCidade());
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

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}
}