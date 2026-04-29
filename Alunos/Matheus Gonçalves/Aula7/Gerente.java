package fag;

public class Gerente {
	private String Nome;
	private int Idade;
	private double SalarioBase;
	private double SalarioRecebido;
	private Endereco endereco;
	private Loja Loja;
	
	public Gerente() {}
	
	public String getNome() {
		return Nome;
	}
	
	public void setNome(String nome) {
		if (nome == null || nome.trim().isEmpty()) {
			System.out.println("Nome inválido!");
		} else {
			this.Nome = nome;
		}
	}

	public int getIdade() {
		return Idade;
	}
	
	public void setIdade(int idade) {
		if (idade <= 0) {
			System.out.println("Idade inválida!");
		} else {
			this.Idade = idade;
		}
	}

	public Loja getLoja() {
		return Loja;
	}
	
	public void setLoja(Loja loja) {
		if (loja == null) {
			System.out.println("Loja inválida!");
		} else {
			this.Loja = loja;
		}
	}

	public double getSalarioBase() {
		return SalarioBase;
	}
	
	public void setSalarioBase(double salarioBase) {
		if (salarioBase <= 0) {
			System.out.println("Salário base inválido!");
		} else {
			this.SalarioBase = salarioBase;
		}
	}

	public double getSalarioRecebido() {
		return SalarioRecebido;
	}

	public void setSalarioRecebido(double salarioRecebido) {
		if (salarioRecebido <= 0) {
			System.out.println("Salário recebido inválido!");
		} else {
			this.SalarioRecebido = salarioRecebido;
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
