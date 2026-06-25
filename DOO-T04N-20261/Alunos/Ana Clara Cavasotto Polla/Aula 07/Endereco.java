package fag.objetos;

public class Endereco {

	private String estado;
	private String cidade;
	private String bairro;
	private String numero;
	private String complemento;

	public Endereco(String estado, String cidade, String bairro, String numero, String complemento) {
		this.estado = validarTexto(estado, "estado");
		this.cidade = validarTexto(cidade, "cidade");
		this.bairro = validarTexto(bairro, "bairro");
		this.numero = validarTexto(numero, "numero");
		this.complemento = validarTexto(complemento, "complemento");
	}

	// setters

	public void setEstado(String estado) {
		this.estado = validarTexto(estado, "estado");
	}

	public void setCidade(String cidade) {
		this.cidade = validarTexto(cidade, "cidade");
	}

	public void setBairro(String bairro) {
		this.bairro = validarTexto(bairro, "bairro");
	}

	public void setNumero(String numero) {
		this.numero = validarTexto(numero, "numero");
	}

	public void setComplemento(String complemento) {
		this.complemento = validarTexto(complemento, "complemento");
	}

	// getters

	public String getEstado() {
		return estado;
	}

	public String getCidade() {
		return cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public String getNumero() {
		return numero;
	}

	public String getComplemento() {
		return complemento;
	}

	// metodos

	public void apresentarLogradouro() {
		System.out.printf("Estado: %s | Cidade: %s | Bairro: %s | Numero: %s | Complemento: %s%n",
				estado, cidade, bairro, numero, complemento);
	}

	public String toString() {
		return String.format("Estado: %s | Cidade: %s | Bairro: %s | Numero: %s | Complemento: %s",
				estado, cidade, bairro, numero, complemento);
	}

	private String validarTexto(String valor, String campo) {
		if (valor == null || valor.isBlank()) {
			System.out.println("O campo " + campo + " deve ser preenchido.");
			return "";
		}
		return valor;
	}
}
