public class Gerente extends Funcionario {

    // Método para apresentar gerente
    public void apresentarse() {
        System.out.println("Nome do gerente: " + nome + " | Idade: " + idade +
        " | Loja: " + loja);
    }

    // Método para calcular bonus (implementação polimórfica)
    @Override
    public void calcularBonus() {
        double bonus = salarioBase * 0.35;
        System.out.printf("O bonus do gerente %s é R$ %.2f\n", nome, bonus);
    }

    // Método para exibir dados completos do gerente
    public void exibirDadosGerente() {
        exibirDados(); // Usa o método da classe pai
    }
}