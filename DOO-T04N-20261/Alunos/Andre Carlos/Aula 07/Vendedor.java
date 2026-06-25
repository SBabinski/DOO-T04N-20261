import java.util.Arrays;

public class Vendedor extends Pessoa {
    String loja;
    double salarioBase;
    double[] salarios = {2000, 2100, 2200};

    public Vendedor(String nome, int idade, Endereco endereco, String loja, double salarioBase) {
        super(nome, idade, endereco);
        this.loja = loja;
        this.salarioBase = salarioBase;
    }

    public void apresentarSe() {
        System.out.println("Vendedor: " + nome + " | Loja: " + loja);
    }

    public double calcularMedia() {
        return Arrays.stream(salarios).average().orElse(0);
    }

    public double calcularBonus() {
        return salarioBase * 0.2;
    }
}