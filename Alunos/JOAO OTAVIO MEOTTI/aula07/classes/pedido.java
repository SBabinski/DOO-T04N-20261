import java.util.ArrayList;
import java.util.Date;

public class Pedido {
    private int id;
    private Date dataCriacao;
    private Date dataPagamento;
    private Date dataVencimentoReserva;
    private Cliente cliente;
    private Vendedor vendedor;
    private String loja;
    private ArrayList<Item> itens;
    public Pedido(int id, Date dataCriacao, Date dataVencimentoReserva,
                  Cliente cliente, Vendedor vendedor, String loja) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataVencimentoReserva = dataVencimentoReserva;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
        this.itens = new ArrayList<>();
    }
    public void adicionarItem(Item item) {
        itens.add(item);
    }
    public double calcularValorTotal() {
        double total = 0;
        for (Item i : itens) {
            total += i.getValor();
        }
        return total;
    }
    public String gerarDescricaoVenda() {
        return "Pedido criado em: " + dataCriacao +
                " | Valor Total: R$" + calcularValorTotal();
    }
    public Date getDataVencimentoReserva() {
        return dataVencimentoReserva;
    }
    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
        
    }
}