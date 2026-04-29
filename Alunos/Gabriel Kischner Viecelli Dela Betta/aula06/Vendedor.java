import java.util.ArrayList;



public class Vendedor {
    String nome;
    int idade;
    String cidade;
    String bairro;
    String rua;
    String loja;
    float salBase;
    public ArrayList<Float> salRec = new ArrayList<>();


    Vendedor(String nome, int idade, String cidade, String bairro, String rua, String loja, float salBase) {
        this.nome = nome;
        this.idade = idade;
        this.cidade = cidade;
        this.bairro =  bairro;
        this.rua = rua;
        this.loja = loja;
        this.salBase = salBase;
        
    }

     public void apresentarSe(){
        System.out.println("O seu nome é " + nome + " e voce tem  " + idade + " ano(s) e trabalha na loja "+ loja);
    }

    public float calcularMedia(){
        float soma = 0;
        for(float i: salRec){
            soma +=i;
        }
        return soma / salRec.size();

    }

    public float calcularBonus(){
        return salBase*0.2f;
    }



    
}
