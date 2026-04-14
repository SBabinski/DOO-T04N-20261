import java.util.ArrayList;
import java.util.Date;

public class Pedido {
    int id;
    Date dataCriacao;
    Date dataPagamento;
    Date dataVencimentoReserva;
    Cliente cliente;
    Vendedor vendedor;
    String loja;
    ArrayList<Item> itens = new ArrayList<>();

    public Pedido(int id, Date dataCriacao, Date dataVencimentoReserva, Cliente cliente, Vendedor vendedor, String loja) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataVencimentoReserva = dataVencimentoReserva;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
    }

    public void adicionarItem(Item item) {
        itens.add(item);
    }

    public double calcularValorTotal() {
        double total = 0;
        for (Item i : itens) {
            total += i.valor;
        }
        return total;
    }

    public void gerarDescricaoVenda() {
        System.out.println("Pedido criado em: " + dataCriacao);
        System.out.println("Valor total: R$ " + calcularValorTotal());
    }
}