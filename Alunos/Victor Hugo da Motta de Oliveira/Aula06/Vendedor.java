public class Vendedor {
    public String nome;
    public int idade;
    public String cidade;
    public String bairro;
    public String rua;
    public String loja;
    public double salarioBase;

    public double [] salarioRecebido = new double[3];

    public void apresentarse(){
        System.out.println("Olá, sou o vendedor " + nome + " e tenho " + idade + " anos e trabalho na loja " + loja + ".");
    }

    public double calcularMedia(){
        double soma = 0;
        for (int i = 0; i < salarioRecebido.length; i++) {
            soma += salarioRecebido[i];
        }
        return soma / salarioRecebido.length;
    }

    public double calcularBonus(){
        return salarioBase * 0.2;
    }
}
