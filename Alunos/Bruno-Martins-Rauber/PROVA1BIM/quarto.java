package principal.classes;

public abstract class quarto {
    protected double valor;
    protected int numero_quarto;
    
    public quarto(double valor, int numero_quarto) {
        this.valor = valor;
        this.numero_quarto = numero_quarto;
    }

    public double getValor() {
        return valor;
    }

    public void mostrarDados() {
        System.out.print("Quarto: " + numero_quarto + " | Diária R$: " + valor);
    }
}