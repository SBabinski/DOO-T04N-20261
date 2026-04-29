package calculadora;

public class Item {
	
	int id;
	String nome;
	String tipo;
	float valor;
	
	public Item() {
		
	}
	
	public Item(int id, String nome, String tipo, float valor) {
		setId(id);
		setNome(nome);
		setTipo(tipo);
		setValor(valor);
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
	public float getValor() {
		return valor;
	}
	
	public void setValor(float valor) {
		this.valor = valor;
	}
	
	public String gerarDescricao() {
		return "ID: " + id + "| Nome: " + nome + "| Tipo: " + tipo + "| Valor: R$" + valor;
	}
}