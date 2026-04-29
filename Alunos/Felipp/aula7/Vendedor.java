package fag.objetos;

public class Vendedor extends Funcionario {
    public Vendedor(String nome, int idade, Endereco endereco, Loja loja, double salarioBase) {
        super(nome, idade, loja, salarioBase, endereco);
    }
}
