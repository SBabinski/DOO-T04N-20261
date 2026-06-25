public class QuartoLuxo extends Quartos{
     public boolean temVaranda;

    public QuartoLuxo(int numQuarto, double vlrDiaria,  boolean temVaranda){
        super(numQuarto,vlrDiaria);
        this.temVaranda = temVaranda;
    }

    @Override
    public void exibirInfo() {
        super.exibirInfo();
        System.out.println("Tem Ventilador? " + (temVaranda ? "Sim" : "Não"));
    }


    
}
