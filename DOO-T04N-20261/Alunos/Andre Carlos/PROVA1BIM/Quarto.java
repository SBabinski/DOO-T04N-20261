public  abstract class Quarto {

    int num;
    double valorDiaria;

    public Quarto(int num, double valorDiaria){
        this.num = num;
        this.valorDiaria = valorDiaria;
    }

    public double getValorDiaria() {
        return valorDiaria;

    }

    public abstract void exibirInfo();

}
