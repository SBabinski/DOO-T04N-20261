import java.util.Date;

public class Aula7 {

    public static void main(String[] args) {

        Endereco end1 = new Endereco("Cascavel", "Centro", "Rua A");
        Endereco end2 = new Endereco("Cascavel", "Centro", "Rua B");

        Vendedor v1 = new Vendedor("Ana", 30, "My Plant", end1, 3000);
        Cliente c1 = new Cliente("Maria", 22, end2);

        Item i1 = new Item(1, "Planta A", "Ornamental", 50);
        Item i2 = new Item(2, "Planta B", "Frutífera", 80);

        Pedido pedido = new Pedido(
                1,
                new Date(),
                new Date(System.currentTimeMillis() + 86400000),
                c1,
                v1,
                "My Plant",
                new Item[]{i1, i2}
        );

        ProcessaPedido proc = new ProcessaPedido();
        proc.processar(pedido);

        pedido.gerarDescricaoVenda();
    }
}