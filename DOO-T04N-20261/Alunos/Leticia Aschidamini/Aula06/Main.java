public class Main {
    public static void main(String[] args) {

        Loja loja = new Loja("My Plant", "My Plant LTDA", "123456789", "Cascavel", "Centro", "Rua Erechim");

        Vendedor vendedor = new Vendedor("João", 30, "My Plant", "Cascavel", "Centro", "Rua Presidente Kennedy", 2000);
        Cliente cliente = new Cliente("Maria", 25, "Cascavel", "Centro", "Rua B");

        loja.vendedores.add(vendedor);
        loja.clientes.add(cliente);

        loja.apresentarSe();
        loja.contarClientes();
        loja.contarVendedores();

        vendedor.apresentarSe();
        System.out.println("Média salários: " + vendedor.calcularMedia());
        System.out.println("Bônus: " + vendedor.calcularBonus());

        cliente.apresentarSe();
    }
}