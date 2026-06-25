package Objetos;

public class Gerente extends Pessoa {

    private String loja;
    private double salarioBase;
    private double[] salarios = {3000, 3200, 3500};

    public double calcularMedia() {
        double soma = 0;
        for (double s : salarios) soma += s;
        return soma / salarios.length;
    }

    public double calcularBonus() {
        return salarioBase * 0.35;
    }

    @Override
    public void apresentarSe() {
        System.out.println("Gerente: " + nome + ", Loja: " + loja);
    }

    public void setLoja(String loja) {
    	this.loja = loja;
    }
    
    public void setSalarioBase(double salarioBase) {
    	this.salarioBase = salarioBase;
    }
    
}