public class QuartoSimples extends Quartos{
    public boolean temVentilador;

    public QuartoSimples(int numQuarto, double vlrDiaria,  boolean temVentilador){
        super(numQuarto,vlrDiaria);
        this.temVentilador = temVentilador;
    }

    @Override
    public void exibirInfo() {
        super.exibirInfo();
        System.out.println("Tem Ventilador? " + (temVentilador ? "Sim" : "Não"));
    }


    
}
