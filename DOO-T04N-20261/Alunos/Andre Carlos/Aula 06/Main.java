public class Main {

    public static void main(String[] args) {

        Loja loja = new Loja(
                "My Plant",
                "My Plant LTDA",
                "12.345.678/0001-99",
                "Cascavel",
                "Centro",
                "Rua das Flores"
        );

        Cliente cliente1 = new Cliente(
                "Carlos",
                25,
                "Cascavel",
                "Centro",
                "Rua A"
        );

        Cliente cliente2 = new Cliente(
                "Ana",
                30,
                "Cascavel",
                "Neva",
                "Rua B"
        );

        loja.clientes.add(cliente1);
        loja.clientes.add(cliente2);

        Vendedor vendedor1 = new Vendedor(
                "João",
                40,
                loja,
                "Cascavel",
                "Centro",
                "Rua C",
                3000
        );

        vendedor1.salarioRecebido[0] = 3000;
        vendedor1.salarioRecebido[1] = 3200;
        vendedor1.salarioRecebido[2] = 3100;

        loja.vendedores.add(vendedor1);

        loja.apresentarSe();

        System.out.println();

        cliente1.apresentarSe();

        System.out.println();

        vendedor1.apresentarSe();

        System.out.println("Média Salarial: "
                + vendedor1.calcularMedia());

        System.out.println("Bônus: "
                + vendedor1.calcularBonus());

        System.out.println();

        System.out.println("Quantidade de clientes: "
                + loja.contarClientes());

        System.out.println("Quantidade de vendedores: "
                + loja.contarVendedores());
    }
}