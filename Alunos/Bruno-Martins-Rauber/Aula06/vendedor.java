package metodos;

public class vendedor {
    String nome, loja, cidade, bairro, rua;
    int idade = 20;
    double salarioBase;
    double[] salarioRecebido = {2100, 2500, 2800}; 

    public void apresentarse() {
        System.out.println("Olá! Me chamo " + nome + ", tenho " + idade + " anos e trabalho na loja " + loja);
    }

   
    		public void calcularMedia() {
    			double soma = 0;
        for (int i = 0; i < salarioRecebido.length; i++) {
            soma += salarioRecebido[i];
        }
        double media = soma / salarioRecebido.length;
        System.out.printf("Média dos últimos salários: R$ %.2f%n", media);
    }

  
    public void calcularBonus() {
    		double bonus = salarioBase * 0.2;
        System.out.println("Bônus calculado (20%): R$ " + bonus);
    }
}