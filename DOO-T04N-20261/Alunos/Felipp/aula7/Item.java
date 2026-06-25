package fag.objetos;

public class Item {
    private String id;
    private String nome;
    private String tipo;
    private double valor;

    public Item(String id, String nome, String tipo, double valor) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    public void gerarDescricao() {
        System.out.println("Id: " + id + " | Nome: " + nome + " | Tipo: " + tipo + " | Valor: " + valor);
    }
}
