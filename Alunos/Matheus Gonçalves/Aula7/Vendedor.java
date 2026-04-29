package fag;

public class Vendedor {
    private String nome;
    private int idade;
    private Loja loja;
    private double salarioBase;
    private double[] salarioRecebido;
    private Endereco endereco;
    
    public Vendedor() {}

    public Vendedor(String nome, int idade, String loja, double salarioBase, Endereco endereco) {
        this.nome = nome;
        this.idade = idade;
        this.loja = null;
        this.salarioBase = salarioBase;
        this.salarioRecebido = new double[]{1200.0, 1300.0, 1400.0};
        setEndereco(endereco);
    }
    
    public String getNome() { 
    	return nome; 
    }
    public void setNome(String nome) { 
    	if(!nome.isBlank()) 
    		this.nome = nome; 
    }

    public int getIdade() { 
    	return idade; 
    }
    public void setIdade(int idade) { 
    	if(idade >= 0)
    		this.idade = idade; 
    }

    public Loja getLoja() { 
    	return loja; 
    }
    public void setLoja(Loja loja) { 
    	if(loja != null)
    		this.loja = loja; 
    }
    
    public double getSalarioBase() { 
    	return salarioBase; 
    }
    public void setSalarioBase(double salarioBase) { 
    	if (salarioBase >= 0.0) {
    		this.salarioBase = salarioBase; 
    	}
    }

    public double[] getSalarioRecebido() { 
    	return salarioRecebido; 
    }
    public void setSalarioRecebido(double[] salarioRecebido) { 
    	this.salarioRecebido = salarioRecebido;
    }
    
    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        if (endereco == null) {
            System.out.println("Endereço inválido!");
        } else {
            this.endereco = endereco;
        }
    }
    
    public void apresentarse() {
        System.out.println("Nome: " + nome);
        System.out.println("Idade: " + idade);
        System.out.println("Loja: " + loja.getNomeFantasia());
    }

    public double calcularMedia() {
        double soma = 0;
        for (double s : salarioRecebido) {
            soma += s;
        }
        double media = soma / salarioRecebido.length;
        System.out.println("Média salarial de " + nome + ": R$ " + media);
        return media;
    }

    public double calcularBonus() {
        double bonus = salarioBase * 0.2;
        System.out.println("Bônus de " + nome + ": R$ " + bonus);
        return bonus;
    }

}