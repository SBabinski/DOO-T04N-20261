public class Teste {
     public static Loja  gerarDados() {
      Loja lojinha = new Loja("lojaGabi",null, null, null, null, null, null, 0);
      lojinha.nomeFantasia = "LojaGabi";
      lojinha.cnpj = "12.345.678/0001-45";
      lojinha.rua = "av Saopaulo";
      lojinha.bairro = "Centro";
      lojinha.cidade = "Cascavel";

      Vendedor v1 = new Vendedor("Gabriela", 25, "Cascavel", "Centro", "Av Sao Paulo", lojinha.nomeFantasia, 2500.0f);
      v1.salRec.add(2600.0f);
      v1.salRec.add(2700.0f);
      v1.salRec.add(2800.0f);

      Cliente c1 = new Cliente(null, 0, null, null, null);
      c1.nome = "Cleber";
      c1.idade = 32;
      c1.bairro = "Souza Neves";

      lojinha.cliE.add(c1);
      lojinha.venD.add(v1);


      return lojinha;
        



     }

}
