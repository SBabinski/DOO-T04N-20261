package fag.objetos;

public class Hospede {
	private String nome;
	private String cpf;
	private String telefone;

	public Hospede(String nome, String cpf, String telefone) {
		this.setNome(nome);
		this.setCpf(cpf);
		this.setTelefone(telefone);
	}

	public void exibirInformacoes() {
		System.out.printf("Nome: %s | CPF: %s | Telefone: %s%n", this.nome, this.cpf, this.telefone);
	}

	private boolean possuiApenasZeros(String valor) {
		String apenasNumeros = valor.replace(".", "").replace("-", "").replace(" ", "").replace("(", "").replace(")",
				"");

		if (apenasNumeros.isEmpty()) {
			return false;
		}

		for (int i = 0; i < apenasNumeros.length(); i++) {
			if (apenasNumeros.charAt(i) != '0') {
				return false;
			}
		}

		return true;
	}

	//getters:
	public String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public boolean dadosValidos() {
		if (this.nome == null || this.nome.isEmpty()) {
			return false;
		}
		if (this.cpf == null || this.cpf.isEmpty()) {
			return false;
		}
		if (this.telefone == null || this.telefone.isEmpty()) {
			return false;
		}
		return true;
	}

	//setters:
	public boolean setNome(String nome) {
		if (nome == null) {
			System.out.println("O nome nao pode ser nulo.");
			return false;
		}
		if (nome.trim().isEmpty()) {
			System.out.println("O nome nao pode ficar em branco.");
			return false;
		}
		this.nome = nome.trim();
		return true;
	}

	public boolean setCpf(String cpf) {
		if (cpf == null) {
			System.out.println("O cpf nao pode ser nulo.");
			return false;
		}
		if (cpf.trim().isEmpty()) {
			System.out.println("O cpf nao pode ficar em branco.");
			return false;
		}
		if (possuiApenasZeros(cpf)) {
			System.out.println("O cpf nao pode ser formado apenas por zeros.");
			return false;
		}
		this.cpf = cpf.trim();
		return true;
	}

	public boolean setTelefone(String telefone) {
		if (telefone == null) {
			System.out.println("O telefone nao pode ser nulo.");
			return false;
		}
		if (telefone.trim().isEmpty()) {
			System.out.println("O telefone nao pode ficar em branco.");
			return false;
		}
		if (possuiApenasZeros(telefone)) {
			System.out.println("O telefone nao pode ser formado apenas por zeros.");
			return false;
		}
		this.telefone = telefone.trim();
		return true;
	}
}
