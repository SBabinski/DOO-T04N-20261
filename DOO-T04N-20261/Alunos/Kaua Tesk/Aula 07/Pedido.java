import java.util.Date;

public class Pedido {

    int id;
    Date dataCriacao;
    Date dataPagamento;
    Date dataVencimentoReserva;
    Cliente  cliente;
    Vendedor vendedor;
    Loja     loja;
    Item[]   itens;

    Pedido(int id, Date dataCriacao, Date dataPagamento, Date dataVencimentoReserva,
           Cliente cliente, Vendedor vendedor, Loja loja, Item[] itens) {
        this.id                    = id;
        this.dataCriacao           = dataCriacao;
        this.dataPagamento         = dataPagamento;
        this.dataVencimentoReserva = dataVencimentoReserva;
        this.cliente               = cliente;
        this.vendedor              = vendedor;
        this.loja                  = loja;
        this.itens                 = itens;
    }

    double calcularValorTotal() {
        double total = 0;
        for (Item item : itens) {
            total += item.valor;
        }
        return total;
    }

    void gerarDescricaoVenda() {
        System.out.println("=== Pedido #" + id + " ===");
        System.out.println("Data de criação: " + dataCriacao);
        System.out.printf( "Valor total    : R$ %.2f%n", calcularValorTotal());
    }
}
