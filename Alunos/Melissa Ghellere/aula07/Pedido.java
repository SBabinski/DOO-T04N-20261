package aula07;
import java.util.Date;

public class Pedido {
    private int id;
    private Date dataCriacao, dataPagamento, dataVencimentoReserva;
    private String cliente, vendedor, loja;
    private Item[] itens;

    public Pedido(int id, String cliente, String vendedor, String loja, Item[] itens, Date vencimento) {
        this.id = id;
        this.dataCriacao = new Date();
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
        this.itens = itens;
        this.dataVencimentoReserva = vencimento;
    }

    public double calcularValorTotal() {
        double total = 0;
        for (Item item : itens) total += item.getValor();
        return total;
    }

    public void gerarDescricaoVenda() {
        System.out.println("Pedido criado em: " + dataCriacao + " | Valor Total: R$ " + calcularValorTotal());
    }

    public Date getDataVencimentoReserva() { return dataVencimentoReserva; }
}
