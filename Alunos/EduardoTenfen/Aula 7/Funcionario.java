package Aula2;

public class Funcionario {
	private String nome;
	private int idade;
	private Endereco endereco;
	private Loja loja;
	private double salarioBase;
	private double[] salarioRecebido;

	public Funcionario(String nome, int idade, Endereco endereco, Loja loja, double salarioBase,
			double[] salarioRecebido) {
		setNome(nome);
		setIdade(idade);
		setEndereco(endereco);
		setLoja(loja);
		setSalarioBase(salarioBase);
		setSalarioRecebido(salarioRecebido);
	}

	public void apresentarse() {

	}

	public double calcularMedia() {
		double soma = 0;
		for (int i = 0; i < salarioRecebido.length; i++) {
			soma += salarioRecebido[i];
		}
		return soma / salarioRecebido.length;
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public double getSalarioBase() {
		return salarioBase;
	}

	public void setSalarioBase(double salarioBase) {
		this.salarioBase = salarioBase;
	}

	public double[] getSalarioRecebido() {
		return salarioRecebido;
	}

	public void setSalarioRecebido(double[] salarioRecebido) {
		this.salarioRecebido = salarioRecebido;
	}

}