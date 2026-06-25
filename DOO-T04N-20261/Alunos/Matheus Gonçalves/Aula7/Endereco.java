package fag;

public class Endereco {
	private String estado;
	private String cidade;
	private String bairro;
	private int numero;
	private String complemento;

	public Endereco() {}

	public Endereco(String estado, String cidade, String bairro, int numero, String complemento) {
		setEstado(estado);
		setCidade(cidade);
		setBairro(bairro);
		setNumero(numero);
		setComplemento(complemento);
	}

	// GETTERS
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		if (estado == null || estado.trim().isEmpty()) {
			System.out.println("Estado inválido!");
		} else {
			this.estado = estado;
		}
	}

	public String getCidade() {
		return cidade;
	}
	
	public void setCidade(String cidade) {
		if (cidade == null || cidade.trim().isEmpty()) {
			System.out.println("Cidade inválida!");
		} else {
			this.cidade = cidade;
		}
	}

	public String getBairro() {
		return bairro;
	}
	
	public void setBairro(String bairro) {
		if (bairro == null || bairro.trim().isEmpty()) {
			System.out.println("Bairro inválido!");
		} else {
			this.bairro = bairro;
		}
	}

	public int getNumero() {
		return numero;
	}
	
	public void setNumero(int numero) {
		if (numero <= 0) {
			System.out.println("Número inválido!");
		} else {
			this.numero = numero;
		}
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		if (complemento == null || complemento.trim().isEmpty()) {
			System.out.println("Complemento inválido!");
		} else {
			this.complemento = complemento;
		}
	}

	public void apresentarLogradouro() {
		System.out.println(
			"Endereço: " + bairro + ", " + numero +
			" (" + complemento + ") - " +
			cidade + " / " + estado
		);
	}
}