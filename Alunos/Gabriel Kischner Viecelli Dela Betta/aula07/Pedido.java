import java.util.Date;
import java.util.ArrayList;

public class Pedido {
    public int id;
    public Date dataCriacao;
    public Date dataPagamento;
    public Date dataVencimentoReserva;
    public Cliente cliente;
    public Vendedor vendedor;
    public Loja loja;
    public ArrayList<Item> itens;

    public Pedido(int id, Cliente cliente, Vendedor vendedor, Loja loja, ArrayList<Item> itens) {
        this.id = id;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
        this.itens = itens;
        this.dataCriacao = new Date(); 
     
        long tresDiasEmMs = 3L * 24 * 60 * 60 * 1000;
        this.dataVencimentoReserva = new Date(dataCriacao.getTime() + tresDiasEmMs);
    }

    public float calcularValorTotal() {
        float total = 0;
        for (Item item : itens) {
            total += item.valor;
        }
        return total;
    }

    public void gerarDescricaoVenda() {
        System.out.println("Pedido ID: " + id + " | Criado em: " + dataCriacao + " | Valor Total: R$ " + calcularValorTotal());
    }
}