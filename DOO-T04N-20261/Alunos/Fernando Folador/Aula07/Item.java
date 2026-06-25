public class Item {
    public int id;
    public String nome;
    public String tipo;
    public double valor;

    public double getValor() {
        return valor;
    }

    public void gerarDescricao() {
        System.out.println(id + " - " + nome + " (" + tipo + ") R$" + valor);
    }
}