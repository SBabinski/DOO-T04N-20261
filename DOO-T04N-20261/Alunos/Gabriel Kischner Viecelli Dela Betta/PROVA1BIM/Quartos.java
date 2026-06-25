public class Quartos {

    public int numQuarto;
    public double vlrDiaria;

    public Quartos(int numQuarto, double vlrDiaria) {
        this.numQuarto = numQuarto;
        this.vlrDiaria = vlrDiaria;
        
      
    }

    public void exibirInfo() {
        System.out.println("Numero do Quarto: " + numQuarto + " | Valor da Diaria: " + vlrDiaria);
    }

    

    
}
