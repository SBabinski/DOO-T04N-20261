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
        this.desconto = calcularDesconto(subtotal);
        this.valorTotal = subtotal - desconto;
    }

    private double calcularDesconto(double subtotal) {
        if (quantidade > 10) {
            return subtotal * 0.05;
        }
        return 0;
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