public class Vendedor {

    String nome;
    int idade;

    Loja loja;

    String cidade;
    String bairro;
    String rua;

    double salarioBase;

    double[] salarioRecebido = new double[3];

    public Vendedor(String nome,
                    int idade,
                    Loja loja,
                    String cidade,
                    String bairro,
                    String rua,
                    double salarioBase) {

        this.nome = nome;
        this.idade = idade;
        this.loja = loja;

        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;

        this.salarioBase = salarioBase;
    }

    public void apresentarSe() {

        System.out.println("=== VENDEDOR ===");
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);

        if (loja != null) {
            System.out.println("Loja: " + loja.nomeFantasia);
        }
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