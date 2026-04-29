//Pedido 
import java.util.ArrayList;
import java.util.Date;

public class Pedido {
    private int id;
    private Date dataCriacao;
    private Date dataPagamento;
    private Date dataVencimentoReserva;
    private Cliente cliente;
    private Vendedor vendedor;
    private Loja loja;
    private ArrayList<Item> itens = new ArrayList<>();

    public Pedido() {}

    public Pedido(int id, Date dataCriacao, Date dataVencimentoReserva,
                  Cliente cliente, Vendedor vendedor, Loja loja) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataVencimentoReserva = dataVencimentoReserva;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getDataCriacao() { return dataCriacao; }
    public void setDataCriacao(Date dataCriacao) { this.dataCriacao = dataCriacao; }

    public Date getDataPagamento() { return dataPagamento; }
    public void setDataPagamento(Date dataPagamento) { this.dataPagamento = dataPagamento; }

    public Date getDataVencimentoReserva() { return dataVencimentoReserva; }
    public void setDataVencimentoReserva(Date dataVencimentoReserva) { this.dataVencimentoReserva = dataVencimentoReserva; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Vendedor getVendedor() { return vendedor; }
    public void setVendedor(Vendedor vendedor) { this.vendedor = vendedor; }

    public Loja getLoja() { return loja; }
    public void setLoja(Loja loja) { this.loja = loja; }

    public ArrayList<Item> getItens() { return itens; }

    public void adicionaItem(Item item) {
        itens.add(item);
    }

    public double calcularValorTotal() {
        double total = 0;
        for(Item i : itens) {
            total += i.getValor();
        }
        return total;
    }

    public void gerarDescricaoVenda() {
        System.out.println("Pedido " + id + " criado em " + dataCriacao +
                           " | Valor total: R$ " + calcularValorTotal());
    }
}
