public class Funcionario {
    protected String nome;
    protected int idade;
    protected String Loja;
    protected Endereco endereco;
    protected double salarioBase;
    protected double[] salarioRecebido;

    public Funcionario(String nome, int idade, String Loja, Endereco endereco, double salarioBase, double[] salarioRecebido) {
        this.nome = nome;
        this.idade = idade;
        this.Loja = Loja;
        this.endereco = endereco;
        this.salarioBase = salarioBase;
        this.salarioRecebido = salarioRecebido;
    }

    public void apresentarse() {
        System.out.println("Nome: " + nome + ", Idade: " + idade + ", Loja: " + Loja);
    }

    public double calcularMedia() {
        double sum = 0;
        for (double sal : salarioRecebido) {
            sum += sal;
        }
        return sum / salarioRecebido.length;
    }

    public double calcularBonus() {
        return 0;
    }
}