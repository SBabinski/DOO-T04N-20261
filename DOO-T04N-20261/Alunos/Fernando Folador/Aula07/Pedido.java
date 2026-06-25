import java.util.ArrayList;
import java.util.Date;

public class Pedido {
    public int id;
    public Date dataCriacao;
    public Date dataPagamento;
    public Date dataVencimentoReserva;
    public Cliente cliente;
    public Vendedor vendedor;
    public Loja loja;
    public ArrayList<Item> itens = new ArrayList<>();

    public double calcularValorTotal() {
        double total = 0;
        for (Item i : itens) {
            total += i.valor;
        }
        return total;
    }

    public void gerarDescricaoVenda() {
        System.out.println("Data: " + dataCriacao);
        System.out.println("Total: R$" + calcularValorTotal());
    }
}