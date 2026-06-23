import java.util.ArrayList;
public class Gerente extends Pessoa {
    private String loja;
    private double salarioBase;
    private ArrayList<Double> salarioRecebido;

    public Gerente(String nome, int idade, Endereco endereco, String loja, double salarioBase) {
        super(nome, idade, endereco);
        this.loja = loja;
        this.salarioBase = salarioBase;
        this.salarioRecebido = new ArrayList<>();
        salarioRecebido.add(3000.0);
        salarioRecebido.add(3200.0);
        salarioRecebido.add(3100.0);
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
        return salarioBase * 0.35;
    }
}