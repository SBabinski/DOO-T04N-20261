public class Main {
    public static void main(String[] args) {

        Loja minhaLoja = new Loja(
                "My Plant",
                "My Plant LTDA",
                "123456789",
                "Cascavel",
                "Centro",
                "Rua A"
        );

        Vendedor vendedor1 = new Vendedor(
                "João",
                30,
                "My Plant",
                "Cascavel",
                "Centro",
                "Rua A",
                2000
        );
        Cliente cliente1 = new Cliente(
                "Maria",
                25,
                "Cascavel",
                "Centro",
                "Rua B"
        );

        minhaLoja.vendedores.add(vendedor1);
        minhaLoja.clientes.add(cliente1);


        minhaLoja.apresentarSe();
        minhaLoja.contarClientes();
        minhaLoja.contarVendedores();

        System.out.println("----------------------");

        vendedor1.apresentarSe();
        double mediaSalarios = vendedor1.calcularMedia();
        double bonus = vendedor1.calcularBonus();

        System.out.println("Média dos salários: " + mediaSalarios);
        System.out.println("Bônus do vendedor: " + bonus);

        System.out.println("----------------------");
        
        cliente1.apresentarSe();
    }
}