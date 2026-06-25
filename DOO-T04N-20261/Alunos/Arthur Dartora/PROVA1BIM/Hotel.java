
public class Hotel {
	Reserva[] reservas;
	int totalR;
	
	public Hotel() {
		reservas = new Reserva[10];
		totalR = 0;
	}
	
	public void addReserva(Reserva reserva) {
		if(totalR < 10) {
			reservas[totalR] = reserva;
			totalR ++;
			System.out.println("Cadastro de reserva realizado");
		} else {
			System.out.println("Sem quartos disponíveis");
		}
	}
	
	public void listaRAtiva() {
		System.out.println("Reservas ativas: ");
		boolean temR = false;
		for (int i = 0; i < totalR; i++) {
			if(!reservas[i].checkout) {
				reservas[i].exibirR();
				temR = true;
			}
		}
		if(!temR) {
			System.out.println("Nenhuma reserva");
		}
	}
	
	public Reserva buscaR(int p) {
		if (p >= 1 && p <= totalR) {
			return reservas[ p - 1];
		}
		return null;
	}
	
	public void listaReservas() {
		if(totalR == 0) {
			System.out.println("Nenhuma reserva");
			return;
		}
		for ( int i = 0; i < totalR; i++) {
			System.out.println("Reserva " + (i+1) + ": ");
			reservas[i].exibirR();
		}
	}
}
