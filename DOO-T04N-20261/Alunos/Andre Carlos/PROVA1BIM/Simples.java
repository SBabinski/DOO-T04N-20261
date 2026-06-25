public class Simples extends Quarto{
    private boolean ventilador;


    public Simples(int num, double valorDiaria, boolean ventilador) {
        super(num, valorDiaria);
        this.ventilador = ventilador;

    }
    @Override
    public void exibirInfo(){
        System.out.println("Quarto Simples - N:  " + num);
        System.out.println("Diaria:  " + valorDiaria);
        System.out.println("ventilador? (Sim/Nao)  " + (ventilador ? "Sim" : "Nao"));

    }

}
