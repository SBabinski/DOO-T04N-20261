import java.util.ArrayList;

public class Gerente extends Pessoa {
    public Loja loja;
    public double salarioBase;
    public ArrayList<Double> salarioRecebido = new ArrayList<>();

    public void apresentarSe() {
        System.out.println("Gerente: " + nome + ", Idade: " + idade + ", Loja: " + loja.nomeFantasia);
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