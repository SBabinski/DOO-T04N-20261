public class Item {

    int id;
    String nome;
    String tipo;
    double valor;

    Item(int id, String nome, String tipo, double valor) {
        this.id    = id;
        this.nome  = nome;
        this.tipo  = tipo;
        this.valor = valor;
    }

    void gerarDescricao() {
        System.out.println("=== Item ===");
        System.out.println("ID   : " + id);
        System.out.println("Nome : " + nome);
        System.out.println("Tipo : " + tipo);
        System.out.printf( "Valor: R$ %.2f%n", valor);
    }
}
