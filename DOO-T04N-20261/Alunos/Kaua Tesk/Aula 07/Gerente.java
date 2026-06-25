public class Gerente extends Funcionario {

    Gerente(String nome, int idade, String loja, Endereco endereco, double salarioBase) {
        super(nome, idade, loja, endereco, salarioBase);
    }

    @Override
    void apresentarse() {
        System.out.println("=== Gerente ===");
        super.apresentarse();
    }

    @Override
    double calcularBonus() {
        return salarioBase * 0.35;
    }
}
