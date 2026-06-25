package calculadora_dona_gabrielinha;

public class Gerente extends Funcionario {

    public Gerente(String nome, int idade, Endereco endereco, Loja loja, double salarioBase) {
        super(nome, idade, endereco, loja, salarioBase);

        // Histórico inicial de salários recebidos
        salarioRecebido.add(5000.0);
        salarioRecebido.add(5500.0);
        salarioRecebido.add(6000.0);
    }

    @Override
    public void apresentarSe() {
        System.out.println("Nome: " + nome + ".");
        System.out.println("Idade: " + idade + ".");
        System.out.println("Loja: " + loja.nomeFantasia + ".");
    }

    // Bônus do gerente: 35% do salário base
    @Override
    public double calcularBonus() {
        return salarioBase * 0.35;
    }
}
