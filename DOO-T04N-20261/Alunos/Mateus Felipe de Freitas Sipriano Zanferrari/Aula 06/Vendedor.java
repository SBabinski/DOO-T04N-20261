import java.util.ArrayList;
import java.util.List;


public class Vendedor {

    public String nome;
    public int idade;
    public String nomeLoja;
    public String cidade;
    public String bairro;
    public String rua;
    public double salarioBase;
    public List<Double> salarioRecebido;
    
    public Vendedor(String nome, int idade, String nomeLoja, String cidade, String bairro, String rua, double salarioBase) {
        this.nome = nome;
        this.idade = idade;
        this.nomeLoja = nomeLoja;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.salarioBase = salarioBase;
        this.salarioRecebido = new ArrayList<>(List.of(salarioBase, salarioBase * 1.2, salarioBase * 0.98)); // Inicializa com o salário base para os 3 meses
    }
    
    public  void apresentarVendedor(){
        System.out.println("Nome do vendedor: " + nome);
        System.out.println("Idade: " + idade + " anos");
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