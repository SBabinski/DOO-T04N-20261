package fag;

public class Cliente {
	private String nome;
	private int idade;
	private String cidade;
	private String bairro;
	private String rua;
	
	public Cliente() {}
	
	public Cliente(String nome, int idade, String cidade, String bairro, String rua) {
		this.nome = nome;
		this.idade = idade;
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
	}
	
	public void apresentarse() {
		System.out.println("Nome: " + nome);
		System.out.println("Idade: " + idade);
	}
	
	public String getNome() {
		return nome; 
	}
	public void setNome(String nome) {
		if (!nome.isBlank()) 
			this.nome = nome; 
	}
	
	public int getIdade() { 
		return idade; 
	}
	public void setIdade(int idade) { 
	    if (idade > 0) {
	        this.idade = idade; 
	    }
	}
	
	public String getCidade() { 
		return cidade;
	}
	public void setCidade(String cidade) { 
		if (!cidade.isBlank()) 
			this.cidade = cidade; 
	}
	
	public String getBairro() { 
		return bairro; 
	}
	public void setBairro(String bairro) { 
		if (!bairro.isBlank()) 
			this.bairro = bairro; 
	}
	
	public String getRua() { 
		return rua; 
	}
	public void setRua(String rua) { 
		if (!rua.isBlank()) 
			this.rua = rua;
	}
}