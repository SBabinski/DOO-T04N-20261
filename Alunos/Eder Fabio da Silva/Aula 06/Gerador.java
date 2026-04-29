
public class Gerador {

    
    // Método para gerar uma loja com dados pré-definidos
    public static void gerarLoja() {
        Loja loja1 = new Loja();
        loja1.nomeFantasia = "My Plant1";
        loja1.razaoSocial = "My Plant1 Comércio Ltda.";
        loja1.cnpj = "12.345.678/0001-99";
        loja1.cidade = "São Paulo";
        loja1.bairro = "Centro";
        loja1.rua = "Avenida Paulista, 1000";
        Loja.lojas.add(loja1);

        Loja loja2 = new Loja();
        loja2.nomeFantasia = "My Plant2";
        loja2.razaoSocial = "My Plant2 Comércio Ltda.";
        loja2.cnpj = "12.345.678/0002-99";
        loja2.cidade = "Curitiba";
        loja2.bairro = "Centro";
        loja2.rua = "Avenida Brasil, 1000";
        Loja.lojas.add(loja2);
    }
    
    // Método para gerar um vendedor
    public static void gerarVendedor() {
        Vendedor vendedor1 = new Vendedor();
        vendedor1.nome = "Joao";
        vendedor1.idade = 30;
        vendedor1.loja = "My Plant1";
        vendedor1.cidade = "São Paulo";
        vendedor1.bairro = "Centro";
        vendedor1.rua = "Rua das Flores, ";
        vendedor1.salarioBase = 2000;
        Loja.vendedores.add(vendedor1);   

        Vendedor vendedor2 = new Vendedor();
        vendedor2.nome = "Maria";
        vendedor2.idade = 25;
        vendedor2.loja = "My Plant2";
        vendedor2.cidade = "Curitiba";
        vendedor2.bairro = "Centro";
        vendedor2.rua = "Rua das Plantas, ";
        vendedor2.salarioBase = 2500;  
        Loja.vendedores.add(vendedor2);
        
    }
    
    // Método para gerar um cliente
    public static void gerarCliente() {
        Cliente cliente1 = new Cliente();
        cliente1.nome = "Carlos";
        cliente1.idade = 28;
        cliente1.cidade = "São Paulo";
        cliente1.bairro = "Centro";
        cliente1.rua = "Rua do Comércio, 123";
        Loja.clientes.add(cliente1);

        Cliente cliente2 = new Cliente();
        cliente2.nome = "Ana";
        cliente2.idade = 32;
        cliente2.cidade = "Curitiba";
        cliente2.bairro = "Centro";
        cliente2.rua = "Rua das Plantas, 456";
        Loja.clientes.add(cliente2);
        
        Cliente cliente3 = new Cliente();
        cliente3.nome = "Pedro";
        cliente3.idade = 25;
        cliente3.cidade = "São Paulo";
        cliente3.bairro = "Centro";
        cliente3.rua = "Rua das Flores, 789";
        Loja.clientes.add(cliente3);
        
        Cliente cliente4 = new Cliente();
        cliente4.nome = "Julia";
        cliente4.idade = 30;
        cliente4.cidade = "Curitiba";
        cliente4.bairro = "Centro";
        cliente4.rua = "Rua do Comércio, 1011";
        Loja.clientes.add(cliente4);
    }
    
    
}
