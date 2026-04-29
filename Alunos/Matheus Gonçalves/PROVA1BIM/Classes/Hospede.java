package fag;

public class Hospede {
	private String Nome;
	private String CPF;
	private String Telefone;
	
	public Hospede(String nome, String cpf, String telefone) {
		setNome(nome);
		setCPF(cpf);
		setTelefone(telefone);
	}


	public String getNome() {
		return Nome;
	}

	public void setNome(String nome) {
		if (!nome.isBlank()) {
			Nome = nome;
		}
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		if (!cPF.isBlank()) {
			CPF = cPF;
		}
	}

	public String getTelefone() {
		return Telefone;
	}

	public void setTelefone(String telefone) {
		if (!telefone.isBlank()) {
			Telefone = telefone;
		}
	}
}