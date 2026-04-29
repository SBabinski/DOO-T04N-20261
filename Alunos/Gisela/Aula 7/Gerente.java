//Gerente
public class Gerente extends Funcionario {
    @Override
    public double calcularBonus() {
        return getSalarioBase() * 0.35;
    }
}
