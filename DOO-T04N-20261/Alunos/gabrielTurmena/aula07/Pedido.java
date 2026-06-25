package classesAtividade;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    int id;
    Date dataCriacao;
    Date dataPagamento;
    Date dataVencimentoReserva;
    String cliente;
    String vendedor;
    String loja;
    List<Item> itens;
    double total;

    public Pedido(int id, Date dataCriacao, Date dataPagamento,
                  Date dataVencimentoReserva, String cliente,
      String vendedor, String loja) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.dataPagamento = dataPagamento;
        this.dataVencimentoReserva = dataVencimentoReserva;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.loja = loja;
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(Item item) {
        itens.add(item);
    }
    public Double calcularValorTotal() {
        this.total = 0.0;
        for (Item item : itens) {
            this.total += item.valor;
        }
        return this.total;
    }
    
    public void gerarDescricaoVenda() {
        System.out.println("Data de criação: " + dataCriacao);
        System.out.println("Valor total: " + total);
    }
}