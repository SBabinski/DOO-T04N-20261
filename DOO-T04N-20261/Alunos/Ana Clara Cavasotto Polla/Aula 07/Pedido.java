package fag.objetos;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedido {

	private static final SimpleDateFormat FORMATADOR_DATA = new SimpleDateFormat("dd/MM/yyyy");

	private int id;
	private Date dataCriacao;
	private Date dataPagamento;
	private Date dataVencimentoReserva;
	private Cliente cliente;
	private Vendedor vendedor;
	private Loja loja;
	private List<Item> itens;

	public Pedido(int id, Date dataCriacao, Date dataPagamento, Date dataVencimentoReserva,
			Cliente cliente, Vendedor vendedor, Loja loja, List<Item> itens) {
		this.id = validarId(id);
		this.dataCriacao = copiarDataObrigatoria(dataCriacao, "dataCriacao");
		this.dataPagamento = copiarDataOpcional(dataPagamento);
		this.dataVencimentoReserva = copiarDataObrigatoria(dataVencimentoReserva, "dataVencimentoReserva");
		this.cliente = validarCliente(cliente);
		this.vendedor = validarVendedor(vendedor);
		this.loja = validarLoja(loja);
		this.itens = validarItens(itens);
	}

	// setters

	public void setId(int id) {
		this.id = validarId(id);
	}

	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = copiarDataObrigatoria(dataCriacao, "dataCriacao");
	}

	public void setDataPagamentoOpcional(Date dataPagamento) {
		this.dataPagamento = copiarDataOpcional(dataPagamento);
	}

	public void setDataVencimentoReserva(Date dataVencimentoReserva) {
		this.dataVencimentoReserva = copiarDataObrigatoria(dataVencimentoReserva, "dataVencimentoReserva");
	}

	public void setCliente(Cliente cliente) {
		this.cliente = validarCliente(cliente);
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = validarVendedor(vendedor);
	}

	public void setLoja(Loja loja) {
		this.loja = validarLoja(loja);
	}

	public void setItens(List<Item> itens) {
		this.itens = validarItens(itens);
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = copiarDataObrigatoria(dataPagamento, "dataPagamento");
	}

	// getters

	public int getId() {
		return id;
	}

	public Date getDataCriacao() {
		return copiarDataObrigatoria(dataCriacao, "dataCriacao");
	}

	public Date getDataPagamento() {
		return copiarDataOpcional(dataPagamento);
	}

	public Date getDataVencimentoReserva() {
		return copiarDataObrigatoria(dataVencimentoReserva, "dataVencimentoReserva");
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public Loja getLoja() {
		return loja;
	}

	public List<Item> getItens() {
		return new ArrayList<>(itens);
	}

	// metodos

	public Item getItemPrincipal() {
		if (itens.isEmpty()) {
			return null;
		}

		return itens.get(0);
	}

	public int getQuantidadeItens() {
		return itens.size();
	}

	public double calcularDesconto() {
		if (getQuantidadeItens() > 10) {
			return calcularValorTotal() * 0.05;
		}

		return 0;
	}

	public double calcularValorFinal() {
		return calcularValorTotal() - calcularDesconto();
	}

	public double calcularValorTotal() {
		double total = 0;

		for (Item item : itens) {
			total += item.getValor();
		}

		return total;
	}

	public void gerarDescricaoVenda() {
		Item itemPrincipal = getItemPrincipal();
		String nomeItem = itemPrincipal != null ? itemPrincipal.getNome() : "Nao informado";
		double precoUnitario = itemPrincipal != null ? itemPrincipal.getValor() : 0;
		
		System.out.println("---------------------------");
		System.out.printf(
				"Pedido %d | Data: %s | Cliente: %s | Vendedor: %s | Loja: %s | Item: %s | Quantidade: %d | Preco Unitario: R$ %.2f | Valor Total: R$ %.2f | Desconto: R$ %.2f | Valor Final: R$ %.2f%n",
				id, FORMATADOR_DATA.format(dataCriacao), cliente.getNome(), vendedor.getNome(), loja.getNomeFantasia(),
				nomeItem, getQuantidadeItens(), precoUnitario, calcularValorTotal(), calcularDesconto(),
				calcularValorFinal());
		System.out.println ("---------------------------");
	}

	private int validarId(int id) {
		if (id <= 0) {
			System.out.println("O id do pedido deve ser maior que zero.");
			return 0;
		}
		return id;
	}

	private Cliente validarCliente(Cliente cliente) {
		if (cliente == null) {
			System.out.println("O cliente deve ser informado.");
			return this.cliente;
		}
		return cliente;
	}

	private Vendedor validarVendedor(Vendedor vendedor) {
		if (vendedor == null) {
			System.out.println("O vendedor deve ser informado.");
			return this.vendedor;
		}
		return vendedor;
	}

	private Loja validarLoja(Loja loja) {
		if (loja == null) {
			System.out.println("A loja deve ser informada.");
			return this.loja;
		}
		return loja;
	}

	private List<Item> validarItens(List<Item> itens) {
		if (itens == null || itens.isEmpty()) {
			System.out.println("O pedido deve possuir ao menos um item.");
			return new ArrayList<>();
		}

		List<Item> itensValidados = new ArrayList<>();
		for (Item item : itens) {
			if (item == null) {
				System.out.println("Os itens do pedido devem ser validos.");
				return new ArrayList<>();
			}
			itensValidados.add(item);
		}

		return itensValidados;
	}

	private Date copiarDataObrigatoria(Date data, String campo) {
		if (data == null) {
			System.out.println("O campo " + campo + " deve ser informado.");
			return new Date();
		}
		return new Date(data.getTime());
	}

	private Date copiarDataOpcional(Date data) {
		if (data == null) {
			return null;
		}
		return new Date(data.getTime());
	}
}
