package calculadora;

import java.time.*;

public class ProcessaPedido {
	
	boolean cp;

	public void processar(Pedido pedido) {
		confirmarPagamento(pedido.dataVencimentoReserva);
	}
	
	private void confirmarPagamento(LocalDate dataVR) {
		LocalDate dataH = LocalDate.now();
		if (dataVR.isAfter(dataH) || dataVR.isEqual(dataH)) {
			System.out.println("Datas Validas");
			cp = true;
		} else {
			System.out.println("Datas de validade invalida, porfavor tente novamente");
			cp = false;
		}
	}
	
}