public class Vendedor extends Pessoa {

    String loja;
    Endereco endereco;
    double salarioBase;
    double[] salarioRecebido;

    Vendedor(String nome, int idade, String loja, Endereco endereco, double salarioBase) {
        super(nome, idade);
        this.loja = loja;
        this.endereco = endereco;
        this.salarioBase = salarioBase;
        this.salarioRecebido = new double[]{salarioBase, salarioBase * 1.1, salarioBase * 0.95};
    }

    @Override
    public void apresentarse() {
        super.apresentarse();
        System.out.println("Loja: " + loja);
        endereco.apresentarLogradouro();
    }

    public double calcularMedia() {
        double soma = 0;
        for (double salario : salarioRecebido) {
            soma += salario;
        }
        return soma / salarioRecebido.length;
    }

    public double calcularBonus() {
        return salarioBase * 0.2;
    }
}
