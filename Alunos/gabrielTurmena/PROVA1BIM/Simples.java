package provaBimestral;

public class Simples extends Quartos{
public int possuiVentilador;


public Simples(int numeroQuarto, double valorDiaria, int possuiVentilador) {
	super.cadastrarQuartos(numeroQuarto, valorDiaria);
	this.possuiVentilador = possuiVentilador;
}
public void exibirInformacoes() {
	String resposta = "";
	if(possuiVentilador == 1 ) {
		resposta = " Possuí Ventilador";
	}
	else {
		resposta = " Não Possuí Ventilador";
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
           " | Ventilador: " + (possuiVentilador == 1 ? "Sim" : "Não");
}
public double getValor() {
	// TODO Auto-generated method stub
	return valorDiaria;
}
}
