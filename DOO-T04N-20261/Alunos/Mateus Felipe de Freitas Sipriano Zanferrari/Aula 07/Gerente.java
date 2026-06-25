import java.util.ArrayList;
import java.util.List;

public class Gerente extends Pessoa {

    public String loja;
    public double salarioBase;
    public List<Double> salarioRecebido;

    public Gerente(String nome, int idade, Endereco endereco, String loja, double salarioBase) {
        super(nome, idade, endereco);
        this.loja = loja;
        this.salarioBase = salarioBase;
        // Ao menos três lançamentos de salário
        this.salarioRecebido = new ArrayList<>(List.of(
                salarioBase * 1.10,  // mês 1
                salarioBase * 1.35,  // mês 2
                salarioBase * 1.20,  // mês 3
                salarioBase * 1.35   // mês 4
        ));
    }

    @Override
    public void apresentarse() {
        System.out.println("Gerente: " + nome + " | Idade: " + idade + " | Loja: " + loja);
        endereco.apresentarLogradouro();
    }

    public void calcularMedia() {
        double soma = 0;
        for (double salario : salarioRecebido) {
            soma += salario;
        }
        double media = soma / salarioRecebido.size();
        System.out.printf("Média salarial de %s: R$ %.2f%n", nome, media);
    }

    public void calcularBonus() {
        System.out.printf("Bônus de %s: R$ %.2f%n", nome, salarioBase * 0.35);
    }
}