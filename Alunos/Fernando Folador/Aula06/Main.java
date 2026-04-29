public class Main {

    public static void main(String[] args) {

        Loja loja = new Loja();
        loja.nomeFantasia = "My Plant";
        loja.cnpj = "123456789";
        loja.cidade = "Cascavel";
        loja.bairro = "Centro";
        loja.rua = "Rua das Flores";

        Vendedor vendedor = new Vendedor();
        vendedor.nome = "Fernando";
        vendedor.idade = 20;
        vendedor.loja = "My Plant";
        vendedor.salarioBase = 2000;

        vendedor.salarioRecebido.add(2000.0);
        vendedor.salarioRecebido.add(2200.0);
        vendedor.salarioRecebido.add(2100.0);

        loja.vendedores.add(vendedor);

        Cliente cliente = new Cliente();
        cliente.nome = "João";
        cliente.idade = 30;

        loja.clientes.add(cliente);

        loja.apresentarSe();
        vendedor.apresentarSe();

        System.out.println("Média salarial: " + vendedor.calcularMedia());
        System.out.println("Bônus: " + vendedor.calcularBonus());

        System.out.println("Total de clientes: " + loja.contarClientes());
        System.out.println("Total de vendedores: " + loja.contarVendedores());
    }
}