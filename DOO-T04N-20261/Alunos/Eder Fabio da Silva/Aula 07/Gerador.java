
public class Gerador {

    // Método para gerar uma loja com dados pré-definidos
    public static void gerarLoja() {
        Loja loja1 = new Loja();
        loja1.nomeFantasia = "My Plant1";
        loja1.razaoSocial = "My Plant1 Comércio Ltda.";
        loja1.cnpj = "12.345.678/0001-99";
        loja1.enderecoLoja = new Endereco("Sao Paulo", "Sao Paulo", "Centro", 1000, "Avenida Paulista");
        Loja.lojas.add(loja1);

        Loja loja2 = new Loja();
        loja2.nomeFantasia = "My Plant2";
        loja2.razaoSocial = "My Plant2 Comércio Ltda.";
        loja2.cnpj = "12.345.678/0002-99";
        loja2.enderecoLoja = new Endereco("Paraná", "Curitiba", "Centro", 2000, "Avenida Brasil");
        Loja.lojas.add(loja2);
    }

    // Método para gerar um vendedor
    public static void gerarVendedor() {
        Vendedor vendedor1 = new Vendedor();
        vendedor1.nome = "Joao";
        vendedor1.idade = 30;
        vendedor1.loja = "My Plant1";
        vendedor1.endereco = new Endereco("Sao Paulo", "Sao Paulo", "Centro", 123, "Rua das Flores");
        vendedor1.salarioBase = 2000;
        Loja.vendedores.add(vendedor1);

        Vendedor vendedor2 = new Vendedor();
        vendedor2.nome = "Maria";
        vendedor2.idade = 25;
        vendedor2.loja = "My Plant2";
        vendedor2.endereco = new Endereco("Paraná", "Curitiba", "Centro", 456, "Rua das Plantas");
        vendedor2.salarioBase = 2500;
        Loja.vendedores.add(vendedor2);

    }

    // Método para gerar um cliente
    public static void gerarCliente() {
        Cliente cliente1 = new Cliente();
        cliente1.nome = "Carlos";
        cliente1.idade = 28;
        cliente1.endereco = new Endereco("Sao Paulo", "Sao Paulo", "Centro", 123, "Rua do Comércio");
        Loja.clientes.add(cliente1);

        Cliente cliente2 = new Cliente();
        cliente2.nome = "Ana";
        cliente2.idade = 32;
        cliente2.endereco = new Endereco("Paraná", "Curitiba", "Centro", 456, "Rua das Plantas");
        Loja.clientes.add(cliente2);

        Cliente cliente3 = new Cliente();
        cliente3.nome = "Pedro";
        cliente3.idade = 25;
        cliente3.endereco = new Endereco("Sao Paulo", "Sao Paulo", "Centro", 789, "Rua das Flores");
        Loja.clientes.add(cliente3);

        Cliente cliente4 = new Cliente();
        cliente4.nome = "Julia";
        cliente4.idade = 30;
        cliente4.endereco = new Endereco("Paraná", "Curitiba", "Centro", 1011, "Rua do Comércio");
        Loja.clientes.add(cliente4);
    }

    // Método para gerar um gerente
    public static void gerarGerente() {
        Gerente gerente1 = new Gerente();
        gerente1.nome = "Roberto Silva";
        gerente1.idade = 45;
        gerente1.loja = "My Plant1";
        gerente1.endereco = new Endereco("São Paulo", "São Paulo", "Centro", 500, "Avenida Central");
        gerente1.salarioBase = 5000;
        Loja.gerentes.add(gerente1);

        Gerente gerente2 = new Gerente();
        gerente2.nome = "Mariana Costa";
        gerente2.idade = 38;
        gerente2.loja = "My Plant2";
        gerente2.endereco = new Endereco("Paraná", "Curitiba", "Centro", 800, "Rua Principal");
        gerente2.salarioBase = 5500;
        Loja.gerentes.add(gerente2);
    }

    // Método para gerar itens
    public static void gerarItem() {
        Item item1 = new Item("Vaso de Cerâmica", "Decoração", 500.00);
        Loja.itens.add(item1);

        Item item2 = new Item("Adubo Orgânico", "Jardinagem", 30.00);
        Loja.itens.add(item2);

        Item item3 = new Item("Sementes de Girassol", "Jardinagem", 10.00);
        Loja.itens.add(item3);

        Item item4 = new Item("Planta de Interior", "Plantas", 100.00);
        Loja.itens.add(item4);
    }
}
