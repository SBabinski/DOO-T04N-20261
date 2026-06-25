public class Vendedor extends Funcionario {
    @Override
    public void apresentarSe() {
        System.out.println("Vendedor: " + nome + " | Loja: " + loja);
    }

    @Override
    public double calcularBonus() {
        return salarioBase * 0.20;
    }
}