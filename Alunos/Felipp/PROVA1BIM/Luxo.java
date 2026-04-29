package prova.objetos;

public class Luxo extends TipoQuarto {
    private boolean possuiVaranda;

    public Luxo(int numeroQuarto, double valorDiaria, boolean possuiVaranda) {
        super(numeroQuarto, valorDiaria);
        this.possuiVaranda = possuiVaranda;
    }

    @Override
    public void listarQuarto() {
        System.out.println("Quarto Luxo - Número: " + getNumeroQuarto() + ", Valor Diária: " + getValorDiaria() + ", Possui Varanda: " + (possuiVaranda ? "Sim" : "Não"));
    }
    
}
