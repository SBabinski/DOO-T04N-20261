package objetos;
import java.util.ArrayList;


public class Hotel {
			ArrayList<Reserva> reservas;
			
			public Hotel() {
				reservas = new ArrayList<Reserva>();
			}
			
		public void fazerReserva(Reserva reserva) {
			if(reservas.size() < 10) {
				reservas.add(reserva);
				System.out.println("Sua reserva foi realizada com sucesso");
				System.out.println("Agradecemos por sua escolha!");

			} else {
				System.out.println("Limite maximo de reservas atingido :(");
			}
		}
			
		public void listarReservasAtivas() {
			boolean taAtiva = false;
			
			for(int i=0; i < reservas.size(); i++) {
				Reserva reserva = reservas.get(i);
				
				if(!reserva.encerrado) {
					System.out.println("Reserva Número ["+ i +"] ");
					reserva.exibirInformacao();
					taAtiva = true;
				}
			}
			
			if(!taAtiva) {
				System.out.println("Não foi possivel encontrar nenhuma reserva ativa :(");
			}
			
		}
			
			
}
