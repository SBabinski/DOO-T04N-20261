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
		this.quantidade = quantidade;
		this.data = data;
		this.vendedor = vendedor;
		this.cliente = cliente;

		this.valorBruto = quantidade * valorUnitario;
		this.desconto = (quantidade > 10) ? valorBruto * 0.05 : 0;
		this.total = valorBruto - desconto;
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
}