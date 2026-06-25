public abstract class Funcionario extends Pessoa {
    protected String loja;
    protected double salarioBase;
    protected double[] salarioRecebido = new double[3];

    public double calcularMedia() {
        return (salarioRecebido[0] + salarioRecebido[1] + salarioRecebido[2]) / 3.0;
    }

    public abstract double calcularBonus();
}