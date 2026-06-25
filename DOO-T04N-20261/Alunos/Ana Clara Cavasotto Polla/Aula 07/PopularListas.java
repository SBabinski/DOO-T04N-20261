package fag.objetos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PopularListas {

	public List<Loja> criarLojas() {
		List<Loja> lojas = new ArrayList<>();

		Loja lojaUm = new Loja("My Plant 1", "My Plant LTDA", "11.111.111/0001-11",
				new Endereco("EstadoUm", "CidadeUm", "BairroUm", "100", "Sala 1"));
		Loja lojaDois = new Loja("My Plant 2", "My Plant LTDA", "22.222.222/0001-22",
				new Endereco("EstadoDois", "CidadeDois", "BairroDois", "200", "Sala 2"));

		lojas.add(lojaUm);
		lojas.add(lojaDois);
		return lojas;
	}

	public List<Cliente> criarClientes(List<Loja> lojas) {
		List<Cliente> clientes = new ArrayList<>();

		Cliente clienteUm = new Cliente("Joao", 30,
				new Endereco("EstadoUm", "CidadeUm", "BairroUm", "10", "Casa"), lojas.get(0));
		Cliente clienteDois = new Cliente("Paulo", 26,
				new Endereco("EstadoUm", "CidadeUm", "BairroDois", "20", "Apto"), lojas.get(0));
		Cliente clienteTres = new Cliente("Lucas", 29,
				new Endereco("EstadoDois", "CidadeDois", "BairroTres", "30", "Casa"), lojas.get(1));
		Cliente clienteQuatro = new Cliente("Alice", 24,
				new Endereco("EstadoDois", "CidadeDois", "BairroQuatro", "40", "Apto"), lojas.get(1));

		clientes.add(clienteUm);
		clientes.add(clienteDois);
		clientes.add(clienteTres);
		clientes.add(clienteQuatro);

		lojas.get(0).adicionarCliente(clienteUm);
		lojas.get(0).adicionarCliente(clienteDois);
		lojas.get(1).adicionarCliente(clienteTres);
		lojas.get(1).adicionarCliente(clienteQuatro);

		return clientes;
	}

	public List<Vendedor> criarVendedores(List<Loja> lojas) {
		List<Vendedor> vendedores = new ArrayList<>();

		Vendedor vendedorUm = new Vendedor("Paula", 27,
				new Endereco("EstadoUm", "CidadeUm", "BairroUm", "50", "Casa"), lojas.get(0), 2200.0,
				List.of(2100.0, 2200.0, 2300.0));
		Vendedor vendedorDois = new Vendedor("Claudia", 31,
				new Endereco("EstadoDois", "CidadeDois", "BairroDois", "60", "Casa"), lojas.get(1), 2500.0,
				List.of(2400.0, 2500.0, 2600.0));

		vendedores.add(vendedorUm);
		vendedores.add(vendedorDois);

		lojas.get(0).adicionarVendedor(vendedorUm);
		lojas.get(1).adicionarVendedor(vendedorDois);

		return vendedores;
	}

	public List<Gerente> criarGerentes(List<Loja> lojas) {
		List<Gerente> gerentes = new ArrayList<>();

		gerentes.add(new Gerente("Ana", 38,
				new Endereco("EstadoUm", "CidadeUm", "BairroGerenteUm", "70", "Casa"), lojas.get(0),
				4200.0, List.of(4100.0, 4200.0, 4300.0)));
		gerentes.add(new Gerente("Maria", 35,
				new Endereco("EstadoDois", "CidadeDois", "BairroGerenteDois", "80", "Casa"),
				lojas.get(1), 4500.0, List.of(4400.0, 4500.0, 4600.0)));

		return gerentes;
	}

	public List<Item> criarItens() {
		List<Item> itens = new ArrayList<>();

		itens.add(new Item(1, "Tulipa", "Flor", 15.0));
		itens.add(new Item(2, "Rosa", "Flor", 12.5));
		itens.add(new Item(3, "Orquidea", "Flor", 32.5));

		return itens;
	}

	public List<Pedido> criarPedidos(List<Cliente> clientes, List<Vendedor> vendedores, List<Item> itens) {
		List<Pedido> pedidos = new ArrayList<>();

		if (clientes == null || vendedores == null || itens == null) {
			return pedidos;
		}
		
		if (clientes.size() < 4 || vendedores.size() < 2 || itens.size() < 3) {
			return pedidos;
		}

		long umDia = 24L * 60L * 60L * 1000L;
		Date agora = new Date();
		
		Cliente clienteUm = clientes.get(0);
		Cliente clienteDois = clientes.get(1);
		Cliente clienteTres = clientes.get(2);
		Cliente clienteQuatro = clientes.get(3);

		Vendedor vendedorUm = buscarVendedorDaLoja(clienteUm.getLoja(), vendedores);
		Vendedor vendedorDois = buscarVendedorDaLoja(clienteTres.getLoja(), vendedores);

		List<Item> itensPedidoUm = new ArrayList<>();
		itensPedidoUm.add(itens.get(0));
		itensPedidoUm.add(itens.get(0));
		itensPedidoUm.add(itens.get(0));
		itensPedidoUm.add(itens.get(0));
		itensPedidoUm.add(itens.get(0));
		itensPedidoUm.add(itens.get(0));
		itensPedidoUm.add(itens.get(0));
		itensPedidoUm.add(itens.get(0));
		itensPedidoUm.add(itens.get(0));
		itensPedidoUm.add(itens.get(0));
		itensPedidoUm.add(itens.get(0));
		itensPedidoUm.add(itens.get(0));

		List<Item> itensPedidoDois = new ArrayList<>();
		itensPedidoDois.add(itens.get(1));
		itensPedidoDois.add(itens.get(1));
		itensPedidoDois.add(itens.get(1));
		itensPedidoDois.add(itens.get(1));
		itensPedidoDois.add(itens.get(1));

		List<Item> itensPedidoTres = new ArrayList<>();
		itensPedidoTres.add(itens.get(2));
		itensPedidoTres.add(itens.get(2));
		itensPedidoTres.add(itens.get(2));
		itensPedidoTres.add(itens.get(2));
		itensPedidoTres.add(itens.get(2));
		itensPedidoTres.add(itens.get(2));
		itensPedidoTres.add(itens.get(2));
		itensPedidoTres.add(itens.get(2));

		List<Item> itensPedidoQuatro = new ArrayList<>();
		itensPedidoQuatro.add(itens.get(2));
		itensPedidoQuatro.add(itens.get(2));
		itensPedidoQuatro.add(itens.get(2));

		Pedido pedidoUm = new Pedido(1, new Date(agora.getTime()), null, new Date(agora.getTime() + umDia),
				clienteUm, vendedorUm, clienteUm.getLoja(), itensPedidoUm);
		Pedido pedidoDois = new Pedido(2, new Date(agora.getTime() - umDia), null,
				new Date(agora.getTime() + umDia), clienteDois, vendedorUm, clienteDois.getLoja(), itensPedidoDois);
		Pedido pedidoTres = new Pedido(3, new Date(agora.getTime() - (2 * umDia)), null,
				new Date(agora.getTime() + umDia), clienteTres, vendedorDois, clienteTres.getLoja(), itensPedidoTres);
		Pedido pedidoQuatro = new Pedido(4, new Date(agora.getTime() - (3 * umDia)), null,
				new Date(agora.getTime() + umDia), clienteQuatro, vendedorDois, clienteQuatro.getLoja(),
				itensPedidoQuatro);

		pedidos.add(pedidoUm);
		pedidos.add(pedidoDois);
		pedidos.add(pedidoTres);
		pedidos.add(pedidoQuatro);

		return pedidos;
	}

	

	private Vendedor buscarVendedorDaLoja(Loja loja, List<Vendedor> vendedores) {
		for (Vendedor vendedor : vendedores) {
			if (vendedor.getLoja().equals(loja)) {
				return vendedor;
			}
		}

		return null;
	}
}
