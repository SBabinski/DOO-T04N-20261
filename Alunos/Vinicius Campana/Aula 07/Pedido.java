package doo;

import java.util.Date;

public class Pedido {

    int id;
    Date dataCriacao;
    Date dataPagamento;
    Date dataVencimentoReserva;

    Cliente cliente;
    Vendedor vendedor;
    Loja loja;

    Item[] itens;

    public Pedido(int id, Date dataCriacao, Date dataVencimentoReserva, Cliente cliente, Vendedor vendedor, Loja loja) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataVencimentoReserva = dataVencimentoReserva;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;

        itens = new Item[2];
    }

    public float calcularValorTotal() {
        float total = 0;

        for (Item i : itens) {
            if (i != null) total += i.valor;
        }

        return total;
    }

    public String gerarDescricaoVenda() {
        return "Data: " + dataCriacao + " | Total: " + calcularValorTotal();
    }
}