public class Gerente extends Funcionario {
    @Override
    public void apresentarSe() {
        System.out.println("Gerente: " + nome + " | Loja: " + loja);
    }

    @Override
    public double calcularBonus() {
        return salarioBase * 0.35;
    }
}