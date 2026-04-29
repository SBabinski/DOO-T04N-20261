import java.util.ArrayList;

public class Gerente extends Pessoa {
    private String loja;
    private Endereco endereco;
    private double salarioBase;
    private ArrayList<Double> salarioRecebido;

    public Gerente(String nome, int idade, String loja, String estado, String cidade, String bairro, String rua, int numero, double salarioBase) {
        super(nome, idade);
        this.loja = loja;
        this.endereco = new Endereco(estado, cidade, bairro, rua, numero, "");
        this.salarioBase = salarioBase;
        this.salarioRecebido = new ArrayList<>();

        salarioRecebido.add(4500.0);
        salarioRecebido.add(4800.0);
        salarioRecebido.add(5000.0);
    }

    @Override
    public void apresentarSe() {
        System.out.println("Gerente - Nome: " + nome + ", Idade: " + idade + ", Loja: " + loja);
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

    public String getLoja() {
        return loja;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public ArrayList<Double> getSalarioRecebido() {
        return salarioRecebido;
    }

    public void setLoja(String loja) {
        this.loja = loja;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }
}
