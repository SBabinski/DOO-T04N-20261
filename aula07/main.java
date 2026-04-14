public class Main {
    public static void main(String[] args) {

        Endereco end = new Endereco("PR", "Cascavel", "Centro", "123", "Apto 1");

        Cliente cliente = new Cliente("Ana", 30, end);
        Vendedor vendedor = new Vendedor("Carlos", 28, end, "My Plant");

        ProcessaPedido processador = new ProcessaPedido();
        Pedido pedido = processador.processar(1, cliente, vendedor, "My Plant");

        Item item1 = new Item(1, "Vaso", "Decoração", 50.0);
        Item item2 = new Item(2, "Planta", "Natural", 80.0);

        pedido.adicionarItem(item1);
        pedido.adicionarItem(item2);

        pedido.gerarDescricaoVenda();
    }
}