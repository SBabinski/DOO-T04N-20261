
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Vendas {

    private static int proximoId = 1;
    private int id;
    private int quantidade;
    private double precoUnitario; 
    private Date dataVenda;
    private Date dataPagamento;
    private Date dataVencimentoReserva;
    private Cliente cliente;
    private Vendedor vendedor;
    private Loja loja;
    private ArrayList<Item> itens;

    public Vendas(Date dataPagamento, Cliente cliente, Vendedor vendedor,
            Loja loja, ArrayList<Item> itens, int quantidade) {
        this.id = proximoId++;
        this.dataVenda = new Date();
        this.dataPagamento = dataPagamento;
        this.dataVencimentoReserva = new Date(System.currentTimeMillis() + 2 * 24 * 60 * 60 * 1000); // 2 dias a partir de agora
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
        this.itens = itens;
        this.quantidade = quantidade;
        this.precoUnitario = (itens != null && !itens.isEmpty()) ? itens.get(0).getValor() : 0.0;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public int getId() {
        return id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public Date getDataVencimentoReserva() {
        return dataVencimentoReserva;
    }

    public void setDataVencimentoReserva(Date dataVencimentoReserva) {
        this.dataVencimentoReserva = dataVencimentoReserva;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Loja getLoja() {
        return loja;
    }

    public ArrayList<Item> getItens() {
        return itens;
    }

    public double getValorTotal() {
        double total = precoUnitario * quantidade;
        if (quantidade > 10) {
            return total * 0.95; // desconto de 5%
        }
        return total;
    }

    public void gerarDescricaoVenda() {
        System.out.println("=== Venda #" + id + " ===");
        System.out.println("Data de criacao: " + new SimpleDateFormat("dd/MM/yyyy").format(dataVenda));
        System.out.println("Quantidade: " + quantidade);
        System.out.printf("Valor total: R$ %.2f\n", getValorTotal());
    }
}
