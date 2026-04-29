import java.util.ArrayList;
import java.util.List;

public class Gerador {
    public static List<Loja> criarLojas() {
        List<Loja> rede = new ArrayList<>();

        // ---- LOJA CASCAVEL ----
        Loja cascavel = new Loja("My Plant - Cascavel", "My Plant LTDA", "12.345.678/0001-90", "Cascavel", "Centro");

        cascavel.gerente = new Gerente(
                "Roberto Alves", 42,
                new Endereco("PR", "Cascavel", "Centro", "Av. Brasil", "1000", ""),
                "My Plant - Cascavel", 8000.00
        );

        cascavel.vendedores.add(new Vendedor(
                "João Silva", 25, "My Plant - Cascavel",
                new Endereco("PR", "Cascavel", "Centro", "Rua 1", "10", ""),
                2200.00
        ));
        cascavel.vendedores.add(new Vendedor(
                "Maria Brito", 28, "My Plant - Cascavel",
                new Endereco("PR", "Cascavel", "Centro", "Rua 2", "20", ""),
                2400.00
        ));

        cascavel.clientes.add(new Cliente("Maria Souza", 30,
                new Endereco("PR", "Cascavel", "Centro", "Rua A", "1", "")));
        cascavel.clientes.add(new Cliente("Pedro Lima", 35,
                new Endereco("PR", "Cascavel", "Centro", "Rua B", "2", "")));
        cascavel.clientes.add(new Cliente("Ana Lima", 28,
                new Endereco("PR", "Cascavel", "Centro", "Rua C", "3", "")));
        cascavel.clientes.add(new Cliente("Lucas Pereira", 40,
                new Endereco("PR", "Cascavel", "Centro", "Rua D", "4", "")));
        rede.add(cascavel);

        // ---- LOJA LONDRINA ----
        Loja londrina = new Loja("My Plant - Londrina", "My Plant LTDA", "98.765.432/0001-10", "Londrina", "Centro");

        londrina.gerente = new Gerente(
                "Patrícia Melo", 38,
                new Endereco("PR", "Londrina", "Centro", "Rua das Palmeiras", "200", ""),
                "My Plant - Londrina", 7500.00
        );

        londrina.vendedores.add(new Vendedor(
                "Carlos Oliveira", 30, "My Plant - Londrina",
                new Endereco("PR", "Londrina", "Centro", "Rua 3", "30", ""),
                2500.00
        ));
        londrina.vendedores.add(new Vendedor(
                "Fernanda Santos", 27, "My Plant - Londrina",
                new Endereco("PR", "Londrina", "Centro", "Rua 4", "40", ""),
                2300.00
        ));

        londrina.clientes.add(new Cliente("Marcia Oliveira", 32,
                new Endereco("PR", "Londrina", "Centro", "Rua A", "1", "")));
        londrina.clientes.add(new Cliente("José Santos", 45,
                new Endereco("PR", "Londrina", "Centro", "Rua B", "2", "")));
        londrina.clientes.add(new Cliente("Anita Gomes", 25,
                new Endereco("PR", "Londrina", "Centro", "Rua C", "3", "")));
        londrina.clientes.add(new Cliente("Marcos Silva", 37,
                new Endereco("PR", "Londrina", "Centro", "Rua D", "4", "")));
        rede.add(londrina);

        return rede;
    }
}
