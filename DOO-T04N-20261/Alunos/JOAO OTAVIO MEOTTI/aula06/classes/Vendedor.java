import java.util.ArrayList;
public class Vendedor {
    String nome;
    int idade;
    String loja;
    String cidade;
    String bairro;
    String rua;
    double salarioBase;
    ArrayList<Double> salarioRecebido;
    public Vendedor(String nome, int idade, String loja, String cidade, String bairro, String rua, double salarioBase) {
        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.salarioBase = salarioBase;
        salarioRecebido = new ArrayList<>();
        salarioRecebido.add(2000.0);
        salarioRecebido.add(2200.0);
        salarioRecebido.add(2100.0);
    }
    public void apresentarSe() {
        System.out.println("=== Dados do Vendedor ===");
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Loja: " + loja);
        System.out.println("Endereço: " + rua + ", " + bairro + " - " + cidade);
    }
    public double calcularMedia() {
        double soma = 0;
        for (int i = 0; i < salarioRecebido.size(); i++) {
            soma += salarioRecebido.get(i);
        }
        return soma / salarioRecebido.size();
    }
    public double calcularBonus() {
        double bonus = salarioBase * 0.2;
        return bonus;
    }
    public void adicionarSalario(double valor) {
        salarioRecebido.add(valor);
    }
    public void mostrarSalarios() {
        System.out.println("Salários recebidos:");
        for (double s : salarioRecebido) {
            System.out.println("R$ " + s);
        }
    }
}