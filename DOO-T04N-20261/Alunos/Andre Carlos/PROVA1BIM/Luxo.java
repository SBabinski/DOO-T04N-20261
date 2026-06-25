public class Luxo extends Quarto{
    private boolean varanda;


    public Luxo(int num, double valorDiaria, boolean varanda){
        super( num,valorDiaria);
        this.varanda = varanda;
    }

    @Override
    public void exibirInfo(){
        System.out.println("Quarto Luxo - N:  " + num);
        System.out.println("Diaria: " + valorDiaria);
        System.out.println("Varanda?" + (varanda ? "Sim" : "Nao"));
    }
}
