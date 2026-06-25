import java.util.Date;

public class Pedido {
    int id;
    Date criacao, pagamento, vencimento;
    Cliente cliente;
    Vendedor vendedor;
    String loja;
    Item[] itens;

    public Pedido(int i, Date c, Date p, Date v, Cliente cl, Vendedor ve, String l, Item[] it) {
        id = i;
        criacao = c;
        pagamento = p;
        vencimento = v;
        cliente = cl;
        vendedor = ve;
        loja = l;
        itens = it;
    }

    public double total() {
        double t = 0;
        for (Item i : itens) t += i.valor;
        return t;
    }

    public void descricao() {
        System.out.println(criacao + " " + total());
    }
}