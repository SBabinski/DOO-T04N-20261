package prova01;

public class QuartoSimples extends Quarto {
    private boolean temVentilador;

    public QuartoSimples(int numero, double valorDiaria, boolean temVentilador) {
        super(numero, valorDiaria); 
        this.temVentilador = temVentilador;
    }

    @Override
    public void exibirInformacoes() {
        String ventilador = temVentilador ? "Sim" : "Não";
        System.out.println("Quarto Simples -> Número: " + numero + 
                           " | Diária: R$ " + valorDiaria + 
                           " | Ventilador: " + ventilador);
    }
}