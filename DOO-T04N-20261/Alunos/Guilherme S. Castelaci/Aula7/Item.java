public class Item {
    int id;
    String nome, tipo;
    double valor;

    public Item(int i, String n, String t, double v) {
        id = i;
        nome = n;
        tipo = t;
        valor = v;
    }

    public void gerarDescricao() {
        System.out.println(id + " " + nome + " " + tipo + " " + valor);
    }
}