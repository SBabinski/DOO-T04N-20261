public class QuartoLuxo extends Quarto{
    
    private boolean varanda;

    public QuartoLuxo(int numero, double valorDiarias, boolean varanda){
        super(numero, valorDiarias);
        this.varanda = varanda;
    }

    @Override
    public void exibirInfo() {
        System.out.println("Quarto Luxo | Nr: "+ numero+ " Diarias: " + valorDiarias+ "Varanda: "+  (varanda? "sim":"nao"));
    }
}
