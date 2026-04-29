package Aula07;
import java.time.LocalDate;

public class ProcessaPedido {
	
	public Pedido processar(int id, Cliente cliente, Vendedor vendedor, Loja loja) {
		Pedido pedido = new Pedido(id, cliente, vendedor, loja);
		pedido.dataReserva = LocalDate.now().plusDays(7);
		return pedido;
	}
	
	private boolean pagamento(Pedido pedido) {
		LocalDate hoje = LocalDate.now();
		return !hoje.isAfter(pedido.dataReserva);
	}
	
	public void testarPag(Pedido pedido) {
		if(pagamento(pedido)) {
			System.out.println("Pagamento autorizado");
		} else {
			System.out.println("Pagamento recusado");
		}
	}
}