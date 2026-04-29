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
    private ArrayList<Item> itens;

    public Pedido(int id, Date dataCriacao, Date dataPagamento, Date dataVencimentoReserva, Cliente cliente, Vendedor vendedor, Loja loja) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataPagamento = dataPagamento;
        this.dataVencimentoReserva = dataVencimentoReserva;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
        this.itens = new ArrayList<>();
    }

    public double calcularValorTotal() {
        double total = 0;
        for (Item item : itens) {
            total += item.getValor();
        }
        return total;
    }

    public void gerarDescricaoVenda() {
        System.out.println("=== DESCRIÇÃO DO PEDIDO ===");
        System.out.println("ID do Pedido: " + id);
        System.out.println("Data de Criação: " + dataCriacao);
        System.out.println("Valor Total: R$ " + String.format("%.2f", calcularValorTotal()));
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Vendedor: " + vendedor.getNome());
        System.out.println("Loja: " + loja.getNomeFantasia());
        System.out.println("Quantidade de Itens: " + itens.size());
    }

    public void adicionarItem(Item item) {
        itens.add(item);
    }

    public void removerItem(Item item) {
        itens.remove(item);
    }

    public int getId() {
        return id;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public Date getDataVencimentoReserva() {
        return dataVencimentoReserva;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public Loja getLoja() {
        return loja;
    }

    public ArrayList<Item> getItens() {
        return itens;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public void setDataVencimentoReserva(Date dataVencimentoReserva) {
        this.dataVencimentoReserva = dataVencimentoReserva;
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
}
