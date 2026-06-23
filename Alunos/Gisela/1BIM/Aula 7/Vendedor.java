//Vendedor
public class Vendedor extends Funcionario {
    @Override
    public double calcularBonus() {
        return getSalarioBase() * 0.2;
    }
}
