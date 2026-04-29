package calculadora.model;

import java.util.ArrayList;

public class Vendedor extends Pessoa {

    private String loja;
    private Endereco endereco;
    private double salarioBase;
    private ArrayList<Double> salarioRecebido;

    public Vendedor(String nome, int idade, String loja,
                    Endereco endereco, double salarioBase) {

        super(nome, idade);
        this.loja = loja;
        this.endereco = endereco;
        this.salarioBase = salarioBase;

        this.salarioRecebido = new ArrayList<>();
        salarioRecebido.add(2000.0);
        salarioRecebido.add(2100.0);
        salarioRecebido.add(2200.0);
    }

    @Override
    public void apresentarSe() {
        super.apresentarSe();
        System.out.println("Loja: " + loja);
    }

    public double calcularMedia() {
        double soma = 0;

        for (double salario : salarioRecebido) {
            soma += salario;
        }

        return soma / salarioRecebido.size();
    }

    public double calcularBonus() {
        return salarioBase * 0.2;
    }
}