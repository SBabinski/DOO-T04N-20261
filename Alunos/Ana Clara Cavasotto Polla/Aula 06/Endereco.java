package fag.objetos;

public class Endereco {
	private String cidade;
	private String bairro;
	private String rua;

	public Endereco() {
	}

	public Endereco(String cidade, String bairro, String rua) {
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		if (cidade != null && !cidade.isBlank()) {
			this.cidade = cidade;
		}
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		if (bairro != null && !bairro.isBlank()) {
			this.bairro = bairro;
		}
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		if (rua != null && !rua.isBlank()) {
			this.rua = rua;
		}
	}

	public void imprimirEndereco() {
		System.out.printf(" Rua %s, Bairro %s, Cidade %s\n", rua, bairro, cidade);
	}
}
