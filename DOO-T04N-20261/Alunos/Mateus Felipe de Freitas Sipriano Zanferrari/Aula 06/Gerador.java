
import java.util.ArrayList;
import java.util.List;

public class Gerador {
      public static List<Loja> criarLojas(){
         List<Loja> rede = new ArrayList<>();
         
         Loja cascavel = new Loja("My Plant - Cascavel", "My Plant LTDA", "12.345.678/0001-90", "Cascavel", "Centro");
         cascavel.vendedores.add(new Vendedor("João Silva", 25, "My Plant - Cascavel", "Cascavel", "Centro", "Rua 1", 2200.00));
         cascavel.vendedores.add(new Vendedor("Maria Brito", 28, "My Plant - Cascavel", "Cascavel", "Centro", "Rua 2", 2400.00));
         cascavel.clientes.add(new Cliente("Maria Souza", 30, "Cascavel", "Centro", "Rua A"));
         cascavel.clientes.add(new Cliente("Pedro Lima", 35, "Cascavel", "Centro", "Rua B"));
         cascavel.clientes.add(new Cliente("Ana Lima", 28, "Cascavel", "Centro", "Rua C"));  
         cascavel.clientes.add(new Cliente("Lucas Pereira", 40, "Cascavel", "Centro", "Rua D"));
         rede.add(cascavel);

         Loja londrina = new Loja("My Plant - Londrina", "My Plant LTDA", "98.765.432/0001-10", "Londrina", "Centro");
         londrina.vendedores.add(new Vendedor("Carlos Oliveira", 30, "My Plant - Londrina", "Londrina", "Centro", "Rua 3", 2500.00));
         londrina.vendedores.add(new Vendedor("Fernanda Santos", 27, "My Plant - Londrina", "Londrina", "Centro", "Rua 4", 2300.00));
         londrina.clientes.add(new Cliente("Marcia Oliveira", 32, "Londrina", "Centro", "Rua A"));
         londrina.clientes.add(new Cliente("José Santos", 45, "Londrina", "Centro", "Rua B")); 
         londrina.clientes.add(new Cliente("Anita Gomes", 25, "Londrina", "Centro", "Rua C"));
         londrina.clientes.add(new Cliente("Marcos Silva", 37, "Londrina", "Centro", "Rua D"));
         rede.add(londrina); 

         return rede;
      }
}
