
public abstract class Quartos {

    private int numero;
    private double preco;
    // construtor.
    public Quartos(int numero, double preco) {
        this.numero = numero;
        this.preco = preco;
    }
    // getters.
    public int getNumero() {
        return numero;
    }

    public double getPreco() {
        return preco;
    }
    // método abstrato para exibir os detalhes do quarto.
    public abstract void exibirDetalhes();
}
