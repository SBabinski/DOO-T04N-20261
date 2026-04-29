public class Main {
    public static void main(String[] args) {
        Loja loja = new Loja("My Plant", "My Plant LTDA", "123456789", "Cascavel", "Centro", "Rua A");
        Vendedor v1 = new Vendedor("João", 30, "My Plant", "Cascavel", "Centro", "Rua A", 2000);
        Cliente c1 = new Cliente("Maria", 25, "Cascavel", "Centro", "Rua B");
        loja.adicionarVendedor(v1);
        loja.adicionarCliente(c1);
        loja.apresentarSe();
        loja.contarClientes();
        loja.contarVendedores();
        loja.listarVendedores();
        loja.listarClientes();
        System.out.println("\n=== Informações do Vendedor ===");
        v1.apresentarSe();
        v1.mostrarSalarios();
        System.out.println("Média salários: " + v1.calcularMedia());
        System.out.println("Bônus: " + v1.calcularBonus());
        System.out.println("\n=== Informações do Cliente ===");
        c1.apresentarSe();
        c1.mostrarEndereco();
        System.out.println("Maior de idade: " + c1.maiorDeIdade());
    }
}