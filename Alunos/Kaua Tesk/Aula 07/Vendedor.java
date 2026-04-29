public class Vendedor extends Funcionario {

    Vendedor(String nome, int idade, String loja, Endereco endereco, double salarioBase) {
        super(nome, idade, loja, endereco, salarioBase);
    }

    @Override
    void apresentarse() {
        System.out.println("=== Vendedor ===");
        super.apresentarse();
    }

    @Override
    double calcularBonus() {
        return salarioBase * 0.2;
    }
}
