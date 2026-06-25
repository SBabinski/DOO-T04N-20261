package gerenciarHotel;

public class QuartoLuxo extends Quarto {
    private boolean varanda;

    public QuartoLuxo(int numero, double valorDiaria, boolean varanda) {
        super(numero, valorDiaria);
        this.varanda = varanda;
    }

 
    public void exibirInformacoes() {
        System.out.println("Quarto Luxo");
        System.out.println("Número: " + numero);
        System.out.println("Valor diária: " + valorDiaria);
        System.out.println("Varanda: " + (varanda ? "Sim" : "Não"));
    }
}
