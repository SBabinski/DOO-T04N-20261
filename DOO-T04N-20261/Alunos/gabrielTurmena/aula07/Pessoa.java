package classesAtividade;

public class Pessoa extends Endereco{
    String nome;
    int idade;
    String loja;
    
    public void apresentarse() {
        System.out.println("Nome: " + nome + " | Idade: " + idade + " | Loja: " + loja + " | Bairro: " + bairro + " | Rua: " + rua);
    }    
    
}
