package Aula07;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Pedido {

	int id;
	LocalDate dataPedido;
	LocalDate dataPagamento;
	LocalDate dataReserva;
	
	Cliente cliente;
	Vendedor vendedor;
	Loja loja;
	
	ArrayList<Item> itens;
	
	public Pedido(int id, Cliente cliente, Vendedor vendedor, Loja loja) {
		this.id = id;
		this.cliente = cliente;
		this.vendedor = vendedor;
		this.loja = loja;
		this.dataPedido = LocalDate.now();
		this.itens = new ArrayList<>();
	}
	
	public double calcvalorTotal() {
		double total = 0;
		for(Item i:itens) total += i.valor;
		return total;
	}
	
	public void gerarDescricaoVenda() {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println("Data: " + dataPedido.format(fmt));
		System.out.println("Total: " + calcvalorTotal());
	}
}