import java.util.ArrayList;

public class Vendedor extends Pessoa {

    public String loja;
    public double salarioBase;
    public ArrayList<Double> salarioRecebido = new ArrayList<>();

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