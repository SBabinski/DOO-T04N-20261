package classesAtividade;

public class Endereco {
    String cidade;
    String bairro;
    String rua;
    
   public void apresentarLogradouro() {
	   String logradouro = String.format("%s, %s - %s", rua, bairro, cidade);
	   System.out.println(logradouro);
	   
   }
}
