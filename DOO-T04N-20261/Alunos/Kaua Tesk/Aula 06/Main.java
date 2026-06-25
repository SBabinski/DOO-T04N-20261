public class Main {

    public static void main(String[] args) {

        
        Vendedor v1 = new Vendedor("Kauã Tesk",   28, "My Plant", "Cascavel", "Centro",        "Rua das Flores, 10",    3000.00);
        Vendedor v2 = new Vendedor("Ana Souza",      35, "My Plant", "Cascavel", "Jardim",         "Av. das Palmeiras, 5",  3200.00);
        Vendedor v3 = new Vendedor("Pedro Oliveira", 22, "My Plant", "Cascavel", "São Cristóvão",  "Rua do Ipê, 88",        2800.00);

       
        Cliente c1 = new Cliente("Maria Lima",    45, "Cascavel", "Centro",    "Rua das Orquídeas, 22");
        Cliente c2 = new Cliente("João Ferreira", 30, "Cascavel", "Jardim",    "Av. Brasil, 100");
        Cliente c3 = new Cliente("Lucia Mendes",  52, "Cascavel", "Pioneiros", "Rua das Hortênsias, 7");
        Cliente c4 = new Cliente("Rafael Costa",  27, "Cascavel", "Centro",    "Rua Marechal, 55");

      
        Loja loja = new Loja(
            "My Plant",
            "My Plant Comércio de Plantas LTDA",
            "12.345.678/0001-99",
            "Cascavel", "Centro", "Rua XV de Novembro, 200",
            new Vendedor[]{v1, v2, v3},
            new Cliente[]{c1, c2, c3, c4}
        );

        
        loja.apresentarse();
        System.out.println("Total de Vendedores: " + loja.contarVendedores());
        System.out.println("Total de Clientes  : " + loja.contarClientes());

        System.out.println();
        v1.apresentarse();
        System.out.printf("Média salarial: R$ %.2f%n", v1.calcularMedia());
        System.out.printf("Bônus         : R$ %.2f%n", v1.calcularBonus());

        System.out.println();
        c1.apresentarse();
    }
}
