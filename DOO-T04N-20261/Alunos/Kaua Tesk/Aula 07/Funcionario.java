public class Funcionario {

    String nome;
    int idade;
    String loja;
    Endereco endereco;
    double salarioBase;
    double[] salarioRecebido;

    Funcionario(String nome, int idade, String loja, Endereco endereco, double salarioBase) {
        this.nome            = nome;
        this.idade           = idade;
        this.loja            = loja;
        this.endereco        = endereco;
        this.salarioBase     = salarioBase;
        this.salarioRecebido = new double[]{salarioBase, salarioBase * 1.1, salarioBase * 0.95};
    }

    void apresentarse() {
        System.out.println("Nome : " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Loja : " + loja);
    }

    double calcularMedia() {
        double soma = 0;
        for (double s : salarioRecebido) {
            soma += s;
        }
        return soma / salarioRecebido.length;
    }

    // Cada subclasse define seu próprio bônus
    double calcularBonus() {
        return 0;
    }
}
