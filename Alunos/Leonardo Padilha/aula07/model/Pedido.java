import java.time.LocalDate;
import java.util.ArrayList;

public class Pedido {

    private int id;
    private LocalDate dataCriacao;
    private LocalDate dataPagamento;
    private LocalDate dataVencimentoReserva;

    private Pessoa cliente;
    private Pessoa vendedor;
    private String loja;

    private ArrayList<Item> itens;

    public Pedido(int id, Pessoa cliente, Pessoa vendedor, String loja,
                  LocalDate dataVencimentoReserva, ArrayList<Item> itens) {

        this.id = id;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
        this.itens = itens;

        this.dataCriacao = LocalDate.now();
        this.dataVencimentoReserva = dataVencimentoReserva;
    }

    public double calcularValorTotal() {
        double total = 0;

        for (Item item : itens) {
            total += item.getValor();
        }

        return total;
    }

    public String gerarDescricaoVenda() {
        return "Pedido criado em: " + dataCriacao +
                " | Valor total: R$ " + calcularValorTotal();
    }

    public LocalDate getDataVencimentoReserva() {
        return dataVencimentoReserva;
    }

    public void confirmarPagamento() {
        this.dataPagamento = LocalDate.now();
    }
}