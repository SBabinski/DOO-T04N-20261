package hotel;

public abstract class Quarto {

    int numero;
    double diaria;

    public Quarto(int numero, double diaria) {
        this.numero = numero;
        this.diaria = diaria;
    }

    public abstract void mostrar();
}