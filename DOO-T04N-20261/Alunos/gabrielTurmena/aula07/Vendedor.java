package classesAtividade;

public class Vendedor extends Pessoa{
	 

    double salarioBase;
    double[] salarioRecebido;
 
    public Vendedor(String nome, int idade, String loja, String cidade, String bairro, String rua, double salarioBase) {
        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.salarioBase = salarioBase;
        this.salarioRecebido = new double[]{4200.00, 3980.50, 4500.75};
    }
 
    public void apresentarse() {
        super.apresentarse(); // chama o método da Pessoa
    }
 
    public double calcularMedia() {
        double soma = 0;
        for (double salario : salarioRecebido) {
            soma += salario;
        }
        return soma / salarioRecebido.length;
    }
 
    public double calcularBonus() {
        return salarioBase * 0.2;
    }
}