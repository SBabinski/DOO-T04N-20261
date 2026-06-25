package objetos;

public class Quarto {
	
	private int numero;
	private double valorDaDiaria;
	
	public Quarto(int numero, double valorDaDiaria) {
		this.numero = numero;
		this.valorDaDiaria = valorDaDiaria;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public double getValorDaDiaria() {
		return valorDaDiaria;
	}

	public void setValorDaDiaria(double valorDaDiaria) {
		this.valorDaDiaria = valorDaDiaria;
	}
	
	public void exibirInformacoes() {
		System.out.println("Numero do quarto: " + numero);
		System.out.println("Valor da diaria: " + valorDaDiaria);
	}
	
	// O hotel trabalha com dois tipos de quartos: simples e luxo. Ambos possuem número do quarto e valor da diária.
	// O quarto simples possui a informação de ter ou não ventilador. O quarto luxo possui a informação de ter ou não varanda.
}
