package Objetos;

public class Vendedor extends Pessoa {

    private String loja;
    private Double salarioRecebido;

    private float[] salarios = {1500, 1700, 2000};

    public float calcularMedia() {
        float soma = 0;
        for (float s : salarios) soma += s;
        return soma / salarios.length;
    }

    public float calcularBonus() {
        return (float) (salarioRecebido * 0.2);
    }

    @Override
    public void apresentarSe() {
        System.out.println("Vendedor: " + nome + " - Loja: " + loja);
    }

    public void cadastroDoVendedor() {
        System.out.println("NOME: " + nome);
        System.out.println("MEDIA: " + calcularMedia());
        System.out.println("BONUS: " + calcularBonus());
    }

    public void setLoja(String loja) {
    	this.loja = loja; 
    		}
    
    public void setSalarioRecebido(Double salarioRecebido) {
    	this.salarioRecebido = salarioRecebido;
    		}
}