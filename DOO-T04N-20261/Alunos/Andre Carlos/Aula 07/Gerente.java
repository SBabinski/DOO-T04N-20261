import java.util.Arrays;

public class Gerente extends Pessoa {
    String loja;
    double salarioBase;
    double[] salarios = {5000, 5200, 5100};

    public Gerente(String nome, int idade, Endereco endereco, String loja, double salarioBase) {
        super(nome, idade, endereco);
        this.loja = loja;
        this.salarioBase = salarioBase;
    }

    public void apresentarSe() {
        System.out.println("Gerente: " + nome + " | Loja: " + loja);
    }

    public double calcularMedia() {
        return Arrays.stream(salarios).average().orElse(0);
    }

    public double calcularBonus() {
        return salarioBase * 0.35;
    }
}