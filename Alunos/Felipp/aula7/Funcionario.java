package fag.objetos;

import java.util.ArrayList;

public class Funcionario extends Pessoa {

    private double salarioBase;
    private ArrayList<Double> salariosRecebidos;
    private Loja loja;

    public Funcionario(String nome, int idade, Loja loja, double salarioBase, Endereco endereco) {
        super(nome, idade, endereco);
        this.salarioBase = salarioBase;

        this.salariosRecebidos = new ArrayList<>();
        this.salariosRecebidos.add(4200.00);
        this.salariosRecebidos.add(3980.50);
        this.salariosRecebidos.add(4500.75);

        this.loja = loja;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public double calcularMedia() {
        double soma = 0;
        for (double salario : salariosRecebidos) {
            soma += salario;
        }
        return salariosRecebidos.isEmpty() ? 0 : soma / salariosRecebidos.size();
    }

    public Loja getLoja() {
        return loja;
    }

    public double calcularBonus(double porcentagemBonus) {
        return salarioBase * porcentagemBonus;
    }

    @Override
    public void apresentarse() {
        super.apresentarse();
        System.out.println("LOJA CADASTRADA");
        loja.mostrarInformacoesLoja();
    }
}
