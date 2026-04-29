package prov1bim.java;

public class Quarto {
	private int num;
	private double val_diaria;
	
	public Quarto() {
		
	}
	public Quarto(int num, double val_diaria) {
		this.num = num;
		this.val_diaria = val_diaria;
	}
	
	public int getnum() {
		return num;
	}
	
	public void setnum(int num) {
		this.num = num;
	}
	
	public double getval_diaria() {
		return val_diaria;
	}
	
	public void setval_diaria(double val_diaria) {
		this.val_diaria = val_diaria;
	}
	
	public void exibirInfo() {
		System.out.println("Quarto:" + num);
		System.out.println("Valor diaria: R$" + val_diaria);
	}
}
