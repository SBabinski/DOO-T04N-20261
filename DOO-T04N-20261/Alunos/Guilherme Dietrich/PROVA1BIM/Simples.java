package objetos;

public class Simples extends Quarto{
		boolean temVentilador;
		
		
		public Simples(int numeroQuarto, double diaria, boolean temVentilador) {
			super(numeroQuarto, diaria);
			this.temVentilador = temVentilador;
		}
		
		public void exibirInformacao() {
			System.out.println("Tipo do quarto: Simples");
			System.out.println("Numero do quarto: " + numeroQuarto);
			System.out.println("Valor diária: " + diaria);
			System.out.println("O quarto tem ventilador?: " + (temVentilador ? "Sim" : "Não"));
		}
		
}
