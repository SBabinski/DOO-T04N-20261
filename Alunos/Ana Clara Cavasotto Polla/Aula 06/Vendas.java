package fag.objetos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Vendas {

	private int quantidade;
	private double preco;
	private double precoTotal;
	private double desconto;
	private double valorFinal;
	private LocalDate data;

	private Cliente cliente;
	private Vendedor vendedor;
	private Loja loja;

	private static final double taxaDesconto = 0.05;
	private static final DateTimeFormatter FORMATADOR_DATA_BR = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	public Vendas() {
		this.data = LocalDate.now();
	}

	public Vendas(int quantidade, double preco, Cliente cliente, Vendedor vendedor, Loja loja) {
		setQuantidade(quantidade);
		setPreco(preco);
		setCliente(cliente);
		setVendedor(vendedor);
		setLoja(loja);
		this.data = LocalDate.now();
		calcularValorFinal();
	}

	//  GETTERS 

	public LocalDate getData() {
		return data;
	}

	public String getDataFormatada() {
		return data.format(FORMATADOR_DATA_BR);
	}

	public int getQuantidade() {
		return quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public double getPrecoTotal() {
		return precoTotal;
	}

	public double getDesconto() {
		return desconto;
	}

	public double getValorFinal() {
		return valorFinal;
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

	//  SETTERS 

	public void setQuantidade(int quantidade) {
		if (quantidade > 0) {
			this.quantidade = quantidade;
		} else {
			System.out.println("Quantidade inválida!");
		}
	}

	public void setPreco(double preco) {
		if (preco > 0) {
			this.preco = preco;
		} else {
			System.out.println("Preço inválido!");
		}
	}

	public void setCliente(Cliente cliente) {
		if (cliente != null) {
			this.cliente = cliente;
		} else {
			System.out.println("Cliente inválido!");
		}
	}

	public void setVendedor(Vendedor vendedor) {
		if (vendedor != null) {
			this.vendedor = vendedor;
		} else {
			System.out.println("Vendedor inválido!");
		}
	}

	public void setLoja(Loja loja) {
		if (loja != null) {
			this.loja = loja;
		} else {
			System.out.println("Loja inválida!");
		}
	}

	

	public void calcularValorFinal() {
		this.precoTotal = quantidade * preco;

		if (quantidade >= 10) {
			this.desconto = precoTotal * taxaDesconto;
		} else {
			this.desconto = 0;
		}

		this.valorFinal = precoTotal - desconto;
	}

	

	public void mostrarDetalhes() {
		System.out.printf(
				"Data: %s | Cliente: %s | Vendedor: %s | Loja: %s | Quantidade: %d | Preço Unitário: R$ %.2f | Total: R$ %.2f | Desconto: R$ %.2f | Valor Final: R$ %.2f%n",
				getDataFormatada(),
				cliente != null ? cliente.getNome() : "Não informado",
				vendedor != null ? vendedor.getNome() : "Não informado",
				loja != null ? loja.getNomeFantasia() : "Não informada",
				quantidade, preco, precoTotal, desconto, valorFinal);
	}
}