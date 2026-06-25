package prova.objetos;

public abstract class TipoQuarto {
    private int numeroQuarto;
    private double valorDiaria;

    public TipoQuarto(int numeroQuarto, double valorDiaria) {
        this.numeroQuarto = numeroQuarto;
        this.valorDiaria = valorDiaria;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public int getNumeroQuarto() {
        return numeroQuarto;
    }

    public abstract void listarQuarto();
}
