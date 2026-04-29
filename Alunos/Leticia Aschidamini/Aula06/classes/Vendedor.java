import java.util.ArrayList;

public class Vendedor {
    String nome;
    int idade;
    String loja;
    String cidade;
    String bairro;
    String rua;
    double salarioBase;
    ArrayList<Double> salarioRecebido = new ArrayList<>();

    public Vendedor(String nome, int idade, String loja, String cidade, String bairro, String rua, double salarioBase) {
        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.salarioBase = salarioBase;

        salarioRecebido.add(2000.0);
        salarioRecebido.add(2200.0);
        salarioRecebido.add(2100.0);
    }

    public void apresentarSe() {
        System.out.println("Nome: " + nome + ", Idade: " + idade + ", Loja: " + loja);
    }

    public double calcularMedia() {
        double soma = 0;
        for (double s : salarioRecebido) {
            soma += s;
        }
        return soma / salarioRecebido.size();
    }

    public double calcularBonus() {
        return salarioBase * 0.2;
    }
}