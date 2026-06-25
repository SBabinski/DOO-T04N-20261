package objetos;

public class Reserva {
			Hospede hospede;
			Quarto quarto;
			String dataInicio;
		    String dataFim;
		    boolean encerrado;
		    
		    
		    
		    public Reserva(Hospede hospede,Quarto quarto,String dataInicio,String dataFim,boolean encerrado) {
		    	this.hospede = hospede;
		    	this.quarto = quarto;
		    	this.dataInicio = dataInicio;
		    	this.dataFim = dataFim;
		    	this.encerrado = encerrado;	    	
		    }
		    
		 public void exibirInformacao() {
			 System.out.println("Hospede da reserva:");
			 hospede.exibirInformacao();
			 System.out.println("Hospedado no quarto:");
			 quarto.exibirInformacao();
			 System.out.println("Data de incio da reserva: " + dataInicio);
			 System.out.println("Data final da reserva: " + dataFim);
			 System.out.println("A reserva ja acabou? " + (encerrado ? "SIM" : "NÃO"));
			 System.out.println("Valor pago pelo momento permanecido: " +  valorTotalDiaria() + " reais ");
		 }
		    
		    public double valorTotalDiaria() {
		    	int dias = 30;
		    	return quarto.diaria * dias;
		    }
		    
		    
}
