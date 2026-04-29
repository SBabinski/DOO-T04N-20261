import java.util.Date;
public class Main {
    public static void main(String[] args) {
        Endereco endereco = new Endereco("PR", "Cascavel", "Centro", "123", "Apto 1");
        Cliente cliente = new Cliente("João", 30, endereco);
        Vendedor vendedor = new Vendedor("Maria", 28, endereco, "Loja Central");

        Pedido pedido = new Pedido(
                1,
                new Date(),
                new Date(System.currentTimeMillis() + 86400000), // +1 dia
                cliente,
                vendedor,
                "Loja Central"
        );
        pedido.adicionarItem(new Item(1, "Vaso", "Decoração", 50.0));
        pedido.adicionarItem(new Item(2, "Planta", "Natural", 30.0));
        ProcessaPedido processador = new ProcessaPedido();
        processador.processar(pedido);
        System.out.println(pedido.gerarDescricaoVenda());
        
    }
}