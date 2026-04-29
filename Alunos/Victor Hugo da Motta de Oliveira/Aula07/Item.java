public class Item {
    public int id;
    public String nome;
    public String tipo;
    public double preco;

    public void gerarDescricao() {
        System.out.println("ID: " + id + " | " + nome + " (" + tipo + ") - R$ " + preco);
    }
}