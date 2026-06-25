import java.util.ArrayList;
import java.util.List;

public class Vendedor extends Pessoa {

    public String nomeLoja;
    public double salarioBase;
    public List<Double> salarioRecebido;

    public Vendedor(String nome, int idade, String nomeLoja, Endereco endereco, double salarioBase) {
        super(nome, idade, endereco);
        this.nomeLoja = nomeLoja;
        this.salarioBase = salarioBase;
        this.salarioRecebido = new ArrayList<>(List.of(
                salarioBase,
                salarioBase * 1.2,
                salarioBase * 0.98
        ));
    }

    @Override
    public void apresentarse() {
        apresentarVendedor();
    }

    // Mantido para compatibilidade com chamadas existentes no MyPlant.java
    public void apresentarVendedor() {
        System.out.println("Nome do vendedor: " + nome);
        System.out.println("Idade: " + idade + " anos");
        System.out.println("Loja: " + nomeLoja);
        endereco.apresentarLogradouro();
    }

    public void calcularMedia() {
        double soma = 0;
        for (double salario : salarioRecebido) {
            soma += salario;
        }
        System.out.println("Média Salarial: R$ " + (soma / salarioRecebido.size()));
    }

    public void calcularBonus() {
        System.out.println("Bonus calculado: R$ " + (salarioBase * 0.2));
    }
}