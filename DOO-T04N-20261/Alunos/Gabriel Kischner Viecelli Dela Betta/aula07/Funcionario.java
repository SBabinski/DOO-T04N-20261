import java.util.ArrayList;

public class Funcionario extends Pessoa {
    public String loja;
    public float salBase;
    public ArrayList<Float> salRec = new ArrayList<>();

    public Funcionario(String nome, int idade, Endereco endereco, String loja, float salBase) {
        super(nome, idade, endereco);
        this.loja = loja;
        this.salBase = salBase;
    }

    public float calcularMedia() {
        float soma = 0;
        for (float s : salRec) {
            soma += s;
        }
        return salRec.isEmpty() ? 0 : soma / salRec.size();
    }
}