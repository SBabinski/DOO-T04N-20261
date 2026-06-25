package fag.objetos;

public abstract class Pessoa {

	private String nome;
	private int idade;
	private Endereco endereco;
	private Loja loja;

	protected Pessoa(String nome, int idade, Endereco endereco, Loja loja) {
		this.nome = validarTexto(nome, "nome");
		this.idade = validarIdade(idade);
		this.endereco = validarEndereco(endereco);
		this.loja = validarLoja(loja);
	}

	// setters

	public void setNome(String nome) {
		this.nome = validarTexto(nome, "nome");
	}

	public void setIdade(int idade) {
		this.idade = validarIdade(idade);
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = validarEndereco(endereco);
	}

	public void setLoja(Loja loja) {
		this.loja = validarLoja(loja);
	}

	// getters

	public String getNome() {
		return nome;
	}

	public int getIdade() {
		return idade;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public Loja getLoja() {
		return loja;
	}

	// metodos

	public abstract void apresentar();

	private String validarTexto(String valor, String campo) {
		if (valor == null || valor.isBlank()) {
			System.out.println("O campo " + campo + " deve ser preenchido.");
			return "";
		}
		return valor;
	}

	private int validarIdade(int idade) {
		if (idade < 0) {
			System.out.println("A idade nao pode ser negativa.");
			return 0;
		}
		return idade;
	}

	private Endereco validarEndereco(Endereco endereco) {
		if (endereco == null) {
			System.out.println("O endereco deve ser informado.");
			return this.endereco;
		}
		return endereco;
	}

	private Loja validarLoja(Loja loja) {
		if (loja == null) {
			System.out.println("A loja deve ser informada.");
			return this.loja;
		}
		return loja;
	}
}
