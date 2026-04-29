import java.util.Date;
import java.util.List;

public class Pedido {
    private int id;
    private Date dataCriacao;
    private Date dataPagamento;
    private Date dataVencimentoReserva;
    private String cliente; // Pode ser alterado para a classe Cliente
    private String vendedor; // Pode ser alterado para a classe Vendedor
    private String loja;
    private List<Item> itens;

    public Pedido(int id, String cliente, String vendedor, String loja, List<Item> itens) {
        this.id = id;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
        this.itens = itens;
        this.dataCriacao = new Date();
        // Reserva vence em 3 dias por padrão
        this.dataVencimentoReserva = new Date(System.currentTimeMillis() + (3L * 24 * 60 * 60 * 1000));
    }

    public double calcularValorTotal() {
        double total = 0;
        for (Item item : itens) {
            total += item.getValor();
        }
        return total;
    }

    public void gerarDescricaoVenda() {
        System.out.println("Pedido criado em: " + dataCriacao + " | Valor Total: R$" + calcularValorTotal());
    }

    public Date getDataVencimentoReserva() {
        return dataVencimentoReserva;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}