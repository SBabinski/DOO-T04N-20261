
public class Reserva {
	Hospede hospede;
	Quarto quarto;
	String dCheckIn;
	String dCheckOut;
	boolean checkout;
	
	public Reserva(Hospede hospede, Quarto quarto, String dCheckIn, String dCheckOut) {
		this.hospede = hospede;
		this.quarto = quarto;
		this.dCheckIn = dCheckIn;
		this.dCheckOut = dCheckOut;
		this.checkout = false;
	}
	
	public int calcDiaria() {
		String[] pIn = dCheckIn.split("/");
		int diaIn = Integer.parseInt(pIn[0]);
		int mesIn = Integer.parseInt(pIn[1]);
		int anoIn = Integer.parseInt(pIn[2]);
		
		String[] pOut = dCheckOut.split("/");
		int diaOut = Integer.parseInt(pOut[0]);
		int mesOut = Integer.parseInt(pOut[1]);
		int anoOut = Integer.parseInt(pOut[2]);
		
		int totalIn = anoIn * 365 + mesIn * 30 + diaIn;
		int totalOut = anoOut *365 + mesOut * 30 + diaOut;
		
		return totalOut - totalIn;
	}
	
	public double calcValor() {
		return calcDiaria() * quarto.valorDia;
	}
	
	public void exibirR() {
		System.out.println("Hóspede:");
		hospede.exibeH();
		System.out.println("Quarto:");
		quarto.exibirQ();
		System.out.println("Check in: " + dCheckIn);
		System.out.println("Check out previsto: " + dCheckOut);
		System.out.println("Diarias: " + calcDiaria());
		System.out.println("Valor total: R$" + calcValor());
		if ( checkout) {
			System.out.println("Checkout já realizado");
		} else {
			System.out.println("Checkout pendente");
		}
		
	}
}
