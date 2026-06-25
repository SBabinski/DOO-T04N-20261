public class Luxo extends Quarto{
    private boolean temVaranda;

    public Luxo (int numero, double valorDiaria, boolean temVaranda){
        super(numero, valorDiaria);
        this.temVaranda = temVaranda;
    }

    public void exibirInfo () {
        super.exibirInfo();
        System.out.println("Tem varanda?" + (temVaranda ? "Sim":"Não"));
    }
}
