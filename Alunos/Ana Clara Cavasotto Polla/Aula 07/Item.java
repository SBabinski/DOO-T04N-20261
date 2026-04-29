package fag.objetos;

public class Item {

	private int id;
	private String nome;
	private String tipo;
	private double valor;

	public Item(int id, String nome, String tipo, double valor) {
		this.id = validarId(id);
		this.nome = validarTexto(nome, "nome");
		this.tipo = validarTexto(tipo, "tipo");
		this.valor = validarValor(valor);
	}

	// setters

	public void setId(int id) {
		this.id = validarId(id);
	}

	public void setNome(String nome) {
		this.nome = validarTexto(nome, "nome");
	}

	public void setTipo(String tipo) {
		this.tipo = validarTexto(tipo, "tipo");
	}

	public void setValor(double valor) {
		this.valor = validarValor(valor);
	}

	// getters

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getTipo() {
		return tipo;
	}

	public double getValor() {
		return valor;
	}

	// metodos

	public void gerarDescricao() {
		System.out.printf("Item %d | Nome: %s | Tipo: %s | Valor: R$ %.2f%n", id, nome, tipo, valor);
	}

	private int validarId(int id) {
		if (id <= 0) {
			System.out.println("O id do item deve ser maior que zero.");
			return 0;
		}
		return id;
	}

	private double validarValor(double valor) {
		if (valor < 0) {
			System.out.println("O valor do item nao pode ser negativo.");
			return 0;
		}
		return valor;
	}

	private String validarTexto(String valor, String campo) {
		if (valor == null || valor.isBlank()) {
			System.out.println("O campo " + campo + " deve ser preenchido.");
			return "";
		}
		return valor;
	}
}
