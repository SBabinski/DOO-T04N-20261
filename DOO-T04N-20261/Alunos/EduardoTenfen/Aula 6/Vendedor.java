package Aula2;

public class Vendedor {
	private String nome;
	private int idade;
	private Loja loja;
	private String cidade;
	private String bairro;
	private String rua;
	private double salarioBase;
	private double[] salarioRecebido;

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

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
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

	public void apresentarse() {
		System.out.println("Nome: " + this.getNome());

	}

	public Vendedor(String nome, int idade, Loja loja, String cidade, String bairro, String rua, double salarioBase,
			double[] salarioRecebido) {
		setNome(nome);
		setIdade(idade);
		setLoja(loja);
		setCidade(cidade);
		setBairro(bairro);
		setRua(rua);
		setSalarioBase(salarioBase);
		setSalarioRecebido(salarioRecebido);
	}
}
