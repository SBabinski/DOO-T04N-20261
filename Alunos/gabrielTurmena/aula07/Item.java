package classesAtividade;

public class Item {
	int id;
	Double valor;
	String nome;
	String tipo;
	public void gerarDescricao() {
		System.out.println( "  ID: " + id + "Nome: " + nome + " | Valor: " + valor + " | Tipo: " + tipo);
	}
	Item(int id, String nome, Double valor, String tipo) {
		this.id = id;
		this.nome = nome;
		this.valor = valor;
		this.tipo = tipo;}
}
