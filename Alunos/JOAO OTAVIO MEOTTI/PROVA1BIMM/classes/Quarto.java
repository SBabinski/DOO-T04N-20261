public class Quarto {
    int numero;
    double diaria;
    public Quarto(int numero, double diaria) {
        this.numero = numero;
        this.diaria = diaria;
    }
    public void mostrar() {
        System.out.println("Quarto: " + numero);
        System.out.println("Diária: " + diaria);
    }
}