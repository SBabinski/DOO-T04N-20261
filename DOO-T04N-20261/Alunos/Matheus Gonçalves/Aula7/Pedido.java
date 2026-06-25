package fag;

import java.util.ArrayList;
import java.util.Date;

public class Pedido {
    protected int id;
    protected Date dataCriacao;
    protected Date dataPagamento;
    protected Date dataVencimentoReserva;

    protected Cliente cliente;
    protected Vendedor vendedor;
    protected Loja loja;

    protected ArrayList<Item> itens = new ArrayList<>();

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public void setDataVencimentoReserva(Date dataVencimentoReserva) {
        this.dataVencimentoReserva = dataVencimentoReserva;
    }

    public Date getDataVencimentoReserva() {
        return dataVencimentoReserva;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public void setLoja(Loja loja) {
        this.loja = loja;
    }

    public void adicionarItem(Item item) {
        itens.add(item);
    }

    public double calcularValorTotal() {
        double total = 0;
        for (Item item : itens) {
            total += item.getValor();
        }
        return total;
    }

    public void gerarDescricaoVenda() {
        System.out.println("Pedido criado em: " + dataCriacao);
        System.out.println("Valor total: R$ " + calcularValorTotal());
    }
}