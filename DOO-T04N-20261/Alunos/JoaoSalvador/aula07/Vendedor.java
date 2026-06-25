package calculadora_dona_gabrielinha;

public class Vendedor extends Funcionario {

    public Vendedor(String nome, int idade, Endereco endereco, Loja loja, double salarioBase) {
        super(nome, idade, endereco, loja, salarioBase);

        // Histórico inicial de salários recebidos
        salarioRecebido.add(2900.0);
        salarioRecebido.add(3200.0);
        salarioRecebido.add(3600.0);
    }

    @Override
    public void apresentarSe() {
        System.out.println("Nome: " + nome + ".");
        System.out.println("Idade: " + idade + ".");
        System.out.println("Loja: " + loja.nomeFantasia + ".");
    }

    // Bônus do vendedor: 20% do salário base
    @Override
    public double calcularBonus() {
        return salarioBase * 0.20;
    }
}
