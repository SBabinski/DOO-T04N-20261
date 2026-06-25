package Aula07;

public class Gerente extends Pessoa {
		Loja loja;
		double salarioBase;
		double[] salarioRecebido;
		
		public Gerente(String nome, int idade, Endereco endereco, Loja loja, double salarioBase) {
			super(nome, idade, endereco);
			this.loja = loja;
			this.salarioBase = salarioBase;
			salarioRecebido = new double[] {3000, 3100, 3200};
			
		}
		
		@Override
		public void apresentarSe() {
			System.out.println(nome + " " + idade + " " + loja.nomeFantasia);
		}
		
		public double calcularMedia() {
			double soma = 0;
			for(double som:salarioRecebido) soma += som;
			return soma / salarioRecebido.length;
		}
		public double calcularBonus() {
			return salarioBase * 0.35;
		}
}
