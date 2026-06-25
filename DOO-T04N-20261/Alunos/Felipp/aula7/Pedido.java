package fag.objetos;
import java.time.LocalDateTime;
import java.util.List;

public class Pedido {
    private String id;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataPagamento;
    private LocalDateTime dataVencimentoReserva;
    private Cliente cliente;
    private Vendedor vendedor;
    private Loja loja;
    private List<Item> itens;

    public LocalDateTime getDataVencimentoReserva() {
        return dataVencimentoReserva;
    }

    public Pedido(String id, LocalDateTime dataCriacao, LocalDateTime dataPagamento, LocalDateTime dataVencimentoReserva, Cliente cliente, Vendedor vendedor, Loja loja, List<Item> itens) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataPagamento = dataPagamento;
        this.dataVencimentoReserva = dataVencimentoReserva;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
        this.itens = itens;
    }

    public void adicionarItem(Item item) {
        itens.add(item);
    }

    public double calcularValorTotal() {
        double valorTotal = 0;

        for(Item i : itens) {
            valorTotal += i.getValor();
        }

        return valorTotal;
    }

    public void gerarDescricaoVenda() {
        final double valorTotal = calcularValorTotal();

        System.out.println("Data de criação: " + dataCriacao + " | Valor total: " + valorTotal);
    }
}
