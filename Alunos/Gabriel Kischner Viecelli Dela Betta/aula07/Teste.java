public class Teste {
    public static Loja gerarDados() {

        Endereco endLoja = new Endereco("PR", "Cascavel", "Centro", 100, "Av Brasil", "Loja A");
        Endereco endVendedor = new Endereco("PR", "Cascavel", "Centro", 50, "Av Sao Paulo", "Ap 10");
        Endereco endCliente = new Endereco("PR", "Ponta Grossa", "Souza Neves", 200, "Rua das Flores", "Casa");

        Loja lojinha = new Loja("My Plant", "Gabi Plantas LTDA", "12.345.678/0001-45", "Cascavel", "Centro", "Av Brasil", "Matriz", 2500.0f);
      
        Vendedor v1 = new Vendedor("Gabriela", 25, endVendedor, lojinha.nomeFantasia, 2500.0f);
        v1.salRec.add(2600.0f);
        v1.salRec.add(2700.0f);
        v1.salRec.add(2800.0f);

        
        Cliente c1 = new Cliente("Cleber", 32, endCliente);

        
        Gerente g1 = new Gerente("Jucelino", 45, endLoja, lojinha.nomeFantasia, 5500.0f);
        g1.salRec.add(5400.0f);
        g1.salRec.add(5500.0f);
        g1.salRec.add(5600.0f);

       
        lojinha.cliE.add(c1);
        lojinha.venD.add(v1);
       

        return lojinha;
    }
}