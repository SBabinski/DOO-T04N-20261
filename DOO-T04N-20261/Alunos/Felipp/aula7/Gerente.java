package fag.objetos;

public class Gerente extends Funcionario {
    public Gerente(String nome, int idade, Endereco endereco, Loja loja, double salarioBase) {
        super(nome, idade, loja, salarioBase, endereco);
    }
}
