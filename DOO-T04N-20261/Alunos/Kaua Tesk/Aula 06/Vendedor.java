public class Vendedor {

    String nome;
    int idade;
    String loja;
    String cidade;
    String bairro;
    String rua;
    double salarioBase;
    double[] salarioRecebido;

    Vendedor(String nome, int idade, String loja, String cidade, String bairro, String rua, double salarioBase) {
        this.nome            = nome;
        this.idade           = idade;
        this.loja            = loja;
        this.cidade          = cidade;
        this.bairro          = bairro;
        this.rua             = rua;
        this.salarioBase     = salarioBase;
        // Três lançamentos de salário pré-definidos
        this.salarioRecebido = new double[]{salarioBase, salarioBase * 1.1, salarioBase * 0.95};
    }

    void apresentarse() {
        System.out.println("=== Vendedor ===");
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

    double calcularBonus() {
        return salarioBase * 0.2;
    }
}
