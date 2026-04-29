public class Item {
    public int id;
    public String nome;
    public String tipo;
    public float valor;

    public Item(int id, String nome, String tipo, float valor) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
    }

    public void gerarDescricao() {
        System.out.println("Item ID: " + id + " | " + nome + " (" + tipo + ") - R$ " + valor);
    }
}