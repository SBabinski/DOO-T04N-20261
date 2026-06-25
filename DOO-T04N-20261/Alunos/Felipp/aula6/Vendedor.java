package fag.objetos;

import java.util.ArrayList;

public class Vendedor {

    private String nome;
    private int idade;
    private Loja loja;
    private String cidade;
    private String bairro;
    private String rua;
    private double salarioBase;
    private ArrayList<Double> salariosRecebidos;

    // Construtor
    public Vendedor(String nome, int idade, Loja loja, String cidade, String bairro, String rua, double salarioBase) {
        this.nome = nome;
        this.idade = idade;
        this.loja = loja;
        this.cidade = cidade;
        this.bairro = bairro;
        this.rua = rua;
        this.salarioBase = salarioBase;

        this.salariosRecebidos = new ArrayList<>();
        this.salariosRecebidos.add(4200.00);
        this.salariosRecebidos.add(3980.50);
        this.salariosRecebidos.add(4500.75);
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public Loja getLoja() {
        return loja;
    }

    public String getCidade() {
        return cidade;
    }

    public String getBairro() {
        return bairro;
    }

    public String getRua() {
        return rua;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public ArrayList<Double> getSalariosRecebidos() {
        return salariosRecebidos;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public void setSalariosRecebidos(ArrayList<Double> salariosRecebidos) {
        this.salariosRecebidos = salariosRecebidos;
    }

    public void apresentarse() {
        System.out.println("Nome: " + nome + " | Idade: " + idade + " | Loja: " + loja.getNomeFantasia());
    }

    public double calcularMedia() {
        double soma = 0;
        for (double salario : salariosRecebidos) {
            soma += salario;
        }
        return salariosRecebidos.isEmpty() ? 0 : soma / salariosRecebidos.size();
    }

    public double calcularBonus() {
        return salarioBase * 0.2;
    }
}