package fag.objetos;

import java.util.Date;
import java.util.List;

public class TesteProcessarPedido {

	public static void main(String[] args) {
		Loja loja = new Loja("Loja Teste", "Loja Teste LTDA", "00.000.000/0001-00",
				new Endereco("Estado", "Cidade", "Bairro", "1", "Sala"));
		Cliente cliente = new Cliente("Cliente Teste", 20,
				new Endereco("Estado", "Cidade", "Bairro", "2", "Casa"), loja);
		Vendedor vendedor = new Vendedor("Vendedor Teste", 25,
				new Endereco("Estado", "Cidade", "Bairro", "3", "Casa"), loja, 2000.0,
				List.of(1800.0, 1900.0, 2000.0));
		Item item = new Item(1, "Tulipa", "Flor", 10.0);

		long umDia = 24L * 60L * 60L * 1000L;
		Date agora = new Date();
		Pedido pedidoValido = new Pedido(1, agora, null, new Date(agora.getTime() + umDia), cliente, vendedor, loja,
				List.of(item));
		Pedido pedidoVencido = new Pedido(2, agora, null, new Date(agora.getTime() - umDia), cliente, vendedor, loja,
				List.of(item));

		ProcessarPedido processador = new ProcessarPedido();

		System.out.println("Pedido valido pago: " + processador.processar(pedidoValido));
		System.out.println("Pedido valido possui dataPagamento: " + (pedidoValido.getDataPagamento() != null));
		System.out.println("Pedido vencido pago: " + processador.processar(pedidoVencido));
		System.out.println("Pedido vencido possui dataPagamento: " + (pedidoVencido.getDataPagamento() != null));
	}
}
