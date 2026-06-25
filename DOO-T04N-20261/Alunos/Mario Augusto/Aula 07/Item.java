public class Item {

    int id;
    String nome;
    String tipo;
    double valor;

    Item(int id, String nome, String tipo, double valor) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
    }

    public void gerarDescricao() {
        System.out.println("Item #" + id +
                " | Nome: " + nome +
                " | Tipo: " + tipo +
                String.format(" | Valor: R$ %.2f", valor));
    }
}
