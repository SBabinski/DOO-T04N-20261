package calculadora.model;

import java.time.LocalDate;

public class Venda {
    private int quantidade;
    private double preco;
    private double desconto;
    private double valorTotal;
    private LocalDate data;

    public Venda(int quantidade, double preco) {
        this(quantidade, preco, LocalDate.now());
    }

    public Venda(int quantidade, double preco, LocalDate data) {
        this.quantidade = quantidade;
        this.preco = preco;
        this.data = data;

        double subtotal = preco * quantidade;

        if (quantidade > 10) {
            this.desconto = subtotal * 0.05;
        } else {
            this.desconto = 0;
        }

        this.valorTotal = subtotal - desconto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPreco() {
        return preco;
    }

    public double getDesconto() {
        return desconto;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}