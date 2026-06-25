package provaBimestral;

public class Luxo extends Quartos{
public int possuiVaranda;

public Luxo(int numeroQuarto, double valorDiaria, int possuiVaranda) {
	super.cadastrarQuartos(numeroQuarto, valorDiaria);
	this.possuiVaranda = possuiVaranda;
}
public void exibirInformacoes() {
	String resposta = "";
	if(possuiVaranda == 1 ) {
		resposta = " Possuí Varanda";
	}
	else {
		resposta = " Não Possuí Varanda";
	}
	System.out.println("O numero do quarto é: " + numeroQuarto + " O valor da diária é: " + valorDiaria+ " e ele : " + resposta);
}

public Object getNumero() {
	// TODO Auto-generated method stub
	return numeroQuarto;
}
@Override
public String toString() {
    return "Quarto: " + numeroQuarto + 
           " | Diária: R$ " + valorDiaria + 
           " | Varanda: " + (possuiVaranda == 1 ? "Sim" : "Não");
}
public double getValor() {
	// TODO Auto-generated method stub
	return valorDiaria;
}
}
