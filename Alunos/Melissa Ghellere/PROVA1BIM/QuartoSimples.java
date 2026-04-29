package Alunos.Melissa_Ghellere.PROVA1BIM;

public class QuartoSimples extends Quarto {
    public QuartoSimples(int numero, double valorDiaria, boolean disponivel) {
        super(numero, valorDiaria, disponivel);
    }

    @Override
    public void exibirInformacoes() {
        System.out.println("Quarto Simples nº " + numero + " | Diária: R$" + valorDiaria);
    }
}
