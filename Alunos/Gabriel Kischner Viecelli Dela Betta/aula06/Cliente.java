

public class Cliente {
    String nome;
    int idade;
    String cidade;
    String bairro;
    String rua;

    Cliente(String nome, int idade, String cidade, String bairro, String rua ) {
        this.nome = nome;
        this.idade = idade;
        this.cidade = cidade;
        this.bairro =  bairro;
        this.rua = rua;
    }

    public void apresentarSe(){
        System.out.println("O seu nome é " + nome + " e voce tem  " + idade + " ano(s)");
    }
}