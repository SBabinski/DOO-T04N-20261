public class Item {
    public int id;
    public String nome;
    public String tipo;
    public double valor;

    public Item(int id, String nome, String tipo, double valor) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
    }

    public void gerarDescricao() {
        System.out.printf("Item #%d | Nome: %s | Tipo: %s | Valor: R$ %.2f%n",
                id, nome, tipo, valor);
    }
}
