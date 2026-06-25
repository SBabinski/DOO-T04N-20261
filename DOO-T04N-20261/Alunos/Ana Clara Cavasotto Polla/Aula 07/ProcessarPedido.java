package fag.objetos;

import java.util.Date;

public class ProcessarPedido {

	public boolean processar(Pedido pedido) {
		if (pedido == null) {
			System.out.println("O pedido deve ser informado.");
			return false;
		}
		if (confirmarPagamento(pedido)) {
			pedido.setDataPagamento(new Date());
			return true;
		}

		return false;
	}

	private boolean confirmarPagamento(Pedido pedido) {
		if (pedido.getDataVencimentoReserva() == null) {
			System.out.println("A data de vencimento da reserva deve ser informada.");
			return false;
		}
		return !new Date().after(pedido.getDataVencimentoReserva());
	}
}
