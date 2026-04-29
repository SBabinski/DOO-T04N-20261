//Funcionario
public abstract class Funcionario extends Pessoa {
    private String loja;
    private double salarioBase;
    private double[] salarioRecebido;

    public Funcionario() {}

    public String getLoja() { return loja; }
    public void setLoja(String loja) { this.loja = loja; }

    public double getSalarioBase() { return salarioBase; }
    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
        this.salarioRecebido = new double[]{salarioBase, salarioBase+200, salarioBase+300};
    }

    public double[] getSalarioRecebido() { return salarioRecebido; }

    public double calcularMedia() {
        double soma = 0;
        for(double s : salarioRecebido) soma += s;
        return soma / salarioRecebido.length;
    }

    public abstract double calcularBonus();
}
