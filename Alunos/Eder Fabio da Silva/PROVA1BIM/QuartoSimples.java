
public class QuartoSimples extends Quartos {

    private boolean ventilador;
    // construtor.
    public QuartoSimples(int numero, double preco, boolean ventilador) {
        super(numero, preco);
        this.ventilador = ventilador;
    }
    // getter.
    public boolean isVentilador() {
        return ventilador;
    }
    // método para exibir os detalhes do quarto.
    @Override
    public void exibirDetalhes() {
        System.out.println("Quarto Simples - Número: " + getNumero()
                + ", Valor da diária: " + getPreco()
                + ", Ventilador: " + (ventilador ? "Sim" : "Não"));
    }
}
