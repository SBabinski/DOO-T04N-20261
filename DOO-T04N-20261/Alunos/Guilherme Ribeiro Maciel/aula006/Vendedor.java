package calculadora;

import java.util.ArrayList;

public class Vendedor {
	String nome;
	int idade;
	String cidade;
	String rua;
	String bairro;
	float salarioBase;
	int loja;
	ArrayList<Float> salarioRecebido = new ArrayList<>();

	public Vendedor() {
		
	}
	
	public Vendedor(String nome, int idade, String cidade, String bairro, String rua, int loja) {
		setNome(nome);
		setIdade(idade);
		setCidade(cidade);
		setRua(rua);
		setBairro(bairro);
		setLoja(loja);
	}
	
	public ArrayList<Float> getSalarioRecebido(){
		return salarioRecebido;
	}
	
	public void setSalarioRecebido(float salarioRecebido) {
		this.salarioRecebido.add(salarioRecebido);
	}
	
	public int getLoja() {
		return loja;
	}

	public void setLoja(int loja) {
		this.loja = loja;
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
	
	public String getCidade() {
		return cidade;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getRua() {
		return rua;
	}
	
	public void setRua(String rua) {
		this.rua = rua;
	}
	
	public String getBairro() {
		return bairro;
	}
	
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	
	public float getSalarioBase() {
		return salarioBase;
	}
	
	public void setSalarioBase(float salarioBase) {
		this.salarioBase = salarioBase;
	}
	
	public float calcularBonus() {
		return salarioBase * 0.2f;
	}
	
	public String apresentarse() {
		return "Nome: " + nome + "|| Idade: " + idade + "|| Loja: " + (loja+1);
	}
}
