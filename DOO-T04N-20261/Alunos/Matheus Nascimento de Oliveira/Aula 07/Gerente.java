public class Gerente extends Pessoa {
    double salarioBase;
    double[] salarioRecebido = new double[3];

    public Gerente(String nome, int idade, String loja, Endereco endereco, double salarioBase) {
        super(nome, idade, loja, endereco);
        this.salarioBase = salarioBase;
        // Simulando lancamentos iniciais
        this.salarioRecebido[0] = salarioBase;
        this.salarioRecebido[1] = 2200.0;
        this.salarioRecebido[2] = 2500.0;
    }

    @Override
    public void apresentarse() {
        System.out.println("Nome: " + nome + " | Idade: " + idade + " | Loja: " + loja);
    }

    public double calcularMedia() {
        double total = 0;
        for (double s : salarioRecebido) {
            total += s;
        }
        return total / 3.0;
    }

    public double calcularBonus() {
        return salarioBase * 0.35;
    }
}