
public class QuartoLuxo extends Quartos {

    private boolean varanda;
    // construtor.
    public QuartoLuxo(int numero, double preco, boolean varanda) {
        super(numero, preco);
        this.varanda = varanda;
    }
    // getter.
    public boolean isVaranda() {
        return varanda;
    }
    // método para exibir os detalhes do quarto.
    @Override
    public void exibirDetalhes() {
        System.out.println("Quarto Luxo - Número: " + getNumero()
                + ", Valor da diária: " + getPreco()
                + ", Varanda: " + (varanda ? "Sim" : "Não"));
    }
}
