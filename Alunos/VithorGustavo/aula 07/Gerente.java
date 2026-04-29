public class Gerente extends Funcionario {

    public Gerente(String nome, int idade, String Loja, Endereco endereco, double salarioBase, double[] salarioRecebido) {
        super(nome, idade, Loja, endereco, salarioBase, salarioRecebido);
    }

    @Override
    public double calcularBonus() {
        return salarioBase * 0.35;
    }
}