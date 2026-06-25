public class Gerente extends Pessoa {

    String loja;
    Endereco endereco;
    double salarioBase;
    double[] salarioRecebido;

    Gerente(String nome, int idade, String loja, Endereco endereco, double salarioBase) {
        super(nome, idade);
        this.loja = loja;
        this.endereco = endereco;
        this.salarioBase = salarioBase;
        this.salarioRecebido = new double[]{salarioBase, salarioBase * 1.15, salarioBase * 1.05};
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
        return salarioBase * 0.35;
    }
}
