public class Simples extends Quarto{
    private boolean temVentilador;

    public Simples (int numero, double valorDiaria, boolean temVentilador){
        super (numero, valorDiaria);
        this.temVentilador = temVentilador;
    }

    @Override
    public void exibirInfo(){
        super.exibirInfo();
        System.out.println("tem ventilador?" + (temVentilador ? "Sim" : "Não"));
    }
}
