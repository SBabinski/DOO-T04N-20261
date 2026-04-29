package Objetos;

import java.util.Date;

public class Pedido {

    private int id;
    private Date dataCriacao;
    private Date dataPagamento;
    private Date dataVencimentoReserva;

    private Cliente cliente;
    private Vendedor vendedor;
    private Loja loja;

    private Item[] itens;

    public double calcularValorTotal() {
        double total = 0;
        for (Item i : itens) {
            total += i.getValor();
        }
        return total;
    }

    public String gerarDescricaoVenda() {
        return "Pedido criado em: " + dataCriacao +
               " | Total: R$ " + calcularValorTotal();
    }

    public void setItens(Item[] itens) {
    	this.itens = itens;
    	}
    
    public void setDataCriacao(Date dataCriacao) {
    	this.dataCriacao = dataCriacao;
    	}
    
    public void setDataVencimentoReserva(Date data) {
    	this.dataVencimentoReserva = data;
    	}
    
    public void setCliente(Cliente cliente) {
    	this.cliente = cliente;
    	}
    
    public void setVendedor(Vendedor vendedor) {
    	this.vendedor = vendedor;
    	}
    
}