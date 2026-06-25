package fag;

public class Cliente {
	private String nome;
	private int idade;
	private Endereco endereco;
	
	public Cliente() {}
	
	public Cliente(String nome, int idade, Endereco endereco) {
		this.nome = nome;
		this.idade = idade;
		setEndereco(endereco);
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
	
	public Endereco getEndereco() {
	    return endereco;
	}

	public void setEndereco(Endereco endereco) {
	    if (endereco == null) {
	        System.out.println("Endereço inválido!");
	    } else {
	        this.endereco = endereco;
	    }
	}
}