import java.util.*;

public class CalculadoraPlantas {

    public static void main(String[] args) {

        Endereco e = new Endereco("SP", "Assis", "Centro", 10, "Loja");

        Cliente c = new Cliente("Ana", 25, e);
        Vendedor v = new Vendedor("Carlos", 30, e, 2000);

        Item[] itens = {
                new Item(1, "Planta A", "Decorativa", 50),
                new Item(2, "Planta B", "Exotica", 80)
        };

        ProcessaPedido p = new ProcessaPedido();

        p.processar(1, new Date(), new Date(),
                new Date(System.currentTimeMillis() + 86400000),
                c, v, "My Plant", itens);
    }
}