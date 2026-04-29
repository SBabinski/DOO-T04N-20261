package Aula2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Vendas {
	private int quantidade;
	private double valorBruto;
	private double desconto;
	private double total;
	private LocalDate data;
	private Vendedor vendedor;
	private Cliente cliente;

	public Vendas(int quantidade, double valorUnitario, LocalDate data, Vendedor vendedor, Cliente cliente) {
		setQuantidade(quantidade);
		setData(data);
		setVendedor(vendedor);
		setCliente(cliente);
		setValorBruto(quantidade * valorUnitario);
		setDesconto((quantidade > 10) ? valorBruto * 0.05 : 0);
		setTotal(valorBruto - desconto);
	}

	public void mostrarVenda() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println("Data: " + data.format(formatter));
		System.out.println("Vendedor: " + vendedor.getNome());
		System.out.println("Cliente: " + cliente.getNome());
		System.out.println("Quantidade: " + quantidade);
		System.out.println("Total: R$ " + total);
		System.out.println("-------------------------");
	}

	public LocalDate getData() {
		return data;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getValorBruto() {
		return valorBruto;
	}

	public void setValorBruto(double valorBruto) {
		this.valorBruto = valorBruto;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}
	
}