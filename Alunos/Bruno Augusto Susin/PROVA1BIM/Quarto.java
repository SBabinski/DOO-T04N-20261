public class Quarto {
    protected int numero;
    protected double diaria;


    public Quarto(int numero, double diaria) {
        this.numero = numero;
        this.diaria = diaria;
    }

    public void exibirInfo() {
        System.out.println("Numero do quarto : " + numero);
        System.out.println("Valor da diaria : " + diaria);
    }

    public int getNumero() {
        return numero;
    }

    public double getDiaria() {
        return diaria;
    }
}