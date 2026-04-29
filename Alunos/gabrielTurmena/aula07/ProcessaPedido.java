package classesAtividade;

import java.util.Date;

public class ProcessaPedido{

	  public void processar(Pedido pedido) {

	        pedido.total = pedido.calcularValorTotal();

	        
	        if (confirmarPagamento(pedido)) {
	            pedido.dataPagamento = new Date();
	            System.out.println("Pagamento confirmado!");
	        } else {
	            System.out.println("Reserva vencida. Não foi possível confirmar.");
	        }
	    }

	    private boolean confirmarPagamento(Pedido pedido) {
	        Date agora = new Date();

	        if (pedido.dataVencimentoReserva == null) {
	            return true;
	        }

	        return !agora.after(pedido.dataVencimentoReserva);
	    }
	}