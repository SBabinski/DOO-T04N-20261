package prova.objetos;

public class Simples extends TipoQuarto {

    private boolean possuiVentilador;

    public Simples(int numeroQuarto, double valorDiaria, boolean possuiVentilador) {
        super(numeroQuarto, valorDiaria);
        this.possuiVentilador = possuiVentilador;
    }

    @Override
    public void listarQuarto() {
        System.out.println("Quarto Simples - Número: " + getNumeroQuarto() + ", Valor Diária: " + getValorDiaria() + ", Possui Ventilador: " + (possuiVentilador ? "Sim" : "Não"));
    }
}
