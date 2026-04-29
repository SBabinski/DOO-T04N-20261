import java.util.Date;

public class Pedido {
    public int id;
    public Date dataCriacao;
    public Date dataPgto;
    public Date dataVencimento;
    
    public Cliente cliente;
    public Vendedor vendedor;
    public Loja loja;

    public Item[] itens = new Item[10];
    public int qtdItens = 0;

    public double calcularValorTotal() {
        double total = 0;
        for(int i = 0; i < qtdItens; i++) {
            total += itens[i].preco;
        }
        return total;
    }

    public void gerarDescricaoVenda() {
        System.out.println("\n--- RECIBO DE VENDA ---");
        System.out.println("Pedido ID: " + id);
        System.out.println("Data de Criação: " + dataCriacao);
        System.out.println("Vendedor Responsável: " + vendedor.nome);
        System.out.println("Cliente: " + cliente.nome);
        System.out.printf("Valor Total: R$ %.2f\n", calcularValorTotal());
    }
}