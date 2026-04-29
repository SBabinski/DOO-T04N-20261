import java.util.ArrayList;

public class Gerente extends Pessoa {

    private String loja;
    private Endereco endereco;
    private double salarioBase;
    private ArrayList<Double> salarioRecebido;

    public Gerente(String nome, int idade, String loja, Endereco endereco, double salarioBase) {
        super(nome, idade);
        this.loja = loja;
        this.endereco = endereco;
        this.salarioBase = salarioBase;

        this.salarioRecebido = new ArrayList<>();
        salarioRecebido.add(3000.0);
        salarioRecebido.add(3200.0);
        salarioRecebido.add(3400.0);
    }

    @Override
    public void apresentarSe() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Loja: " + loja);
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