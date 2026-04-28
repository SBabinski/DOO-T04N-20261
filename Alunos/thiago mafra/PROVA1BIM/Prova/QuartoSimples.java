package Prova;

public class QuartoSimples extends Quarto {
    private boolean ventilador;

    public QuartoSimples(int numero, double valorDiaria, boolean ventilador) {
        super(numero, valorDiaria);
        this.ventilador = ventilador;
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("Quarto Simples - Nº: " + numero);
        System.out.println("Diária: R$ " + valorDiaria);
        System.out.println("Ventilador: " + (ventilador ? "Sim" : "Não"));
    }
}